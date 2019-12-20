package com.codevenue.skillerandroid.views;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.codevenue.skillerandroid.BuildConfig;
import com.codevenue.skillerandroid.R;
import com.codevenue.skillerandroid.constants.FirebaseKeys;
import com.codevenue.skillerandroid.model.users.Student;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Checking if user is signed in
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // already signed in
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            // not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setLogo(R.drawable.logo_with_slogan)
                            .setTheme(R.style.LoginTheme)
                            .setAvailableProviders(Arrays.asList(
                                   // new AuthUI.IdpConfig.FacebookBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build()))
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG,true)
                            .build(),
                    RC_SIGN_IN);
            AuthUI.IdpConfig phoneConfigWithDefaultNumber = new AuthUI.IdpConfig.PhoneBuilder()
                    .setDefaultCountryIso("eg")
                    .build();


        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {
                    String studentUid = user.getUid();

                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference()
                            .child(FirebaseKeys.CHILD_STUDENTS).child(studentUid);
                    try {
                        mDatabase.child(FirebaseKeys.Tutor.User.CHILD_USER_UID).setValue(studentUid);
                        mDatabase.child(FirebaseKeys.Tutor.User.CHILD_USER_IMAGE_URL).setValue(user.getPhotoUrl().toString());
                        mDatabase.child(FirebaseKeys.Tutor.User.CHILD_USER_CONTACT)
                                .child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_EMAIL).setValue(user.getEmail());
                        mDatabase.child(FirebaseKeys.Tutor.User.CHILD_USER_CONTACT)
                                .child(FirebaseKeys.Tutor.User.Contact.KEY_CHILD_PHONE).setValue(user.getPhoneNumber());
                    } catch (Exception e) {
                    }
                }

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                // ...
            }else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Sign in error.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
