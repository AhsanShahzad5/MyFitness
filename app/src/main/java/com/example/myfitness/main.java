package com.example.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.MyFitness.png.R;

public class main extends AppCompatActivity {

    ImageView mImageView;
    TextView mTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView =findViewById(R.id.imageView);
        mTextView3 =findViewById(R.id.textView3);

        int delayMillis = 3500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        }, delayMillis);
    }
}
