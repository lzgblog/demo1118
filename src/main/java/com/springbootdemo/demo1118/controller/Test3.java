package com.springbootdemo.demo1118.controller;

import io.netty.util.internal.StringUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author luzg
 * @date 2022-11-25 13:20
 */
public class Test3{
    private int flag = 2;

    public synchronized void printA(){

        while (flag != 1)
        {
            try
            {
                this.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = 2;
        this.notifyAll();
    }
    public synchronized void printB(){
        while (flag != 2)
        {
            try
            {
                this.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = 3;
        this.notifyAll();
    }
    public synchronized void printC(){
        while (flag != 3)
        {
            try
            {
                this.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = 1;
        this.notifyAll();
    }

    public static void main(String args[]) {
        ts();
        /*String reg = "[b]$";//已b结尾
        Pattern pattern = Pattern.compile(reg);
        System.out.println(pattern.matcher("aab").find());
        if(pattern.matcher("aab").find()){
            System.out.println("11111");
        }
        Scanner in = new Scanner(System.in);
        int i = 0;
        int num = 0;
        String twoLine = "";
        String line = "";
        while (in.hasNextLine()) {
            if(i == 1){
                twoLine = in.nextLine();
                for(int j=0;j<line.length();j++){
                    char c = line.charAt(j);
                    if(twoLine != null && !"".equals(twoLine) && c == twoLine.toLowerCase().charAt(0)){
                        num += 1;
                    }
                }
                System.out.println(num);
            }else{
                line = in.nextLine().toLowerCase();
            }

            if(i == 1){
                i = 0;
                line = "";
                twoLine = "";
                num = 0;
            }else{
                i++;
            }
        }*/
    }
    public static void ts(){
       /* Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int i = 0;
        TreeSet<Integer> set = new TreeSet<>();
        while (in.hasNextLine()){
            if(i != 0){
                int nextInt = in.nextInt();
                set.add(nextInt);
            }
            if(count == i){
                for (Integer jh:set) {
                    System.out.println(jh);
                }
            }
            i++;
        }*/

        LinkedHashSet<Integer> set = new LinkedHashSet<>();//去重  按添加顺序
        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });//去重 自然排序
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(3);
        list.add(5);
        set.addAll(list);
        treeSet.addAll(list);
        ArrayList<Integer> list2 = new ArrayList<>();
        Stream<Integer> distinct = list.stream().distinct();
        list.stream().filter(e->e>2).forEach(e -> list2.add(e));
        List<Integer> collect = distinct.collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(set);
        System.out.println(treeSet);
        System.out.println(list2);
    }
}
