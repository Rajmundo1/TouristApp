package hu.bme.aut.android.touristapp.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.android.touristapp.MainPageActivity;
import hu.bme.aut.android.touristapp.R;
import hu.bme.aut.android.touristapp.model.Content;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder>{

    private final List<Content> contents;
    MainPageActivity activity;

    ItemChangedListener listener;

    public RecAdapter(MainPageActivity activity) {
        this.contents = new ArrayList<>();
        this.activity = activity;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_row, parent, false);
        return new MyViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.MyViewHolder holder, int position) {
        Content item = contents.get(position);
        holder.country.setText(item.getCountry());
        holder.city.setText(item.getPlace());
        holder.description.setText(item.getDescription());

        if(item.isIsvisited() == 1) holder.visited.setImageDrawable(holder.circle);
        if(item.isIsvisited() == 0) holder.visited.setImageDrawable(holder.x);

        if(item.isIsfavorite() == 1) holder.favorite.setImageDrawable(holder.star);
        if(item.isIsfavorite() == 0) holder.favorite.setImageDrawable(holder.notStar);

        if(item.isIsvisited() == 1) holder.textVisited.setText("Visited");
        if(item.isIsvisited() == 0) holder.textVisited.setText("Not Visited");

        if(item.getDesireToVisit() == 1) holder.wantToGo.setImageDrawable(holder.Dwanttogo);
        if(item.getDesireToVisit() == 0) holder.wantToGo.setImageDrawable(holder.Dnotwanttogo);



        holder.item = item;
    }



    public void addItem(Content item) {
        contents.add(item);
        notifyItemInserted(contents.size() - 1);
    }

    public void update(List<Content> contentList) {
        contents.clear();
        contents.addAll(contentList);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return contents.size();
    }


    public interface ItemChangedListener {
        void ItemChanged(Content item);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        ImageView visited;
        TextView country;
        TextView city;
        TextView description;
        TextView textVisited;
        LinearLayout aLayout;


        ImageView favorite;
        ImageView wantToGo;

        Drawable circle;
        Drawable x;
        Drawable star;
        Drawable notStar;
        Drawable Dwanttogo;
        Drawable Dnotwanttogo;

        Content item;





        @SuppressLint("ResourceType")
        public MyViewHolder(View v, final MainPageActivity act) {
            super(v);
            circle = v.getResources().getDrawable(R.drawable.ic_check_circle);
            x = v.getResources().getDrawable(R.drawable.ic_cancel);
            star = v.getResources().getDrawable(R.drawable.ic_star_favorite);
            notStar = v.getResources().getDrawable(R.drawable.ic_star_notfavorite);
            Dwanttogo = v.getResources().getDrawable(R.drawable.ic_wanttogo);
            Dnotwanttogo = v.getResources().getDrawable(R.drawable.ic_notwanttogo);

            visited = v.findViewById(R.id.visited);
            country = v.findViewById(R.id.country);
            city = v.findViewById(R.id.city);
            description = v.findViewById(R.id.description);
            favorite = v.findViewById(R.id.star_favorite);
            textVisited = v.findViewById(R.id.textVisited);
            wantToGo = v.findViewById(R.id.im_wantToGo);

            aLayout = v.findViewById(R.id.recycleritem_id);


            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(item.isIsfavorite() == 1){
                        Content dummy = new Content(item);
                        dummy.setIsfavorite(0);
                        act.elementChanged(item, dummy);
                    }
                    else if(item.isIsfavorite() == 0){
                        Content dummy = new Content(item);
                        dummy.setIsfavorite(1);
                        act.elementChanged(item, dummy);

                    }
                }
            });


            visited.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(item.isIsvisited() == 1){
                        Content dummy = new Content(item);
                        dummy.setIsvisited(0);
                        act.elementChanged(item, dummy);
                    }
                    else if(item.isIsvisited() == 0){
                        Content dummy = new Content(item);
                        dummy.setIsvisited(1);
                        act.elementChanged(item, dummy);
                    }
                }
            });

            wantToGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(item.getDesireToVisit() == 1){
                        Content dummy = new Content(item);
                        dummy.setDesireToVisit(0);
                        act.elementChanged(item, dummy);
                    }
                    else if(item.getDesireToVisit() == 0){
                        Content dummy = new Content(item);
                        dummy.setDesireToVisit(1);
                        act.elementChanged(item, dummy);
                    }
                }
            });

            aLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final LinearLayout uRB = aLayout;
                    final Content cont = item;
                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    popup.inflate(R.menu.menu_delete);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.men_delete) {
                                act.elementChanged(cont, null);
                                return true;
                            }
                            return false;
                        }
                    });
                    popup.show();
                    return true;
                }
            });

        }
    }
}
