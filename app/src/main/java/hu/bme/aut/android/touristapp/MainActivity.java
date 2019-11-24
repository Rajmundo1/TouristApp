package hu.bme.aut.android.touristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hu.bme.aut.android.touristapp.model.User;
import hu.bme.aut.android.touristapp.sqlite.PersistentDataHelper;

public class MainActivity extends AppCompatActivity implements NewUserDialog.ExampleDialogListener {

    private LayoutInflater inflater;
    private LinearLayout loginLinearLayout;
    private Button newUserButton;

    private PersistentDataHelper dataHelper;

    @Override
    protected void onDestroy() {
        dataHelper.open();
        dataHelper.persistUser(dataHelper.restoreUser());
        dataHelper.persistContent(dataHelper.restoreContent());
        dataHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new PersistentDataHelper(this);
        dataHelper.open();


        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        loginLinearLayout = findViewById(R.id.loginLinearLayout);

        newUserButton = findViewById(R.id.BtnNewUser);

        newUserButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        restorePersistedUsers();

    }

    private void openDialog(){
        NewUserDialog newUserDialog = new NewUserDialog();
        newUserDialog.show(getSupportFragmentManager(), "New User");
    }

    private void restorePersistedUsers() {
        List<User> list = dataHelper.restoreUser();
        loginLinearLayout.removeAllViews();
        for (final User item: list) {
            View rowItem = inflater.inflate(R.layout.user_row, null);

            TextView userRowButton = rowItem.findViewById(R.id.user_row_button);
            userRowButton.setText(item.getUsername());
            userRowButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(MainActivity.this, MainPage.class);
                    intent.putExtra("username", item.getUsername());
                    startActivity(intent);

                }
            });

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


    @Override
    public void applyTexts(String username) {
        dataHelper.getUsers().add(new User (username));
        dataHelper.persistUser(dataHelper.getUsers());
        restorePersistedUsers();
    }
}

