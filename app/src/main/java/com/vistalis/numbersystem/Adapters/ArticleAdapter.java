package com.vistalis.numbersystem.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vistalis.numbersystem.DatabaseModules.Models.Articles;
import com.vistalis.numbersystem.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

    List<Articles> list_article;

    public ArticleAdapter(List<Articles> list_country) {
        this.list_article = list_country;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_layout, parent, false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {
        Articles article = list_article.get(position);
        holder.title.setText(article.getTitle());
        holder.subtitle.setText(article.getContent());
    }

    @Override
    public int getItemCount() {
        return list_article.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle;

        public ArticleHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_textView);
            subtitle = itemView.findViewById(R.id.subtitle_textView);
        }
    }
}
