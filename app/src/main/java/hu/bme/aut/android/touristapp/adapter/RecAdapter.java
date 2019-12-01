package hu.bme.aut.android.touristapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hu.bme.aut.android.touristapp.R;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder>{


    @NonNull
    @Override
    public RecAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.MyViewHolder holder, int position) {
        holder.country.setText("CUCKFORNIA");
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        ImageView visited;
        TextView country;
        TextView city;
        TextView description;


        public MyViewHolder(View v) {
            super(v);

            visited = v.findViewById(R.id.visited);
            country = v.findViewById(R.id.country);
            city = v.findViewById(R.id.city);
            description = v.findViewById(R.id.description);
        }
    }
}
