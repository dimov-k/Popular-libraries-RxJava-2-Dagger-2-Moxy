package ru.mrroot.popularlibraryuser.domain.converter

import android.content.Context

object ImageConverterFactory {

    fun create(context: Context): IImageConverter = ImageConverterImpl(context)
}