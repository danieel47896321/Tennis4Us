package com.example.tennis4us.controller;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.tennis4us.Guest.CreateAccount;
import com.example.tennis4us.Guest.SignIn;
import com.example.tennis4us.MainActivity;
import com.example.tennis4us.model.MainModel;

public class MainController {
    private MainModel mainModel;
    private MainActivity view;
    private ViewPagerAdapter fragmentPager;
    public MainController(MainModel mainModel, MainActivity view) {
        super();
        this.mainModel = mainModel;
        this.view = view;
    }
    public void setView() {
        String title = "";
        fragmentPager = new ViewPagerAdapter(view, mainModel.getTitles());
        view.setHeader(title, View.GONE);
        view.setFragment(fragmentPager,getTitles());
    }
    private String[] getTitles(){
        int TitleSize = mainModel.getTitles().length;
        String[] titles = new String[TitleSize];
        for(int i = 0; i < TitleSize; i++)
            titles[i] = view.getResources().getString(mainModel.getTitles()[i]);
        return titles;
    }
    public static class ViewPagerAdapter extends FragmentStateAdapter {
        private int[] titles;
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int[] titles) {
            super(fragmentActivity);
            this.titles = titles;
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new SignIn();
                case 1:
                    return new CreateAccount();
            }
            return new SignIn();
        }
        @Override
        public int getItemCount() { return titles.length; }
    }
}
