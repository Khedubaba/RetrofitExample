package me.adityakhedekar.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts(
            //int is primitive type hence it is not nullable thus we used Integer type which is wrapper around int and is nullable
            //Also you can either use array, ArrayList or Var Args (Integer...) to accept multiple ids.
            //To use var args know that var args parameter must always be placed last
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    //if you don't want to specify what arguments to pass and decide it when call this method
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    //Do this if url format is more complex and not possible to use parameters and want to pass whole url
    @GET
    Call<List<Comment>> getComments(@Url String url);

}
