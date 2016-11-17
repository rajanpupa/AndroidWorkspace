package nepalinews.oneworld.np.com.rajanu.myapplication;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Rajan on 11/14/2016.
 */

public class Browser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

}
