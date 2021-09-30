package com.example.week04.utils;

import com.example.week04.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "eFT9Ng1cD5UudPQqezYc");
        headers.add("X-Naver-Client-Secret", "Vka_F8Q6xF");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
//        System.out.println("response"+response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result){
        JSONObject rjson = new JSONObject(result);
       // System.out.println("rjson: "+ rjson);//rjson: {"total":45902,"lastBuildDate":"Thu, 23 Sep 2021 14:07:17 +0900","display":10,"start":1,"items":[{"category2":"PC","image":"https://shopping-phinf.pstatic.net/main_2399616/23996167522.202
        JSONArray items = rjson.getJSONArray("items"); //키가 items인 애들만 "items":[{"category2":"PC","image":"https://shopping-phinf.pstatic.net/main_2399616/23996167522.20200922132620.jpg","mallName":"네이버","category3":"브랜드PC","category4":"","productId":"23996167522","category1":"디지털/가전","link":"https://search.shopping.naver.com/gate.nhn?id=23996167522","maker":"Apple","title":"Apple <b>아이맥<\/b> 27형 2020년형 (MXWT2KH/A)","lprice":"2246230","hprice":"","bran

        List<ItemDto> itemDtoList = new ArrayList<>();
        for (int i=0; i<items.length(); i++) {
            JSONObject itemJson = (JSONObject) items.get(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }

    public static void main(String[] args) {
        NaverShopSearch naverShopSearch = new NaverShopSearch();
        String result = naverShopSearch.search("아이맥");
    }
}
