package id.baleha.promuslim.view.main.fragments.asma;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import id.baleha.promuslim.model.asma.DataItem;

public class RecyclerAsmaAdapter extends RecyclerView.Adapter<RecyclerAsmaViewHolder> {

    private List<DataItem> asmaList;
    private int rowLayout;
    private Context context;

    public RecyclerAsmaAdapter(List<DataItem> asmaList, int rowLayout, Context context){
        this.asmaList = asmaList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAsmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(rowLayout, parent, false);
        return new RecyclerAsmaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAsmaViewHolder holder, int position) {
        DataItem asmaItem = asmaList.get(position);
        Log.d("RLOG", String.valueOf(asmaItem.getNumber()));
        try {
            holder.tvNumber.setText(String.valueOf(asmaItem.getNumber()));
            holder.tvTransliteration.setText(asmaItem.getTransliteration());
            holder.tvName.setText(Html.fromHtml(asmaItem.getName()));
            holder.tvMeaning.setText(asmaItem.getEn().getMeaning());
        } catch (Exception e){
            e.printStackTrace();
            Log.d("RLOG", "FAILED: "+e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return asmaList.size();
    }
}
