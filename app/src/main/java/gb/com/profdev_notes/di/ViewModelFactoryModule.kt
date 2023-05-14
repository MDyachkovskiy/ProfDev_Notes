package gb.com.profdev_notes.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import gb.com.profdev_notes.ui.viewmodel.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(
        viewModelProvideFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory
}
