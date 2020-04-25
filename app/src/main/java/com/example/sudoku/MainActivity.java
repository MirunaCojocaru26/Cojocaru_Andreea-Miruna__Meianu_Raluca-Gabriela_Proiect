package com.example.sudoku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Music = "music";
    public static final String Language = "lang";
    public static final String Theme = "theme";
    public static final String Change = "change";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeTheme();
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //play music
        setAppLocale(sharedPreferences.getString(Language,""));
        if(sharedPreferences.getString(Music,"").equals("true")){
            if (!isMyServiceRunning(service.class)) {
                startService(new Intent(MainActivity.this, service.class));
            }
        }
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_scors).setOnClickListener(this);
        findViewById(R.id.btn_info).setOnClickListener(this);
        findViewById(R.id.btn_playgame).setOnClickListener(this);

        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("email", "public_profile"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_playgame:
                getFragmentManager().beginTransaction().add(R.id.fragment_difficulty, new ChooseDifficultyFragment(),"main_activity_fragment").commit();
                break;
            case R.id.btn_info:
                startActivity(new Intent(MainActivity.this, RulesActivity.class));
                break;
            case R.id.btn_scors:
                startActivity(new Intent(MainActivity.this, TimeActivity.class));
                break;
        }
    }


    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void setAppLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }
    @Override
    protected void onStart() {
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String change = sharedPreferences.getString(Change,"");
        if(change.equals("true")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Change, "false");
            editor.apply();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken == null)
            {
                getSupportActionBar().setTitle("Sudoku");
                Toast.makeText(MainActivity.this,"User loged out",Toast.LENGTH_SHORT);
            }
            else
                loadUserProfile(currentAccessToken);
        }
    };

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest graphRequest = GraphRequest.newMeRequest(newAccessToken,new GraphRequest.GraphJSONObjectCallback(){
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    getSupportActionBar().setTitle("Hello "+first_name+" "+last_name+"!");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields","first_name, last_name, email,id");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    private void changeTheme(){
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String value_theme = sharedPreferences.getString(Theme,"");
        switch (value_theme){
            case "dark":
                setTheme(R.style.AppTheme);
                break;
            case "white":
                setTheme(R.style.AppTheme1);
                break;
        }

    }

}
