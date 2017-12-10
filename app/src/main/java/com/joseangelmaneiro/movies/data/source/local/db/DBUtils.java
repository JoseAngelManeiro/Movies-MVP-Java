package com.joseangelmaneiro.movies.data.source.local.db;


import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;


public class DBUtils {

    private static final String SEPARATOR = ",";


    public static String transformIntegerListToString(List<Integer> integerList){
        return TextUtils.join(SEPARATOR, integerList);
    }

    public static List<Integer> transformStringToIntegerList(String text){
        String[] arrayDB = text.split(SEPARATOR);
        List<Integer> integerList = new ArrayList<>();
        for(String s : arrayDB){
            integerList.add(Integer.parseInt(s));
        }
        return integerList;
    }

}
