package com.example.teama.ent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.teama.R;

import java.util.Calendar;

public class EntOpenCloseSet extends AppCompatActivity {

    EditText openTimeView, closeTimeView;
    ImageButton timeUpdate, timeCancel, openClosePoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ent_open_close_set);

        openTimeView = findViewById(R.id.openTimeView);
        closeTimeView = findViewById(R.id.closeTimeView);
        timeUpdate = findViewById(R.id.timeUpdate);
        timeCancel = findViewById(R.id.timeCancel);
        openClosePoint = findViewById(R.id.openClosePoint);

        findViewById(R.id.setOpenTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOpenTime();
            }
        });

        findViewById(R.id.setCloseTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCloseTime();
            }
        });

        timeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        openClosePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void selectOpenTime() {
        final Calendar cal = Calendar.getInstance();

        TimePickerDialog dialog = new TimePickerDialog(EntOpenCloseSet.this
                , android.R.style.Theme_Holo_Light_Dialog_NoActionBar
                , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String state = "??????";
                if(hour > 12){
                    state = "??????";
                }
                String msg = String.format(state + " " + hour + "??? " + min + "???");
                Toast.makeText(EntOpenCloseSet.this, msg + "??? ?????????????????????.", Toast.LENGTH_SHORT).show();
                openTimeView.setText(msg);
            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);  //????????? boolean ?????? ????????? 24???????????? ????????? ?????????

        dialog.show();

    }

    private void selectCloseTime() {
        final Calendar cal = Calendar.getInstance();

        TimePickerDialog dialog = new TimePickerDialog(EntOpenCloseSet.this
                , android.R.style.Theme_Holo_Light_Dialog_NoActionBar
                , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String state = "??????";
                if(hour > 12){
                    hour -= 12;
                    state = "??????";
                }
                String msg = String.format(state + " " + hour + "??? " + min + "???");
                Toast.makeText(EntOpenCloseSet.this, msg + "??? ?????????????????????.", Toast.LENGTH_SHORT).show();
                closeTimeView.setText(msg);
            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);  //????????? boolean ?????? ????????? 24???????????? ????????? ?????????
        dialog.show();
    }

}