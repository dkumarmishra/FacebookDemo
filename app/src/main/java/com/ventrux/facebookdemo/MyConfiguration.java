package com.ventrux.facebookdemo;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

/**
 * Created by Dharmendra kumar mishra (dkumarmishra) on 20/06/17.
 */


public class MyConfiguration {
    Permission[] permissions=new Permission[]{Permission.EMAIL,Permission.PUBLISH_ACTION,Permission.USER_POSTS,Permission.USER_HOMETOWN,Permission.USER_LOCATION,Permission.USER_BIRTHDAY,Permission.PUBLIC_PROFILE};
    static final String APP_ID="1342485649175068";
    public SimpleFacebookConfiguration getMyConfig(){
        SimpleFacebookConfiguration configs=new SimpleFacebookConfiguration.Builder()
                .setAppId(APP_ID)
                .setNamespace("facebookdemodkm")
                .setPermissions(permissions).build();
        return configs;
    }
}