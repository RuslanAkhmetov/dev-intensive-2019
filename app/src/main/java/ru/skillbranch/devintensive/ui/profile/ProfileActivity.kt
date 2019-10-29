package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.Bender
import ru.skillbranch.devintensive.extensions.*


class ProfileActivity : AppCompatActivity(), View.OnClickListener {


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
            Log.d("M_MainActivity", "onRestart")
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
     * Этот метод сохраняет состояние представления в Bundle
     * Для API lEVEL<28 (Android P) этот метод будет выполняться до onStor() , и нет никаких гарантий относительно того,
     * произойдет ли это до или после onPause()
     * Для API Level >=28 будет вызван после onStor()
     * Не будет вызван если Activity будет явно закрыто пользователем при нажатии на системную клавишу back
     */

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)


    }






}
