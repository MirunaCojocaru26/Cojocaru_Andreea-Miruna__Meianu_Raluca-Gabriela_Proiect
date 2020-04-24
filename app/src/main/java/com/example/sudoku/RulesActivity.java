package com.example.sudoku;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.sudoku.MainActivity.Language;
import static com.example.sudoku.MainActivity.MyPREFERENCES;
import static com.example.sudoku.MainActivity.Theme;

public class RulesActivity  extends AppCompatActivity {
    private  String urlEn =  "https://my-json-server.typicode.com/meianuraluca/JsonServer/info" ;
    String  article1, article2,article3, article4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //change theme
        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String value_theme = sharedPreferences.getString(Theme,"");
        switch (value_theme){
            case "dark":
                setTheme(R.style.AppTheme);
                break;
            case "red":
                setTheme(R.style.AppTheme1);
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        getRulesFromJson();
    }
    public void getRulesFromJson(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlEn, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    try{
                        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        String lang = sharedPreferences.getString(Language,"");
                        switch (lang){
                            case "en":
                                article1 = response.getJSONObject(0).getString("article1");
                                article2 = response.getJSONObject(0).getString("article2");
                                article3 = response.getJSONObject(0).getString("article3");
                                article4 = response.getJSONObject(0).getString("article4");
                                break;
                            case "ro":
                                article1 = response.getJSONObject(1).getString("article1");
                                article2 = response.getJSONObject(1).getString("article2");
                                article3 = response.getJSONObject(1).getString("article3");
                                article4 = response.getJSONObject(1).getString("article4");
                                break;
                            case "fr":
                                article1 = response.getJSONObject(2).getString("article1");
                                article2 = response.getJSONObject(2).getString("article2");
                                article3 = response.getJSONObject(2).getString("article3");
                                article4 = response.getJSONObject(2).getString("article4");
                                break;

                        }
                        setRules();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
//                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),"Volley error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                FragmentManager fm = getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);

    }
    public void setRules(){
        TextView varticle1 = (TextView)findViewById(R.id.TextViewArticle1);
        varticle1.setText(article1);
        TextView varticle2 = (TextView)findViewById(R.id.TextViewArticle2);
        varticle2.setText(article2);
        TextView varticle3 = (TextView)findViewById(R.id.TextViewArticle3);
        varticle3.setText(article3);
        TextView varticle4 = (TextView)findViewById(R.id.TextViewArticle4);
        varticle4.setText(article4);


    }
}
