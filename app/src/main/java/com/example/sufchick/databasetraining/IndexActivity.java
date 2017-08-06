package com.example.sufchick.databasetraining;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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




    /*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Toast.makeText(this, "1+"+item.getItemId(), Toast.LENGTH_SHORT).show();
        /*switch(item.getItemId()){
            case R.id.chapter_zero:
                changeContent("zero",R.string.chapter_zero);
                break;
            case R.id.chapter_one_1:
                changeContent("one.1",R.string.chapter_one_1);
                break;
            case R.id.chapter_one_2:
                changeContent("one.2",R.string.chapter_one_2);
                break;
            case R.id.chapter_one_3:
                changeContent("one.3",R.string.chapter_one_3);
                break;
            case R.id.chapter_one_4:
                changeContent("one.4",R.string.chapter_one_4);
                break;
            case R.id.chapter_two_0:
                changeContent("two.0",R.string.chapter_two_0);
                break;
            case R.id.chapter_two_1:
                changeContent("two.1",R.string.chapter_two_1);
                break;
            case R.id.chapter_three_0:
                changeContent("three.0",R.string.chapter_three_0);
                break;
            case R.id.chapter_three_1:
                changeContent("three.1",R.string.chapter_three_1);
                break;
            case R.id.chapter_three_2:
                changeContent("three.2",R.string.chapter_three_2);
                break;
            case R.id.chapter_four_1:
                changeContent("four.1",R.string.chapter_four_1);
                break;
            case R.id.chapter_four_2:
                changeContent("four.2",R.string.chapter_four_2);
                break;
            case R.id.chapter_five_1:
                changeContent("five.1",R.string.chapter_five_1);
                break;

            default:
                Toast.makeText(this, "操作异常，请重试", Toast.LENGTH_SHORT).show();
        }

        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    /*
    private void changeContent(String fileName,int titleId){
        mScrollView.fullScroll(View.FOCUS_UP);
        title=getString(titleId);
        mToolbarTextView.setText(title);
        mWebView.loadUrl("file:///android_asset/web/"+fileName+".html");
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mScrollView.fullScroll(View.FOCUS_UP);
    }
*/

}
