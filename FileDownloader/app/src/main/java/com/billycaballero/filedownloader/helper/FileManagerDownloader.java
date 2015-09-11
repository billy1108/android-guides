package com.billycaballero.filedownloader.helper;

import android.content.Context;
import android.os.Environment;

import com.billycaballero.filedownloader.asynctask.FileDecodeAsynTask;
import com.billycaballero.filedownloader.services.FileDownloaderServices;
import com.billycaballero.filedownloader.util.Constants;

import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;

/**
 * Created by billycaballero on 9/11/15.
 */
public class FileManagerDownloader {
    private Context context;
    private String fileName;
    private String fileUrl;
    private String data;

    public FileManagerDownloader(Context context, String name, String url){
        this.context = context;
        this.fileName = name; // -> maven.pdf
        this.fileUrl = url; // -> http://maven.apache.org/maven-1.x/maven.pdf
    }

    public FileManagerDownloader(String name, String data){
        this.fileName = name; // -> miexcel.xlsx
        this.data = data; // -> Base64
    }

    public void startDonwload(){
        File pdfFile = getDirectory();
        FileDownloaderServices.startService(context, fileUrl, pdfFile);
    }

    public void startDecode(){
        File pdfFile = getDirectory();
        new FileDecodeAsynTask().execute(context, data, pdfFile);
    }

    private File getDirectory(){
        File folder = new File(	Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), Constants.NOMBRE_DIRECTORIO);
        folder.mkdir();

        File pdfFile = new File(folder, fileName);

        try{
            pdfFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        return pdfFile;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
