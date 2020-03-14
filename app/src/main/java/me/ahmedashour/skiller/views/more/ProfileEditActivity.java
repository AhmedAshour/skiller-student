package me.ahmedashour.skiller.views.more;

import android.app.ProgressDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.ahmedashour.skiller.R;
import me.ahmedashour.skiller.model.users.Student;
import me.ahmedashour.skiller.viewmodels.StudentViewModel;

public class ProfileEditActivity extends AppCompatActivity {

    private String TAG = ProfileEditActivity.class.getSimpleName();

    //Views
    @BindView(R.id.activity_edit_profile_iv_profile_pic)
    CircleImageView civProfilePic;
    @BindView(R.id.activity_edit_profile_et_name_first)
    EditText etNameFirst;
    @BindView(R.id.activity_edit_profile_et_name_last)
    EditText etNameLast;
    @BindView(R.id.activity_edit_profile_btn_save)
    Button btnSave;
    @BindView(R.id.activity_edit_profile_et_city)
    EditText etCity;
    @BindView(R.id.activity_edit_profile_et_country)
    EditText etCountry;
    @BindView(R.id.activity_edit_profile_et_email)
    EditText etEmail;
    @BindView(R.id.activity_edit_profile_et_phone_num)
    EditText etPhoneNumber;
    @BindView(R.id.activity_edit_profile_radio_group_gender_radio_btn_male)
    RadioButton cbMale;
    @BindView(R.id.activity_edit_profile_radio_group_gender_radio_btn_female)
    RadioButton cbFemale;
    @BindView(R.id.activity_edit_profile_img_btn_back)
    ImageButton btnBack;

    private StudentViewModel studentViewModel;
    private String downloadUrl;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        ButterKnife.bind(this);
        initViewModel();
    }

    private void initViewModel() {
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getStudentData().observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                Log.d(TAG, student.toString());
//                ProfileEditActivity.this.student = student;
                populateViews(student);
            }
        });
    }

    private void populateViews(Student student) {
        if (student.getImageURL() != null)
            Glide.with(this).load(student.getImageURL()).into(civProfilePic);
        etNameFirst.setText(student.getName().getFirstName());
        etNameLast.setText(student.getName().getLastName());
        etCity.setText(student.getContact().getLocation().getCity());
        etCountry.setText(student.getContact().getLocation().getCountry());
        etEmail.setText(student.getContact().getEmail());
        etPhoneNumber.setText(student.getContact().getPhoneNumber());
        try {
            if (student.getGender().toLowerCase().equals("male"))
                cbMale.setChecked(true);
            else if (student.getGender().toLowerCase().equals("female"))
                cbFemale.setChecked(true);
        } catch (NullPointerException e) {
        }

    }

    @OnClick(R.id.activity_edit_profile_img_btn_back)
    public void onClickBtnBack(View view){
        finish();
    }

    @OnClick(R.id.activity_edit_profile_btn_save)
    public void onClickBtnSave(View view) {
        Student updatedStudent = new Student();
        if (etNameFirst.getText() != null)
            updatedStudent.getName().setFirstName(etNameFirst.getText().toString());
        if (etNameLast.getText() != null)
            updatedStudent.getName().setLastName(etNameLast.getText().toString());
        if (downloadUrl != null)
            updatedStudent.setImageURL(downloadUrl);
        if (etCity.getText() != null)
            updatedStudent.getContact().getLocation().setCity(etCity.getText().toString());
        if (etPhoneNumber.getText() != null)
            updatedStudent.getContact().setPhoneNumber(etPhoneNumber.getText().toString());
        if (etEmail.getText() != null)
            updatedStudent.getContact().setEmail(etEmail.getText().toString());
        if (etCountry.getText() != null)
            updatedStudent.getContact().getLocation().setCountry(etCountry.getText().toString());
        if (cbMale.isChecked())
            updatedStudent.setGender("male");
        else if (cbFemale.isChecked())
            updatedStudent.setGender("female");

        studentViewModel.updateStudentProfile(updatedStudent);

        finish();

    }

    @OnClick(R.id.activity_edit_profile_iv_profile_pic)
    public void onClickProfilePic(View view) {

        //From gallery
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Uploading your photo ...");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    Uri selectedImage = imageReturnedIntent.getData();
                    civProfilePic.setImageURI(selectedImage);

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference().child(user.getUid());
                    mStorageRef.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = uri.toString();
                                    Log.d(TAG, downloadUrl);
                                    progressDialog.hide();
                                }
                            });
                        }
                    });

                }
                break;
        }
    }
}
