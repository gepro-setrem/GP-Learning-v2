package com.gplearning.gplearning.Controllers;

import android.app.Application;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gplearning.gplearning.Enums.Fragments;
import com.gplearning.gplearning.Models.Quote;
import com.gplearning.gplearning.R;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SharedPreferences shared = getSharedPreferences("login", MODE_PRIVATE);
        String string_temp = shared.getString("user", null);
        if (string_temp == null) {
            Intent intentL = new Intent(this, LoginActivity.class);
            startActivity(intentL);
        } else {
            Intent intent = getIntent();
            if (intent != null) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Log.i("Event", "Tem bundle");
                    if (bundle.containsKey("PAGE")) {
                        String page = bundle.getString("PAGE"); //intent.getLongExtra("ID", 0);
                        changefragment(page);
                    }
                } else {
                    changefragment("");
                }

            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // changefragment("");
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

//            SharedPreferences pref;
//            pref = getSharedPreferences("login",MODE_PRIVATE);
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putString("user",null);
//            editor.commit();

            new getAssync().execute();

//            Error:Execution failed for task ':app:transformResourcesWithMergeJavaResForDebug'.
//                    > com.android.build.api.transform.TransformException: com.android.builder.packaging.DuplicateFileException: Duplicate files copied in APK META-INF/LICENSE
//            File1: C:\Users\Mateus\.gradle\caches\modules-2\files-2.1\com.fasterxml.jackson.core\jackson-core\2.3.2\559b70ac8a0d5cad611da4223137a920147201ba\jackson-core-2.3.2.jar
//            File2: C:\Users\Mateus\.gradle\caches\modules-2\files-2.1\com.fasterxml.jackson.core\jackson-databind\2.3.2\c75edc740a6d8cb1cef6fa82fa594e0bce561916\jackson-databind-2.3.2.jar
//            File3: C:\Users\Mateus\.gradle\caches\modules-2\files-2.1\com.fasterxml.jackson.core\jackson-annotations\2.3.0\f5e853a20b60758922453d56f9ae1e64af5cb3da\jackson-annotations-2.3.0.jar
//
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//http://stackoverflow.com/questions/24777985/how-to-implement-onfragmentinteractionlistener


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        if (item.getItemId() == R.id.nav_project) {
            // Handle the camera action
            changefragment(Fragments.projetos.toString());
        } else if (item.getItemId() == R.id.nav_comments) {
            changefragment(Fragments.comentarios.toString());
        } else if (item.getItemId() == R.id.nav_area) {
            changefragment(Fragments.nivelAcesso.toString());

        }

        return true;
    }

    public void changefragment(String fragments) {
        Fragment fragment = null;
        Bundle args = new Bundle();

        if (fragments.equals(Fragments.projetos.toString())) {
            // Handle the camera action
            fragment = new ProjetoFragment();

        } else if (fragments == Fragments.comentarios.toString()) {
            // Handle the camera action
            //  fragment = new ProjetoFragment();

        } else if (fragments == Fragments.projetos.toString()) {
        } else { //if (fragments == Fragments.nivelAcesso) {
            Intent intent = new Intent(this, NivelAcessoActivity.class);
            startActivity(intent);
        }

        if (fragment != null) {
            fragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();

            //FragmentManager manager = getSupportFragmentManager();
            // manager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    public class getAssync extends AsyncTask<String,Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            Log.i("WB",quote.toString());
            return null;
        }

    }

    /**
     * Check whether the device is connected, and if so, whether the connection
     * is wifi or mobile (it could be something else).
     */
//    private void checkNetworkConnection() {
//        // BEGIN_INCLUDE(connect)
//        ConnectivityManager connMgr =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
//        if (activeInfo != null && activeInfo.isConnected()) {
//            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
//            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
//            if(wifiConnected) {
//                Log.i(TAG, getString(R.string.wifi_connection));
//            } else if (mobileConnected){
//                Log.i(TAG, getString(R.string.mobile_connection));
//            }
//        } else {
//            Log.i(TAG, getString(R.string.no_wifi_or_mobile));
//        }
//        // END_INCLUDE(connect)
//    }


}
