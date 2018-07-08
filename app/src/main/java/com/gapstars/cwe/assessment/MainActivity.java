package com.gapstars.cwe.assessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gapstars.cwe.assessment.api.ServiceClient;
import com.gapstars.cwe.assessment.model.JobItem;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity{

    private CompositeSubscription compositeSubscription;
    private List<JobItem> jobItems = new ArrayList<>();
    private DataAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DataAdapter(jobItems, R.layout.post_item);
        recyclerView.setAdapter(adapter);

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
                        true,
                        getSectionCallback(jobItems));
        recyclerView.addItemDecoration(sectionItemDecoration);
        compositeSubscription = new CompositeSubscription();
        getData();

    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<JobItem> jobItems) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || !(jobItems.get(position).date.date.subSequence(0,10)
                        .equals(jobItems.get(position - 1).date.date.subSequence(0,10)));
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return jobItems.get(position)
                        .date.date.subSequence(0,10);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }

    private void getData(){
        compositeSubscription.add(
                ServiceClient.getInstance().getStarredRepos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(result -> compositeSubscription.add(
                                Observable.from(result.getValues())
                                        .map(list -> list).subscribe(r -> {
                                    adapter.addAll(r);
                                })))
                        .subscribe()
        );
    }
}
