package com.hamilton;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.hamilton.utility.Constants;
import com.hamilton.utility.Utils;
import com.hamilton.view.TypefacedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {
    @BindView(R.id.lblHeader)
    TypefacedTextView lblHeader;
    @BindView(R.id.webView)
    WebView webView;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        lblHeader.setText(getIntent().hasExtra(Constants.KEY_TITLE) ? getIntent().getStringExtra(Constants.KEY_TITLE) : "");

        String url = getIntent().hasExtra(Constants.KEY_WEB_URL) ? getIntent().getStringExtra(Constants.KEY_WEB_URL) : "";

        mDialog = Utils.getLoadingDialog(WebviewActivity.this);
        loadWebViewWithContent(webView, url);

    }

    private void loadWebViewWithContent(WebView webView, String url) {

        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        final WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(final String origin,
                                                           final GeolocationPermissions.Callback callback) {

                final boolean remember = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(WebviewActivity.this);
                builder.setMessage("Would like to use your Current Location ").setCancelable(true)
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // origin, allow, remember
                                callback.invoke(origin, true, remember);
                            }
                        }).setNegativeButton("Don't Allow", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // origin, allow, remember
                        callback.invoke(origin, false, remember);
                    }
                });
                AlertDialog alert = builder.create();
                alert.setCancelable(false);
                alert.show();
            }

        });

        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (mDialog != null && !mDialog.isShowing()) {
                    mDialog.show();
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    new CountDownTimer(1000, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            //progressBar.dismiss();
                            if (mDialog != null && mDialog.isShowing()) {
                                mDialog.dismiss();
                            }
                        }
                    }.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (Utils.hasInternetAccess(WebviewActivity.this)) {
            if (url.contains("pdf"))
                webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
            else
                webView.loadUrl(url);
        } else {
            Toast.makeText(WebviewActivity.this, getResources().getString(R.string.no_network_access), Toast.LENGTH_SHORT).show();
            mDialog.dismiss();
        }
    }

    public void loadUrlInWebView(WebView webView, String url) {

        if (url != null && url.length() > 0) {
            mDialog.show();
            loadWebViewWithContent(webView, url);

        }
    }
}
