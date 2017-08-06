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
import android.widget.Toast;

public class ContactusActivity extends BaseActivity {

    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        mToolbar=(Toolbar)findViewById(R.id.aboutus_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.aboutus_drawer_layout);

        mNavigationView=(NavigationView)findViewById(R.id.aboutus_nav_view);
        mNavigationView.setCheckedItem(R.id.contactus);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(mNavigationView);
                switch (item.getItemId()){
                    case R.id.contactus:
                        break;

                    case R.id.marking:
                        if(hasAnyMarketInstalled(ContactusActivity.this)){
                            Uri uri = Uri.parse("market://details?id="+getPackageName());
                            Intent markingIntent = new Intent(Intent.ACTION_VIEW,uri);
                            markingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(markingIntent);
                        }
                        else{
                            Toast.makeText(ContactusActivity.this, "没有找到应用市场", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(ContactusActivity.this, "您的操作有误，请重试", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }
}
