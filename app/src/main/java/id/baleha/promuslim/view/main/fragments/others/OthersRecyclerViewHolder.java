package id.baleha.promuslim.view.main.fragments.others;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.baleha.promuslim.R;

public class OthersRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView itemTitle;
    ImageView itemIcon;
    RelativeLayout itemLayout;

    public OthersRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        itemLayout = itemView.findViewById(R.id.ll_other_item);
        itemTitle = itemView.findViewById(R.id.other_item_tv_title);
        itemIcon = itemView.findViewById(R.id.other_item_iv_icon);
    }


}
