package com.example.tennis4us;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tennis4us.controller.MainController;
import com.example.tennis4us.model.MainModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private TextView Title;
    private ImageView BackIcon;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MainController mainController;
    private MainModel mainModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        mainModel = new ViewModelProvider(this).get(MainModel.class);
        mainController = new MainController(mainModel, this);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        BackIcon = findViewById(R.id.BackIcon);
        Title = findViewById(R.id.Title);
        mainController.setView();
    }
    public void setHeader(String title, int view) {
        Title.setText(title);
        BackIcon.setVisibility(view);
    }
    public void setFragment(MainController.ViewPagerAdapter fragmentPager, String[] titles) {
        viewPager2.setAdapter(fragmentPager);
        new TabLayoutMediator(tabLayout,viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
    }
}