package com.gapstars.cwe.assessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gapstars.cwe.assessment.api.Service;
import com.gapstars.cwe.assessment.model.JobItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @Inject
    CompositeSubscription compositeSubscription;
    private List<JobItem> jobItems = new ArrayList<>();
    private DataAdapter adapter;
    private Unbinder unbinder;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Inject
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        adapter = new DataAdapter(this, jobItems, R.layout.post_item);
        recyclerView.setAdapter(adapter);

        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(
                        getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
                        true,
                        getSectionCallback(jobItems));
        recyclerView.addItemDecoration(sectionItemDecoration);
        getData();

    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<JobItem> jobItems) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || !(jobItems.get(position).date.date.subSequence(0, 10)
                        .equals(jobItems.get(position - 1).date.date.subSequence(0, 10)));
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return jobItems.get(position)
                        .date.date.subSequence(0, 10);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
        unbinder.unbind();
    }

    private void getData() {
        compositeSubscription.add(
                service.getData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(result -> compositeSubscription.add(
                                Observable.from(result.getValues())
                                        .map(list -> list).subscribe(r -> adapter.addAll(r))))
                        .subscribe()
        );
    }
}
