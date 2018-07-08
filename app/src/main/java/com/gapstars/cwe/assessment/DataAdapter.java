package com.gapstars.cwe.assessment;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gapstars.cwe.assessment.model.JobItem;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private final Context context;
    private final List<JobItem> list;
    private final int rowLayout;

    public DataAdapter(Context context, List<JobItem> jobItems, @LayoutRes int rowLayout) {
        this.context = context;
        this.list = jobItems;
        this.rowLayout = rowLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(rowLayout, parent, false);
        return new ViewHolder(view);
    }

    public void addAll(Collection<? extends JobItem> collection) {
        if (collection != null) {
            list.addAll(collection);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JobItem jobItem = list.get(position);
        Picasso.get().load(jobItem.client.photos.get(0).formats.get(0).cdnUrl).into(holder.imageView);
        holder.textViewRate.setText(String.format(context.getString(R.string.place_holder_rate),
                jobItem.maxPossibleEarningsHour));
        holder.textViewName.setText(jobItem.title);
        holder.distance.setText(String.format(context.getString(R.string.place_holder_distance),
                jobItem.distance));
        holder.ratingBar.setRating((float) jobItem.client.rating.average);
        holder.textViewReviews.setText(String.format(context.getString(R.string.place_holder_review),
                jobItem.client.rating.count));
        holder.otherText.setText(String.format(context.getString(R.string.place_holder_positions),
                jobItem.openPositions, jobItem.shifts.get(0).startTime, jobItem.shifts.get(0).duration));
        holder.textViewTempersNeeded.setText(String.format(context.getString(R.string.place_holder_tempers),
                jobItem.shifts.get(0).tempersNeeded));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textViewRate)
        TextView textViewRate;
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.distance)
        TextView distance;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;
        @BindView(R.id.textViewReviews)
        TextView textViewReviews;
        @BindView(R.id.otherText)
        TextView otherText;
        @BindView(R.id.textViewTempersNeeded)
        TextView textViewTempersNeeded;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
