package com.example.navegador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private String GOOGLE = "https://google.es";
    private WebView web;
    private String SEARCH_PATH = "/search?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView search = (SearchView) this.findViewById(R.id.buscador);
        web = (WebView) this.findViewById(R.id.web);

        //precargar página de google
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(GOOGLE);

        //listener del SearchView
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!query.trim().isEmpty()){
                    if (URLUtil.isValidUrl(query)){
                        //URL valida
                        web.loadUrl(query);
                    }else {
                        //No es un URL válida
                        web.loadUrl(GOOGLE+SEARCH_PATH+query);
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
    }

    @Override
    public void onBackPressed () {
        if (web.canGoBack()){
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
