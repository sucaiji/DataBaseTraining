package com.example.sufchick.databasetraining;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


///目录  数据库练习 关于我们     目录使用listview   并且根据点击的不同 传递不同参数 进入新页面 显示不同的字符串（课程）
public class MainActivity extends BaseActivity {
    Toolbar mToolbar;
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mNavigationView=(NavigationView)findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(mNavigationView);
                switch (item.getItemId()){
                    case R.id.contactus:
                        Toast.makeText(MainActivity.this, "联系", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.index:
                        Toast.makeText(MainActivity.this, "目录", Toast.LENGTH_SHORT).show();
                        Intent indexIntent=new Intent(MainActivity.this,IndexActivity.class);
                        startActivity(indexIntent);
                        break;
                    case R.id.training:
                        Toast.makeText(MainActivity.this, "练习", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.marking:
                        Toast.makeText(MainActivity.this, "评价", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "您的操作有误，请重试", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }
}
