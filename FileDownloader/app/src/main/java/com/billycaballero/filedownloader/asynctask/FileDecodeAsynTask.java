package com.billycaballero.filedownloader.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by billycaballero on 9/11/15.
 */
public class FileDecodeAsynTask extends AsyncTask<Object, Void, Void> {

    private static final int  MEGABYTE = 1024 * 1024;
    private String bytesFromUrl;
    private File directory;
    private Context context;

    @Override
    protected Void doInBackground(Object... params) {
        context = (Context) params[0];
        bytesFromUrl = (String) params[1];
        directory = (File) params[2];
        writeFile();
        return null;
    }

    public void writeFile(){
        try{
            byte[] byteArray = Base64.decode(bytesFromUrl.getBytes(), 1);
            InputStream stream = new ByteArrayInputStream(byteArray);

            FileOutputStream fileOutputStream = new FileOutputStream(directory);

            byte[] buffer = new byte[MEGABYTE];
            int bufferLength = 0;
            while((bufferLength = stream.read(buffer))>0 ){
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
