package com.vistalis.numbersystem.DatabaseModules.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "articles")
public class Articles {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String content;

    public Articles(String title , String content)
    {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
