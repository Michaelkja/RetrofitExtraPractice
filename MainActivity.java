package com.example.micha.retrofitextrapractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResults;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResults = findViewById(R.id.text_view_results);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //getPosts();
        //getComments();
        //createPost();
        //updatePost();
        deletePost();
        }

    private void getPosts() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()) {
                    textViewResults.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserId :" + post.getUserId() + "\n";
                    content += "Title :" + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n \n";


                    textViewResults.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });

    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi
                .getComments("https://jsonplaceholder.typicode.com/posts/3/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textViewResults.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    textViewResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });
    }
    private void createPost() {
        Post post = new Post(43, "Rekenen", "23 is meer dan 4");

        Map<String,String> fields = new HashMap<>();
        fields.put("userId", "2010");
        fields.put("title", "ff checken");


        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResults.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = (Post) response.body();

                String content = "";
                content += "Code :" + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "UserId :" + postResponse.getUserId() + "\n";
                content += "Title :" + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n \n";

                textViewResults.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(13, "drie vier 4", "Wie weet hoeveel dat is");

        Call<Post> call = jsonPlaceHolderApi.putPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResults.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = (Post) response.body();

                String content = "";
                content += "Code :" + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "UserId :" + postResponse.getUserId() + "\n";
                content += "Title :" + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n \n";

                textViewResults.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            textViewResults.setText(t.getMessage());
            }
        });
    }
    private void deletePost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResults.setText("code :" + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });
    }
}
