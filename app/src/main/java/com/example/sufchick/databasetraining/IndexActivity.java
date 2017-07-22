package com.example.sufchick.databasetraining;

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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class IndexActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;

    private ActionBar mActionBar;

    private TextView mToolbarTextView;

    private ScrollView mScrollView;

    private NavigationView mNavigationView;

    private DrawerLayout mDrawerLayout;


    private WebView mWebView;

    private String content;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mToolbar = (Toolbar) findViewById(R.id.index_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mActionBar=getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mScrollView=(ScrollView)findViewById(R.id.index_scroll);


        mToolbarTextView=(TextView)findViewById(R.id.index_toolbar_textview);

        mNavigationView=(NavigationView)findViewById(R.id.index_nav_view);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.index_drawer_layout);

        mNavigationView.setNavigationItemSelectedListener(this);

        mWebView=(WebView) findViewById(R.id.index_content_view);

        changeContent("zero",R.string.chapter_zero);

        //content=getString(R.string.chapter_content_zero);

        //mTextView.setText(Html.fromHtml(content));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(mNavigationView);
        switch(item.getItemId()){
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
            case R.id.chapter_two_1:
                changeContent("two.1",R.string.chapter_two_1);
                break;
            case R.id.chapter_three_1:
                changeContent("three.1",R.string.chapter_three_1);
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
                break;

        }
        return true;
    }
    private void changeContent(String fileName,int titleId){
        title=getString(titleId);
        mToolbarTextView.setText(title);
        mWebView.loadUrl("file:///android_asset/web/"+fileName+".html");
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    private void changeContent(int contentId,int titleId){
        content=getString(contentId);
        title=getString(titleId);
        mToolbarTextView.setText(title);
       // mTextView.setText(Html.fromHtml(content));
        mScrollView.fullScroll(View.FOCUS_UP);

    }

    /**
     * TextView字体自适应
     * @param tv
     * @param maxWidth
     * @param text
     */
    private void adjustTvTextSize(TextView tv, int maxWidth, String text) {
        int avaiWidth = maxWidth - tv.getPaddingLeft() - tv.getPaddingRight() - 10;

        if (avaiWidth <= 0) {
            return;
        }

        TextPaint textPaintClone = new TextPaint(tv.getPaint());

        float trySize = textPaintClone.getTextSize();

        while (textPaintClone.measureText(text) > avaiWidth) {
            trySize--;
            textPaintClone.setTextSize(trySize);
        }

        Toast.makeText(this, "123123", Toast.LENGTH_SHORT).show();
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);
    }
}
