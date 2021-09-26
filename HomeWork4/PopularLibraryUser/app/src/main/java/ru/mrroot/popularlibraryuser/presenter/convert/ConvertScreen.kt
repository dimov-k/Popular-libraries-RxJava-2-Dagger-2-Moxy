package ru.mrroot.popularlibraryuser.presenter.convert

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ConvertScreen() {

    fun create(): Screen = FragmentScreen { ConvertFragment.newInstance() }
}