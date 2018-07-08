package com.gapstars.cwe.assessment.di;

import android.app.Application;

import com.gapstars.cwe.assessment.JobApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidInjectionModule.class,ApplicationModule.class,ActivityBuilder.class,ApiModule.class})
public interface ApplicationComponent extends AndroidInjector<JobApplication>{
    @Component.Builder
    interface Builder{
        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}
