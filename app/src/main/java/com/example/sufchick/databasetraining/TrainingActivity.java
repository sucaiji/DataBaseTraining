package com.example.sufchick.databasetraining;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TrainingActivity extends BaseActivity implements View.OnClickListener{
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        mToolbar=(Toolbar)findViewById(R.id.training_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //mSubmit=(Button)findViewById(R.id.training_submit);
        //mSubmit.setOnClickListener(this);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.training_drawer_layout);

        mNavigationView=(NavigationView)findViewById(R.id.training_nav_view);
        mNavigationView.setCheckedItem(R.id.training);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(mNavigationView);
                switch (item.getItemId()){
                    case R.id.contactus:
                        Intent contactusIntent=new Intent(TrainingActivity.this,ContactusActivity.class);
                        startActivity(contactusIntent);
                        break;
                    case R.id.start_study:
                        Intent indexIntent=new Intent(TrainingActivity.this,IndexActivity.class);
                        startActivity(indexIntent);
                        break;
                    case R.id.training:
                        break;
                    case R.id.marking:
                        if(hasAnyMarketInstalled(TrainingActivity.this)){
                            Uri uri = Uri.parse("market://details?id="+getPackageName());
                            Intent markingIntent = new Intent(Intent.ACTION_VIEW,uri);
                            markingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(markingIntent);
                        }
                        else{
                            Toast.makeText(TrainingActivity.this, "没有找到应用市场", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(TrainingActivity.this, "您的操作有误，请重试", Toast.LENGTH_SHORT).show();
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
            case android.R.id.home:
                finish();
                break;
            case R.id.toolbar_menu:
                mDrawerLayout.openDrawer(mNavigationView);
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        /*switch (v.getId()){
            case R.id.training_submit:
                Intent submitIntent=new Intent(this,ResultActivity.class);
                startActivity(submitIntent);
                break;
            default:
                break;
        }*/
    }
}
