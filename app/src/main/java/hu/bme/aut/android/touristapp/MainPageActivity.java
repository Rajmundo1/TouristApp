package hu.bme.aut.android.touristapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import hu.bme.aut.android.touristapp.ui.main.SectionsPagerAdapter;

public class MainPageActivity extends AppCompatActivity {



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


/*        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;*/
/*        recyclerView = findViewById(R.id.ContentRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new RecAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);*/




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}