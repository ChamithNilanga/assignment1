package com.gapstars.cwe.assessment.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    @Singleton
    static Context providesContext(Application application){
        return application;
    }
}
