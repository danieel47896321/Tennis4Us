package com.example.tennis4us.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tennis4us.Adapters.GameAdapter;
import com.example.tennis4us.Class.FourPlayers;
import com.example.tennis4us.Class.Player;
import com.example.tennis4us.Class.User;
import com.example.tennis4us.R;

import java.util.ArrayList;

public class Game2vs2 extends AppCompatActivity {
    private TextView Title;
    private ImageView BackIcon;
    private RecyclerView recyclerView;
    private Intent intent;
    private User user = new User();
    private FourPlayers fourPlayers = new FourPlayers();
    private ArrayList<Player> players;
    private ConstraintLayout Field;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1vs1);
        init();
    }
    private void init(){
        setID();
        BackIcon();
        setPlayers();
    }
    private void setID(){
        intent = getIntent();
        players = new ArrayList<>();
        user = (User)intent.getSerializableExtra("user");
        fourPlayers = (FourPlayers) intent.getSerializableExtra("4Players");
        recyclerView = findViewById(R.id.recyclerView);
        BackIcon = findViewById(R.id.BackIcon);
        Field = findViewById(R.id.Field);
        Title = findViewById(R.id.Title);
        Title.setText(getResources().getString(R.string.Game2vs2));

    }
    private void setPlayers(){
        players.add(new Player(fourPlayers.getTeam1Names(), "0", "0"));
        players.add(new Player(fourPlayers.getTeam2Names(), "0", "0"));
        ShowTags(players);
    }
    private void ShowTags(ArrayList<Player> PlayersList){
        GameAdapter game2vs2Adapter = new GameAdapter(this, PlayersList, user, Field, Integer.valueOf(fourPlayers.getGame()), "2 VS 2");
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(game2vs2Adapter);
    }
    private void BackIcon(){
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });
    }
    public void onBackPressed() {
        intent = new Intent(Game2vs2.this, Home.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}