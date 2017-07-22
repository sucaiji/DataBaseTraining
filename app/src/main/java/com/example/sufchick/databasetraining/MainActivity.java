package com.example.sufchick.databasetraining;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


//其他页面仿照主页 但是在学习页面添加上一章 下一章 练习 的底栏
//
public class MainActivity extends BaseActivity implements View.OnClickListener{
    Toolbar mToolbar;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    Button mStudyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mStudyButton=(Button)findViewById(R.id.main_study_button);
        mStudyButton.setOnClickListener(this);




        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mNavigationView=(NavigationView)findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(mNavigationView);
                switch (item.getItemId()){
                    case R.id.contactus:
                        Intent contactusIntent=new Intent(MainActivity.this,ContactusActivity.class);
                        startActivity(contactusIntent);
                        break;
                    case R.id.start_study:
                        Intent indexIntent=new Intent(MainActivity.this,IndexActivity.class);
                        startActivity(indexIntent);
                        break;
                    case R.id.training:
                        Intent trainingIntent=new Intent(MainActivity.this,TrainingActivity.class);
                        startActivity(trainingIntent);
                        break;
                    case R.id.marking:
                        if(hasAnyMarketInstalled(MainActivity.this)){
                            Uri uri = Uri.parse("market://details?id="+getPackageName());
                            Intent markingIntent = new Intent(Intent.ACTION_VIEW,uri);
                            markingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(markingIntent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "没有找到应用市场", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "您的操作有误，请重试", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_menu:
                mDrawerLayout.openDrawer(mNavigationView);
                break;
        }
        return true;
    }

    private boolean hasAnyMarketInstalled(Context context) {

        Intent intent =new Intent();

        intent.setData(Uri.parse("market://details?id=android.browser"));

        List list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return 0!= list.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_study_button:
                Intent studyIntent=new Intent(this,IndexActivity.class);
                startActivity(studyIntent);
                break;
        }
    }
}
