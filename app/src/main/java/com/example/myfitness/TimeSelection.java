package com.example.myfitness;
import android.animation.ObjectAnimator;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import java.sql.Connection;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.MyFitness.png.R;

import java.sql.PreparedStatement;

public class TimeSelection extends AppCompatActivity {
    ImageView mOneMonthImg;
    ImageView mTwoMonthImg;
    String userEmail;
    boolean timePeriodOneSelected = false;
    boolean timePeriodTwoSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_selection);


         mOneMonthImg = findViewById(R.id.OneMonthimg);
         mTwoMonthImg = findViewById(R.id.TwoMonthimg);
         Button shiningButton;

        shiningButton = findViewById(R.id.shiningButton);
        shiningButton.setOnTouchListener(new ShiningButtonTouchListener());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("userEmail");
        }
        mOneMonthImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePeriodOneSelected = !timePeriodOneSelected; // Toggle the value
                // Set the image tint color based on the selection
                if (timePeriodOneSelected) {
                    mOneMonthImg.setColorFilter(Color.GREEN); // Change to your desired color
                } else {
                    mOneMonthImg.setColorFilter(Color.TRANSPARENT); // Remove the tint color
                }
            }
        });

        mTwoMonthImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePeriodTwoSelected = !timePeriodTwoSelected; // Toggle the value
                // Set the image tint color based on the selection
                if (timePeriodTwoSelected) {
                    mTwoMonthImg.setColorFilter(Color.GREEN); // Change to your desired color
                } else {
                    mTwoMonthImg.setColorFilter(Color.TRANSPARENT); // Remove the tint color
                }
            }
        });

        shiningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to store the data in the database, passing the boolean values
                storeTimeSelectionData(timePeriodOneSelected, timePeriodTwoSelected);
            }
        });

    }

    private void storeTimeSelectionData(boolean timePeriodOneSelected, boolean timePeriodTwoSelected) {
        try {
            // Establish the database connection
            ConnectionJAVA connectionJAVA = new ConnectionJAVA();
            Connection con = connectionJAVA.connectionclass();

            // Prepare the SQL statement
            String query = "UPDATE UserProfile SET timePeriodOne = ?, timePeriodTwo = ? WHERE Email = ?";
            PreparedStatement stmt = con.prepareStatement(query);

            // Set the parameter values
            stmt.setBoolean(1, timePeriodOneSelected);
            stmt.setBoolean(2, timePeriodTwoSelected);
            stmt.setString(3, userEmail);

            // Execute the SQL statement
            stmt.executeUpdate();

            // Close the statement and connection
            stmt.close();
            con.close();

            // Optionally, show a success message or perform any other actions

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that occur during database operations
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private class ShiningButtonTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                // Shining effect when the button is touched
                startShiningAnimation();
            } else if (action == MotionEvent.ACTION_UP) {
                // Reset the button color when touch is released
                resetButtonColor();
            }
            return false;
        }
    }

    private void startShiningAnimation() {
        Button shiningButton = findViewById(R.id.shiningButton);
        ObjectAnimator animator = ObjectAnimator.ofArgb(shiningButton, "textColor",
                Color.WHITE, Color.YELLOW, Color.WHITE);
        animator.setDuration(500);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }

    private void resetButtonColor() {
        Button shiningButton = findViewById(R.id.shiningButton);
        shiningButton.setTextColor(Color.WHITE);
    }


}

