package com.example.trivialy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                //startActivityForResult(newIntent, 0);
                //MainActivity.this.startActivity(newIntent);
                view.getContext().startActivity(newIntent);
            }
        });
        Toolbar topToolbar = findViewById(R.id.topToolbar);
        topToolbar.setTitle("");
        setSupportActionBar(topToolbar);
    }
}