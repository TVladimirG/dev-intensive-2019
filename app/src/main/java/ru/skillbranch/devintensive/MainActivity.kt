package ru.skillbranch.devintensive

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender
import java.util.*

class MainActivity : AppCompatActivity(), OnClickListener, TextView.OnEditorActionListener {

    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEv: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("M_MainActivity","onCreate")

        benderImage = iv_bender
        textTxt = tv_text
        messageEv = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        val userMessage = savedInstanceState?.getString("USER_MESSAGE") ?: ""

        messageEv.setText(userMessage)

        benderObj = Bender( Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)

        // установить в клавиатуре кнопку "DONE" и обрабатывать ввод по нажатию на нее.
        // В начале нужно утановить тип ввода
        messageEv.inputType = InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE; // короткое сообщение
      //  messageEv.inputType = InputType.TYPE_CLASS_PHONE; // номер телефона например
        // Теперь опцию - заменим кнопку Enter на "DONE"
        messageEv.imeOptions = EditorInfo.IME_ACTION_DONE
        //  messageEv.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE);  // А это не работает!
        // И в конце нужно установить слушатель кнопки Enter
        messageEv.setOnEditorActionListener(this)

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS",  benderObj.status.name)
        outState.putString("QUESTION",  benderObj.question.name)
        outState.putString("USER_MESSAGE", messageEv.text.toString())
    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            runSending()

            //   val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
         //   imm.hideSoftInputFromWindow(messageEv.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

            hideKeyboard()
           // Activity().hideKeyboard(messageEv as View)
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        Log.d("M_MainActivity","onEditorAction-start")
        if (v?.id == R.id.et_message) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                runSending()
                hideKeyboard()
                Log.d("M_MainActivity","onEditorAction")
                Log.d("M_MainActivity","$actionId")
                return true
            }
        }
        return false
    }

    private fun runSending() {
        val (phrase, color) = benderObj.listenAnswer(
                messageEv.text.toString().toLowerCase(Locale.ROOT)
                )
        messageEv.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
    }
}