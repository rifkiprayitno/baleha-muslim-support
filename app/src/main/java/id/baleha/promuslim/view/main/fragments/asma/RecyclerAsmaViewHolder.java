package id.baleha.promuslim.view.main.fragments.asma;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.baleha.promuslim.R;

public class RecyclerAsmaViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_number)public TextView tvNumber;
    @BindView(R.id.tv_transliteration)TextView tvTransliteration;
    @BindView(R.id.tv_name)TextView tvName;
    @BindView(R.id.tv_meaning)TextView tvMeaning;

    public RecyclerAsmaViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
