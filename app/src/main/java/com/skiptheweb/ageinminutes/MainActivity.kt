package com.skiptheweb.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener() {

                clickDatePicker()

        }
    }


    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth,
                                                                             selectedDayOfMonth ->

            //Toast.makeText(this,"The year is $selectedYear, month is ${selectedMonth+1} and day is $selectedDayOfMonth ", Toast.LENGTH_SHORT).show()

            var selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate?.text = selectedDate


            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val thedate = sdf.parse(selectedDate)

            thedate?.let {

                val selectedDateInMinutes = thedate.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 60000
                    val difference = currentDateInMinutes - selectedDateInMinutes
                    tvAgeInMinutes?.text = difference.toString()
                }



            }








        },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()



    }
}