package com.hamilton;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.hamilton.view.TypefacedEditText;
import com.hamilton.view.TypefacedTextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentActivity extends AppCompatActivity {
    @BindView(R.id.linearLayout5)
    LinearLayout linearLayout5;
    @BindView(R.id.typefacedTextView)
    TypefacedTextView typefacedTextView;
    @BindView(R.id.edit_fname)
    TypefacedEditText editFname;
    @BindView(R.id.edit_lname)
    TypefacedEditText editLname;
    @BindView(R.id.edit_email)
    TypefacedEditText editEmail;
    @BindView(R.id.edit_phone)
    TypefacedEditText editPhone;
    @BindView(R.id.edit_dd)
    TypefacedEditText editDd;
    @BindView(R.id.edit_mm)
    TypefacedEditText editMm;
    @BindView(R.id.edit_yyyy)
    TypefacedEditText editYyyy;
    @BindView(R.id.img_click_calander)
    AppCompatImageView imgClickCalander;
    @BindView(R.id.edit_time)
    TypefacedEditText editTime;
    private Calendar cal;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);
        imgClickCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog();
            }
        });

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        editDd.setText(String.valueOf(day));
        editMm.setText(String.valueOf(month));
        editYyyy.setText(String.valueOf(year));


    }

    public void dateDialog() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day = dayOfMonth;
                month = monthOfYear;
                AppointmentActivity.this.year = year;
                editDd.setText(String.valueOf(dayOfMonth));
                editMm.setText(String.valueOf(monthOfYear));
                editYyyy.setText(String.valueOf(year));
            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }
}
