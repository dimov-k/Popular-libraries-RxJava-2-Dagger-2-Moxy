package ru.mrroot.gitclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.mrroot.gitclient.mvp.model.entity.UserRepository

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryView : MvpView {
    fun fillFields(repo: UserRepository)
}
