package com.test.a1.di

import androidx.lifecycle.ViewModel
import com.test.a1.ui.viewmodels.AttackDefenceViewModel
import com.test.a1.ui.viewmodels.NewsVewModel
import com.test.a1.ui.viewmodels.OptionsViewModel
import com.test.a1.ui.viewmodels.TournamentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TournamentViewModel::class)
    fun bindTournamentViewModel(viewModel: TournamentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsVewModel::class)
    fun bindNewsViewModel(viewModel: NewsVewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AttackDefenceViewModel::class)
    fun bindAttackDefenceViewModel(viewModel: AttackDefenceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OptionsViewModel::class)
    fun bindOptionsViewModel(viewModel: OptionsViewModel): ViewModel
}
