package com.example.hoang.kaitea.data;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by HOANG on 10/12/2018.
 */

public class MyWebView extends WebViewClient
{
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }
}
