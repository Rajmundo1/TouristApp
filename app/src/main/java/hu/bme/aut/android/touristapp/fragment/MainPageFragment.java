package hu.bme.aut.android.touristapp.fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.bme.aut.android.touristapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainPageFragment extends Fragment {

    public MainPageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }
}
