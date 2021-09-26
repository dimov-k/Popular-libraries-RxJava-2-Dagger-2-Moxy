package ru.mrroot.popularlibraryuser.presenter.convert

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.mrroot.popularlibraryuser.domain.converter.ImageConverterFactory
import ru.mrroot.popularlibraryuser.extensions.visible
import ru.mrroot.popularlibraryuser.scheduler.SchedulerFactory
import ru.mrroot.popularlibraryuser.ui.extensions.showSnakeBar
import ru.mrroot.popularlibraryuser.R
import ru.mrroot.popularlibraryuser.databinding.FragmentConvertBinding



class ConvertFragment : MvpAppCompatFragment(R.layout.fragment_convert), IConvertView {

    companion object {
        fun newInstance(): Fragment = ConvertFragment()
        private const val PERMISSIONS_READ = Manifest.permission.READ_EXTERNAL_STORAGE
        private const val PERMISSIONS_WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    }

    private val vb: FragmentConvertBinding by viewBinding()

    override fun showMessage(message: String) {
        vb.root.showSnakeBar(message)
    }

    private val presenter: ConvertPresenter by moxyPresenter {
        ConvertPresenter(
            imageConverter = ImageConverterFactory.create(requireActivity().application),
            schedulers = SchedulerFactory.create()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.convert_title)
        init()
    }

    /**
     * Начальная инициализация. Для кнопок навешиваются ClickListener-ы.
     * Делаются неактивными кнопки Конвертировать и Отмена
     */
    private fun init() {
        with(vb) {
            btnSelectImage.setOnClickListener { setImage() }
            btnConvert.setOnClickListener { convertStart() }
            btnCancel.setOnClickListener { convertCancel() }
            showLoading(false)
        }

        useConvertButtons(false)
    }

    /**
     * Отменить конвертирование
     */
    private fun convertCancel() {
        presenter.convertStop()
        showLoading(false)
    }

    /**
     * Начать конвертирование
     */
    private fun convertStart() {
        checkWriteFilesPermission()
    }

    /**
     * Выбрать изображения для конвертации
     */
    private fun setImage() {
        checkReadFilesPermission()
    }

    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                requireContext().contentResolver.openInputStream(uri)?.readBytes()?.let { data ->
                    presenter.onImageSelected(it)
                }
            }
        }

    override fun chooseImage() {
        imagePicker.launch("image/*")
    }

    /**
     * Отобразить исходный рисунок с помощью Glide
     * @param uri Uri исходного изображения
     */
    override fun showSelectedImage(uri: Uri?) {
        Glide.with(requireContext())
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.ic_img_def)
            .into(vb.targetImg)
    }

    /**
     * Отобразить конвертированный рисунок с помощью Glide
     * @param uri Uri конвертированного изображения
     */
    override fun showResultImage(uri: Uri?) {
        Glide.with(requireContext())
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .error(R.drawable.ic_img_def)
            .into(vb.completeImg)
    }

    override fun useConvertButtons(use: Boolean) {
        when (use) {
            true -> {
                vb.btnConvert.isEnabled = true
                vb.btnCancel.isEnabled = true
            }
            else -> {
                vb.btnConvert.isEnabled = false
                vb.btnCancel.isEnabled = false
            }
        }
    }

    override fun showLoading(isLoading: Boolean) {
        vb.progress.visible { isLoading }
    }

    /**
     * Проверить разрешение на чтение файлов
     */
    private fun checkReadFilesPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                PERMISSIONS_READ
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            presenter.selectImage()
        } else {
            if (shouldShowRequestPermissionRationale(PERMISSIONS_READ)) {
                Snackbar.make(
                    vb.root,
                    getString(R.string.need_permission_read_file),
                    Snackbar.LENGTH_LONG
                ).setAction(getString(R.string.grant))
                {
                    permissionRequestReadFile.launch(PERMISSIONS_READ)
                }.show()
            } else {
                permissionRequestReadFile.launch(PERMISSIONS_READ)
            }
        }
    }

    /**
     * Проверить разрешение на запись файлов. Хотя для записи на флешку у меня не спрашивало.
     */
    private fun checkWriteFilesPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                PERMISSIONS_WRITE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            presenter.convertStart()
        } else {
            if (shouldShowRequestPermissionRationale(PERMISSIONS_WRITE)) {
                Snackbar.make(
                    vb.root,
                    getString(R.string.need_permission_write_file),
                    Snackbar.LENGTH_LONG
                ).setAction(getString(R.string.grant))
                {
                    permissionRequestWriteFile.launch(PERMISSIONS_WRITE)
                }.show()
            } else {
                permissionRequestWriteFile.launch(PERMISSIONS_WRITE)
            }
        }
    }

    private val permissionRequestReadFile =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                presenter.selectImage()
            } else {
                showMessage(getString(R.string.permission_failed_to_read_files))
            }
        }

    private val permissionRequestWriteFile =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                presenter.convertStart()
            } else {
                showMessage(getString(R.string.permission_failed_to_write_files))
            }
        }
}