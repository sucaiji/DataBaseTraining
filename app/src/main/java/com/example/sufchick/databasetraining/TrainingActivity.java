package com.example.sufchick.databasetraining;

import android.content.Intent;
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

public class TrainingActivity extends AppCompatActivity {
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        mToolbar=(Toolbar)findViewById(R.id.training_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.training_drawer_layout);

        mNavigationView=(NavigationView)findViewById(R.id.training_nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(mNavigationView);
                switch (item.getItemId()){
                    case R.id.contactus:
                        Intent contactusIntent=new Intent(TrainingActivity.this,ContactusActivity.class);
                        startActivity(contactusIntent);
                        break;
                    case R.id.index:
                        Intent indexIntent=new Intent(TrainingActivity.this,IndexActivity.class);
                        startActivity(indexIntent);
                        break;
                    case R.id.training:
                        Intent trainingIntent=new Intent(TrainingActivity.this,TrainingActivity.class);
                        startActivity(trainingIntent);
                        break;
                    case R.id.marking:
                        Toast.makeText(TrainingActivity.this, "评价成功", Toast.LENGTH_SHORT).show();
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
}
