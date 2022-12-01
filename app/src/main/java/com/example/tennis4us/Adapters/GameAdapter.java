package com.example.tennis4us.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tennis4us.Class.Player;
import com.example.tennis4us.Class.PopUpMSG;
import com.example.tennis4us.Class.User;
import com.example.tennis4us.R;
import com.example.tennis4us.User.Home;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {
    private Context context;
    private List<Player> players;
    private User user;
    private ConstraintLayout Field;
    private Boolean PLAYED = false;
    private int GAMES;
    private String Type;
    public GameAdapter(Context context, List<Player> players, User user, ConstraintLayout field, int games, String type) {
        this.context = context;
        this.players = players;
        this.user = user;
        this.Field = field;
        this.GAMES = games;
        this.Type = type;
        if(Type.equals("1 VS 1")) {
            Field.setBackground(context.getDrawable(R.drawable.field1));
        } else{
            Field.setBackground(context.getDrawable(R.drawable.field3));
        }
        int ID = 0;
        for (Player player : players){
            player.setID(ID++);
        }
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView PlayerName, PlayerGame, PlayerScore;
        ImageView Increase;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            PlayerName = itemView.findViewById(R.id.PlayerName);
            PlayerGame = itemView.findViewById(R.id.PlayerGame);
            PlayerScore = itemView.findViewById(R.id.PlayerScore);
            Increase = itemView.findViewById(R.id.Increase);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
    public GameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.scoreboard, parent, false);
        return new GameAdapter.MyViewHolder(view);
    }
    private Player getOtherPlayer(int ID){
        for (Player player : players) {
            if (player.getID() != ID) {
                return player;
            }
        }
        return players.get(0);
    }
    @SuppressLint("ResourceType")
    public void onBindViewHolder(@NonNull GameAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.PlayerName.setText(players.get(position).getName());
        holder.PlayerGame.setText(players.get(position).getGame());
        holder.PlayerScore.setText(players.get(position).getScore());
        holder.Increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(players.get(0).getGame()) < GAMES && Integer.valueOf(players.get(1).getGame()) < GAMES) {
                    PLAYED = !PLAYED;
                    if (PLAYED) {
                        if(Type.equals("1 VS 1")) {
                            Field.setBackground(context.getDrawable(R.drawable.field2));
                        }else{
                            Field.setBackground(context.getDrawable(R.drawable.field4));
                        }
                    } else {
                        if(Type.equals("1 VS 1")) {
                            Field.setBackground(context.getDrawable(R.drawable.field1));
                        } else{
                            Field.setBackground(context.getDrawable(R.drawable.field3));
                        }
                    }
                    String score = players.get(position).getScore();
                    Player otherPlayer = getOtherPlayer(position);
                    if (score.equals("0")) {
                        players.get(position).setScore("15");
                        holder.PlayerScore.setText(players.get(position).getScore());
                    } else if (score.equals("15")) {
                        players.get(position).setScore("30");
                        holder.PlayerScore.setText(players.get(position).getScore());
                    } else if (score.equals("30")) {
                        players.get(position).setScore("40");
                        holder.PlayerScore.setText(players.get(position).getScore());
                    } else if (score.equals("40")) {
                        if (otherPlayer.getScore().equals("A")) {
                            players.get(otherPlayer.ID).setScore("40");
                        } else if (otherPlayer.getScore().equals("40")) {
                            players.get(position).setScore("A");
                            holder.PlayerScore.setText(players.get(position).getScore());
                        }else{
                            players.get(0).setScore("0");
                            players.get(1).setScore("0");
                            players.get(position).setGame(String.valueOf(Integer.valueOf(players.get(position).getGame()) + 1));
                            if(Type.equals("1 VS 1")) {
                                Field.setBackground(context.getDrawable(R.drawable.field1));
                            } else{
                                Field.setBackground(context.getDrawable(R.drawable.field3));
                            }
                        }
                        notifyDataSetChanged();
                    } else if (score.equals("A")) {
                        players.get(0).setScore("0");
                        players.get(1).setScore("0");
                        players.get(position).setGame(String.valueOf(Integer.valueOf(players.get(position).getGame()) + 1));
                        if(Type.equals("1 VS 1")) {
                            Field.setBackground(context.getDrawable(R.drawable.field1));
                        } else{
                            Field.setBackground(context.getDrawable(R.drawable.field3));
                        }                        notifyDataSetChanged();
                    }
                    GameFinish();
                }
            }
        });
    }
    private void GameFinish(){
        if(Integer.valueOf(players.get(0).getGame()) == GAMES){
            if(Type.equals("1 VS 1")) {
                new PopUpMSG(context,context.getResources().getString(R.string.Finish),context.getResources().getString(R.string.Player) + " " + players.get(0).getName() + " " + context.getResources().getString(R.string.Won1), Home.class);
            }else{
                new PopUpMSG(context,context.getResources().getString(R.string.Finish),context.getResources().getString(R.string.Player) + " " + players.get(0).getName() + " " + context.getResources().getString(R.string.Won2), Home.class);
            }
        } else if(Integer.valueOf(players.get(1).getGame()) == GAMES){
            if(Type.equals("1 VS 1")) {
                new PopUpMSG(context, context.getResources().getString(R.string.Finish), context.getResources().getString(R.string.Player) + " " + players.get(1).getName() + " " + context.getResources().getString(R.string.Won1), Home.class);
            } else{
                new PopUpMSG(context, context.getResources().getString(R.string.Finish), context.getResources().getString(R.string.Player) + " " + players.get(1).getName() + " " + context.getResources().getString(R.string.Won2), Home.class);
            }
        }
    }
    public int getItemCount() {
        return players.size();
    }
}