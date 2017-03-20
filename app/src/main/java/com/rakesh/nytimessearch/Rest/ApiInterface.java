package com.rakesh.nytimessearch.Rest;

import com.rakesh.nytimessearch.Models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rparuthi on 3/15/2017.
 */

public interface ApiInterface {

    @GET("articlesearch.json")
    Call<ApiResponse> getArticle(@Query("api_key") String apiKey,
                                 @Query("q") String searchParam,
                                 @Query("sort") String sortOrder,
                                 @Query("begin_date") String beginDate,
                                 @Query("fq") String filterQuery,
                                 @Query("page") int pageNumber
                                 );





}
