package nepalinews.oneworld.np.com.rajanu.myapplication;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.ActionBarDrawerToggle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    String url = "http://www.google.com";

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;
    WebView myWebView;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();

        Log.i("rajan", "onCreate: url= " + url);

        myWebView = (WebView) findViewById(R.id.webView1);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new Browser());
        myWebView.loadUrl(url);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.navigation_item_2:
                        startActivity(new Intent(MainActivity.this, ViralAndroidActivity.class));
                        break;
                    case R.id.navigation_item_3:
                        Log.i("rajan", "navigation item 3 ");
                        //startActivity(new Intent(MainActivity.this, GoogleActivity.class));
                        url = "http://www.bbc.com/nepali";
                        myWebView.loadUrl(url);
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.navigation_item_4:
                        Log.i("rajan", "navigation item 4");
                        //startActivity(new Intent(MainActivity.this, ViralAndroidActivity.class));
                        Log.i("rajan", "setting to rajanu.com.np");
                        url = "http://rajanu.com.np";
                        myWebView.loadUrl(url);
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.navigation_item_5:
                        Log.i("rajan", "setting to blogspot.com");
                        url = "http://rajanpupa.blogspot.com";
                        myWebView.loadUrl(url);
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
        //setContentView(R.layout.activity_main);
        //myWebView.refreshDrawableState();
        myWebView.invalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

