package com.letterball.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.letterball.common.BaseService;
import com.letterball.entity.ResponseBase;
import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jsonData")
@RestController
public class JsonJX extends BaseService {

    /**
     * json解析
     */
    @PostMapping("/jsonData")
    public ResponseBase jsonData(@RequestBody String jsonData) throws JSONException {

        /**
         * 方法解析
         */
        //     JSONObject jsonObject   = JSON.parseObject(json字符串)
        //   {}  =  data = jsonObject.getJSONObject("data");
        //   []  =  jsonObject.getJSONObject("data");


        JSONObject jsonObject = JSON.parseObject(jsonData);
//        String data = jsonObject.getString("data");
        JSONObject data = jsonObject.getJSONObject("data");
        System.err.println("总数据" + data);

        // nodes数据

        JSONArray nodes = data.getJSONArray("nodes");
        System.out.println("nodes集合"+nodes);
        JSONArray linkes = data.getJSONArray("linkes");
        System.out.println("linkes集合"+linkes);

//        JSONArray jsonArray=new JSONArray(s);
        for(int i = 0; i < nodes.size(); i++){
            JSONObject jsonObject2 = nodes.getJSONObject(i);
            String name = (String) jsonObject2.get("name");
            String addflag = (String) jsonObject2.get("addflag");
            System.out.println(name + addflag);
        }

        for(int i = 0; i < nodes.size(); i++){
            JSONObject jsonObject3 = linkes.getJSONObject(i);
            String name = (String) jsonObject3.get("name");
            String id = (String) jsonObject3.get("id");
            System.err.println(name + id);
        }



        return setResultSuccessData(jsonData);
    }


}
