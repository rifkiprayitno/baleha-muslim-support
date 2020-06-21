package id.baleha.promuslim.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private Context context;

    public GridSpacingItemDecoration(Context context, int spanCount, int spacng, boolean includeEdge){
        this.context = context;
        this.spanCount = spanCount;
        this.spacing = dpToPx(spacng);
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        if(includeEdge){
            outRect.left = spacing  - getDistanceLeft(column);
            outRect.right = getDistanceRight(column);

            if(position < spanCount){
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
        } else {
            outRect.left = getDistanceLeft(column);
            outRect.right = spacing - getDistanceRight(column);
            if(position >= spanCount) outRect.top = spacing;
        }
    }

    private int getDistanceLeft(int column){
        return column*spacing/spanCount;
    }

    private int getDistanceRight(int column){
        return (column+1)*spacing/spanCount;
    }

    private int dpToPx(int dp){
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
