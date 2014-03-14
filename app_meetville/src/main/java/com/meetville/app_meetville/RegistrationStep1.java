package com.meetville.app_meetville;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationStep1 extends Activity implements View.OnClickListener {
    TextView tvRegistrationText, tvIam, tvBirthday, tvLoockingFor, tvAgeOfPartner, tvCity;
    RelativeLayout view;
    Button btnRegistration;
    Button btnDone, btnPrev, btnNext;
    NumberPicker numberPickerIam, numberPickerFrom, numberPickerTo, numberPickerLookingFor;
    DatePicker datePicker;
    EditText editTextNickName, editTextEmail, editTextPassword;
    LinearLayout linearLayoutIam, linearLayoutLookingFor, linearLayoutFromTo, linearLayoutBirthday;
    LinearLayout linearLayoutPrevNextDone;
    RelativeLayout rlIam, rlLookingFor, rlBirthday, rlFromTo;
    LinearLayout lrNickname;


    //дата рождения, значения для барабанов по умолчанию
    private int myYear = 1990;
    private int myMonth = 11;
    private int myDay = 16;
    private int iam; //0 - man 1 - women
    private int from, to; // возраст партнера от и до
    private int lookingFor; //0 - мена 1 - вумена
    private String nickname;
    private String city;
    private String email;
    private String password;
    private String tempAccountInfo;
    private String iam_string;
    private String lookingFor_string;

   private int menuItems[];
   private int currentMenuItem = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_step1);
        findWidgets();
        createLinks(); //Делаем ссылки в текстВью на активити
        menuItems = new int[]{0,1,2,3,4,5,6,7};
        numberPickerIam();
        numberPickerLookingFor();
        numberPickerFromTo();
    }

    //----------------------------------РЕАЛИЗАЦИЯ МЕТОДОВ------------------------------------//

    //вызов диалога выбора даты рождения, по нажатию на пункт "birthday
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.nickname:
                currentMenuItem = 0;
                goneAllLayout(currentMenuItem);
                setVisible(currentMenuItem);
                break;
            case R.id.iam:
                currentMenuItem = 1;
                goneAllLayout(currentMenuItem);
                setVisible(currentMenuItem);
                break;
            case R.id.lookingFor:
                currentMenuItem = 2;
                goneAllLayout(currentMenuItem);
                setVisible(currentMenuItem);
                break;
            case R.id.birthday:
                currentMenuItem = 3;
                goneAllLayout(currentMenuItem);
                setVisible(currentMenuItem);
                break;
            case R.id.fromTo:
                currentMenuItem = 5;
                goneAllLayout(currentMenuItem);
                setVisible(currentMenuItem);
                break;
            case R.id.buttonSignUpStep1: //кнопка регестрации "sign up" тут будет отправляться запрос на сервер и проверяться правильность заполнения полей
                nickname = editTextNickName.getText().toString();
                password = editTextPassword.getText().toString();
                email = editTextEmail.getText().toString();
                iam_string = tvIam.getText().toString();
                lookingFor_string = tvLoockingFor.getText().toString();
                city = tvCity.getText().toString();

                tempAccountInfo = "nickname: " + nickname + " I am: " + iam_string + " Looking for: " + lookingFor_string +
                        " Birthday: " + (myMonth+1)+"/"+myDay+"/"+myYear + " city: " + city + " Age of partner: " + from + "-" + to +
                        " Email: " + email + " password: " + password;
                Toast.makeText(this, tempAccountInfo, Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonDone:
                setValue(currentMenuItem);
                break;
            case R.id.buttonNext:
                setValue(currentMenuItem);
                if( currentMenuItem < 7)currentMenuItem++;
                else currentMenuItem = 0;
                setVisible(currentMenuItem);
                break;
            case R.id.buttonPrev:
                setValue(currentMenuItem);
                if(currentMenuItem > 0) currentMenuItem--;
                else currentMenuItem = 7;
                setVisible(currentMenuItem);
                break;
        }
    }

    //---------------------------------------ЛОГИКА КЛАССА-------------------------------------//

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
        Linkify.addLinks(tvRegistrationText, pattern1, "activity1://");
        Pattern pattern2 = Pattern.compile("Cookie");
        Linkify.addLinks(tvRegistrationText, pattern2, "activity2://");
        Pattern pattern3 = Pattern.compile("private information");
        Linkify.addLinks(tvRegistrationText, pattern3, "activity3://");
    }

    private void findWidgets(){
        lrNickname = (LinearLayout)findViewById(R.id.nickname);
        lrNickname.setOnClickListener(this);
        rlIam = (RelativeLayout) findViewById(R.id.iam);
        rlLookingFor = (RelativeLayout) findViewById(R.id.lookingFor);
        rlBirthday = (RelativeLayout) findViewById(R.id.birthday);
        rlFromTo = (RelativeLayout) findViewById(R.id.fromTo);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        rlIam.setOnClickListener(this);
        rlLookingFor.setOnClickListener(this);
        rlBirthday.setOnClickListener(this);
        rlFromTo.setOnClickListener(this);
        linearLayoutBirthday = (LinearLayout) findViewById(R.id.linearLayoutBirthday);
        linearLayoutFromTo = (LinearLayout) findViewById(R.id.linearLayoutAgeOfPartner);
        linearLayoutLookingFor = (LinearLayout) findViewById(R.id.linearLayoutLookingFor);
        linearLayoutIam = (LinearLayout)findViewById(R.id.LinareLayoutIam);
        numberPickerLookingFor = (NumberPicker) findViewById(R.id.numberPickerLookingFor);
        tvRegistrationText = (TextView) findViewById(R.id.textViewWeb);
        tvBirthday = (TextView) findViewById(R.id.textViewBirthday);
        tvIam = (TextView) findViewById(R.id.textViewIam);
        tvLoockingFor = (TextView) findViewById(R.id.textViewLoockingFor);
        tvAgeOfPartner = (TextView) findViewById(R.id.textViewAgeOfPartner);
        btnRegistration = (Button) findViewById(R.id.buttonSignUpStep1);
        btnRegistration.setOnClickListener(this);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextNickName = (EditText) findViewById(R.id.editTextNickname);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        tvCity = (TextView) findViewById(R.id.textViewCity);
        btnDone = (Button) findViewById(R.id.buttonDone);
        btnDone.setOnClickListener(this);
        linearLayoutPrevNextDone = (LinearLayout) findViewById(R.id.LinareLayoutPrevNextDone);
        btnNext = (Button) findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(this);
        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnPrev.setOnClickListener(this);
    }

    private void numberPickerIam(){
        numberPickerIam = (NumberPicker) findViewById(R.id.numberPickerIam);
        numberPickerIam.setMinValue(1); numberPickerIam.setMaxValue(2);
        numberPickerIam.setWrapSelectorWheel(false);
        numberPickerIam.setDisplayedValues(new String[]{"Man", "Woman"});
    }

    private void numberPickerLookingFor(){
        numberPickerLookingFor = (NumberPicker) findViewById(R.id.numberPickerLookingFor);
        numberPickerLookingFor.setMinValue(1); numberPickerLookingFor.setMaxValue(2);
        numberPickerLookingFor.setWrapSelectorWheel(false);
        numberPickerLookingFor.setDisplayedValues(new String[]{"Man", "Woman"});
    }

    private void numberPickerFromTo(){
        numberPickerFrom = (NumberPicker) findViewById(R.id.numberPickerFrom);
        numberPickerTo = (NumberPicker) findViewById(R.id.numberPickerTo);
        numberPickerFrom.setMinValue(18); numberPickerFrom.setMaxValue(99);
        numberPickerFrom.setWrapSelectorWheel(false);
        numberPickerTo.setMinValue(18); numberPickerTo.setMaxValue(99);
        numberPickerTo.setWrapSelectorWheel(false);
    }

    private void setVisible(int currentMenuItem){
        switch(currentMenuItem){
            case 0:
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                showHideKeyboard(true);
                break;
            case 1:
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                linearLayoutIam.setVisibility(View.VISIBLE);
                break;
            case 2:
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                linearLayoutLookingFor.setVisibility(View.VISIBLE);
                break;
            case 3:
                linearLayoutBirthday.setVisibility(View.VISIBLE);
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                break;
            case 4:
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                break;
            case 5:
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                linearLayoutFromTo.setVisibility(View.VISIBLE);
                break;
            case 6:
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                break;
            case 7:
                linearLayoutPrevNextDone.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setValue(int currentMenuItem){
        switch(currentMenuItem){
            case 0:
                nickname = editTextNickName.getText().toString();
                linearLayoutPrevNextDone.setVisibility(View.GONE);
                showHideKeyboard(false);
                break;
            case 1:
                iam = numberPickerIam.getValue();
                if(iam == 1)tvIam.setText("Man"); else tvIam.setText("Woman");
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutPrevNextDone.setVisibility(View.GONE);
                break;
            case 2:
                lookingFor = numberPickerLookingFor.getValue();
                iam = numberPickerIam.getValue();
                if(lookingFor == 1)tvLoockingFor.setText("Man"); else tvLoockingFor.setText("Woman");
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutPrevNextDone.setVisibility(View.GONE);
                break;
            case 3:
                myDay = datePicker.getDayOfMonth();
                myMonth = datePicker.getMonth();
                myYear = datePicker.getYear();
                tvBirthday.setText((myMonth+1)+"/"+myDay+"/"+myYear);
                linearLayoutBirthday.setVisibility(View.GONE);
                linearLayoutPrevNextDone.setVisibility(View.GONE);
                break;
            case 4:
                break;
            case 5:
                from = numberPickerFrom.getValue();
                to = numberPickerTo.getValue();
                //если от > чем до, то меняем местами, иначе будет 22-18, а нужно 18-22
                if(to < from){ int temp = from; from = to; to = temp; }
                tvAgeOfPartner.setText(""+ from + "-" + to);
                linearLayoutFromTo.setVisibility(View.GONE);
                linearLayoutPrevNextDone.setVisibility(View.GONE);
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }

    private void goneAllLayout(int currentMenuItem){
        switch(currentMenuItem){
            case 0:
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutBirthday.setVisibility(View.GONE);
                linearLayoutFromTo.setVisibility(View.GONE);
                break;
            case 1:
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutBirthday.setVisibility(View.GONE);
                linearLayoutFromTo.setVisibility(View.GONE);
                break;
            case 2:
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutBirthday.setVisibility(View.GONE);
                linearLayoutFromTo.setVisibility(View.GONE);
                break;
            case 3:
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutFromTo.setVisibility(View.GONE);
                break;
            case 4:
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutBirthday.setVisibility(View.GONE);
                linearLayoutFromTo.setVisibility(View.GONE);
                break;
            case 5:
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutBirthday.setVisibility(View.GONE);
                break;
            case 6:
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutBirthday.setVisibility(View.GONE);
                linearLayoutFromTo.setVisibility(View.GONE);
                break;
            case 7:
                linearLayoutIam.setVisibility(View.GONE);
                linearLayoutLookingFor.setVisibility(View.GONE);
                linearLayoutBirthday.setVisibility(View.GONE);
                linearLayoutFromTo.setVisibility(View.GONE);
                break;
        }
    }

    private void showHideKeyboard(boolean param){
            EditText edtText = (EditText) this.getCurrentFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(param){
            inputMethodManager.toggleSoftInput(0, 0);
            edtText.setSelection(edtText.getText().length());
            }
        else inputMethodManager.hideSoftInputFromWindow(edtText.getWindowToken(), 0);

    }

}
