package com.vistalis.numbersystem;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vistalis.numbersystem.Adapters.ArticleAdapter;
import com.vistalis.numbersystem.DatabaseModules.DB;
import com.vistalis.numbersystem.DatabaseModules.Models.Articles;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {


    public LearnFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView;

        List<Articles> article_list = DB.getInstance(getContext()).articlesDao().getArticles();

        ArticleAdapter articleAdapter;

        articleAdapter = new ArticleAdapter(article_list);

        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(),
                        LinearLayoutManager.VERTICAL)
        );


        LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(articleAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

}
