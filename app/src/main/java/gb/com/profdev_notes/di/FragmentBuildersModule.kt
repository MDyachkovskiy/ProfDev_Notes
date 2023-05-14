package gb.com.profdev_notes.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import gb.com.profdev_notes.ui.AddFragment
import gb.com.profdev_notes.ui.EditFragment
import gb.com.profdev_notes.ui.ListFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment

    @ContributesAndroidInjector
    abstract fun contributeAddFragment(): AddFragment

    @ContributesAndroidInjector
    abstract fun contributeEditFragment(): EditFragment
}