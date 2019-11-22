package com.example.tools;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HttpRequestUtil {
    Logger logger = LogManager.getLogger("mylog");
    String responseResult = "";
    //同步get请求
    public String  ynchronousGetRequests(String url){
        OkHttpClient okhttp = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okhttp.newCall(request);
        call.enqueue((new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                logger.error(e.getMessage());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                responseResult=response.body().string();
            }
        }));
        return responseResult;
    }

    //同步post请求,异步请求用excute()
    public String ynchronousPostString(String requestUrl,String requestBody){
        MediaType mediaType = MediaType.parse("text/x-markdown;charset=utf-8");
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(RequestBody.create(mediaType,requestBody))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                logger.warn(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                StringBuffer buffer = new StringBuffer();
                buffer.append("postString"+"\r\n");
                buffer.append(response.protocol()+" "+response.code()+" "+response.message()+"\r\n");
                Headers headers = response.headers();
                for(int i=0;i<headers.size();i++){
                    buffer.append(headers.name(i)+":"+headers.value(i)+"\r\n");
                }
                buffer.append("response:"+response.body().string());
                responseResult=buffer.toString();
            }
        });
        return responseResult;
    }
}
