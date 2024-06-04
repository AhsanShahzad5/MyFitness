package com.example.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.MyFitness.png.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Register2 extends AppCompatActivity {
    ConnectionJAVA connectionJAVA;
    ImageView mReg2Logo,mImageView3,mImageView4;
    TextView mTextRegister,mLinkLogin,mBMI;
    EditText mWeight,mHeight;
    RadioButton mBulking,mCutting;
    Button mRegBtn;
    double bmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mReg2Logo =findViewById(R.id.reg2Logo);
        mImageView3 =findViewById(R.id.imageView3);
        mImageView4 =findViewById(R.id.imageView4);
        mTextRegister =findViewById(R.id.textRegister);
        mLinkLogin =findViewById(R.id.linkLogin);
        mBMI =findViewById(R.id.BMI);
        mWeight =findViewById(R.id.weight);
        mHeight =findViewById(R.id.height);
        mBulking =findViewById(R.id.bulking);
        mCutting =findViewById(R.id.cutting);
        mRegBtn =findViewById(R.id.regBtn);

        connectionJAVA = new ConnectionJAVA();
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered data
                final String userEmail = getIntent().getStringExtra("userEmail");
                float weight = Float.parseFloat(mWeight.getText().toString().trim());
                float height = Float.parseFloat(mHeight.getText().toString().trim());
                boolean cutting = mCutting.isChecked();
                boolean bulking = mBulking.isChecked();
                double bmi = calculateBMI(weight, height);
                mBMI.setText("BMI calculated is " + bmi);
                // Call the method to store the additional data in the database
                storeAdditionalData(userEmail, weight, height, cutting, bulking);
                startActivity(new Intent(getApplicationContext(), TimeSelection.class));

                // Optionally, show a success message or perform any other actions

                // Finish the activity and return to the previous screen
                finish();
            }
        });
    }

    public double calculateBMI(double weightKg, double heightCm) {
        double heightM = heightCm / 100; // Convert height from cm to meters
        return weightKg / (heightM * heightM);
    }


    private void storeAdditionalData(String userEmail, float weight, float height, boolean cutting, boolean bulking) {
        try {
            // Establish the database connection
            Connection con = connectionJAVA.connectionclass();

            // Prepare the SQL statement
            String query = "UPDATE UserProfile SET weight = ?, height = ?, bmi = ?, cutting = ?, bulking = ? WHERE userEmail = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            // Set the parameter values
            stmt.setFloat(1, weight);
            stmt.setFloat(2, height);
            stmt.setDouble(3, bmi);
            stmt.setBoolean(4, cutting);
            stmt.setBoolean(5, bulking);
            stmt.setString(6, userEmail);

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


