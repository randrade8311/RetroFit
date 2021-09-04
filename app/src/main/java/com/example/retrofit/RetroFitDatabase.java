package com.example.retrofit;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.retrofit.model.Post;
import com.example.retrofit.model.User;
import com.example.retrofit.model.UserDao;
import com.example.retrofit.rest.PostApiService;
import com.example.retrofit.rest.UserApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RetroFitDatabase extends RoomDatabase {
    private static RetroFitDatabase sInstance;
    public abstract UserDao user();
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;
    private List<Post> posts;
    private List<User> users;

    public static synchronized RetroFitDatabase getInstance(Context context){
        if (sInstance == null){
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(),
                            RetroFitDatabase.class,
                            "users.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

    public void populateUsers() {
        if (user().count() == 0){
            for (int i = 0; i < users.size(); i++) {
                user().addUser(users.get(i));
            }
        }
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
                Log.i("RetroFitDatabase", "Num of posts: " + posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("RetroFitDatabase", "Error: " + t.toString());
            }
        });


    }

    public void getUsersAPIData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        UserApiService userApiService = retrofit.create(UserApiService.class);
        Call<List<User>> call = userApiService.getUsers();
        call.enqueue(new retrofit2.Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users = response.body();
                Log.i("RetroFitDatabase", "Num of users: " + users.size());
                populateUsers();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("RetroFitDatabase", "Error: " + t.toString());
            }
        });
    }
}
