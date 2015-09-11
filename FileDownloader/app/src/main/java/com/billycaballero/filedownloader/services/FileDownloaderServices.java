package com.billycaballero.filedownloader.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.logging.Handler;

/**
 * Created by billycaballero on 9/11/15.
 */
public class FileDownloaderServices extends IntentService{
    private String urlDownload;
    private File directory;

    private static final String TAG = FileDownloaderServices.class.getName();
    private static final String FILE_PATH = "filePath";
    private static final String URL = "url";
    private static final int  MEGABYTE = 1024 * 1024;

    public static void startService(Context context, String url, File file){
        Intent i = new Intent(Intent.ACTION_SYNC, null, context, FileDownloaderServices.class);
        i.putExtra(URL, url);
        i.putExtra(FILE_PATH, file);
        context.startService(i);
    }

    public FileDownloaderServices(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        directory = (File) intent.getSerializableExtra(FILE_PATH);
        urlDownload =  intent.getStringExtra(URL);
        downloadFile();
        this.stopSelf();
    }

    private void downloadFile(){
        try {
            URL url = new URL(urlDownload);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            //urlConnection.setRequestMethod("GET");
            //urlConnection.setDoOutput(true);
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(directory);
            int totalSize = urlConnection.getContentLength();

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while((bufferLength = inputStream.read(buffer))>0 ){
                fileOutputStream.write(buffer, 0, bufferLength);
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
