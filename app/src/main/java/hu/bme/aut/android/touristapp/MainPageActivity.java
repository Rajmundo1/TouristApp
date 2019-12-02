package hu.bme.aut.android.touristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.android.touristapp.Dialogs.NewPlaceDialog;
import hu.bme.aut.android.touristapp.adapter.RecAdapter;
import hu.bme.aut.android.touristapp.model.Content;
import hu.bme.aut.android.touristapp.sqlite.PersistentDataHelper;

public class MainPageActivity extends AppCompatActivity implements NewPlaceDialog.ExampleDialogListener {

    private RecyclerView recyclerView;
    private RecAdapter adapter;

    String username;

    private PersistentDataHelper database;

    FILTER myfilter = FILTER.ALL;


    public enum FILTER {
        ALL, VOLTAM_MAR, WANT_TO_GO, FAVORIT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        username = getIntent().getStringExtra("username");
        TextView tvUser = findViewById(R.id.tv_user);
        tvUser.setText(username);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPlaceDialog newPlaceDialog = new NewPlaceDialog();
                newPlaceDialog.show(getSupportFragmentManager(), "New Place");
            }
        });


        Button all = findViewById(R.id.btn_all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfilter = FILTER.ALL;
                restorePersistedContent();
            }
        });


        Button favorite = findViewById(R.id.btn_favorite);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfilter = FILTER.FAVORIT;
                restorePersistedContent();
            }
        });


        Button voltam = findViewById(R.id.btn_voltam);
        voltam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfilter = FILTER.VOLTAM_MAR;
                restorePersistedContent();
            }
        });


        Button wanttogo = findViewById(R.id.btn_wanttogo);
        wanttogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfilter = FILTER.WANT_TO_GO;
                restorePersistedContent();
            }
        });


        database = new PersistentDataHelper(this);
        database.open();

        initRecyclerView();

        restorePersistedContent();


    }


    private void initRecyclerView() {
        recyclerView = findViewById(R.id.MainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecAdapter(this);

        recyclerView.setAdapter(adapter);
    }

    private void restorePersistedContent() {
        List<Content> list = database.restoreContent();

        adapter.update(new ArrayList<Content>());

        //ALL, VOLTAM_MAR, WANT_TO_GO, FAVORIT
        for (Content item : list) {
            if(item.getUsername().equals(username))
            {
                switch(myfilter){
                    case ALL:
                        adapter.addItem(item);
                        break;

                    case FAVORIT:
                        if(item.isIsfavorite() == 1)
                            adapter.addItem(item);
                        break;

                    case VOLTAM_MAR:
                        if(item.isIsvisited() == 1)
                            adapter.addItem(item);
                        break;

                    case WANT_TO_GO:
                        if(item.getDesireToVisit() == 1)
                            adapter.addItem(item);
                        break;

                    default:
                        break;
                }
            }

        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        database.open();
        database.persistUser(database.restoreUser());
        database.persistContent(database.restoreContent());
        database.close();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        database.open();
    }

    @Override
    protected void onPause() {
        database.persistUser(database.restoreUser());
        database.persistContent(database.restoreContent());
        database.close();
        super.onPause();
    }

    @Override
    public void addPlaceElement(Content item) {
        item.setUsername(username);
        boolean contains = false;
        List<Content> list = database.restoreContent();

        for(Content i: list){
            if(i.equals(item))
                contains = true;
        }

        if(!contains)
            list.add(item);
        else{
            Snackbar.make(findViewById(R.id.MainRecyclerView), "Item already added, can't add two indentical item" , Snackbar.LENGTH_SHORT)
                    .show();
        }

        database.persistContent(list);

        restorePersistedContent();
    }

    public void elementChanged (Content contentOld, Content contentNew){
        List<Content> list = database.restoreContent();
        List<Content> dummy = new ArrayList<>();

        for(Content item: list){

            if(item.equals(contentOld) && contentNew != null){

                dummy.add(contentNew);
            }
            else if(item.equals(contentOld) && contentNew == null){
                //Nothing happens
            }
            else
                dummy.add(item);
        }

        database.persistContent(dummy);

        restorePersistedContent();
    }

/*    public void addContentElement(String username) {
        database.getUsers().add(new User(username));
        database.persistUser(database.getUsers());
        restorePersistedUsers();
    }

    public void deleteContentElement(String username) {
        database.deleteUser(username);
        database.persistUser(database.getUsers());
        restorePersistedUsers();
    }*/
}


