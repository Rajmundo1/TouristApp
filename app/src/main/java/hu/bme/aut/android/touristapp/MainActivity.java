package hu.bme.aut.android.touristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hu.bme.aut.android.touristapp.model.Content;
import hu.bme.aut.android.touristapp.model.User;
import hu.bme.aut.android.touristapp.sqlite.PersistentDataHelper;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater inflater;
    private LinearLayout loginLinearLayout;

    private PersistentDataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new PersistentDataHelper(this);
        dataHelper.open();
        restorePersistedUsers();

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        loginLinearLayout = findViewById(R.id.loginLinearLayout);

    }

    private void restorePersistedUsers() {
        List<User> list = dataHelper.restoreUser();
        for (User item: list) {
            View rowItem = inflater.inflate(R.layout.user_row, null);

            TextView userRowText = rowItem.findViewById(R.id.user_row_textView);
            userRowText.setText(item.getUsername());

            loginLinearLayout.addView(rowItem);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataHelper.open();
    }

    @Override
    protected void onPause() {
        dataHelper.close();
        super.onPause();
    }


}

