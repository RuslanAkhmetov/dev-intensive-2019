package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender


class MainActivity : AppCompatActivity(), View.OnClickListener {
     lateinit var benderImage: ImageView
     lateinit var textTxt: TextView
     lateinit var messageEt: EditText
     lateinit var sendBtn: ImageView
     lateinit var benderObj: Bender

    /**
     * Вызывается при первом создании или перезапуске Activity
     * pltcm pflftncz dytiybq dbl frnbdyjcnb (UI) через метод setContentView()
     * представления связываются с необходимыми данными и ресурсами
     * связываются данные со списками
     *
     * Этот метод также представляет BUndle, содержащий ранее созданнные
     * состояния активити, если оно было
     *
     * Всегда сопровождается вызовом onStart()
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      benderImage = findViewById(R.id.iv_bender)
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState.getString("QUESTION") ?: Bender.Question.NAME.name

        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))
        Log.d("M_MainActivity","onCreate ${status}")
        val (r,g,b) =benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)


        textTxt.text= benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
    }
    /**
     *Если Activity возвращается в приоритетный режим после вызова onStop()
     * то в этом случае вызывается метод onRestart().
     * Т. е. вызывается после того, ка Activity была остановлена и снова была запущена пользователем.
     *
     * Всегда сопровождается вызовом onStart()
     */
    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","onRestart")
    }

    /**
     * При вызове onStart() окно ещ не видно пользователю, но вскоре будет видно.
     * Вызывается непосредственно перед тем, как активность становится видимой пользователю.
     *
     * Чтение из базы данных
     * Запуск сложной анимации
     * Запуск потоков, отслеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Затем следует onRessume() если Activity выходит на передний план.
     */
    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart")
    }

    /**
     * Вызывается когда, Activity начнет взаимодействовать с пользователем.
     *
     * запуск воспроизведения анимации, аудио и видео
     * регистрация любых BroadcastReceiver или других процессов, которые вы освободили/приостановили в onPause()
     * выполнение любых других инициализаций, которые должы происходить, когда Activity вновь активна (камера)
     *
     * Тут должен быть максимально легкий и быстрый код чтобы приложение оставалось отзывчивым.
     */

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume()")
    }

    /**
     * Метод onPause() вызывается после сворачивания текущей активности или перехода к новому,
     * От onPause() можно перейти  к вызову либо onResume() либо on Stop()
     *
     * остановка анимации, аудиоЮ и видео
     * созраннеи состочния пользовательского ввода (легикие процессы)
     * созранеие в DB если данные должны быть доступными в новой Activity?
     * остановка сервисов, подписок, BroadcastReceiver
     *
     * Тут должен быть максимально легкий и быстрый код чтобы приложение оставалось отзывчивым.
     */
    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","onPause()")
    }



    /**
     * Метода onStop() вызываетс, когда Activity становится невидимым пользователю.
     * Это может произойти при е уничтожении, или если была запущена другая Activity (существующая или новая),
     * перекрывая окно текущей Actuvity.
     *
     * запись в базу данных
     * приостановка сложной анимации
     * приостаовка потоков, отслеживания показаний датчиков, запросов к GPS, таймеров, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Не вызывается при вызове метов finisH() у Activity
     */
    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop()")
    }

    /**
     * Метод вызывается по окончании работы Activity при вызове метода onFinish() мли в случае,
     * когда система уничтожает этот экземпляр активности для освобождения ресурсов.
     */

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy()")
    }

    /**
     * Этот метод сохраняет состояние представления в Bundle
     * Для API lEVEL<28 (Android P) этот метод будет выполняться до onStor() , и нет никаких гарантий относительно того,
     * произойдет ли это до или после onPause()
     * Для API Level >=28 будет вызван после onStor()
     * Не будет вызван если Activity будет явно закрыто пользователем при нажатии на системную клавишу back
     */

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        Log.d("M_MainActivity", "onSaveInstanceState ${benderObj.status.name} ${ benderObj.question.name}")
    }

    override fun onClick(v: View?) {
        if(v?.id==R.id.iv_send){
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r,g,b) =color
            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase

        }
    }

}
