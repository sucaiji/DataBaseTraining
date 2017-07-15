package com.example.sufchick.databasetraining;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class IndexActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;

    private NavigationView mNavigationView;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mToolbar = (Toolbar) findViewById(R.id.index_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);



        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mNavigationView=(NavigationView)findViewById(R.id.index_nav_view);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.index_drawer_layout);

        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(mNavigationView);
        switch(item.getItemId()){
            case R.id.chapter_one:
                Toast.makeText(this, "第一章", Toast.LENGTH_SHORT).show();
                break;
            case R.id.chapter_two:
                Toast.makeText(this, "第二章", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "操作异常，请重试", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.index_toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.toolbar_index:
                mDrawerLayout.openDrawer(mNavigationView);

        }
        return true;
    }
}
