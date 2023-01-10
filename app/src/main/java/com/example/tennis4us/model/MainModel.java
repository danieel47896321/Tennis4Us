package com.example.tennis4us.model;

import androidx.lifecycle.ViewModel;
import com.example.tennis4us.R;

public class MainModel extends ViewModel {
    private int[] titles = {R.string.SignIn, R.string.CreateAccount};
    public int[] getTitles() {return titles;}
}
