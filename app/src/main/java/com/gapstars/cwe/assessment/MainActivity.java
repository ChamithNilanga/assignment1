package com.gapstars.cwe.assessment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.gapstars.cwe.assessment.api.ServiceClient;
import com.gapstars.cwe.assessment.model.JobItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private CompositeSubscription compositeSubscription;
    private List<JobItem> jobItems = new ArrayList<>();
    private DataAdapter adapter;
    private Unbinder unbinder;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.progress_layout)
    RelativeLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        compositeSubscription = new CompositeSubscription();
        if (isConnected()) {
            getData();
        } else {
            showError();
        }

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
                ServiceClient.getInstance().getStarredRepos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(result -> compositeSubscription.add(
                                Observable.from(result.getValues())
                                        .map(list -> list).subscribe(r -> {
                                            adapter.addAll(r);
                                            progressLayout.setVisibility(View.GONE);
                                        })))
                        .subscribe()
        );
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void showError(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(String.format(getString(R.string.alert)));
        alertDialog.setMessage(String.format(getString(R.string.error)));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,String.format(getString(R.string.ok)),
                (dialog, which) -> {
                    progressLayout.setVisibility(View.GONE);
                    dialog.dismiss();
                });
        alertDialog.show();
    }
}
