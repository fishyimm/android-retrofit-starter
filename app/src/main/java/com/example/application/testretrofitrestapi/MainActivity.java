package com.example.application.testretrofitrestapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.application.testretrofitrestapi.interfaces.GitHubService;
import com.example.application.testretrofitrestapi.models.Post;
import com.example.application.testretrofitrestapi.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String baseUrl = "https://jsonplaceholder.typicode.com/";

//    https://stackoverflow.com/questions/24100372/retrofit-and-get-using-parameters
//    https://inthecheesefactory.com/blog/retrofit-2.0/th
//    https://medium.com/@anirut.311/%E0%B8%A1%E0%B8%B2%E0%B9%80%E0%B8%8A%E0%B8%B7%E0%B9%88%E0%B8%AD%E0%B8%A1%E0%B8%95%E0%B9%88%E0%B8%AD-network-%E0%B8%94%E0%B9%89%E0%B8%A7%E0%B8%A2-retrofit2-%E0%B8%81%E0%B8%B1%E0%B8%99%E0%B9%80%E0%B8%96%E0%B8%AD%E0%B8%B0-c1a004e6052e
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new HttpRequestTask().execute();

                getUsersPost();
//                getUsers();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void getUsersPost() {
        Retrofit retrofit  = getRetrofitInstance();
        GitHubService gitHubService = retrofit.create(GitHubService.class);

        Call<List<Post>> call = gitHubService.getUsersPost("1");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.i("onResponse", String.valueOf(response.body().size()) );
                Log.i("onResponse", response.body().toString() );
                if(response.body() != null) {
                    if(response.body().size() > 0) {
                        for(Post post : response.body()) {
                            Log.i("getId", post.getId().toString() );
                            Log.i("getTitle", post.getTitle().toString() );
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i("onFailure", call.toString());
            }
        });

    }

    public void getUsers() {
        Retrofit retrofit  = getRetrofitInstance();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<List<User>> call = gitHubService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("onResponse", String.valueOf(response.body().size()) );
                Log.i("onResponse", response.body().toString() );
                if(response.body() != null) {
                    if(response.body().size() > 0) {
                        for(User user : response.body()) {
                            Log.i("getname", user.getName().toString() );
                            Log.i("getWebsite", user.getWebsite().toString() );
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("onFailure", call.toString());
            }
        });
    }

    public Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, User> {
        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
        }

        @Override
        protected User doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GitHubService gitHubService = retrofit.create(GitHubService.class);
            Call call = gitHubService.getUsers();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("onResponse", response.body().toString());
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.i("onFailure", call.toString());
                }
            });

            return null;
        }
    }
}
