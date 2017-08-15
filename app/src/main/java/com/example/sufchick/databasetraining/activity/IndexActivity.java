package com.example.sufchick.databasetraining.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sufchick.databasetraining.R;
import com.example.sufchick.databasetraining.activity.BaseActivity;

public class IndexActivity extends BaseActivity {

    private Toolbar mToolbar;

    private ActionBar mActionBar;

    private NavigationView mNavigationView;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mToolbar = (Toolbar) findViewById(R.id.index_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mActionBar=getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mListView=(ListView)findViewById(R.id.index_list_view);

        Resources resources=getResources();
        String[] data= resources.getStringArray(R.array.index);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("chapter",position);
                setResult(RESULT_OK,intent);
                finish();
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
