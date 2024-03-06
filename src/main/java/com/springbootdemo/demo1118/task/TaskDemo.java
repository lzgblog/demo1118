package com.springbootdemo.demo1118.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luzg
 * @date 2022-12-13 20:40
 *
 */
public class TaskDemo {




    /**
     * 连续输入字符串
     * 依次输出所有分割后的长度为8的新字符串
     * 长度不是8整数倍的字符串请在后面补数字0
     */
    public void test1(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String value = in.nextLine();
            if(!"".equals(value)){
                List<String> list = new ArrayList<>();
                if(value.length()>=8){
                    int count = value.length()/8;
                    int tail = value.length()%8;
                    for(int i = 0;i<count;i++){
                        String newStr = value.substring(8*i,8*(i+1));
                        list.add(newStr);
                    }
                    if(tail > 0){
                        String zero = "";
                        int count1 = 8-tail;
                        for(int i = 0;i<count1;i++){
                            zero += "0";
                        }
                        list.add(value.substring(8*count)+zero);
                    }

                }else{
                    String result = "";
                    int count = 8-value.length();
                    String zero = "";
                    for(int i = 0;i<count;i++){
                        zero += "0";
                    }
                    result = value+zero;
                    list.add(result);
                }
                for(String str : list){
                    System.out.println(str);
                }
            }


        }
    }
    public static void main(String args[]) {
        TaskDemo demo = new TaskDemo();
        //demo.test1();
        /*int aa = Integer.parseInt("10", 16);
        String bb = Integer.toString(170, 16);
        System.out.println(bb);*/
        demo.base64Pic();
    }
    public void base64Pic(){
        try {
            File file = new File("D:\\item\\demo1118\\src\\main\\java\\com\\springbootdemo\\demo1118\\task\\c0069.png");
            InputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[(int)file.length()];
            inputStream.read(b);
            String encode = Base64.getEncoder().encodeToString(b);
            System.out.println(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void testMethod (){
        // 思路：根据一个运营商站点不重复，求重复的次数
        int carrierCount = 3;//运营商数量，根据项目编码可以查询出来
        Integer[] siteIdArr = {1,1,1,2,2,2,3,3,4,4,5,6,7};//项目下所有的站点id
        List<Integer> siteIdList = Arrays.asList(siteIdArr);//项目下所有的站点id，sql查询出来
        Map<Integer,Integer> siteIdAndCountMap = new HashMap();//siteId=count
        for(Integer siteId : siteIdList){
            if(siteIdAndCountMap.containsKey(siteId)){
                Integer count = siteIdAndCountMap.get(siteId);
                siteIdAndCountMap.put(siteId,count+1);
            }else{
                siteIdAndCountMap.put(siteId,1);
            }
        }
        Map<Integer,String> siteIdPriorityMap = new HashMap();//siteId=优先级
        for (Map.Entry<Integer,Integer> map:siteIdAndCountMap.entrySet()) {
            Integer count = map.getValue();
            if(count == carrierCount){//第一优先级
                siteIdPriorityMap.put(map.getKey(),"第一优先级");
            }
            if(count == --carrierCount){//第二优先级
                siteIdPriorityMap.put(map.getKey(),"第二优先级");
            }
            if(count == --carrierCount){//第三优先级
                siteIdPriorityMap.put(map.getKey(),"第三优先级");
            }
            /*if(count == --carrierCount){//第四优先级
                siteIdPriorityMap.put(map.getKey(),"第四优先级");
            }*/
            carrierCount = 3;
        }
        //根据项目编码关联规划结果表和对比任务表查询数据queryPlanSiteBySiteIds(projectCode) 获得结果List<Object>
        List<planSiteResult> planSiteResultList = new ArrayList<>();
        for (Map.Entry<Integer,String> siteIdPriority:siteIdPriorityMap.entrySet()) {
            Integer siteId = siteIdPriority.getKey();//站点id
            String priority = siteIdPriority.getValue();//优先级
            if(!priority.equals("第三优先级")){//if(priority != carrierCount){
                //根据序号indexNum，1为第一运营商，2为第二运营商，以此类推
                //流的方式过滤获取最高优先级的plan_site_id
                if(planSiteResultList != null && planSiteResultList.size() > 0){
                    planSiteResult result = planSiteResultList.stream().filter(planSiteResult -> planSiteResult.getSiteId() == siteId)
                            .reduce((p1, p2) -> p1.getIndexNum() < p2.getIndexNum() ? p1 : p2).get();
                    System.out.println(result.getPlanSiteId());
                }
            }else{//最后优先级只有一家选中，直接根据siteId过滤
                List<planSiteResult> collect = planSiteResultList.stream().filter(planSiteResult -> planSiteResult.getSiteId() == siteId)
                        .collect(Collectors.toList());
                if(collect != null && collect.size() > 0){
                    System.out.println(collect.get(0).getPlanSiteId());
                }
                //得到规划结果id，设置到对比结果表的javabean中
            }
            //插入对比结果表数据或是批量插入数据
            // todo
            //更新规划结果表优先级字段
            // todo
        }

        System.out.println(siteIdPriorityMap);
    }
}






















