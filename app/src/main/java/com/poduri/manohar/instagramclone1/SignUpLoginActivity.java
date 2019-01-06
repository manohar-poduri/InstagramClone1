package com.poduri.manohar.instagramclone1;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUsernameSignup,edtPasswordSignup,edtUsernameLogin,edtPasswordLogin;
    private Button btnSignUp,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_login_activity);

        edtUsernameSignup = findViewById(R.id.edtUsernameSignup);
        edtPasswordSignup = findViewById(R.id.edtPasswordSignup);
        edtUsernameLogin = findViewById(R.id.edtUsernameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnSignUp = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();

                appUser.setUsername(edtUsernameSignup.getText().toString());
                appUser.setPassword(edtPasswordSignup.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(SignUpLoginActivity.this, appUser.get("Username") + "is signed up successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }
                        else {

                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUsernameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("Username") + "is logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });
    }
}
