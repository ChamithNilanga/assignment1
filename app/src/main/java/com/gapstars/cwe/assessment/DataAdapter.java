package com.gapstars.cwe.assessment;

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

    private final List<JobItem> list;
    private final int rowLayout;

    public DataAdapter(List<JobItem> jobItems, @LayoutRes int rowLayout) {
        this.list = jobItems;
        this.rowLayout = rowLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
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
        holder.textViewRate.setText("€ "+jobItem.maxPossibleEarningsHour+"/u");
        holder.textViewName.setText(jobItem.title);
        holder.distance.setText(jobItem.distance+" km");
        holder.ratingBar.setRating((float) jobItem.client.rating.average);
        holder.textViewReviews.setText(jobItem.client.rating.count+" reviews");
        holder.otherText.setText(jobItem.openPositions + "open position - " + jobItem.shifts.get(0).startTime+ " ("+jobItem.shifts.get(0).duration+"u)");
        holder.textViewTempersNeeded.setText(jobItem.shifts.get(0).tempersNeeded+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.textViewRate) TextView textViewRate;
        @BindView(R.id.textViewName) TextView textViewName;
        @BindView(R.id.distance) TextView distance;
        @BindView(R.id.ratingBar) RatingBar ratingBar;
        @BindView(R.id.textViewReviews) TextView textViewReviews;
        @BindView(R.id.otherText) TextView otherText;
        @BindView(R.id.textViewTempersNeeded) TextView textViewTempersNeeded;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
