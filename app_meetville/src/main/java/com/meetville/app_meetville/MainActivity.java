package com.meetville.app_meetville;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends Activity implements View.OnClickListener {
    TextView tvRegistrationText, tvIam, tvBirthday, tvLoockingFor, tvAgeOfPartner;
    RelativeLayout view;
    Button btnBirthday; //выбрать дату рождения
    Button btnIam, btnLoockingFor, btnAgeOfPartner;
    NumberPicker numberPickerIam, numberPickerFrom, numberPickerTo;
    int myYear = 1990;
    int myMonth = 11;
    int myDay = 16;
    int iam; //0 - man 1 - women
    int from, to; // возраст партнера от и до

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findWidgets();
        createLinks(); //Делаем ссылки в текстВью на активити
    }

    //----------------------------------РЕАЛИЗАЦИЯ МЕТОДОВ------------------------------------//

    //вызов диалога выбора даты рождения, по нажатию на пункт "birthday
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonBirthday:
                showDialog(1);
                break;
            case R.id.buttonIam:
                showDialog(2);
                break;
            case R.id.buttonLoockingFor:
                showDialog(3);
                break;
            case R.id.buttonAgeOfPartner:
                showDialog(4);
                break;
        }
    }

    //---------------------------------------ЛОГИКА КЛАССА-------------------------------------//

    //вызываем диалог выбора даты рождения
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        else if(id == 2){
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("");
            view = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialog_iam, null);
            adb.setView(view);
            adb.setPositiveButton("Done", myClickListenerIam);
            return adb.create();
        }
        else if(id == 3){
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("");
            view = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialog_iam, null);
            adb.setView(view);
            adb.setPositiveButton("Done",myClickListenerLoockingFor);
            return adb.create();
        }
        else if(id == 4){
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("");
            view = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialog_age_of_partner, null);
            adb.setView(view);
            adb.setPositiveButton("Done",myClickListenerAgeOfPartner);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year; myMonth = monthOfYear; myDay = dayOfMonth;
            tvBirthday.setText((monthOfYear+1)+"/"+dayOfMonth+"/"+year);
        }
    };

    //обработка нажатия кнопок в диалоге "Iam"
    OnClickListener myClickListenerIam = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    iam = numberPickerIam.getValue();
                    if(iam == 1)tvIam.setText("Man"); else tvIam.setText("Woman");
                    break;
            }}
    };
    //обработка нажатия кнопок в диалоге "LoockingFor"
    OnClickListener myClickListenerLoockingFor = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    iam = numberPickerIam.getValue();
                    if(iam == 1)tvLoockingFor.setText("Man"); else tvLoockingFor.setText("Woman");
                    break;
            }}
    };
    //обработка нажатия кнопок в диалоге "AgeOfPartner"
    OnClickListener myClickListenerAgeOfPartner = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    from = numberPickerFrom.getValue();
                    to = numberPickerTo.getValue();
                    if(to < from){ int temp = from; from = to; to = temp; }
                    tvAgeOfPartner.setText(""+ from + "-" + to);
                    break;
            }}
    };


    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (id == 2 || id == 3) {
            numberPickerIam = (NumberPicker) dialog.getWindow().findViewById(R.id.numberPickerIam);
            numberPickerIam.setMinValue(1); numberPickerIam.setMaxValue(2);
            numberPickerIam.setWrapSelectorWheel(false);
            numberPickerIam.setDisplayedValues(new String[]{"Man", "Woman"});
        }
        if (id == 4) {
            numberPickerFrom = (NumberPicker) dialog.getWindow().findViewById(R.id.numberPickerFrom);
            numberPickerTo = (NumberPicker) dialog.getWindow().findViewById(R.id.numberPickerTo);
            numberPickerFrom.setMinValue(18); numberPickerFrom.setMaxValue(99);
            numberPickerTo.setMinValue(18); numberPickerTo.setMaxValue(99);
            numberPickerFrom.setWrapSelectorWheel(false); numberPickerTo.setWrapSelectorWheel(false);
        }
    }

    private void createLinks(){
        Pattern pattern1 = Pattern.compile("Terms of use");
        Linkify.addLinks(tvRegistrationText, pattern1, "sherif-activity1://");
        Pattern pattern2 = Pattern.compile("Cookie");
        Linkify.addLinks(tvRegistrationText, pattern2, "sherif-activity2://");
        Pattern pattern3 = Pattern.compile("private information");
        Linkify.addLinks(tvRegistrationText, pattern3, "sherif-activity3://");
    }

    private void findWidgets(){
        btnBirthday = (Button) findViewById(R.id.buttonBirthday);
        btnIam = (Button) findViewById(R.id.buttonIam);
        btnIam.setOnClickListener(this);
        btnBirthday.setOnClickListener(this);
        tvRegistrationText = (TextView) findViewById(R.id.textViewWeb);
        tvBirthday = (TextView) findViewById(R.id.textViewBirthday);
        tvIam = (TextView) findViewById(R.id.textViewIam);
        tvLoockingFor = (TextView) findViewById(R.id.textViewLoockingFor);
        btnLoockingFor = (Button) findViewById(R.id.buttonLoockingFor);
        btnLoockingFor.setOnClickListener(this);
        tvAgeOfPartner = (TextView) findViewById(R.id.textViewAgeOfPartner);
        btnAgeOfPartner = (Button) findViewById(R.id.buttonAgeOfPartner);
        btnAgeOfPartner.setOnClickListener(this);
    }

}
