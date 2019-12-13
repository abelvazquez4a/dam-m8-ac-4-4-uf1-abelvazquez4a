package com.example.myfilmratesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class DownloadTrailer extends AppCompatActivity {

    private Button downloadButton;
    private long downloadID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_trailer);

        downloadButton = (Button) findViewById(R.id.button_descargar);
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long broadcastedDownloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                if (broadcastedDownloadID == downloadID){

                    if(getDownloadStatus() == DownloadManager.STATUS_SUCCESSFUL){
                        Toast.makeText(DownloadTrailer.this,"Descarga completada", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(DownloadTrailer.this,"No se ha completado la descarga",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        },filter);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
    }

    private int getDownloadStatus(){
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadID);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Cursor cursor = downloadManager.query(query);

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
            int status = cursor.getInt(columnIndex);

            return status;
        }

        return downloadManager.ERROR_UNKNOWN;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void startDownload(){
        Uri uri = Uri.parse("http://movietrailers.apple.com/movies/independent/terminator-2-3d/terminator-2-3d-trailer-1_h1080p.mov");
        //Uri uri = Uri.parse("https://img.radio.cz/2kBY--tQaaCwVMcWtONau_yGdSc=/fit-in/1800x1800/1525178894__pictures/r/zemedelstvi/repka10.jpg");


        File file = new File(getExternalFilesDir(null),"Terminator 2");

        DownloadManager.Request  request = new DownloadManager.Request(uri);
        request.setTitle("Trailer Terminator 2");
        request.setDescription("Descargando trailer");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setDestinationUri(Uri.fromFile(file));
        request.setRequiresCharging(false);
        request.setAllowedOverMetered(true);
        request.setAllowedOverRoaming(true);

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadID = downloadManager.enqueue(request);
    }
}
