package com.example.trivialy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button singleplayerButton = findViewById(R.id.goToSingleplayerButton);
        singleplayerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent newIntent = new Intent(view.getContext(), SingleplayerMenu.class);
                view.getContext().startActivity(newIntent);
            }
        });

        Button multiplayerButton = findViewById(R.id.goToMultiplayerButton);
        multiplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(view.getContext(), Multiplayer_menu.class);
                view.getContext().startActivity(newIntent);
            }
        });
<<<<<<< HEAD
=======

        ImageButton aboutUsButton = findViewById(R.id.aboutUs_btn);
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(view.getContext(), InfoPage.class);
                view.getContext().startActivity(newIntent);
            }
        });
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
    }

    @Override
    public void onBackPressed() {
    }
}