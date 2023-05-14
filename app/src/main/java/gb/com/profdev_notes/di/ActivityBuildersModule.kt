package gb.com.profdev_notes.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import gb.com.profdev_notes.ui.MainActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelModule::class,
            FragmentBuildersModule::class
        ])
    abstract fun contributeMainActivity(): MainActivity
}