package com.example.testliveedgedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.adityaarora.liveedgedetection.activity.ScanActivity;
import com.adityaarora.liveedgedetection.constants.ScanConstants;
import com.adityaarora.liveedgedetection.util.ScanUtils;
import com.example.testliveedgedetection.hexacode.Area;
import com.example.testliveedgedetection.hexacode.Code;
import com.example.testliveedgedetection.hexacode.CodeCountDigite;
import com.example.testliveedgedetection.hexacode.Functionality;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 101;
    private ImageView scannedImageView;
    ViewGroup constrantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constrantId = findViewById(R.id.constrantId);
        scannedImageView = findViewById(R.id.scanned_image);
        scannedImageView.setVisibility(View.GONE);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float dpHeight = displayMetrics.heightPixels;
        float dpWidth = displayMetrics.widthPixels ;
        Toast.makeText(this,  String.valueOf(dpHeight),Toast.LENGTH_LONG).show();
        int h= (int) dpWidth;
        int w= (int) dpWidth;

        System.out.println(dpHeight);
        System.out.println(dpWidth);
        Functionality functionality = new Functionality(constrantId,this);
        String std = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Standardization).getHXValue();
        String sector = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Sector).getHXValue();
        String entity = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Entity).getHXValue();
        String time = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.TimeStump).getHXValue();
        String section = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Section).getHXValue();
        String item = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Item).getHXValue();
        String quality = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Quality).getHXValue();
        String unit = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Unit).getHXValue();
        String validity = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Validty).getHXValue();
        ArrayList<Area> arrayList = functionality.ConvertCode(w,h , std, item, quality, sector, section, unit, time, entity, validity);
        functionality.DrawCode(arrayList,w,h);


        // startScan();
    }

    private void startScan() {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (null != data && null != data.getExtras()) {
//                    String filePath = data.getExtras().getString(ScanConstants.SCANNED_RESULT);
//                    Bitmap baseBitmap = ScanUtils.decodeBitmapFromFile(filePath, ScanConstants.IMAGE_NAME);
//                    scannedImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                    scannedImageView.setImageBitmap(baseBitmap);

//                    Functionality functionality = new Functionality(constrantId,this);
//                    String std = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Standardization).getHXValue();
//                    String sector = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Sector).getHXValue();
//                    String entity = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Entity).getHXValue();
//                    String time = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.TimeStump).getHXValue();
//                    String section = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Section).getHXValue();
//                    String item = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Item).getHXValue();
//                    String quality = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Quality).getHXValue();
//                    String unit = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Unit).getHXValue();
//                    String validity = functionality.ConvertDCtoHX(10, new ArrayList<>(), CodeCountDigite.Validty).getHXValue();
//                    ArrayList<Area> arrayList = functionality.ConvertCode(900, 600, std, item, quality, sector, section, unit, time, entity, validity);
//                    functionality.DrawCode(arrayList);

                    scannedImageView.setVisibility(View.GONE);
//                    try {
////                        File file = new File(filePath);
//                      //  Code code = functionality.ConvertHXtoDC(baseBitmap);
//           //             Log.d("Code:", code.toString());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                finish();
            }
        }
    }

}
