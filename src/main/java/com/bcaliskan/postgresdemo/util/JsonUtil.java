package com.bcaliskan.postgresdemo.util;


import java.util.LinkedHashMap;
import java.util.Map;

public class JsonUtil {
    private static final String STATUS;
    private static final String TAG;
    private static final String ERR_MSG;

    static {
        STATUS = "status";
        TAG = "tag";
        ERR_MSG = "err_msg";
    }


    public static Map<String, Object> constructJSON(String tag, boolean status) {
        /**
         * LinkedHashMap is precisely like HashMap, except that when you iterate over it, it presents the items in the insertion order.
         */
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put(TAG, tag);
        jsonMap.put(STATUS, status);
        return jsonMap;
    }

    public static Map<String, Object> constructJSON(String tag, String string) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put(TAG, tag);
        jsonMap.put(STATUS, string);
        return jsonMap;
    }

    public static Map<String, Object> initEmptyMap() {
        return new LinkedHashMap<>();
    }

    public static Map<String, Object> constructJSON(String tag, byte[] array) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put(TAG, tag);
        jsonMap.put(STATUS, array);
        return jsonMap;
    }

    public static Map<String, Object> constructJSON(String tag, int value) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put(TAG, tag);
        jsonMap.put(STATUS, value);
        return jsonMap;
    }

    public static Map<String, Object> constructJSON(String tag, boolean status, String errorMessage) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put(TAG, tag);
        jsonMap.put(STATUS, status);
        jsonMap.put(ERR_MSG, errorMessage);
        return jsonMap;
    }

}