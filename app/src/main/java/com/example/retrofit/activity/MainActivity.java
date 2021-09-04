package com.example.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.RetroFitDatabase;
import com.example.retrofit.adapter.PostsAdapter;
import com.example.retrofit.model.Post;
import com.example.retrofit.model.User;
import com.example.retrofit.rest.PostApiService;
import com.example.retrofit.rest.UserApiService;

import org.w3c.dom.Text;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.retrofit.RetroFitDatabase.BASE_URL;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    TextView usernameTV;

    private List<Post> posts;
    private RecyclerView recyclerView;
    public PostsAdapter postsAdapter;
    private RetroFitDatabase retroFitDatabase;
    private static Retrofit retrofit = null;
    private int userId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTV = findViewById(R.id.userName);

        Intent i = getIntent();
        userId = i.getIntExtra("userId", 0);
        userName = i.getStringExtra("username");
        usernameTV.setText(userName);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        getPostsAPIData();
    }

    public void getPostsAPIData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        PostApiService postApiService = retrofit.create(PostApiService.class);
        Call<List<Post>> call = postApiService.getPosts();
        call.enqueue(new retrofit2.Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                posts = response.body();

                List<Post> userPosts = new ArrayList<>();

                for (int i = 0; i < posts.size(); i++) {
                    if (posts.get(i).getUId() == userId){
                        userPosts.add(posts.get(i));
                    }
                }

                postsAdapter = new PostsAdapter(getApplicationContext(), userPosts);
                recyclerView.setAdapter(postsAdapter);
                recyclerView.smoothScrollToPosition(0);
                Log.i("RetroFitDatabase", "Num of posts: " + posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("RetroFitDatabase", "Error: " + t.toString());
            }
        });
    }
}