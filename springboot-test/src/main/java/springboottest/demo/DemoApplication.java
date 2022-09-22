package springboottest.demo;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
    private static Resource[] resources = null;

    public static void main(String[] args) throws ParseException {
//        String maxDate = "202206";
//        int minDate = Integer.parseInt(maxDate.substring(0,4)) - 1;
//        System.out.println(String.valueOf( minDate) );

        List<String> data = new ArrayList<>();
        data.add("0");
        data.add("1");
        data.add("2");
        data.add("3");

        Iterator<String> it = data.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if ("b".equals(s)) {
                it.remove();
            }
        }
        System.out.println(data);


        System.out.println(data.size());

        for (int i = 0; i < data.size(); i++) {
            if (i == 1) {
                data.remove(1);
            }
            if (i == 2) {
                data.remove(2);
            }
        }

        System.out.println(data.toString());

    }


    public static List<String> getMonthBetween(String minDate, String maxDate, String pattern) throws ParseException {
        ArrayList<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

}
