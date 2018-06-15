package com.mehow.fixit.model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RecoverySystem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.belladati.httpclientandroidlib.HttpEntity;
import com.belladati.httpclientandroidlib.client.HttpClient;
import com.belladati.httpclientandroidlib.client.methods.HttpGet;
import com.belladati.httpclientandroidlib.impl.client.HttpClients;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.io.IOException;

public class ApiClient {

   final static String url = "http://77.55.208.218:8080";
   String responseMsg=null;


   public String doPut(Issue issue, File pictureFile, Context context)
   {

       GsonBuilder builder = new GsonBuilder();
       Gson gson = builder.create();
       String json=gson.toJson(issue);

       HttpGet httpGet = new HttpGet(url);
       HttpClient httpClient = HttpClients.createDefault();
       try {
           httpClient.execute(httpGet);
       } catch (IOException e) {
           e.printStackTrace();
           return "nie dziala";
       }

       return "dziala";
   }
}




