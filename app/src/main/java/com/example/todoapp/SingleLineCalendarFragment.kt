package com.example.todoapp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import kotlinx.android.synthetic.main.calendar_item.*
import kotlinx.android.synthetic.main.calendar_item.view.*
import kotlinx.android.synthetic.main.calendar_item.view.txt_day_letter_selected
import kotlinx.android.synthetic.main.calendar_item.view.txt_day_number
import kotlinx.android.synthetic.main.fragment_single_line_calendar.*
import kotlinx.android.synthetic.main.selected_calendar_item.view.*
import java.util.*

class SingleLineCalendarFragment : Fragment() {
    private val mCalendar = Calendar.getInstance()
    private var mCurrentMonth = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_single_line_calendar, container, false)
    }

    override fun onResume() {
        super.onResume()
        mCalendar.time = Date()
        mCurrentMonth = mCalendar[Calendar.MONTH]

        //Here i change views
        val myCalendarViewManager = object : CalendarViewManager {
            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                holder.itemView.txt_day_number.text = DateUtils.getDayNumber(date)
                holder.itemView.txt_day_letter_selected.text = DateUtils.getDay3LettersName(date)
                if (holder.itemView.inner_container_selected == null)
                    holder.itemView.inner_container.background.alpha = 70
            }

            //setIdResources
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                val _cal = Calendar.getInstance()
                _cal.time = date
                return if (isSelected)
                    R.layout.selected_calendar_item
                else
                    R.layout.calendar_item

            }
        }

        val myCalendarChangesObserver = object : CalendarChangesObserver {
            @SuppressLint("SetTextI18n")
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                super.whenSelectionChanged(isSelected, position, date)
                txt_date_today.text =
                    "${DateUtils.getDayName(date)}, ${DateUtils.getDayNumber(date)}, ${
                        DateUtils.getMonth3LettersName(date)
                    }"
            }
        }

        // selection manager is responsible for managing selection
        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                // set date to calendar according to position
                val cal = Calendar.getInstance()
                cal.time = date
                //in this example sunday and saturday can't be selected, other item can be selected
                return true

            }
        }
        val singleRowCalendar = single_calendar.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            setDates(getFutureDatesOfCurrentMonth())
            init()
        }

    }


    private fun drawCalendarItem() {

        inner_container.background.alpha = 10
    }

    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        mCurrentMonth = mCalendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }

    //load dates of whole month
    private fun getDates(list: MutableList<Date>): List<Date> {
        mCalendar.set(Calendar.MONTH, mCurrentMonth)
        mCalendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(mCalendar.time)
        while (mCurrentMonth == mCalendar[Calendar.MONTH]) {
            mCalendar.add(Calendar.DATE, +1)
            if (mCalendar[Calendar.MONTH] == mCurrentMonth)
                list.add(mCalendar.time)
        }
        mCalendar.add(Calendar.DATE, -1)
        return list
    }

}