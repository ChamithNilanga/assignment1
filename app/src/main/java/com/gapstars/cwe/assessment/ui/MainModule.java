package com.gapstars.cwe.assessment.ui;

import com.gapstars.cwe.assessment.di.ActivityScoped;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public abstract class MainModule {

    @Provides
    @ActivityScoped
    static CompositeSubscription providesCompositeSubscription() {
        return new CompositeSubscription();
    }
}
