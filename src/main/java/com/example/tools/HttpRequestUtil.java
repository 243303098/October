package com.example.tools;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpRequestUtil {
    Logger logger = LogManager.getLogger("mylog");
    String responseResult = "";
    //同步get请求
    public String  ynchronousGetRequests(String url){
        OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        responseResult = this.responseHandle(request,client);
        return responseResult;
    }

    //同步post请求,异步请求用excute()
    public String ynchronousPostString(String requestUrl,String requestBody){
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/x-markdown;charset=utf-8");
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(RequestBody.create(mediaType,requestBody))
                .build();
        responseResult = this.responseHandle(request,client);

        return responseResult;
    }


    public String responseHandle(Request request,OkHttpClient client){
        String responseData = "";
        try{
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                StringBuffer buffer = new StringBuffer();
                buffer.append("postString"+"\r\n");
                buffer.append(response.protocol()+" "+response.code()+" "+response.message()+"\r\n");
                Headers headers = response.headers();
                for(int i=0;i<headers.size();i++){
                    buffer.append(headers.name(i)+":"+headers.value(i)+"\r\n");
                }
                buffer.append("response:"+response.body().string());
                responseData=buffer.toString();
            }else{
                responseData="请求错误";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return responseData;
    }
}
