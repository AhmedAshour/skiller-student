package com.codevenue.skillerandroid.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.model.misc.Location;
import com.codevenue.skillerandroid.model.misc.Contact;
import com.codevenue.skillerandroid.model.misc.Date;
import com.codevenue.skillerandroid.model.misc.Name;
import com.codevenue.skillerandroid.model.users.Student;
import com.codevenue.skillerandroid.viewmodels.StudentViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentInfoActivity extends AppCompatActivity {

    @BindView(R.id.student_info_apart_et)
    EditText etApart;
    @BindView(R.id.student_info_city_et)
    EditText etCity;
    @BindView(R.id.student_info_moreinfo_et)
    EditText etMoreInfo;
    @BindView(R.id.student_info_email_et)
    EditText etEmail;
    @BindView(R.id.student_info_gender_et)
    EditText etGender;
    @BindView(R.id.student_info_name_et)
    EditText etName;
    @BindView(R.id.student_info_street_et)
    EditText etStreet;
    @BindView(R.id.student_info_phone_et)
    EditText etPhoneNumber;
    @BindView(R.id.student_info_birthday_et)
    EditText etBirthDay;
    @BindView(R.id.student_info_birthmonth_et)
    EditText etBirthMonth;
    @BindView(R.id.student_info_birthyear_et)
    EditText etBirthYear;

    StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        ButterKnife.bind(this);
    }

    public void onBtnSaveInfoClick(View view) {
        String strName = etName.getText().toString();
        String strEmail = etEmail.getText().toString();
        String strPhoneNumber = etPhoneNumber.getText().toString();
        String strApart = etApart.getText().toString();
        String strStreet = etStreet.getText().toString();
        String strCity = etCity.getText().toString();
        String strMoreInfo = etMoreInfo.getText().toString();
        String strBirthDay = etBirthDay.getText().toString();
        String strBirthMonth = etBirthMonth.getText().toString();
        String strBirthYear = etBirthYear.getText().toString();
        String strGender = etGender.getText().toString();

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);


        createStudentObject(strName, strEmail, strPhoneNumber, strApart, strStreet, strCity, strMoreInfo,
                strBirthDay, strBirthMonth, strBirthYear, strGender);
    }

    private void createStudentObject(String strName, String strEmail, String strPhoneNumber,
                                     String strApart, String strStreet, String strCity,
                                     String strMoreInfo, String strBirthDay, String strBirthMonth,
                                     String strBirthYear, String strGender) {

        Student student = new Student();
        student.setGender(strGender);
        student.setName(new Name(strName, strName, strName));
        student.setBirthday(new Date(strBirthDay, strBirthMonth, strBirthYear));
        student.setContact(new Contact(strEmail, strPhoneNumber, new Location(strStreet, strApart,
                strMoreInfo, strCity)));

        HashMap<String, Object> studentMap = student.toMap();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        studentViewModel.writeToCurrentStudent(studentMap);

        finish();

    }


}
