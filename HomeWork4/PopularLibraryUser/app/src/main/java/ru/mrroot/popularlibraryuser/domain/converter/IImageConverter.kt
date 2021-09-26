package ru.mrroot.popularlibraryuser.domain.converter

import android.net.Uri
import io.reactivex.Single
import java.io.File

interface IImageConverter {

    /**
     * @param uriTargetImage Uri исходного изображения
     * @param toFile файл, в который записывается конвертированная картинка
     * @return Uri результирующего изображения
     */
    fun jpegToPng(uriTargetImage: Uri, toFile: File): Single<Uri>
}