package com.example.tennis4us.controller;

import android.view.View;

import com.example.tennis4us.Class.PopUpMSG;
import com.example.tennis4us.Guest.ResetPassword;
import com.example.tennis4us.MainActivity;
import com.example.tennis4us.R;
import com.example.tennis4us.model.ResetPasswordModel;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordController {
    private ResetPasswordModel resetPasswordModel;
    private ResetPassword view;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public ResetPasswordController(ResetPasswordModel resetPasswordModel, ResetPassword view) {
        super();
        this.resetPasswordModel = resetPasswordModel;
        this.view = view;
    }
    public void setView() {
        int title = resetPasswordModel.getTitle();
        view.setTitle(title);
    }
    public void BackIcon() {
        view.StartActivity(MainActivity.class);
    }
    public void ClearText() {
        String text = "";
        view.Clear(text);
    }
    public void ResetPassword(String email) {
        if(email != null && !email.isEmpty()){
            if(!isEmailValid(email)){
                String InvalidEmail = view.getResources().getString(R.string.InvalidEmail);
                view.EmailHelperText(InvalidEmail);
            } else {
                view.setProgressBar(View.VISIBLE);
                firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener( task -> {
                    if(task.isSuccessful()) {
                        if(task.getResult().getSignInMethods() != null && !task.getResult().getSignInMethods().isEmpty()) {
                            FirebaseAuth.getInstance().sendPasswordResetEmail(email);
                            String ResetPasswordTitle = view.getResources().getString(R.string.ResetPasswordTitle);
                            String ResetLink = view.getResources().getString(R.string.ResetLink);
                            view.setProgressBar(View.GONE);
                            new PopUpMSG(view, ResetPasswordTitle, ResetLink, MainActivity.class);
                        } else {
                            view.setProgressBar(View.GONE);
                            String EmailNotExist = view.getResources().getString(R.string.EmailNotExist);
                            view.EmailHelperText(EmailNotExist);
                        }
                    } else {
                        view.setProgressBar(View.GONE);
                        String Error = view.getResources().getString(R.string.Error);
                        String ErrorMSG = view.getResources().getString(R.string.ErrorMSG);
                        new PopUpMSG(view, Error, ErrorMSG);
                    }
                });
            }
        } else {
            String Required = view.getResources().getString(R.string.Required);
            view.EmailHelperText(Required);
        }
    }
    private boolean isEmailValid(CharSequence email) { return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); }
}