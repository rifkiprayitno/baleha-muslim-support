package id.baleha.promuslim.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class SkeletonUtils {

    //TODO add skeleton
    public static int getSkeletonRowCount(Context context, int skeletonRowHeight) {
        int pxHeight = getDeviceHeight(context);
//        int skeletonRowHeight = (int) getResources().getDimension(R.dimen.row_layout_height); //converts to pixel
        return (int) Math.ceil(pxHeight / skeletonRowHeight);
    }

    //TODO add skeleton
    public static int getDeviceHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }

    //TODO add skeleton
    public static void showSkeleton(Context context, LayoutInflater inflater, ShimmerLayout shimmer, LinearLayout skeletonLayout, int itemSkeleton, int skeletonHeight, boolean show) {

        if (show) {

            skeletonLayout.removeAllViews();

            int skeletonRows = getSkeletonRowCount(context, skeletonHeight);
            for (int i = 0; i <= skeletonRows; i++) {
                ViewGroup rowLayout = (ViewGroup) inflater.inflate(itemSkeleton, null);
                skeletonLayout.addView(rowLayout);
            }
            shimmer.setVisibility(View.VISIBLE);
            shimmer.startShimmerAnimation();
            skeletonLayout.setVisibility(View.VISIBLE);
            skeletonLayout.bringToFront();
        } else {
            shimmer.stopShimmerAnimation();
            shimmer.setVisibility(View.GONE);
        }
    }

    //TODO add skeleton
//    public void animateReplaceSkeleton(final View listView) {
    public static void animateReplaceSkeleton(final Context context, final LayoutInflater inflater, final ShimmerLayout shimmer, final LinearLayout skeletonLayout, final int itemSkeleton, final int skeletonHeight) {
        skeletonLayout.setVisibility(View.VISIBLE);
        skeletonLayout.setAlpha(0f);
        skeletonLayout.animate().alpha(1f).setDuration(200).start();

        skeletonLayout.animate().alpha(0f).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
                showSkeleton(context, inflater, shimmer, skeletonLayout, itemSkeleton, skeletonHeight, false);
            }
        }).start();

    }
}
