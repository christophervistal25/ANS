package com.vistalis.numbersystem.DatabaseModules.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vistalis.numbersystem.DatabaseModules.Models.Articles;

import java.util.List;

@Dao
public interface ArticlesDao {
    @Insert
    void addArticle(Articles category);

    @Query("SELECT * FROM articles")
    List<Articles> getArticles();
}
