package com.example.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MyFitness.png.R;

public class Login extends AppCompatActivity {

    ImageView mLoginLogo;
    TextView mTextLogin, mLinkRegister, mForgetPass;
    EditText mUserEmail, mPassWord;
    Button mLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginLogo=findViewById(R.id.loginLogo);
        mTextLogin =findViewById(R.id.textLogin);
        mLinkRegister =findViewById(R.id.linkRegister);
        mForgetPass =findViewById(R.id.forgotPass);
        mUserEmail=findViewById(R.id.userEmail);
        mLoginBtn = findViewById(R.id.loginBtn);
        mPassWord = findViewById(R.id.passWord);

        mLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUserEmail.getText().toString();
                String password = mPassWord.getText().toString();

                if (email.isEmpty()) {
                    mUserEmail.setError("Email is required");
                    mUserEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    mPassWord.setError("Password is required");
                    mPassWord.requestFocus();
                    return;
                }

                // Perform login authentication
                if (email.equals("example@example.com") && password.equals("password123")) {
                    // Successful authentication
                    startActivity(new Intent(getApplicationContext(), TimeSelection.class));
                    finish();
                } else {
                    // Invalid credentials
                    Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}