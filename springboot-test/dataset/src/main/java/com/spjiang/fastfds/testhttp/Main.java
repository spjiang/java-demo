package com.spjiang.fastfds.testhttp;

import com.spjiang.fastfds.util.RSAUtil;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class Main {

    private static final String password = "admin";
    private static final String username = "admin";
    private static final String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEA0vfvyTdGJkdbHkB8mp0f3FE0GYP3AYPaJF7jUd1M0XxFSE2ceK3k2kw20YvQ09NJKk+OMjWQl9WitG9pB6tSCQIDAQABAkA2SimBrWC2/wvauBuYqjCFwLvYiRYqZKThUS3MZlebXJiLB+Ue/gUifAAKIg1avttUZsHBHrop4qfJCwAI0+YRAiEA+W3NK/RaXtnRqmoUUkb59zsZUBLpvZgQPfj1MhyHDz0CIQDYhsAhPJ3mgS64NbUZmGWuuNKp5coY2GIj/zYDMJp6vQIgUueLFXv/eZ1ekgz2Oi67MNCk5jeTF2BurZqNLR3MSmUCIFT3Q6uHMtsB9Eha4u7hS31tj1UWE+D+ADzp59MGnoftAiBeHT7gDMuqeJHPL4b+kC+gzV4FGTfhR9q3tTbklZkD2A==";
    private static final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANL378k3RiZHWx5AfJqdH9xRNBmD9wGD2iRe41HdTNF8RUhNnHit5NpMNtGL0NPTSSpPjjI1kJfVorRvaQerUgkCAwEAAQ==";

    private String token;

    public static void main(String[] args) {
        createDateset();
        //String s = pwdEncrypt();
        //System.out.println(s);
    }

    /**
     * 创建数据集
     * @return
     */
    private static boolean createDateset() {
        String token = login();
        String url = "http://localhost:8800/api/v1/data/datasets";
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        // body
        Dataset dataset = new Dataset();
        dataset.setName("小模型-中国兵器-002");
        dataset.setDataType(3);
        dataset.setAnnotateType(301);
        dataset.setIndustryType("1");
        dataset.setModelScene(2);
        dataset.setModule(0);
        dataset.setRemark("中国兵器测试");
        dataset.setType(0);
        dataset.setImport(false);
        RestTemplate restTemplate = new RestTemplate();
        // 发送GET请求
        HttpEntity<Dataset> entity = new HttpEntity<>(dataset, headers);
        ResponseEntity<DataResponseBody> response = restTemplate.exchange(url, HttpMethod.POST, entity, DataResponseBody.class);
        if (response.getBody().succeed()) {
            System.out.println("创建成功。。。");
            return true;
        }
        return false;
    }

    public static String login() {
        HashMap<String, String> code = getCode();
        if (code == null) {
            return null;
        }
        String url = "http://localhost:8800/api/v1/admin/auth/login";

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // body
        String pwdEncrypt = pwdEncrypt();
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUsername(username);
        authUserDTO.setPassword(pwdEncrypt);
        authUserDTO.setCode(code.get("text"));
        authUserDTO.setUuid(code.get("uuid"));

        RestTemplate restTemplate = new RestTemplate();
        // 发送GET请求
        HttpEntity<AuthUserDTO> entity = new HttpEntity<>(authUserDTO, headers);
        ResponseEntity<DataResponseBody> response = restTemplate.exchange(url, HttpMethod.POST, entity, DataResponseBody.class);
        DataResponseBody body = response.getBody();
        if (body == null) {
            return null;
        }
        HashMap<String, String> result = (HashMap<String, String>) body.getData();
        return result.get("token");
    }

    public static HashMap<String, String> getCode() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8800/api/v1/admin/auth/codeInternal";

        //请求头
        HttpHeaders headers = new HttpHeaders();

        Map<String, String> params = new HashMap<>();
        // 拼接参数到URL
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String requestUrl = sb.toString().substring(0, sb.length() - 1);
        // 发送GET请求
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<DataResponseBody> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, DataResponseBody.class);
        DataResponseBody body = response.getBody();
        if (body == null) {
            return null;
        }
        HashMap<String, String> result = (HashMap<String, String>) body.getData();
        return result;
    }

    private static String pwdEncrypt() {
        // 秘文
        String pwdCiphertext = null;
        try {
            String encodeStr = RSAUtil.publicEncrypt(password, RSAUtil.getPublicKey(publicKey));
            pwdCiphertext = encodeStr;
        } catch (Exception e) {
            System.out.println("请输入正确密码");
        }
        return pwdCiphertext;
    }


}