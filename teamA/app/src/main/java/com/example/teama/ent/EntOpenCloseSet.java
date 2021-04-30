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
                String state = "오전";
                if(hour > 12){
                    state = "오후";
                }
                String msg = String.format(state + " " + hour + "시 " + min + "분");
                Toast.makeText(EntOpenCloseSet.this, msg + "이 선택되었습니다.", Toast.LENGTH_SHORT).show();
                openTimeView.setText(msg);
            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);  //마지막 boolean 값은 시간을 24시간으로 보일지 아닐지

        dialog.show();

    }

    private void selectCloseTime() {
        final Calendar cal = Calendar.getInstance();

        TimePickerDialog dialog = new TimePickerDialog(EntOpenCloseSet.this
                , android.R.style.Theme_Holo_Light_Dialog_NoActionBar
                , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String state = "오전";
                if(hour > 12){
                    hour -= 12;
                    state = "오후";
                }
                String msg = String.format(state + " " + hour + "시 " + min + "분");
                Toast.makeText(EntOpenCloseSet.this, msg + "이 선택되었습니다.", Toast.LENGTH_SHORT).show();
                closeTimeView.setText(msg);
            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);  //마지막 boolean 값은 시간을 24시간으로 보일지 아닐지
        dialog.show();
    }

}