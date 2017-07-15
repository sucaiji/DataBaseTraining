package com.example.sufchick.databasetraining;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


//其他页面仿照主页 但是在学习页面添加上一章 下一章 练习 的底栏
//
public class MainActivity extends BaseActivity {
    Toolbar mToolbar;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);





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
                    case R.id.index:
                        Intent indexIntent=new Intent(MainActivity.this,IndexActivity.class);
                        startActivity(indexIntent);
                        break;
                    case R.id.training:
                        Intent trainingIntent=new Intent(MainActivity.this,TrainingActivity.class);
                        startActivity(trainingIntent);
                        break;
                    case R.id.marking:
                        Toast.makeText(MainActivity.this, "评价成功", Toast.LENGTH_SHORT).show();
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
}
