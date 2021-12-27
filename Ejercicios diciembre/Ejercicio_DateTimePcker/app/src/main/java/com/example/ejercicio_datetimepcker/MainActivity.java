package com.example.ejercicio_datetimepcker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog picker = null;
    TimePickerDialog time = null;
    int day;
    int month;
    int yearG;
    int hour;
    int minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditText fecha = findViewById(R.id.fecha);
        TextView txt_fecha = findViewById(R.id.txt_fecha);
        fecha.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();

                if (day == 0 && month == 0 && yearG == 0) {

                    day = cldr.get(Calendar.DAY_OF_MONTH);
                    month = cldr.get(Calendar.MONTH);
                    yearG = cldr.get(Calendar.YEAR);

                }

                // date picker dialog

                picker = new DatePickerDialog(MainActivity.this,

                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    day = dayOfMonth;
                                    month = monthOfYear;
                                    yearG = year;
                            }

                            }, yearG, month, day);

                picker.show();
           }
        });

        Button btn_fecha = findViewById(R.id.btn_fecha);
        btn_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_fecha.setText("Selected Date: "+ fecha.getText());
            }
        });

        EditText hora = findViewById(R.id.hora);
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar tiempo = Calendar.getInstance();

                if (hour == 0 && minutes == 0) {

                    hour = tiempo.get(Calendar.HOUR_OF_DAY);
                    minutes = tiempo.get(Calendar.MINUTE);
                }

                time = new TimePickerDialog(MainActivity.this,

                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                                hora.setText(hourOfDay + ":" + minute);

                                hour = hourOfDay;
                                minutes = minute;

                            }
                        }, hour, minutes, false);

                time.show();
            }
        });

        TextView txt_hora = findViewById(R.id.txt_hora);
        Button btn_hora = findViewById(R.id.btn_hora);
        btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_hora.setText("Current time: " + hora.getText());
            }
        });
    }
}