package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendar
import kotlinx.android.synthetic.main.fragment_main_page.*
import kotlinx.android.synthetic.main.fragment_single_line_calendar.*
import java.util.*

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.data_container, MainPageFragment())
            .commit()
    }
}