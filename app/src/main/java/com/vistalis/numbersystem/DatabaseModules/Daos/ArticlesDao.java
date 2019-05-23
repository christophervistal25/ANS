package com.vistalis.numbersystem.DatabaseModules.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.vistalis.numbersystem.DatabaseModules.Models.Articles;

@Dao
public interface ArticlesDao {
    @Insert
    void addArticle(Articles category);
}
