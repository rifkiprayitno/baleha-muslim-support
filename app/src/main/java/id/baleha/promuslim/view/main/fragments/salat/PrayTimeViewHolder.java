package id.baleha.promuslim.view.main.fragments.salat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.baleha.promuslim.R;

public class PrayTimeViewHolder extends RecyclerView.ViewHolder {

    TextView prayTimeName, prayTimetime;

    public PrayTimeViewHolder(@NonNull View itemView) {
        super(itemView);
        prayTimeName = (TextView)itemView.findViewById(R.id.tv_praytime_name);
        prayTimetime = (TextView) itemView.findViewById(R.id.tv_praytime_time);
    }

}
