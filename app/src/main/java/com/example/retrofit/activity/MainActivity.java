package com.example.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit.R;
import com.example.retrofit.model.User;
import com.example.retrofit.rest.UserApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private List<User> userList;

    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectAndGetApiData();
    }

//    private void connectAndGetApiData() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//        }
//
//        PostApiService postApiService = retrofit.create(PostApiService.class);
//        Call<List<Post>> call = postApiService.getPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                List<Post> posts = response.body();
//                Log.i(TAG, "Num of posts: " + posts);
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//
//            }
//        });
//    }


    private void connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<List<User>> call = userApiService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
//                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                Log.i(TAG, "Number of users received: " + users.get(0).getUsername());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, "here" + t.toString());
            }
        });
    }
}