package com.poduri.manohar.instagramclone1;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ZyXn5ZW0cRc9TeMMSg6cVz89nztiBDo24G7n6WYd")
                // if defined
                .clientKey("xEklBFTDaR9knqVg7LOl1lVPipHbY1wgXkT7zH26")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
