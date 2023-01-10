package com.example.tennis4us.User;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tennis4us.Adapters.HomeAdapter;
import com.example.tennis4us.Class.Tag;
import com.example.tennis4us.Class.User;
import com.example.tennis4us.MainActivity;
import com.example.tennis4us.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private TextView Title;
    private ImageView BackIcon;
    private User user = new User();
    private RecyclerView recyclerView;
    private ArrayList<Tag> tags;
    private Intent intent;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String HomeTagNames[] ;
    private int TagPhotos[] = {R.drawable.tennis, R.drawable.tennis};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }
    private void init(){
        setID();
        setTags();
        SignOutIcon();
    }
    private void setID(){
        intent = getIntent();
        tags = new ArrayList<>();
        user = (User)intent.getSerializableExtra("user");
        BackIcon = findViewById(R.id.BackIcon);
        BackIcon.setImageResource(R.drawable.signout);
        BackIcon.setRotation(180);
        Title = findViewById(R.id.Title);
        Title.setText(getResources().getString(R.string.Home));
        recyclerView = findViewById(R.id.recyclerView);
        HomeTagNames = new String[TagPhotos.length];
        HomeTagNames = getResources().getStringArray(R.array.HomeTagsName);
    }
    private void SignOutIcon(){
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });
    }
    private void setTags(){
        for(int i = 0; i< HomeTagNames.length; i++)
            tags.add(new Tag(HomeTagNames[i], TagPhotos[i]));
        ShowTags(tags);
    }
    private void ShowTags(ArrayList<Tag> HomeList){
        HomeAdapter homeAdapter = new HomeAdapter(this,HomeList,user);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(homeAdapter);
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setTitle(getResources().getString(R.string.SignOut)).setMessage(getResources().getString(R.string.AreYouSure)).setCancelable(true)
                .setPositiveButton(getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(firebaseAuth.getCurrentUser() != null)
                            firebaseAuth.signOut();
                        startActivity(new Intent(Home.this, MainActivity.class));
                        finish();
                    }
                }).setNegativeButton(getResources().getString(R.string.No), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                }).show();
    }
}