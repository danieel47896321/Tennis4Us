package com.example.tennis4us.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tennis4us.Class.FourPlayers;
import com.example.tennis4us.Class.Tag;
import com.example.tennis4us.Class.TwoPlayers;
import com.example.tennis4us.Class.User;
import com.example.tennis4us.R;
import com.example.tennis4us.User.Game1vs1;
import com.example.tennis4us.User.Game2vs2;
import com.example.tennis4us.User.Home;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private List<Tag> tags;
    private User user;
    private TwoPlayers twoPlayers;
    private FourPlayers fourPlayers;
    private Intent intent;
    private TextInputLayout TextInputLayoutPlayer1, TextInputLayoutPlayer2, TextInputLayoutPlayer3, TextInputLayoutPlayer4, TextInputLayoutGames;
    private Button ButtonOK, ButtonCANCEL;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public HomeAdapter(Context context, List<Tag> tags, User user) {
        this.context = context;
        this.tags = tags;
        this.user = user;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TagName;
        ImageView TagImage;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TagName = itemView.findViewById(R.id.TagName);
            TagImage = itemView.findViewById(R.id.TagImage);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }

    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.tag_view, parent, false);
        return new HomeAdapter.MyViewHolder(view);
    }

    @SuppressLint("ResourceType")
    public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int position) {
        holder.TagName.setText(tags.get(position).getTagName());
        holder.TagImage.setImageResource(tags.get(position).getPhoto());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, Home.class);
                if (holder.TagName.getText().equals(context.getResources().getString(R.string.Game1vs1))) {
                    intent = new Intent(context, Game1vs1.class);
                    Get2PlayersDialog();

                } else if (holder.TagName.getText().equals(context.getResources().getString(R.string.Game2vs2))) {
                    intent = new Intent(context, Game2vs2.class);
                    Get4PlayersDialog();
                }
            }
        });
    }
    private void Get2PlayersDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_1vs1,null);
        builder.setCancelable(false);
        builder.setView(dialogView);
        TextInputLayoutPlayer1 = dialogView.findViewById(R.id.TextInputLayoutPlayer1);
        TextInputLayoutPlayer2 = dialogView.findViewById(R.id.TextInputLayoutPlayer2);
        TextInputLayoutGames = dialogView.findViewById(R.id.TextInputLayoutGames);
        ButtonOK = dialogView.findViewById(R.id.ButtonOK);
        ButtonCANCEL = dialogView.findViewById(R.id.ButtonCANCEL);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButtonCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { alertDialog.cancel(); }
        });
        ButtonOK.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(TextInputLayoutPlayer1.getEditText().getText().toString().equals(""))
                    TextInputLayoutPlayer1.setHelperText(context.getResources().getString(R.string.Required));
                else
                    TextInputLayoutPlayer1.setHelperText("");
                if(TextInputLayoutPlayer2.getEditText().getText().toString().equals(""))
                    TextInputLayoutPlayer2.setHelperText(context.getResources().getString(R.string.Required));
                else
                    TextInputLayoutPlayer2.setHelperText("");
                if(TextInputLayoutGames.getEditText().getText().toString().equals(""))
                    TextInputLayoutGames.setHelperText(context.getResources().getString(R.string.Required));
                else if(Integer.valueOf(TextInputLayoutGames.getEditText().getText().toString()) < 1){
                    TextInputLayoutGames.setHelperText(context.getResources().getString(R.string.MoreThanOne));
                }
                else
                    TextInputLayoutGames.setHelperText("");
                if(!(TextInputLayoutPlayer1.getEditText().getText().toString().equals("")) && !(TextInputLayoutPlayer2.getEditText().getText().toString().equals("")) &&
                        !(TextInputLayoutGames.getEditText().getText().toString().equals("")) && Integer.valueOf(TextInputLayoutGames.getEditText().getText().toString()) > 0) {
                    alertDialog.cancel();
                    twoPlayers = new TwoPlayers(TextInputLayoutPlayer1.getEditText().getText().toString(),TextInputLayoutPlayer2.getEditText().getText().toString(),TextInputLayoutGames.getEditText().getText().toString());
                    intent.putExtra("2Players", twoPlayers);
                    intent.putExtra("user", user);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }
        });
    }
    private void Get4PlayersDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_2vs2,null);
        builder.setCancelable(false);
        builder.setView(dialogView);
        TextInputLayoutPlayer1 = dialogView.findViewById(R.id.TextInputLayoutPlayer1);
        TextInputLayoutPlayer2 = dialogView.findViewById(R.id.TextInputLayoutPlayer2);
        TextInputLayoutPlayer3 = dialogView.findViewById(R.id.TextInputLayoutPlayer3);
        TextInputLayoutPlayer4 = dialogView.findViewById(R.id.TextInputLayoutPlayer4);
        TextInputLayoutGames = dialogView.findViewById(R.id.TextInputLayoutGames);
        ButtonOK = dialogView.findViewById(R.id.ButtonOK);
        ButtonCANCEL = dialogView.findViewById(R.id.ButtonCANCEL);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButtonCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { alertDialog.cancel(); }
        });
        ButtonOK.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(TextInputLayoutPlayer1.getEditText().getText().toString().equals(""))
                    TextInputLayoutPlayer1.setHelperText(context.getResources().getString(R.string.Required));
                else
                    TextInputLayoutPlayer1.setHelperText("");
                if(TextInputLayoutPlayer2.getEditText().getText().toString().equals(""))
                    TextInputLayoutPlayer2.setHelperText(context.getResources().getString(R.string.Required));
                else
                    TextInputLayoutPlayer2.setHelperText("");
                if(TextInputLayoutPlayer3.getEditText().getText().toString().equals(""))
                    TextInputLayoutPlayer3.setHelperText(context.getResources().getString(R.string.Required));
                else
                    TextInputLayoutPlayer3.setHelperText("");
                if(TextInputLayoutPlayer4.getEditText().getText().toString().equals(""))
                    TextInputLayoutPlayer4.setHelperText(context.getResources().getString(R.string.Required));
                else
                    TextInputLayoutPlayer4.setHelperText("");
                if(TextInputLayoutGames.getEditText().getText().toString().equals(""))
                    TextInputLayoutGames.setHelperText(context.getResources().getString(R.string.Required));
                else if(Integer.valueOf(TextInputLayoutGames.getEditText().getText().toString()) < 1){
                    TextInputLayoutGames.setHelperText(context.getResources().getString(R.string.MoreThanOne));
                }
                else
                    TextInputLayoutGames.setHelperText("");
                if(!(TextInputLayoutPlayer1.getEditText().getText().toString().equals("")) && !(TextInputLayoutPlayer2.getEditText().getText().toString().equals("")) &&
                        !(TextInputLayoutPlayer3.getEditText().getText().toString().equals("")) && !(TextInputLayoutPlayer4.getEditText().getText().toString().equals("")) &&
                        !(TextInputLayoutGames.getEditText().getText().toString().equals("")) && Integer.valueOf(TextInputLayoutGames.getEditText().getText().toString()) > 0) {
                    alertDialog.cancel();
                    fourPlayers = new FourPlayers(TextInputLayoutPlayer1.getEditText().getText().toString(),TextInputLayoutPlayer2.getEditText().getText().toString(), TextInputLayoutPlayer3.getEditText().getText().toString(), TextInputLayoutPlayer4.getEditText().getText().toString(),TextInputLayoutGames.getEditText().getText().toString());
                    intent.putExtra("4Players", fourPlayers);
                    intent.putExtra("user", user);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }
        });
    }
    public int getItemCount() {
        return tags.size();
    }
}
