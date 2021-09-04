package com.example.retrofit;

import android.content.Context;
import android.util.Log;
import android.view.ViewParent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.retrofit.activity.MainActivity;
import com.example.retrofit.adapter.PostsAdapter;
import com.example.retrofit.model.Post;
import com.example.retrofit.model.PostDao;
import com.example.retrofit.model.User;
import com.example.retrofit.model.UserDao;
import com.example.retrofit.rest.PostApiService;
import com.example.retrofit.rest.UserApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {User.class, Post.class}, version = 2, exportSchema = false)
public abstract class RetroFitDatabase extends RoomDatabase {
    private static RetroFitDatabase sInstance = null;
    public abstract UserDao user();
    public abstract PostDao post();
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;
    private List<Post> posts;
    private List<User> users;

    public static synchronized RetroFitDatabase getInstance(Context context){
        if (sInstance == null){
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(),
                            RetroFitDatabase.class,
                            "user.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstance;
    }

    public void populateUsers() {
        if (user().count() == 0){
            for (int i = 0; i < users.size(); i++) {
                users.get(i).setPassword("password" + i);
                user().addUser(users.get(i));
            }
        }
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
//                Log.i("RetroFitDatabase", "Num of users: " + users.get(0).getUsername() + " " + users.get(0).getPassword());
                populateUsers();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("RetroFitDatabase", "Error: " + t.toString());
            }
        });
    }
}
