package com.example.comprawebview.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.comprawebview.R;
import com.example.comprawebview.core.WebAppInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView web = (WebView) this.findViewById(R.id.web);

        this.configureWebView(web, "https://www.google.es", 20);

    }

    private void configureWebView(WebView wvView, String url, int defaultFontSize){
        WebSettings settings = wvView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDefaultFontSize(defaultFontSize);
        settings.setJavaScriptEnabled(true);
        wvView.addJavascriptInterface(new WebAppInterface(this), "Android");
        wvView.setWebViewClient(new WebViewClient());

        StringBuilder builder = new StringBuilder();
        BufferedReader br = null;
        try{
            String line;
            InputStream in = this.getAssets().open("web.html");
            br = new BufferedReader(new InputStreamReader(in));
            while((line=br.readLine())!=null){
                builder.append(line);
            }
        } catch (IOException e){
            builder.append("<html><body><big>ERROR internal: loading asset</big></body></html>");
        } finally {
            try {
                if (br != null){
                    br.close();
                }
            }catch (IOException exc){

            }
        }
        wvView.loadData(builder.toString(), "text/html", "utf-8");
    }
}
