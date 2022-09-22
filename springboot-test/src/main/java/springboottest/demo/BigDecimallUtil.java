package springboottest.demo;

/**
 * Package: springboottest.demo
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2022-06-15 10:05
 */
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

public class BigDecimallUtil {
    public static BigDecimal castFromObject(Object value){
        if (value == null){
            return null;
        }
        if (value instanceof BigDecimal){
            return (BigDecimal) value;
        }
        if (value instanceof String){
            if (NumberUtils.isParsable(value.toString())){
                return new BigDecimal(value.toString());
            }
            return null;
        }

        if (value instanceof Integer){
            return new BigDecimal(((Integer) value));
        }

        if (value instanceof Long){
            return new BigDecimal(((Long) value));
        }
        //float
        if (value instanceof Float){

            return new BigDecimal(((Float) value));
        }
        //double
        if (value instanceof Double){
            return new BigDecimal(((Double) value));
        }
        if (value instanceof Number){
            return new BigDecimal(((Number) value).doubleValue());
        }
        return null;
    }
}
