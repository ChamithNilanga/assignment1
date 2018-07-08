package com.gapstars.cwe.assessment.di;

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
