package ru.mrroot.gitclient67.presentation.users

import dagger.assisted.AssistedFactory

@AssistedFactory
interface UsersPresenterFactory {
    fun create(): UsersPresenter
}