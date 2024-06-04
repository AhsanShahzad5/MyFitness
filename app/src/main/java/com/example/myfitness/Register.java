package com.example.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.MyFitness.png.R;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class Register extends AppCompatActivity {

    Connection connect;
    String ConnectionResult = "";

    ConnectionJAVA connectionJAVA;
    public static final String TAG = "TAG";
    ImageView mRegLogo;
    TextView mTextRegister2, mLinkLogin2;
    EditText mFirstName, mLastName, mUserEmail, mPassWord, mConfirmPassWord;
    Button mNextRegPage = findViewById(R.id.nextRegPage);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegLogo = findViewById(R.id.regLogo);
        mTextRegister2 = findViewById(R.id.textRegister2);
        mLinkLogin2 = findViewById(R.id.linkLogin2);
        mFirstName = findViewById(R.id.firstName);
        mLastName = findViewById(R.id.lastName);
        mUserEmail = findViewById(R.id.userEmail);
        mPassWord = findViewById(R.id.passWord);
        mConfirmPassWord = findViewById(R.id.confirmPassWord);

        mLinkLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }


        });
        connectionJAVA = new ConnectionJAVA();

        mNextRegPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = mUserEmail.getText().toString().trim();
                String passWord = mPassWord.getText().toString().trim();
                String confirmPassWord = mConfirmPassWord.getText().toString().trim();
                final String firstName = mFirstName.getText().toString();
                final String lastName = mLastName.getText().toString();

                if (TextUtils.isEmpty(userEmail)) {
                    mUserEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(firstName)) {
                    mFirstName.setError("First Name is required");
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    mLastName.setError("Last Name is required");
                    return;
                }

                if (TextUtils.isEmpty(passWord)) {
                    mPassWord.setError("password is required");
                    return;
                }

                if (TextUtils.isEmpty(confirmPassWord)) {
                    mConfirmPassWord.setError("Confirm password is required");
                    return;
                }

                if (!passWord.equals(confirmPassWord)) {
                    mConfirmPassWord.setError("Passwords do not match");
                    return;
                }
                storeUserData(firstName, lastName, userEmail, passWord);
                Intent intent = new Intent(Register.this, Register2.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(new Intent(getApplicationContext(), Register2.class));

            }
        });

    }
    private void storeUserData(String firstName, String lastName, String userEmail, String passWord) {
        try {
            // Establish the database connection
            Connection con = connectionJAVA.connectionclass();

            // Prepare the SQL statement
            String query = "INSERT INTO UserProfile (userEmail, passWord, firstName, lastName) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);

            // Set the parameter values
            stmt.setString(1, userEmail);
            stmt.setString(2, passWord);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);

            // Execute the SQL statement
            stmt.executeUpdate();

            // Close the statement and connection
            stmt.close();
            con.close();

            // Optionally, show a success message or perform any other actions

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that occur during database operations
        }
    }
}