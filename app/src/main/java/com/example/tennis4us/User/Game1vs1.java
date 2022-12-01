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
import com.example.tennis4us.Class.Player;
import com.example.tennis4us.Class.TwoPlayers;
import com.example.tennis4us.Class.User;
import com.example.tennis4us.R;

import java.util.ArrayList;

public class Game1vs1 extends AppCompatActivity {
    private TextView Title;
    private ImageView BackIcon;
    private RecyclerView recyclerView;
    private Intent intent;
    private User user = new User();
    private TwoPlayers twoPlayers = new TwoPlayers();
    private ConstraintLayout Field;
    private ArrayList<Player> players;
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
        twoPlayers = (TwoPlayers)intent.getSerializableExtra("2Players");
        recyclerView = findViewById(R.id.recyclerView);
        BackIcon = findViewById(R.id.BackIcon);
        Field = findViewById(R.id.Field);
        Title = findViewById(R.id.Title);
        Title.setText(getResources().getString(R.string.Game1vs1));
    }
    private void setPlayers(){
        players.add(new Player(twoPlayers.getPlayer1Name(), "0", "0"));
        players.add(new Player(twoPlayers.getPlayer2Name(), "0", "0"));
        ShowTags(players);
    }
    private void ShowTags(ArrayList<Player> PlayersList){
        GameAdapter gameAdapter = new GameAdapter(this, PlayersList, user, Field, Integer.valueOf(twoPlayers.getGame()), "1 VS 1");
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(gameAdapter);
    }
    private void BackIcon(){
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });
    }
    public void onBackPressed() {
        intent = new Intent(Game1vs1.this, Home.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}