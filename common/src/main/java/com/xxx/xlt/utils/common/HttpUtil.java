package com.xxx.xlt.utils.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpUtil {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求
     * @param url 请求地址 如： http://127.0.0.1:80
     * @param params 参数
     * @return 返回值
     */
    public  String doGet(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            sb.append("/?");
            for (Map.Entry map : params.entrySet()) {
                sb.append(map.getKey())
                        .append("=")
                        .append(map.getValue())
                        .append("&");
            }
            url = sb.substring(0, sb.length() - 1);
        }
        ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
        return (String) responseEntity.getBody();
    }

    /**
     * post请求
     * @param url 如： http://127.0.0.1:80
     * @param httpHeaders httpHeaders
     * @param body body
     * @return 返回值
     */
    public String doPost(String url,HttpHeaders httpHeaders,Map<String, String> body) {
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        httpHeaders.setContentType(type);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<String> apiResponse = restTemplate.postForEntity(url, httpEntity, String.class);
        return apiResponse.getBody();
    }
}


