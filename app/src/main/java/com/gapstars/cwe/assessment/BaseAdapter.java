package com.gapstars.cwe.assessment;

import android.support.v7.widget.RecyclerView;

import com.gapstars.cwe.assessment.model.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by cwe on 7/6/2018.
 */

public abstract class BaseAdapter <VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private ArrayList<Example> items = new ArrayList<>();

    public BaseAdapter() {
        setHasStableIds(true);
    }

    public void add(Example item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void add(int index, Example item) {
        items.add(index, item);
        notifyDataSetChanged();
    }

    public void addAll(Collection<? extends Example> collection) {
        if (collection != null) {
            items.clear();
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(Example... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(Example object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public Example getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
