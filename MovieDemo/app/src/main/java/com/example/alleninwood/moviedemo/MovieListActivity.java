package com.example.alleninwood.moviedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import android.support.v7.widget.RecyclerView;

public class MovieListActivity extends AppCompatActivity {
    boolean lastPage;
    private RecyclerView recyclerView;
    private LinkedList<Map<String, Object>> list = new LinkedList();
    public int offset = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.movie_list);

        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie(v);
            }
        });

        Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousPage(v);
            }
        });

        Button p1 = findViewById(R.id.p1);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page(v, 1);
            }
        });

        Button p2 = findViewById(R.id.p2);
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page(v, 2);
            }
        });

        Button p3 = findViewById(R.id.p3);
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page(v, 3);
            }
        });

        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage(v);
            }
        });
    }

    public void searchMovie(View view) {
        offset = 0;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String query = ((TextView)findViewById(R.id.queryinput)).getText().toString();
        String url = "https://52.14.130.96:8443/catsneeze/full-text?query=" + query + "&mobile=mobile";
        requestQueue.add(jsonShow(url));
        changeButtonText();
    }

    private StringRequestUtil jsonShow(String url) {
        FakeX509TrustManager.allowAllSSL();
        StringRequestUtil jsonObjectRequest = new StringRequestUtil(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String str) {
                        try {
                            list.clear();
                            JSONObject jsonObject = convert(str);
                            if (Integer.valueOf(jsonObject.getString("status")) == 0) {
                                JSONArray movieList = jsonObject.getJSONArray("data");
                                if (movieList.length() < 20) {
                                    lastPage = true;
                                }
                                else {
                                    lastPage = false;
                                }
                                for (int i = 0; i < movieList.length(); ++ i) {
                                    Map<String, Object> listItem = new HashMap();
                                    listItem.put("title", movieList.getJSONObject(i).getString("title"));
                                    listItem.put("year", movieList.getJSONObject(i).getString("year"));
                                    listItem.put("director", movieList.getJSONObject(i).getString("director"));
                                    JSONArray genresList = movieList.getJSONObject(i).getJSONArray("genresList");
                                    JSONArray starsList = movieList.getJSONObject(i).getJSONArray("starsList");
                                    Log.d("genresSize", String.valueOf(genresList.length()));
                                    Log.d("starsSize", String.valueOf(starsList.length()));
                                    LinkedList<String> genres = new LinkedList();
                                    LinkedList<String> stars = new LinkedList();
                                    if (genresList.length() > 3) {
                                        for (int j = 1; j < 3; ++ j) {
                                            genres.add(genresList.getJSONObject(j).getString("name"));
                                        }
                                        genres.add("More");
                                    }
                                    else {
                                        for (int j = 0; j < genresList.length(); ++ j) {
                                            genres.add(genresList.getJSONObject(j).getString("name"));
                                        }
                                    }
                                    if (starsList.length() > 5) {
                                        for (int j = 1; j < 5; ++ j) {
                                            stars.add(starsList.getJSONObject(j).getString("name"));
                                        }
                                        stars.add("More");
                                    }
                                    else {
                                        for (int j = 0; j < genresList.length(); ++ j) {
                                            stars.add(starsList.getJSONObject(j).getString("name"));
                                        }
                                    }
                                    listItem.put("genres", genres);
                                    listItem.put("stars", stars);
                                    listItem.put("picture", R.drawable.movie);
                                    list.add(listItem);
                                }
                                initView();
                            } else {
                                Toast.makeText(getBaseContext(), "Search failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.getStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MovieListActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                        Log.d("security.error", volleyError.toString());
                    }
                }
        );
        return jsonObjectRequest;
    }

    private JSONObject convert(String str) throws JSONException {
        JSONObject jsonObject = new JSONObject(str);
        return jsonObject;
    }

    private void changeButtonText() {
        Button p1 = findViewById(R.id.p1);
        Button p2 = findViewById(R.id.p2);
        Button p3 = findViewById(R.id.p3);
        p1.setText(String.valueOf(offset + 1));
        p2.setText(String.valueOf(offset + 2));
        p3.setText(String.valueOf(offset + 3));
    }

    public void previousPage(View view) {
        if (offset == 0) {
            return;
        }
        offset -= 1;
        String queryOffset = String.valueOf(offset * 20);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String query = ((TextView)findViewById(R.id.queryinput)).getText().toString();
        String url = "https://52.14.130.96:8443/catsneeze/full-text?query=" + query + "&mobile=mobile&offset=" + queryOffset;
        requestQueue.add(jsonShow(url));
        changeButtonText();
    }

    public void Page(View view, int i) {
        Button p;
        if (lastPage) {
            return;
        }
        if(i == 1) {
            p = findViewById(R.id.p1);
        }
        else if (i == 2) {
            p = findViewById(R.id.p2);
        }
        else {
            p = findViewById(R.id.p3);
        }
        String text = p.getText().toString();
        offset = Integer.parseInt(text) - 1;
        String queryOffset = String.valueOf(offset * 20);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String query = ((TextView)findViewById(R.id.queryinput)).getText().toString();
        String url = "https://52.14.130.96:8443/catsneeze/full-text?query=" + query + "&mobile=mobile&offset=" + queryOffset;
        requestQueue.add(jsonShow(url));
        changeButtonText();
    }

    public void nextPage(View view) {
        if (lastPage) {
            return;
        }
        offset += 1;
        String queryOffset = String.valueOf(offset * 20);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String query = ((TextView)findViewById(R.id.queryinput)).getText().toString();
        String url = "https://52.14.130.96:8443/catsneeze/full-text?query=" + query + "&mobile=mobile&offset=" + queryOffset;
        requestQueue.add(jsonShow(url));
        changeButtonText();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
