package org.yaolin6666;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ArrayList<Integer> test=new ArrayList<>();
        test.add(1);
        test.add(2);
        Gson gson=new Gson();
        String test2=gson.toJson(test);
        System.out.println(test2);
    }
}