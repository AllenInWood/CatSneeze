package com.example.alleninwood.moviedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static volatile String localCookie = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    public void login(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://52.14.130.96:8443/catsneeze/login";
        String email = ((TextView)findViewById(R.id.inputemail)).getText().toString();
        String password = ((TextView)findViewById(R.id.inputpassword)).getText().toString();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", email);
        hashMap.put("password",password);
        hashMap.put("mobile", "mobile");

        FakeX509TrustManager.allowAllSSL();
        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST, url, hashMap,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.d("TagJson",jsonObject.toString());
                    try {
                        if (Integer.valueOf(jsonObject.getString("status")) == 0) {
                            Toast.makeText(MainActivity.this, jsonObject.getString("msg"), Toast.LENGTH_LONG).show();
                            Log.d("flag", "success!!");
                            TextView tv = findViewById(R.id.http_response);
                            tv.setText(jsonObject.getString("msg"));
                            //login successfully
                            //todo: redirect false
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, MovieListActivity.class);
                            intent.putExtra("current_user", jsonObject.getJSONObject("data").getString("firstName"));
                            startActivity(intent);
                        } else {
                            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                            Log.d("flag", "account input wrong!");
                            TextView tv = findViewById(R.id.http_response);
                            tv.setText(jsonObject.getString("msg"));
                        }
                    } catch (JSONException e) {
                        e.getStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MainActivity.this, "request errors", Toast.LENGTH_LONG).show();
                    Log.d("security.error", volleyError.toString());
                }
            }
        ) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Response<JSONObject> superResponse = super.parseNetworkResponse(response);
                Map<String, String> responseHeaders = response.headers;
                String rawCookies = responseHeaders.get("Set-Cookie");
                if (rawCookies != null) {
                    localCookie = rawCookies.substring(0, rawCookies.indexOf(";"));
                }
                return superResponse;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}
