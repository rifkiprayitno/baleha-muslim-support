package id.baleha.promuslim.view.main.fragments.others;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.baleha.promuslim.R;

public class OthersRecyclerAdapter extends RecyclerView.Adapter<OthersRecyclerViewHolder> {

    List<String> itemTitleData;
    ArrayList itemIconData;
    ArrayList itemClassDestination;
    Context context;

    public OthersRecyclerAdapter(List<String> itemTitleData, ArrayList itemIconData, ArrayList itemClassDestination, Context context) {
        this.itemTitleData = itemTitleData;
        this.itemIconData = itemIconData;
        this.itemClassDestination = itemClassDestination;
        this.context = context;
    }

    @NonNull
    @Override
    public OthersRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_others, parent, false);
        OthersRecyclerViewHolder viewHolder = new OthersRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OthersRecyclerViewHolder holder, final int position) {
        holder.itemTitle.setText(itemTitleData.get(position));
        holder.itemIcon.setImageResource((Integer) itemIconData.get(position));
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, (Class) itemClassDestination.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemIconData.size();
    }
}
