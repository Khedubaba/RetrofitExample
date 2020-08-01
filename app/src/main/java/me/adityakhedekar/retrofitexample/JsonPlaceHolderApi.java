package me.adityakhedekar.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    //GET request using placeholders & @Path replacement blocks
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    //Do this if url format is more complex and not possible to use parameters and want to pass whole url
    @GET
    Call<List<Comment>> getComments(@Url String url);

    //sending(POST) data to server through http body
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //If your REST API require to send data in Form URL Encoded manner retrofit provides way for that too
    //sending(POST) data to server through Form URL encoding
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    /* Note: @Query & @Field performs same task ie send data in URL Encoded format
    the diference is that @Query sends it over URL and @Field sends it in HTTP body */

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);


    //PUT & PATCH. PUT completely overrite the resource where as PATCH can be used it to change a single field in resources
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}
