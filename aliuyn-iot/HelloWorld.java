import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.InitResult;
import com.aliyun.alink.dm.api.IoTApiClientConfig;
import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.channel.core.base.AError;
import com.aliyun.alink.linksdk.channel.core.base.ARequest;
import com.aliyun.alink.linksdk.channel.core.base.AResponse;
import com.aliyun.alink.linksdk.channel.core.base.IOnCallListener;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnRrpcResponseHandle;
import com.aliyun.alink.linksdk.channel.core.persistent.IOnSubscribeRrpcListener;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentRequest;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.MqttPublishRequest;
import com.common.tools.log.ALog;
import com.common.tools.thread.ThreadPool;
import com.google.gson.Gson;
import com.http.utils.LogUtils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class HelloWorld {
    private static final String TAG = "HelloWorld";
    private String pk = null;
    private String dn = null;

    public static void main(String[] args) {
        HelloWorld manager = new HelloWorld();
        LogUtils.setShowLog(false);
        ALog.print(TAG, "args=" + Arrays.toString(args));
        String deviceInfo = readDeviceInfo(args[0]);
        if (deviceInfo == null){
            ALog.error(TAG, "main - need device info path.");
            return;
        }
        Gson mGson = new Gson();
        DeviceInfoData deviceInfoData = mGson.fromJson(deviceInfo, DeviceInfoData.class);
        if (deviceInfoData == null){
            ALog.error(TAG, "main - deviceInfo format error.");
            return;
        }
        manager.init(deviceInfoData.productKey, deviceInfoData.deviceName, deviceInfoData.deviceSecret, deviceInfoData.region);
    }


    private static String readDeviceInfo(String path) {
        InputStreamReader reader = null;
        BufferedReader bufReader = null;
        try {
            File filename = new File(path);
            reader = new InputStreamReader(new FileInputStream(filename));
            bufReader = new BufferedReader(reader);
            String line = "";
            String result = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null){
                    bufReader.close();
                }
                if (bufReader != null){
                    bufReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private void init(String pk, String dn, String ds, String region) {
        this.pk = pk;
        this.dn = dn;
        LinkKitInitParams params = new LinkKitInitParams();
        IoTMqttClientConfig config = new IoTMqttClientConfig();
        config.productKey = pk;
        config.deviceName = dn;
        config.deviceSecret = ds;
	config.mqttHost = pk + ".iot-as-mqtt." + region + ".aliyuncs.com:1883";
        params.mqttClientConfig = config;
        params.connectConfig = new IoTApiClientConfig();

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.productKey = pk;
        deviceInfo.deviceName = dn;
        deviceInfo.deviceSecret = ds;

        params.deviceInfo = deviceInfo;
        LinkKit.getInstance().init(params, new ILinkKitConnectListener() {
            public void onError(AError aError) {
                ALog.print(TAG, "Init Error error=" + aError);
            }

            public void onInitDone(InitResult initResult) {
                ALog.print(TAG, "onInitDone result=" + initResult);
                executeScheduler();
                invokeService();
            }
        });
    }

    private void invokeService() {
        String topic = "/sys/" + pk + "/" + dn + "/thing/service/property/set";
        LinkKit.getInstance().subscribeRRPC(topic, new IOnSubscribeRrpcListener() {
            public void onSubscribeSuccess(String s) {
                ALog.print(TAG, "subscribe successfully");
            }

            public void onSubscribeFailed(String s, AError aError) {
                ALog.print(TAG, "fail to subscribe");
            }

            public void onReceived(String topic, PersistentRequest persistentRequest, IOnRrpcResponseHandle iOnRrpcResponseHandle) {
                try {
                    if (persistentRequest != null && persistentRequest.payloadObj != null) {
                        String result = new String((byte[])persistentRequest.payloadObj, "UTF-8");
                        RequestModel<String> requestModel = JSONObject.parseObject(result, new TypeReference<RequestModel<String>>() {
                        }.getType());
                        ALog.print(TAG, "Received a message: " + requestModel.params);
                        AResponse response = new AResponse();
                        response.data = "{\"id\":\"123\", \"code\":\"200\"" + ",\"data\":{} }";
                        iOnRrpcResponseHandle.onRrpcResponse(topic + "_reply", response);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            public void onResponseSuccess(String s) {
                ALog.print(TAG, "reply successfully");
            }

            public void onResponseFailed(String s, AError aError) {
                ALog.print(TAG, "fail to reply");
            }

            public boolean needUISafety() {
                return false;
            }
        });
    }


    private void reportState() {
        ALog.print(TAG, "report Hello World.");
        MqttPublishRequest request = new MqttPublishRequest();
        request.topic = "/sys/" + pk + "/" + dn + "/thing/event/property/post";
        request.payloadObj = "{\"id\":\"230788029\",\"method\":\"thing.event.property.post\",\"params\":{\"Status\":1,\"Data\":\"Hello, World!\"},\"version\":\"1.0\"}";
        LinkKit.getInstance().publish(request, new IOnCallListener() {
            public void onSuccess(ARequest aRequest, AResponse aResponse) {
                ALog.print(TAG, "upload successfully");
            }

            public void onFailed(ARequest aRequest, AError aError) {
                ALog.print(TAG, "fail to upload");
            }

            public boolean needUISafety() {
                return false;
            }
        });
    }

    private void executeScheduler() {
        ThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                reportState();
            }
        }, 2, 5, TimeUnit.SECONDS);
    }    

    private class DeviceInfoData extends DeviceInfo{
	/**
     	* 区域
     	*/
    	public String region = "cn-shanghai";
    }
}
