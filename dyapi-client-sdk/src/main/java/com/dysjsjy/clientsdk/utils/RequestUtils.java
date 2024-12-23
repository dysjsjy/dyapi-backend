package com.dysjsjy.clientsdk.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.lang.reflect.Field;

public class RequestUtils {

    public static <T> String buildUrl(String  baseUrl, T params) throws Exception {
        StringBuilder url = new StringBuilder(baseUrl);
        Field[] fields = params.getClass().getDeclaredFields();
        boolean isFirstParam = true;
        for (Field field : fields) {
            field.setAccessible(true);
            String name =  field.getName();
            if ("serialVersionUID".equals(name)) {
                continue;
            }
            try {
                Object value = field.get(params);
                if (value != null) {
                    if (isFirstParam) {
                        url.append("?").append(name).append("=").append(value);
                        isFirstParam = false;
                    } else {
                        url.append("&").append(name).append("=").append(value);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("构建url异常");
            }
        }
        return url.toString();
    }

    public static <T> String get(String baseUrl, T params) throws Exception {
        return get(buildUrl(baseUrl, params));
    }

    public static String get(String url) {
        String body = HttpRequest.get(url).execute().body();
        System.out.println("[interface]: 请求地址: {}, 响应数据: {}" + url + body);
        return body;
    }
}
