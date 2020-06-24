package id.baleha.promuslim.view.main.fragments.salat;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.baleha.promuslim.model.praytime.PrayTimeResponse;

public class PrayTimeAdapter extends RecyclerView.Adapter<PrayTimeViewHolder> {

    private List<PrayTimeResponse> prayTimeList;
    private int rowLayout;
    private Context context;

    public PrayTimeAdapter(List<PrayTimeResponse> prayTimeList, int rowLayout, Context context) {
        this.prayTimeList = prayTimeList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public PrayTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PrayTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrayTimeViewHolder holder, int position) {
        try {
            holder.prayTimetime.setText(prayTimeList.get(position).getData().getTimings().getAsr());
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return prayTimeList.size();
    }
}
