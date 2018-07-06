package com.gapstars.cwe.assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gapstars.cwe.assessment.api.Api;
import com.gapstars.cwe.assessment.api.GitHubClient;
import com.gapstars.cwe.assessment.model.Data;
import com.gapstars.cwe.assessment.model.Example;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements Callback<Data>{

    private CompositeSubscription compositeSubscription;
    private List<Example> exampleList = new ArrayList<>();
    private HeadersAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new HeadersAdapter();
        //adapter.add("Animals below!");
        //adapter.addAll(exampleList);
        //recyclerView.setAdapter(new PersonAdapter(exampleList, R.layout.list_item));
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        recyclerView.addItemDecoration(new DividerDecoration(this));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

//        RecyclerSectionItemDecoration sectionItemDecoration =
//                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),
//                        true,
//                        getSectionCallback(exampleList));
//        recyclerView.addItemDecoration(sectionItemDecoration);


        compositeSubscription = new CompositeSubscription();
//        try {
//            Api.getData(MainActivity.this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        getData();

    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<Example> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || people.get(position)
                        .date.date
                        .charAt(0) != people.get(position - 1)
                        .date.date
                        .charAt(0);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return people.get(position)
                        .date.date
                        .subSequence(0,
                                1);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }

    @Override
    public void onResponse(Call<Data> call, Response<Data> response) {
        System.out.print("dfg");
    }

    @Override
    public void onFailure(Call<Data> call, Throwable t) {
        System.out.print("dfg");
    }


    private void getData(){
        compositeSubscription.add(
                GitHubClient.getInstance().getStarredRepos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result ->{
                    compositeSubscription.add(
                            Observable.from(result.getValues())
                            .map(new Func1<ArrayList<Example>, ArrayList<Example>>() {
                                ArrayList<Example> examples = new ArrayList<>();
                                @Override
                                public ArrayList<Example> call(ArrayList<Example> list) {
                                    examples.addAll(list);
                                    return examples;
                                }
                            }).subscribe(r -> {
                                Log.d("fg", "In onNext()");
                                adapter.addAll(r);
                                //adapter.addAll(getDummyDataSet());
                            }));
                })
                .subscribe()
        );
    }

//    private String[] getDummyDataSet() {
//        return getResources().getStringArray(R.array.animals);
//    }
//
//    private void getStarredRepos() {
//        Observer<Data> observer = new Observer<Data>() {
//            @Override
//            public void onCompleted() {
//                Log.d("fg", "In onCompleted()");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//                Log.d("fg", "In onError()");
//            }
//
//            @Override
//            public void onNext(Data gitHubRepos) {
//                Log.d("fg", "In onNext()");
//                //adapter.setGitHubRepos(gitHubRepos);
//
//                Observable<ArrayList<Example>> observable = Observable.from(gitHubRepos.getValues())
//                        .map(new Func1<ArrayList<Example>, ArrayList<Example>>() {
//                            ArrayList<Example> examples = new ArrayList<>();
//                            @Override
//                            public ArrayList<Example> call(ArrayList<Example> list) {
//                                examples.addAll(list);
//                                return examples;
//                            }
//                        });
//
//                Subscription mSubscription = observable.subscribe(new Action1<ArrayList<Example>>() {
//                    @Override
//                    public void call(ArrayList<Example> integer) {
//                        Log.d("fg", "In onNext()");
//                        //textView.append(String.valueOf(integer) + "\n");
//                    }
//                });
//            }
//        };



//        GitHubClient.getInstance().getStarredRepos()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);


//
//        subscription = GitHubClient.getInstance()
//                .getStarredRepos()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
    }





//        Observable.defer(() -> Observable.just(ItemManager.getItems())
//        .subscribeOn(Schedulers.io())
//        .flatMapIterable(items -> items)
//        .filter(item -> item.isValid())
//        .toList();
