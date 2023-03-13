package com.test.a1.di

import android.app.Application
import com.test.a1.MainActivity
import com.test.a1.ui.screens.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: Application)

    fun inject(activity: MainActivity)

    fun inject(fragment: SplashFragment)

    fun inject(fragment: TableDetailFragment)

    fun inject(fragment: NewsFragment)

    fun inject(fragment: StatisticFragment)

    fun inject(fragment: OptionFragment)

    fun inject(fragment: MainFragment)

    fun inject(fragment: TablesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}
