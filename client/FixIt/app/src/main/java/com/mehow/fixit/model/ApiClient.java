package com.mehow.fixit.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RecoverySystem;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.belladati.httpclientandroidlib.HttpEntity;
import com.belladati.httpclientandroidlib.HttpResponse;
import com.belladati.httpclientandroidlib.client.HttpClient;
import com.belladati.httpclientandroidlib.client.methods.HttpGet;
import com.belladati.httpclientandroidlib.client.methods.HttpPut;
import com.belladati.httpclientandroidlib.entity.ContentType;
import com.belladati.httpclientandroidlib.entity.mime.HttpMultipartMode;
import com.belladati.httpclientandroidlib.entity.mime.MultipartEntityBuilder;
import com.belladati.httpclientandroidlib.entity.mime.content.FileBody;
import com.belladati.httpclientandroidlib.entity.mime.content.StringBody;
import com.belladati.httpclientandroidlib.impl.client.DefaultHttpClient;
import com.belladati.httpclientandroidlib.impl.client.HttpClients;
import com.belladati.httpclientandroidlib.params.BasicHttpParams;
import com.belladati.httpclientandroidlib.params.HttpConnectionParams;
import com.belladati.httpclientandroidlib.params.HttpParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ApiClient {

   final static String url = "http://172.16.209.237:8080/issue/add-issue";
   String responseMsg=null;


   public String doPut(Issue issue, File pictureFile, Context context)
   {

       GsonBuilder gsonBuilder = new GsonBuilder();
       Gson gson = gsonBuilder.create();
       String json =gson.toJson(issue);

       HttpPut httpPut = new HttpPut(url);

//       FileBody fileBody = new FileBody(pictureFile, ContentType.MULTIPART_FORM_DATA);
//       //StringBody stringBody = new StringBody(json, ContentType.APPLICATION_JSON);
//
//       MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//       //builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//       builder.addPart("file", fileBody);
//       //builder.addPart("issue", stringBody);
//       HttpEntity entity = builder.build();
//
//       httpPut.setEntity(entity);

       MultipartEntityBuilder builder = MultipartEntityBuilder.create();
       builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
       builder.addBinaryBody("file", pictureFile);
       HttpEntity entity = builder.build();


       httpPut.setEntity(entity);

       HttpClient httpClient = new DefaultHttpClient();
       HttpResponse execute;
       try {
           execute = httpClient.execute(httpPut);
       } catch (IOException e) {
           Log.i("XXXXXXXXXXXXXXX : ", "ERROR", e);
           return "nie dziala: "+e.getClass()+" - "+e.getLocalizedMessage()+ " "+e.getMessage();
       }

       return "dziala";
   }
}




