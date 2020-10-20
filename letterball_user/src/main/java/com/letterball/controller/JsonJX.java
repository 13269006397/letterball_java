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

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * json解析s
 */
@RequestMapping("/jsonData")
@RestController
public class JsonJX extends BaseService {

    /**
     * json解析
     */
    @PostMapping("/jsonData")
    public ResponseBase jsonData(@RequestBody String jsonData) throws JSONException, IOException {

        /**
         * 方法解析
         */
        //     JSONObject jsonObject   = JSON.parseObject(json字符串)
        //   {}  =  data = jsonObject.getJSONObject("data");
        //   []  =  jsonObject.getJSONObject("data");
        String jsonData1 = readJsonFile();


        JSONObject jsonObject = JSON.parseObject(jsonData1);
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

        return setResultSuccessData(jsonData1);
    }


    //读取json文件
    @RequestMapping("/redsJson")
    public String readJsonFile() throws IOException {
        String jsonStr = "";
        try  {
            String path = JsonJX.class.getClassLoader().getResource("jsonData.json").getPath();
            URI uri = JsonJX.class.getClassLoader().getResource("jsonData.json").toURI();

            FileInputStream jsonFile = new FileInputStream(new File(path));
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            FileChannel channel = jsonFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);


            StringBuffer sb = new StringBuffer();

//            List<String> strings = Files.readAllLines(Paths.get(uri));
//            strings.stream().forEach(sb::append);



            while(channel.read(buffer) != -1){
                buffer.flip();
                out.write(buffer.array());
                buffer.clear();
            }
            channel.close();
            jsonStr = sb.append(new String(out.toByteArray())).toString();
            return jsonStr;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


}
