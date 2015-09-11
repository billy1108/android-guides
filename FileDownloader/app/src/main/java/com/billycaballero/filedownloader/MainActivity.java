package com.billycaballero.filedownloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.billycaballero.filedownloader.helper.FileManagerDownloader;
import com.billycaballero.filedownloader.util.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnDownloadServices;
    Button btnDecodeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        btnDownloadServices = (Button) this.findViewById(R.id.bt_dow_services);
        btnDecodeString = (Button) this.findViewById(R.id.bt_dec_string);

        btnDownloadServices.setOnClickListener(this);
        btnDecodeString.setOnClickListener(this);
    }

    public void startDownload(){
        FileManagerDownloader fileDownloader = new FileManagerDownloader(this, "maven.pdf", "http://maven.apache.org/maven-1.x/maven.pdf");
        fileDownloader.startDonwload();
    }

    public void decodeFile(){
        FileManagerDownloader fileDownloader = new FileManagerDownloader("miexcel.xlsx", Constants.FILE_ENCODE_EXCEL);
        fileDownloader.setContext(this);
        fileDownloader.startDecode();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_dow_services:
                startDownload();
                break;
            case R.id.bt_dec_string:
                decodeFile();
                break;
        }
    }
}
