package appdev.magik.vezdecode_mobile

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


val WEEKNAME: Array<String> = arrayOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
val TEXT_SIZE = 23F
val TEXT_PADDING = 15


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Календарь");
    }

    override fun onResume() {
        super.onResume()
        calendarCreate()
    }

    private fun calendarCreate() {
        val dateView = findViewById<TextView>(R.id.date) as TextView
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        dateView.text = sdf.format(Date())
        dateView.setTextSize(TEXT_SIZE)

        val tableLayout = findViewById<TableLayout>(R.id.tab) as TableLayout
        tableLayout.removeAllViews()

        //Дни недели
        val tableRow1 = TableRow(this)
        for (i in 0 until 7) {
            val textView = TextView(this)
            textView.setPadding(TEXT_PADDING, TEXT_PADDING, TEXT_PADDING, TEXT_PADDING);
            textView.text = WEEKNAME[i]
            textView.setTextSize(TEXT_SIZE)
            textView.gravity = Gravity.CENTER
            if (i < 5) {
                textView.setTextColor(Color.DKGRAY)
            } else {
                textView.setTextColor(Color.RED)
            }
            tableRow1.addView(textView)
        }

        tableLayout.addView(tableRow1)

        Log.d(
            "day_of_month_max",
            Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH).toString()
        )

        //Числа
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        var dateOfFirstDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (dateOfFirstDay == 1) {
            dateOfFirstDay = 8
        }
        dateOfFirstDay -= 3

        Log.d("dateOfFirstDay", dateOfFirstDay.toString())

        var date = -dateOfFirstDay
        for (j in 0 until 5) {
            val tableRow2 = TableRow(this)
            for (i in 0 until 7) {
                val textView = TextView(this)
                textView.setPadding(TEXT_PADDING, TEXT_PADDING, TEXT_PADDING, TEXT_PADDING);
                textView.setTextSize(TEXT_SIZE)
                textView.gravity = Gravity.CENTER
                if (date > 0 && date <= Calendar.getInstance()
                        .getActualMaximum(Calendar.DAY_OF_MONTH)
                ) {
                    textView.text = date.toString()
                    if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == date) {
                        textView.setTextColor(Color.BLACK)
                    }
                } else {
                    textView.text = ""
                }
                date++
                tableRow2.addView(textView)
            }
            tableLayout.addView(tableRow2)
        }
    }
}