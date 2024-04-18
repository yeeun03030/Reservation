package kr.ac.kopo.reservation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.Chronometer
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    lateinit var chrono: Chronometer
    lateinit var btnStart: Button
    lateinit var btnDone: Button
    lateinit var rg: RadioGroup
    lateinit var calendar: CalendarView
    lateinit var timePicker: TimePicker
    lateinit var textResult: TextView
    var selectedYear: Int = 0
    var selectedMonth: Int = 0
    var selectedDay: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chrono = findViewById(R.id.chrono)
        btnStart = findViewById(R.id.btnStart)
        btnDone = findViewById(R.id.btnDone)
        rg = findViewById(R.id.rg)
        calendar = findViewById(R.id.calendar)
        timePicker = findViewById(R.id.timePick)
        textResult = findViewById(R.id.textResult)

        calendar.visibility = View.INVISIBLE
        timePicker.visibility = View.INVISIBLE

        rg.setOnCheckedChangeListener(rgListener)
        btnStart.setOnClickListener {
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.DKGRAY)
        }

        btnDone.setOnClickListener {
            chrono.stop()
            chrono.setTextColor(Color.LTGRAY)
            textResult.setText("" + selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 ")
            textResult.append("" + timePicker.currentHour + ":" + timePicker.currentMinute + " 예약 완료")
        }

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedYear = year
            selectedMonth = month + 1
            selectedDay = dayOfMonth
        }
    }

    var rgListener = OnCheckedChangeListener {group, checkedId ->
        calendar.visibility = View.INVISIBLE
        timePicker.visibility = View.INVISIBLE

        when(rg.checkedRadioButtonId) {
            R.id.rbDate -> calendar.visibility = View.VISIBLE
            R.id.rbTime -> timePicker.visibility = View.VISIBLE
        }
    }
}