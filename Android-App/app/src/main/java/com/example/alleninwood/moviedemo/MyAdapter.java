package com.example.alleninwood.moviedemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private LinkedList<Map<String, Object>> list;

    public MyAdapter(LinkedList<Map<String, Object>> list) {
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.title.setText(list.get(i).get("title").toString());
        viewHolder.title.setVisibility(View.VISIBLE);
        viewHolder.year.setText(list.get(i).get("year").toString());
        viewHolder.year.setVisibility(View.VISIBLE);
        viewHolder.director.setText(list.get(i).get("director").toString());
        viewHolder.director.setVisibility(View.VISIBLE);
        viewHolder.pic.setImageResource((int)list.get(i).get("picture"));
        viewHolder.pic.setVisibility(View.VISIBLE);
        LinkedList<String> gList = (LinkedList)list.get(i).get("genres");
        LinkedList<String> sList = (LinkedList)list.get(i).get("stars");
        for (int j = 0; j < gList.size(); ++ j) {
            viewHolder.genres.get(j).setText(gList.get(j));
            viewHolder.genres.get(j).setVisibility(View.VISIBLE);
        }
        for (int j = 0; j < sList.size(); ++ j) {
            viewHolder.stars.get(j).setText(sList.get(j));
            viewHolder.stars.get(j).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView year;
        public TextView director;
        public LinkedList<TextView> genres = new LinkedList<>();
        public LinkedList<TextView> stars = new LinkedList<>();
        public ImageView pic;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            year = itemView.findViewById(R.id.item_year);
            director = itemView.findViewById(R.id.item_director);
            genres.add((TextView)itemView.findViewById(R.id.item_genre1));
            genres.add((TextView)itemView.findViewById(R.id.item_genre2));
            genres.add((TextView)itemView.findViewById(R.id.item_genre3));
            stars.add((TextView)itemView.findViewById(R.id.item_star1));
            stars.add((TextView)itemView.findViewById(R.id.item_star2));
            stars.add((TextView)itemView.findViewById(R.id.item_star3));
            stars.add((TextView)itemView.findViewById(R.id.item_star4));
            stars.add((TextView)itemView.findViewById(R.id.item_star5));
            pic = itemView.findViewById(R.id.pic);
        }
    }
}