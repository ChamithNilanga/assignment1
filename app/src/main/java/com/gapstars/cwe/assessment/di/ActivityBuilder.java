package com.gapstars.cwe.assessment.di;

import com.gapstars.cwe.assessment.ui.MainActivity;
import com.gapstars.cwe.assessment.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity bindMainActivity();
}
