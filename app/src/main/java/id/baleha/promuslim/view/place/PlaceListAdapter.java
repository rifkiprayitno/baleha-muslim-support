package id.baleha.promuslim.view.place;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.libraries.places.api.model.Place;

import java.util.List;

import id.baleha.promuslim.R;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {

private List<Place> placesList;
private Context context;

public PlaceListAdapter(List<Place> list, Context ctx) {
        placesList = list;
        context = ctx;
        }

@Override
public int getItemCount() {
        return placesList.size();
        }

@Override
public PlaceListAdapter.ViewHolder
        onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_place, parent, false);

    PlaceListAdapter.ViewHolder viewHolder =
        new PlaceListAdapter.ViewHolder(view);
        return viewHolder;
        }


@Override
public void onBindViewHolder(PlaceListAdapter.ViewHolder holder, int position) {
final int itemPos = position;
final Place place = placesList.get(position);
        holder.name.setText(place.getName());
        holder.address.setText(place.getAddress());
//        holder.icon.setImageResource(place.getAddress());

//        if (place.getRating() > -1) {
//            holder.ratingBar.setNumStars((int) place.getRating());
//        } else {
//            holder.ratingBar.setVisibility(View.GONE);
//        }

        }

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView name;
    public TextView address;

    public Button viewOnMap;

    public ViewHolder(View view) {

        super(view);

        name = view.findViewById(R.id.tv_place_title);
        address = view.findViewById(R.id.tv_place_desc);
        icon = view.findViewById(R.id.iv_icon_place);
    }
}
}
