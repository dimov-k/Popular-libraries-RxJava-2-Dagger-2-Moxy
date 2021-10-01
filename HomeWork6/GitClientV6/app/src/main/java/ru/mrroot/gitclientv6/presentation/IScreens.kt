package ru.mrroot.gitclientv6.presentation

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(textLogin: String): Screen
}