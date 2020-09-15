package com.aprendendospring.cursospring.controllers.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URL {
    public static String decodeParam(String s) throws UnsupportedEncodingException {
        return URLDecoder.decode(s, "UTF-8");


    }
    public static List<Integer> decodeIntList(String s){
        String []vet =s.split(",");
       List<Integer> list = new ArrayList<>();
        for (int i=0; i<vet.length; i++){
            list.add(Integer.parseInt(vet[i]));

        }
        return list;

    }
}
