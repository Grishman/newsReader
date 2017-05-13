package com.reader.grishman.newsreader.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reader.grishman.newsreader.R;
import com.reader.grishman.newsreader.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grishman on 13.05.17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.Viewholder> {

    private List<Article> data;
    private Article model;
    private Context context;

    public ArticleAdapter(ArrayList<Article> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public void swapData(List<Article> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        public TextView articleTitle;
        public TextView articleDescription;


        public Viewholder(View itemView) {
            super(itemView);

            articleTitle = (TextView) itemView.findViewById(R.id.article_title);
            articleDescription = (TextView) itemView.findViewById(R.id.article_description);

        }
    }


    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item_layout, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        model = data.get(position);

        holder.articleTitle.setText(model.getTitle());
        holder.articleDescription.setText(model.getDescription());

    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }
}
