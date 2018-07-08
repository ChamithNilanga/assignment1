package com.gapstars.cwe.assessment.di;

import com.gapstars.cwe.assessment.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity bindMainActivity();
}
