package com.rakesh.nytimessearch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.rakesh.nytimessearch.Filters.ArticleFilter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rparuthi on 3/17/2017.
 */

public class NYTimesApp extends Application {

    public void onCreate(){
        super.onCreate();

        setFilterSettings();

        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);

        //Enable chrome Dev tools
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));

        //Enable command line interface
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(getApplicationContext()));

        //Use the InitializerBuuilder to generate an initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        //Initialize Stetho with intializer
        Stetho.initialize(initializer);
    }

    private void setFilterSettings(){
        ArticleFilter settings = ArticleFilter.getArticleFilterInstance();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        settings.setBeginDate(dateFormat.format(cal.getTime()));

        settings.setSortOrder("Newest");
    }
}
