package com.shashank.platform.fancyloginpage;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
public class LoginPage extends AppCompatActivity {
    TextView signin,signup,signin_signup_txt;
    CircleImageView circleImageView;
    Button signin_signup_btn;
    EditText email,password;
    private String userEmail=null,userPassword=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        signin_signup_txt = findViewById(R.id.signin_signup_txt);
        circleImageView = findViewById(R.id.circleImageView);
        signin_signup_btn = findViewById(R.id.signin_signup_btn);
        signin_signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        UserSignIn();
            }
        });
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin.setTextColor(Color.parseColor("#FFFFFF"));
                signin.setBackgroundColor(Color.parseColor("#FF2729C3"));
                signup.setTextColor(Color.parseColor("#FF2729C3"));
                signup.setBackgroundResource(R.drawable.bordershape);
                circleImageView.setImageResource(R.drawable.sigin_boy_img);
                signin_signup_txt.setText("Sign In");
                signin_signup_btn.setText("Sign In");
                signin_signup_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    UserSignIn();
                    }
                });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup.setTextColor(Color.parseColor("#FFFFFF"));
                signup.setBackgroundColor(Color.parseColor("#FF2729C3"));
                signin.setTextColor(Color.parseColor("#FF2729C3"));
                signin.setBackgroundResource(R.drawable.bordershape);
                circleImageView.setImageResource(R.drawable.sigup_boy_img);
                signin_signup_txt.setText("Sign Up");
                signin_signup_btn.setText("Sign Up");
                email.getText().clear();
                password.getText().clear();
                signin_signup_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userEmail = email.getText().toString();
                        userPassword = password.getText().toString();
                        if (!userEmail.isEmpty() && !userPassword.isEmpty()){
                            getSharedPreferences("Email", MODE_PRIVATE).edit().putString("UserEmail", userEmail).apply();
                            getSharedPreferences("Password", MODE_PRIVATE).edit().putString("UserPassword", userPassword).apply();
                            Toast.makeText(LoginPage.this, "Welcome "+userEmail, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginPage.this,MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(LoginPage.this, "name or password is empty ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void UserSignIn() {
        String inputEmail=email.getText().toString();
        String inputPassword=password.getText().toString();
        userEmail=getSharedPreferences("Email", MODE_PRIVATE).getString("UserEmail",null);
        userPassword=getSharedPreferences("Password",MODE_PRIVATE).getString("UserPassword",null);
        if(!inputEmail.isEmpty()&&!inputPassword.isEmpty()){
            if(inputEmail.equals(userEmail)&&inputPassword.equals(userPassword)){
                Toast.makeText(this, "Welcome "+inputEmail, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginPage.this,MainActivity.class));
                finish();
            }else{
                Toast.makeText(LoginPage.this, "name or password is not correct ", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginPage.this, "namre or password is empty ", Toast.LENGTH_SHORT).show();
        }
    }
}
