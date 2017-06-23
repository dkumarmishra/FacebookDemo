package com.ventrux.facebookdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;
import java.util.List;


/**
 * Created by Dharmendra kumar mishra (dkumarmishra) on 20/06/17.
 */


public class MainActivity extends AppCompatActivity {
    Button login;
    SimpleFacebook fb;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        imageView = (ImageView)findViewById(R.id.imageView);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        fb= SimpleFacebook.getInstance(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fb.onActivityResult(requestCode,resultCode,data);
    }

    private void login(){
        OnLoginListener loginListener = new OnLoginListener() {
            @Override
            public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {



                OnProfileListener onProfileListener =new OnProfileListener() {
                    @Override
                    public void onComplete(Profile response) {
                        super.onComplete(response);


                        Toast.makeText(getApplicationContext(),response.getName().toString(),Toast.LENGTH_LONG).show();
                        Glide.with(getApplicationContext())
                                .load(response.getPicture().toString())
                                .error(android.R.drawable.stat_notify_error)
                                .into(imageView);

                    }

                    @Override
                    public void onException(Throwable throwable) {
                        super.onException(throwable);
                    }

                    @Override
                    public void onFail(String reason) {
                        super.onFail(reason);
                    }

                };
                Profile.Properties properties = new Profile.Properties.Builder()
                        .add(Profile.Properties.EMAIL)
                        .add(Profile.Properties.PICTURE)
                        .add(Profile.Properties.NAME)
                        .build();
                fb.getProfile(properties,onProfileListener);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onException(Throwable throwable) {

            }

            @Override
            public void onFail(String reason) {

            }
        };
        fb.login(loginListener);

    }

}