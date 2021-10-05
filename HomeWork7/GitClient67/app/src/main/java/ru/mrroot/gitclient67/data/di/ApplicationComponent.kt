package ru.mrroot.gitclient67.data.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.mrroot.gitclient67.App
import ru.mrroot.gitclient67.data.di.modules.ApiModule
import ru.mrroot.gitclient67.data.di.modules.StorageModule
import ru.mrroot.gitclient67.data.di.modules.UIModule
import ru.mrroot.gitclient67.data.di.modules.UsersModule
import ru.mrroot.gitclient67.scheduler.Schedulers
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        UIModule::class,
        UsersModule::class,
        ApiModule::class,
        StorageModule::class]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): ApplicationComponent

    }

}