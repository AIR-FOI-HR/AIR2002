package com.example.trivialy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DifficultyViewFreePlay extends AppCompatActivity {

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_menu);
        Intent i = getIntent();
        final String category = (String) i.getSerializableExtra("savedString");

        textView1 = (TextView) findViewById(R.id.probaid);
        textView1.setText(category);

        Button difficultyEasy = findViewById(R.id.difficultyEasy);
        difficultyEasy.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {

                                                  Intent newIntent = new Intent(getApplicationContext(), FreePlay.class);
                                                  newIntent.putExtra("category", category);
                                                  newIntent.putExtra("difficulty", "Easy");
                                                  DifficultyViewFreePlay.this.startActivity(newIntent);
                                                  finish();
                                              }
                                          }
        );


        Button difficultyMedium = findViewById(R.id.difficultyMedium);
        difficultyMedium.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                    Intent newIntent = new Intent(getApplicationContext(), FreePlay.class);
                                                    newIntent.putExtra("category", category);
                                                    newIntent.putExtra("difficulty", "Medium");
                                                    DifficultyViewFreePlay.this.startActivity(newIntent);
                                                    finish();
                                                }
                                            }
        );

        Button difficultyHard = findViewById(R.id.difficultyHard);
        difficultyHard.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {

                                                  Intent newIntent = new Intent(getApplicationContext(), FreePlay.class);
                                                  newIntent.putExtra("category", category);
                                                  newIntent.putExtra("difficulty", "Hard");
                                                  DifficultyViewFreePlay.this.startActivity(newIntent);
                                                  finish();
                                              }
                                          }
        );
    }

}
