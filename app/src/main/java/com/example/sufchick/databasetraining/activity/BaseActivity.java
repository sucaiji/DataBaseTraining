package com.example.sufchick.databasetraining.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


/**
 * Created by sufchick on 2017/7/11.
 */

public class BaseActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }
    protected boolean hasAnyMarketInstalled(Context context) {

        Intent intent =new Intent();

        intent.setData(Uri.parse("market://details?id=android.browser"));

        List list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return 0!= list.size();
    }


}
