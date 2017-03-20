package com.rakesh.nytimessearch.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rakesh.nytimessearch.Models.Article;
import com.rakesh.nytimessearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by rparuthi on 3/15/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    List<Article> articles;
    private Context ctx;
    private int rowLayout;


    public  class ArticleViewHolder extends RecyclerView.ViewHolder {

        CardView articlesLayout;
        ImageView ivArticleImage;
        TextView tvArticleText;

        public ArticleViewHolder(View view){
            super(view);
            articlesLayout = (CardView) view.findViewById(R.id.articles_card_layout);
            tvArticleText = (TextView)view.findViewById(R.id.tvArticleText);
            ivArticleImage = (ImageView)view.findViewById(R.id.ivArticleImage);

        }
    }

    public ArticlesAdapter(List<Article> articles, int rowLayout,Context ctx){
        this.articles = articles;
        this.rowLayout =rowLayout;
        this.ctx = ctx;
    }

    //Add articles
    public  void addArticles(List<Article> newArticles,int startposition, int count) {

        articles.addAll(startposition, newArticles);
        //this.notifyItemInserted(newArticles.size() - 1);
        this.notifyItemRangeInserted(startposition, count);

    }

    //Clear articles
    public void clearArticles(){
        articles.clear();
    }

    public int getItemCount(){
        return (null != articles ?articles.size() : 0);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, final int position) {
        Article article = articles.get(position);

        holder.tvArticleText.setText(article.getHeadline().getPrintHeadline());

        if(article.getMultimedia() != null && article.getMultimedia().size() > 0) {
            String imageUrl = article.getMultimedia().get(0).getUrl();
            if (!TextUtils.isEmpty(imageUrl)) {
                Picasso.with(ctx).load(imageUrl)
                        .transform(new RoundedCornersTransformation(2,2))
                        .resize(75, 75)
                        .into(holder.ivArticleImage);
            }
        }

    }
}
