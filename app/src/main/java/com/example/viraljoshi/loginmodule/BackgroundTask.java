package com.example.viraljoshi.loginmodule;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by VIRAL JOSHI on 2017-09-20.
 */

public class BackgroundTask extends AsyncTask<String,Void,String>
{
    Context ctx;
    AlertDialog alertDialog;
    BackgroundTask(Context context)
    {
        ctx=context;
    }

    @Override
    protected String doInBackground(String... params) {
        String task=params[0];
        String urlLogin="https://vgjdevelopers.000webhostapp.com/LoginAndroid/Loginmodule_login.php";
        if(task.equals("login"))
        {
            String username=params[1];
            String password=params[2];
            try {
                URL url= new URL(urlLogin);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                //send the username and password to the database
                OutputStream outputStream=httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);
                String mydata= URLEncoder.encode("username","UTF-8")+"&"+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(mydata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPreExecute()
    {
        alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Status");
    }
    @Override
    protected void onPostExecute(String a)
    {
        alertDialog.setMessage(a);
        alertDialog.show();
    }
}