package hu.bme.aut.android.touristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater inflater;
    private LinearLayout loginLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowItem = inflater.inflate(R.layout.user_row, null);
        View rowItem1 = inflater.inflate(R.layout.user_row, null);
        View rowItem2 = inflater.inflate(R.layout.user_row, null);
        View rowItem3 = inflater.inflate(R.layout.user_row, null);
        View rowItem4 = inflater.inflate(R.layout.user_row, null);
        View rowItem5 = inflater.inflate(R.layout.user_row, null);
        View rowItem6 = inflater.inflate(R.layout.user_row, null);
        View rowItem7 = inflater.inflate(R.layout.user_row, null);
        View rowItem8 = inflater.inflate(R.layout.user_row, null);
        View rowItem9 = inflater.inflate(R.layout.user_row, null);
        View rowItem0 = inflater.inflate(R.layout.user_row, null);
        View rowItem11 = inflater.inflate(R.layout.user_row, null);
        View rowItem12 = inflater.inflate(R.layout.user_row, null);
        View rowItem13 = inflater.inflate(R.layout.user_row, null);
        View rowItem14 = inflater.inflate(R.layout.user_row, null);



        TextView userRowText = rowItem.findViewById(R.id.user_row_textView);
        userRowText.setText("Testing");

        loginLinearLayout = findViewById(R.id.loginLinearLayout);

        loginLinearLayout.addView(rowItem);
        loginLinearLayout.addView(rowItem1);
        loginLinearLayout.addView(rowItem2);
        loginLinearLayout.addView(rowItem3);
        loginLinearLayout.addView(rowItem4);
        loginLinearLayout.addView(rowItem5);
        loginLinearLayout.addView(rowItem6);
        loginLinearLayout.addView(rowItem7);
        loginLinearLayout.addView(rowItem8);
        loginLinearLayout.addView(rowItem9);
        loginLinearLayout.addView(rowItem0);

        loginLinearLayout.addView(rowItem11);
        loginLinearLayout.addView(rowItem12);
        loginLinearLayout.addView(rowItem13);
        loginLinearLayout.addView(rowItem14);
    }
}
