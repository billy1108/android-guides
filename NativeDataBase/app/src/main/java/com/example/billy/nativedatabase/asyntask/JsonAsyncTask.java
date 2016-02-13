package com.example.billy.nativedatabase.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.billy.nativedatabase.activities.OperationSelectActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Billy on 21/11/2015.
 */
public class JsonAsyncTask extends AsyncTask<String, Void, Boolean> {

    private Context mContext;

    public JsonAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected Boolean doInBackground(String... params) {
        try {

            //------------------>>
            HttpGet httppost = new HttpGet("http://www.mocky.io/v2/5185415ba171ea3a00704eed");
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();

            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);


                JSONObject jsono = new JSONObject(data);
                Log.v("GGGG DOTO", jsono.toString());
                ((OperationSelectActivity)this.mContext).showJson(jsono.toString());

                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return false;
    }

    protected void onPostExecute(Boolean result) {

    }
}
