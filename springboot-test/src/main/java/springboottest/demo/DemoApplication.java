package springboottest.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

import java.util.List;

public class DemoApplication {

    //    public static void main(String[] args) {
//        Map<Integer, ArrayList<Map<String, String>>> result = new HashMap<>();
//        List<List<String>> readList = new ArrayList<>();
//        List<String> data1 = new ArrayList<>();
//        data1.add("q11");
//        data1.add("a11");
//
//        data1.add("q12");
//        data1.add("a12");
//
//        data1.add("q13");
//        data1.add("a13");
//
//        List<String> data2 = new ArrayList<>();
//        data2.add("q21");
//        data2.add("a21");
//
//        data2.add("q22");
//        data2.add("a22");
//
//        data2.add("q23");
//        data2.add("a23");
//        readList.add(data1);
//        readList.add(data2);
//
//        String mergeColumn = "0,1,2,3,4,5";
//        String[] column = mergeColumn.split(",");
//        for (int i = 0; i < readList.size(); i++) {
//            List<String> data = readList.get(i);
//            ArrayList<Map<String, String>> record = new ArrayList<>();
//            for (String index : column) {
//                Map<String, String> temp = new HashMap<>();
//                if (Integer.parseInt(index) % 2 == 0) {
//                    // 偶数代表是问题
//                    temp.put("question", data.get(Integer.parseInt(index)));
//                    // 奇数代表是问题
//                    temp.put("answer", data.get(Integer.parseInt(index) + 1));
//                    record.add(temp);
//                } else {
//                    // 奇数代表是答案
//                    System.out.println(data.get(Integer.parseInt(index)));
//                }
//            }
//            result.put(i, record);
//        }
//        Gson gson = new Gson();
//        String json = gson.toJson(result);
//        System.out.println(json);
//    }
//    public static void main(String[] args) throws Exception {
//
//        boolean excludeHeader = true;
//        String mergeColumn = "0,1,2,3,4,5,6";
//
//
//        String filePath = "/Users/jiangshengping/wwwroot/java-demo/springboot-test/llm_v1.csv";
//        CsvReader csvReader = new CsvReader(new InputStreamReader(new FileInputStream(new java.io.File(filePath)), "UTF-8"));
//        int currentNum = 0;
//        if (excludeHeader) {
//            csvReader.readHeaders();
//            currentNum++;
//        }
//        while (csvReader.readRecord()) {
//            String[] lineArray = csvReader.getValues();
//            ArrayList<Map<String, String>> record = new ArrayList<>();
//            for (String column : mergeColumn.split(",")) {
//                if (lineArray.length - 1 >= Integer.parseInt(column) && StringUtils.isNotEmpty(lineArray[Integer.parseInt(column)])) {
//                    Map<String, String> temp = new HashMap<>();
//                    if (Integer.parseInt(column) % 2 == 0) {
//                        // 偶数代表是问题
//                        temp.put("question", lineArray[Integer.parseInt(column)]);
//                        temp.put("answer", lineArray[Integer.parseInt(column)+1]);
//                        record.add(temp);
//                    }
//                }
//            }
//            Gson gson = new Gson();
//            String json = gson.toJson(record);
//            System.out.println(json);
//        }
//        csvReader.close();
//    }

    public static void main(String[] args) throws Exception {
        String content = "[{\"question\":\"帮别人买的 没见到东西 据说还好吧 就是说好 发顺丰的 结果发了邮政小包0\",\"answer\":\"答案0\"},{\"question\":\"请问1是什么？\",\"answer\":\"答案1\"},{\"question\":\"请问2是什么？\",\"answer\":\"答案2\"}]";
        JSONArray jsonArray = JSON.parseArray(content);
        List<AnnotationBigDTO> list = jsonArray.toJavaList(AnnotationBigDTO.class);
        for (AnnotationBigDTO dto:list){
            System.out.println(dto.getQuestion());
        }
        Gson json = new Gson();
        System.out.println(json.toJson(list));
        System.out.println(list);
    }

//    public static void main(String[] args) throws Exception {
//        List<AnnotationBigDTO> list = new ArrayList<>();
//        AnnotationBigDTO dto1 = new AnnotationBigDTO();
//        dto1.setQuestion("qqq");
//        list.add(dto1);
//
//        AnnotationBigDTO dto2 = new AnnotationBigDTO();
//        dto2.setQuestion("qqq22");
//        list.add(dto2);
//        for (AnnotationBigDTO item : list) {
//            item.setAnswer("aaa");
//        }
//        Gson json = new Gson();
//        System.out.println(json.toJson(list));
//    }
}
