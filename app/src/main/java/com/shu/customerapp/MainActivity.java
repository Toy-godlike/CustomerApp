package com.shu.customerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.shu.entity.Sold;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        MyClickListener listener = new MyClickListener();
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn1: {
                    System.out.println("1111111111111111111111111111111");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getProInfo();
                        }
                    }).start();
                    break;
                }
                case R.id.btn3: {
                    System.out.println("33333333333333333333");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                save();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    break;
                }
            }
        }
    }

    //获取商品信息，包括具体信息，位置信息，打折信息
    public void getProInfo(){
        //发送GET方式HTTP请求
        HttpClient httpClient = new DefaultHttpClient();
        String url = "http://139.129.28.167/ProductService/customer/getPro?rfid=";
        String rfid = "33333333";
        HttpGet hg = new HttpGet(url + rfid);
        try {
            HttpResponse hr = httpClient.execute(hg);
            if(hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = hr.getEntity();
                // 获得JSON字符串
                // 内容为{"area":{"a_num":2,"a_name":"动物","location":"2楼动物园"},"product":{"shapcode":"123","pname":"鳄鱼","price":9.0,"picture":null}}
                String jsonstring = EntityUtils.toString(httpEntity);
                //将JSON字符串变为JSON对象
                JSONObject jsonObject = new JSONObject(jsonstring);
                //获得area部分内容
                //内容为{"a_num":2,"a_name":"动物","location":"2楼动物园"}
                JSONObject areaJson = jsonObject.getJSONObject("area");
                //获得a_name字段
                String aname = areaJson.getString("a_name");
                //获得product部分内容，和area同理
                JSONObject productJson = jsonObject.getJSONObject("product");
                String shapcode = productJson.getString("shapcode");
                System.out.println(aname + "----------" + shapcode);
            }else {
                System.out.println(hr.getStatusLine() + "----------failed---------------");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void save() throws JSONException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Sold sold = new Sold();
        sold.setS_num(8);
        sold.setRfid("33333333");
        sold.setShapcode("123");
        sold.setS_date(new Date());
        sold.setO_price(9);
        sold.setS_price(9 * 0.75);
        List<Sold> solds = new ArrayList<>();
        solds.add(sold);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0;i < solds.size();i++){
            JSONObject temp = new JSONObject();
            temp.put("s_num",solds.get(i).getS_num());
            temp.put("rfid",solds.get(i).getRfid());
            temp.put("shapcode",solds.get(i).getShapcode());
            temp.put("s_date",sdf.format(solds.get(i).getS_date()));
            temp.put("o_price",solds.get(i).getO_price());
            temp.put("s_price",solds.get(i).getS_price());
            jsonArray.put(temp);
        }
        String soldinfo = jsonArray.toString();
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost hp = new HttpPost("http://139.129.28.167/ProductService/customer/save");
        System.out.println("-------:" + soldinfo);
        try {
            hp.setEntity(new StringEntity(soldinfo,"UTF-8"));
            HttpResponse hr = httpClient.execute(hp);
            if (hr.getStatusLine().getStatusCode() == 200) {
                String returnValue = EntityUtils.toString(hr.getEntity(), "UTF-8");
                JSONObject respjson = new JSONObject(returnValue);
                boolean result = respjson.getBoolean("result");
                System.out.println("---------------" + result);
            }else {
                System.out.println(hr.getStatusLine() + "----------failed---------------");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
