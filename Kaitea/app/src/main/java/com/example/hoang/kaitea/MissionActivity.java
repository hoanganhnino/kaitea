package com.example.hoang.kaitea;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class MissionActivity extends AppCompatActivity
{
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mission);

        /*webView = (WebView)findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebView());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(getResources().getString(R.string.link_dang_ky));*/

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getResources().getString(R.string.link_dang_ky)));
        startActivity(intent);
        finish();
    }
}
