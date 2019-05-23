package com.vistalis.numbersystem.Repositories;

import android.content.Context;

import com.vistalis.numbersystem.DatabaseModules.DB;
import com.vistalis.numbersystem.DatabaseModules.Models.Articles;

public class ArticleRepository {

     /*ArticleRepository.create(getApplicationContext(),"Binary Numbers","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
            "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        ArticleRepository.create(getApplicationContext(),"Decimal Numbers","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
            "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        ArticleRepository.create(getApplicationContext(),"Octal Numbers","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
            "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        ArticleRepository.create(getApplicationContext(),"Hexadecimal Numbers","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
            "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");*/

    public static void create(Context context, String title, String description)
    {
        Articles category = new Articles(title, description);
        DB.getInstance(context).articlesDao().addArticle(category);
    }

}
