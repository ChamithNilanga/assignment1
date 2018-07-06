package com.gapstars.cwe.assessment;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gapstars.cwe.assessment.model.JobItem;

import java.util.Collection;
import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private final List<JobItem> list;
    private final int rowLayout;

    public DataAdapter(List<JobItem> people, @LayoutRes int rowLayout) {
        this.list = people;
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
        JobItem person = list.get(position);
        holder.fullName.setText(person.title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullName;

        public ViewHolder(View view) {
            super(view);
            fullName = (TextView) view.findViewById(R.id.tv_text);
        }
    }
}
