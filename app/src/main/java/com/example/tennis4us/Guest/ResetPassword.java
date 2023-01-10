package com.example.tennis4us.Guest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.tennis4us.R;
import com.example.tennis4us.controller.ResetPasswordController;
import com.example.tennis4us.model.ResetPasswordModel;
import com.google.android.material.textfield.TextInputLayout;

public class ResetPassword extends AppCompatActivity {
    private Button ButtonFinish;
    private TextInputLayout TextInputLayoutEmail;
    private ImageView BackIcon;
    private TextView Title;
    private ProgressBar progressBar;
    private ResetPasswordModel resetPasswordModel;
    private ResetPasswordController resetPasswordController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        init();
    }
    private void init(){
        resetPasswordModel = new ViewModelProvider(this).get(ResetPasswordModel.class);
        resetPasswordController = new ResetPasswordController(resetPasswordModel, this);
        BackIcon = findViewById(R.id.BackIcon);
        Title = findViewById(R.id.Title);
        progressBar = findViewById(R.id.progressBar);
        ButtonFinish = findViewById(R.id.ButtonFinish);
        TextInputLayoutEmail = findViewById(R.id.TextInputLayoutEmail);
        resetPasswordController.setView();
        BackIcon();
        EndIcon();
        ButtonFinish();
    }
    public void setTitle(int title) { Title.setText(R.string.ResetPasswordTitle); }
    private void BackIcon(){ BackIcon.setOnClickListener( v -> resetPasswordController.BackIcon() );}
    private void EndIcon() { TextInputLayoutEmail.setEndIconOnClickListener( v -> resetPasswordController.ClearText() ) ;}
    @Override
    public void onBackPressed() { resetPasswordController.BackIcon(); }
    public void StartActivity(Class Destination){
        startActivity(new Intent(ResetPassword.this, Destination));
        finish();
    }
    public void Clear(String text){
        if(TextInputLayoutEmail.getEditText() != null) {
            TextInputLayoutEmail.setHelperText(text);
            TextInputLayoutEmail.getEditText().setText(text);
        }
    }
    private void ButtonFinish(){
        ButtonFinish.setOnClickListener( v -> {
            if(TextInputLayoutEmail.getEditText() != null)
                resetPasswordController.ResetPassword(TextInputLayoutEmail.getEditText().getText().toString());
        });
    }
    public void EmailHelperText(String text){ TextInputLayoutEmail.setHelperText(text); }
    public void setProgressBar(int view){ progressBar.setVisibility(view); }
}