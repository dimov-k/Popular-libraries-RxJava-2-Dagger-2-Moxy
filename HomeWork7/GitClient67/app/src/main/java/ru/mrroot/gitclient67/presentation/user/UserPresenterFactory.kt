package ru.mrroot.gitclient67.presentation.user

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface UserPresenterFactory {

    fun create(@Assisted("login") userLogin: String): UserPresenter

}