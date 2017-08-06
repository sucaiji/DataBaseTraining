package com.example.sufchick.databasetraining.fragment;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.sufchick.databasetraining.R;
import com.example.sufchick.databasetraining.activity.IndexActivity;


public class IndexFragment extends Fragment implements View.OnClickListener {

    private FragmentActivity activity;

    private View view;

    private WebView mWebView;

    private ScrollView mScrollView;

    private String title;

    private String[] indexArray;

    private String[] indexToHtmlArray;

    private LinearLayout lastPage;

    private LinearLayout index;

    private LinearLayout nextPage;

    private int pageNumber = 0;

    private int pageSize;

    public IndexFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, null);

        findView();

        initPageInfo();


        activity = getActivity();

        lastPage.setOnClickListener(this);

        index.setOnClickListener(this);

        nextPage.setOnClickListener(this);

        changeContent(0);

        return view;
        //inflater.inflate(R.layout.fragment_index, container, false);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.last_page_button:
                pageNumber--;
                if(outOfPage()){
                    pageNumber++;
                    Toast.makeText(activity, "已经是最前一章了", Toast.LENGTH_SHORT).show();
                }
                changeContent(pageNumber);
                break;
            case R.id.index_button:
                Toast.makeText(getActivity(), "index", Toast.LENGTH_SHORT).show();
                if (activity == null) {
                    Toast.makeText(activity, "发生未知错误balabala", Toast.LENGTH_SHORT).show();
                }
                Intent indexIntent = new Intent(activity, IndexActivity.class);
                activity.startActivityForResult(indexIntent,1);
                break;
            case R.id.next_page_button:
                pageNumber++;
                if(outOfPage()){
                    pageNumber--;
                    Toast.makeText(activity, "已经是最后一章了", Toast.LENGTH_SHORT).show();
                }
                changeContent(pageNumber);
                break;
        }
    }
    private void initPageInfo(){
        Resources res = getResources();
        indexArray = res.getStringArray(R.array.index);
        indexToHtmlArray=res.getStringArray(R.array.index_to_html);
        if (indexArray != null) {
            pageSize = indexArray.length;
        }
        pageSize--;
    }

    private boolean outOfPage() {
        boolean outOfPage=pageNumber<0||pageNumber>pageSize;
        if(outOfPage){
            return true;
        }
        return false;
    }



    public void changeContent(int pageNumber) {
        //mScrollView.fullScroll(View.FOCUS_UP);
        String fileName=indexToHtmlArray[pageNumber];
        title=indexArray[pageNumber];
        mWebView.loadUrl("file:///android_asset/web/" + fileName + ".html");
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void findView() {

        mWebView = (WebView) view.findViewById(R.id.index_content_view);

        //mScrollView = (ScrollView) view.findViewById(R.id.index_scroll);

        lastPage = (LinearLayout) view.findViewById(R.id.last_page_button);

        index = (LinearLayout) view.findViewById(R.id.index_button);

        nextPage = (LinearLayout) view.findViewById(R.id.next_page_button);

    }
}
