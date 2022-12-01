package com.example.tennis4us.Guest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tennis4us.Class.Loading;
import com.example.tennis4us.Class.PopUpMSG;
import com.example.tennis4us.Class.User;
import com.example.tennis4us.R;
import com.example.tennis4us.User.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends Fragment {
    private TextInputLayout TextInputLayoutFirstName, TextInputLayoutLastName ,TextInputLayoutEmail, TextInputLayoutPassword, TextInputLayoutPasswordConfirm;
    private Button ButtonFinish;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance() ;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://tennis4us-a5fc6-default-rtdb.europe-west1.firebasedatabase.app");
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");
    private User user = new User();
    private Loading loading;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_account, container, false);
        TextInputLayoutFirstName = view.findViewById(R.id.TextInputLayoutFirstName);
        TextInputLayoutLastName = view.findViewById(R.id.TextInputLayoutLastName);
        TextInputLayoutEmail = view.findViewById(R.id.TextInputLayoutEmail);
        TextInputLayoutPassword = view.findViewById(R.id.TextInputLayoutPassword);
        TextInputLayoutPasswordConfirm = view.findViewById(R.id.TextInputLayoutPasswordConfirm);
        ButtonFinish = view.findViewById(R.id.ButtonNext);
        SignOut();
        EndIcon();
        CreateAccountCheck();
        return view;
    }
    private void EndIcon() {
        TextInputLayoutFirstName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Clear(TextInputLayoutFirstName); }
        });
        TextInputLayoutLastName.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Clear(TextInputLayoutLastName); }
        });
        TextInputLayoutEmail.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Clear(TextInputLayoutEmail); }
        });
    }
    private void Clear(TextInputLayout input){
        input.setHelperText("");
        input.getEditText().setText("");
    }
    private void SignOut(){
        if(firebaseAuth.getCurrentUser() != null)
            firebaseAuth.getInstance().signOut();
    }
    private boolean isEmailValid(CharSequence email) { return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); }
    private boolean CheckEmail(){
        if(TextInputLayoutEmail.getEditText().getText().length()<1) {
            TextInputLayoutEmail.setHelperText(getResources().getString(R.string.Required));
            return false;
        }
        else if(!isEmailValid(TextInputLayoutEmail.getEditText().getText().toString())) {
            TextInputLayoutEmail.setHelperText(getResources().getString(R.string.InvalidEmail));
            return false;
        }
        else
            TextInputLayoutEmail.setHelperText("");
        return true;
    }
    private boolean CheckName(TextInputLayout name){
        if(name.getEditText().getText().length()<1) {
            name.setHelperText(getResources().getString(R.string.Required));
            return false;
        }
        else
            name.setHelperText("");
        return true;
    }

    private boolean CheckPassword(TextInputLayout password){
        if(password.getEditText().getText().length()<1) {
            password.setHelperText(getResources().getString(R.string.Required));
            return false;
        }
        else if(password.getEditText().getText().length()<6){
            password.setHelperText(getResources().getString(R.string.MinimumChars));
            return false;
        }
        else
            password.setHelperText("");
        return true;
    }
    private void CheckValues(){
        if(TextInputLayoutFirstName.getEditText().getText().length()<1)
            TextInputLayoutFirstName.setHelperText(getResources().getString(R.string.Required));
        else
            TextInputLayoutFirstName.setHelperText("");
        if(TextInputLayoutLastName.getEditText().getText().length()<1)
            TextInputLayoutLastName.setHelperText(getResources().getString(R.string.Required));
        else
            TextInputLayoutLastName.setHelperText("");
        if(TextInputLayoutEmail.getEditText().getText().length()<1)
            TextInputLayoutEmail.setHelperText(getResources().getString(R.string.Required));
        else if(!isEmailValid(TextInputLayoutEmail.getEditText().getText().toString()))
            TextInputLayoutEmail.setHelperText(getResources().getString(R.string.InvalidEmail));
        else
            TextInputLayoutEmail.setHelperText("");
        if(TextInputLayoutPassword.getEditText().getText().length()<1)
            TextInputLayoutPassword.setHelperText(getResources().getString(R.string.Required));
        else if(TextInputLayoutPassword.getEditText().getText().length()<6)
            TextInputLayoutPassword.setHelperText(getResources().getString(R.string.MinimumChars));
        else
            TextInputLayoutPassword.setHelperText("");
        if(TextInputLayoutPasswordConfirm.getEditText().getText().length()<1)
            TextInputLayoutPasswordConfirm.setHelperText(getResources().getString(R.string.Required));
        else if(TextInputLayoutPasswordConfirm.getEditText().getText().length()<6)
            TextInputLayoutPasswordConfirm.setHelperText(getResources().getString(R.string.MinimumChars));
        else
            TextInputLayoutPasswordConfirm.setHelperText("");
    }
    private void CreateAccountCheck(){
        ButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValues();
                if(TextInputLayoutEmail.getEditText().getText().length() > 0 && TextInputLayoutPassword.getEditText().getText().length() > 5 && TextInputLayoutPasswordConfirm.getEditText().getText().length() > 5 &&
                        isEmailValid(TextInputLayoutEmail.getEditText().getText().toString()) && TextInputLayoutFirstName.getEditText().getText().length() > 0 && TextInputLayoutLastName.getEditText().getText().length() > 0){
                    if(!(TextInputLayoutPassword.getEditText().getText().toString().equals(TextInputLayoutPasswordConfirm.getEditText().getText().toString())))
                        TextInputLayoutPasswordConfirm.setHelperText(getResources().getString(R.string.InvalidPassword));
                    else{
                        loading = new Loading(view.getContext());
                        firebaseAuth.fetchSignInMethodsForEmail(TextInputLayoutEmail.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                if(task.isSuccessful()) {
                                    if (!task.getResult().getSignInMethods().isEmpty()) {
                                        loading.stop();
                                        TextInputLayoutEmail.setHelperText(getResources().getString(R.string.EmailExist));
                                    }
                                    else {
                                        TextInputLayoutEmail.setHelperText("");
                                        CreateAccount();
                                    }
                                }
                                else {
                                    loading.stop();
                                    new PopUpMSG(view.getContext(), getResources().getString(R.string.Error), getResources().getString(R.string.ErrorMSG));
                                }
                            }
                        });
                    }
                }
            }
        });
    }
    private void CreateAccount(){
        user = new User(TextInputLayoutEmail.getEditText().getText().toString(), TextInputLayoutFirstName.getEditText().getText().toString(), TextInputLayoutLastName.getEditText().getText().toString());
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(),TextInputLayoutPassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            user.setUid(firebaseAuth.getUid());
                            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                            loading.stop();
                            new PopUpMSG(view.getContext(), getResources().getString(R.string.CreateAccount), getResources().getString(R.string.CompleteCreateAccount), MainActivity.class);
                        }
                    });
                }
                else {
                    loading.stop();
                    new PopUpMSG(view.getContext(), getResources().getString(R.string.Error), getResources().getString(R.string.ErrorMSG));
                }
            }
        });
    }
}