package com.example.tennis4us.Guest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tennis4us.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private TextView Title;
    private ImageView BackIcon;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter fragmentPager;
    private String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        setID();
    }
    private void setID(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);
        BackIcon = findViewById(R.id.BackIcon);
        Title = findViewById(R.id.Title);
        Title.setText("");
        BackIcon.setVisibility(View.GONE);
        fragmentPager = new ViewPagerAdapter(MainActivity.this);
        viewPager2.setAdapter(fragmentPager);
        titles = new String[2];
        titles[0] = getResources().getString(R.string.SignIn);
        titles[1] = getResources().getString(R.string.CreateAccount);
        new TabLayoutMediator(tabLayout,viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
    }
    public static class ViewPagerAdapter extends FragmentStateAdapter {
        private String[] titles = {"Sign In", "Create Account"};
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) { super(fragmentActivity); }
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