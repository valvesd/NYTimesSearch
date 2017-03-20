package com.rakesh.nytimessearch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.rakesh.nytimessearch.Adapters.ArticlesAdapter;
import com.rakesh.nytimessearch.Filters.ArticleFilter;
import com.rakesh.nytimessearch.Fragments.SettingsFragment;
import com.rakesh.nytimessearch.Helper.EndlessRecyclerViewScrollListener;
import com.rakesh.nytimessearch.Helper.ItemClickSupport;
import com.rakesh.nytimessearch.Helper.NetworkHelper;
import com.rakesh.nytimessearch.Models.ApiResponse;
import com.rakesh.nytimessearch.Models.Article;
import com.rakesh.nytimessearch.Rest.ApiClient;
import com.rakesh.nytimessearch.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private final static String API_KEY = "358ecf0ee5264cbfaef616c44a405f1a";

    private static final String TAG = SearchActivity.class.getSimpleName();

    RecyclerView rvArticles;
    ArticlesAdapter articlesAdapter;
    List<Article> articles;
    Handler mHandler;
    String mQueryString;
    Toolbar toolbar;

    private EndlessRecyclerViewScrollListener scrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler(Looper.getMainLooper());

        articles = new ArrayList<>();

        articlesAdapter = new ArticlesAdapter(articles,R.layout.item_article_info,getApplicationContext());

        rvArticles = (RecyclerView) findViewById(R.id.rvArticles);
        rvArticles.setAdapter(articlesAdapter);

        StaggeredGridLayoutManager layoutManager = getLayoutManager();

        //Set Layoutmanager
        rvArticles.setLayoutManager(layoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                final int pageNo = page;
                mHandler.removeCallbacksAndMessages(null);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mQueryString.length() > 1) {
                            //GetArticles
                            getArticles(mQueryString,pageNo);
                        }
                    }
                }, 300);

            }
        };
        rvArticles.addOnScrollListener(scrollListener);

        ItemClickSupport.addTo(rvArticles).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        Article article = articles.get(position);
                        Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                        intent.putExtra("url",article.getWebUrl());

                        startActivity(intent);

                    }
                }
        );


    }

    private StaggeredGridLayoutManager getLayoutManager(){
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return  manager;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_items, menu);

        //Get searchmenu item
        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        //set listener for SearchView
        setListeners(searchView);

        return super.onCreateOptionsMenu(menu);
    }

    public void setListeners(SearchView searchView){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mQueryString = newText;
                if(mQueryString.length() == 0 ){
                    articlesAdapter.clearArticles();
                    articlesAdapter.notifyDataSetChanged();
                    scrollListener.resetState();
                    return true;
                }
                mHandler.removeCallbacksAndMessages(null);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mQueryString.length() > 1) {
                            //GetArticles
                            getArticles(mQueryString,0);
                        }
                    }
                }, 300);

                return true;
            }

        });
    }


    public  void getArticles(String searchText, @Nullable final Integer offset ){

        if(!NetworkHelper.isNetworkAvailable(this.getApplicationContext())){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please make sure that the device is connected to internet",Toast.LENGTH_SHORT);
            toast.show();
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ApiResponse> call = apiService.getArticle(API_KEY,searchText,
                ArticleFilter.getArticleFilterInstance().getSortOrder(),
                ArticleFilter.getArticleFilterInstance().getFilterBeginDate(),
                ArticleFilter.getArticleFilterInstance().getFilterQuery(),
                offset);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try {
                    if(response!= null && response.body()!= null) {
                        articles = response.body().getResponse().getArticles();
                        articlesAdapter.addArticles(articles, offset, articles.size());
                        articlesAdapter.notifyDataSetChanged();


                    }else {
                        Toast.makeText(getApplicationContext(),"There are no articles with the criteria specified",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    Log.e(TAG,ex.getStackTrace().toString());
                    Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Issues connecting to API",Toast.LENGTH_SHORT).show();
                Log.e(TAG,t.toString());
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }else if (id == R.id.action_filter){

            FragmentManager fragMan = getSupportFragmentManager();

            SettingsFragment fragment = new SettingsFragment();

            fragment.show(fragMan,"fragment_settings");

            return  true;

        }

        return super.onOptionsItemSelected(item);
    }
}
