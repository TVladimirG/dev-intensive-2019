package ru.skillbranch.devintensive.ui.profile

import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile_constraint.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    lateinit var viewFields: Map<String, TextView>
    private lateinit var viewModel: ProfileViewModel
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_constraint)

        initViews() // viewFields - создаем Map.

        // Восстановим флаг isEditMode, если он был взведен.
        isEditMode = savedInstanceState?.getBoolean(IS_EDIT_MODE, false) ?: false
        // Нужно либо разблокировать поля для ввода данных или заблокировать их. И кнопку подсветить.
        showCurrentMode(isEditMode)


        // повесим слушатель для кнопки btn_edit
        btn_edit.setOnClickListener {
            if (isEditMode) saveProfileInfo()
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }

        btn_switch_theme.setOnClickListener {
            viewModel.switchTheme()
        }

        // Сохраняем или восстанавливаем данные
        initViewModel()

    }

    private fun initViewModel() {

        // Устарело. Так предлагали на курсе.
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        // Теперь так:
        // начиная с implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
        // Инициируем ViewModel для текущей активити. Указываем класс реализующий ViewModel - ProfileViewModel
        //   viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        // И добавляем любое кол-во LiveData в нашу ViewModel:
        //    viewModel.getProfileData()
        // и сразу подписываем на наблюдение и добавляем обновдление данных
        //    observe(this, Observer { updateUI(it) })
        // и сразу подписываем на обновление данных

        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
        viewModel.getThem().observe(this, Observer { updateTheme(it) })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(IS_EDIT_MODE, isEditMode)
    }

    private fun updateUI(profile: Profile?) {
        profile?.toMap().also {
            for ((k, v) in viewFields) {
                v.text = it?.get(k).toString()
            }
        }
    }

    private fun updateTheme(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
        //   delegate.localNightMode = mode
    }

    private fun initViews() {
        viewFields = mapOf(
            "nickName" to tv_nick_name,
            "rank" to tv_rank,
            "rating" to tv_rating,
            "respect" to tv_respect,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "repository" to et_repository
        )
    }

    private fun showCurrentMode(isEdit: Boolean) {

        // 1)!
        // отберем только те поля которые нужно разблокировать.
        val tmpSet = setOf("firstName", "lastName", "about", "repository")
        val info = viewFields.filter { tmpSet.contains(it.key) }

        // теперь переберем отобранные поля, и сделаем их активными:
        for ((_, v) in info) {
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if (isEdit) 255 else 0
        }

        // 2)!
        // уберем или покажем "глаз"
        ic_eye.visibility = if (isEdit) View.GONE else View.VISIBLE

        // 3)!
        // Уберем или покажем подсчет оставшихся символов
        wr_about.isCounterEnabled = isEdit

        // 4)!
        // Подкрасим кнопку.
        // with can be read as “with this object, do the following.”
        // with - Можно читать как: «с этим объектом, сделайте следующее.»
        with(btn_edit) {

            val filter: ColorFilter? =
                if (isEdit) {
                    PorterDuffColorFilter(
                        resources.getColor(R.color.color_accent, theme),
                        PorterDuff.Mode.SRC_IN
                    )
                } else {
                    null
                }
            // подкрасим фон btn_edit
            background.colorFilter = filter


            // сменим иконку btn_edit
            val icon =
                if (isEdit)
                    resources.getDrawable(R.drawable.ic_save_black_24dp, theme)
                else
                    resources.getDrawable(R.drawable.ic_edit_black_24dp, theme)
            setImageDrawable(icon)
        }
    }

    private fun saveProfileInfo() {
        Profile(
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply {
            viewModel.saveProfileData(this)

        }
    }
}