package com.example.trivialy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.responses.Category;

public class DifficultyView extends AppCompatActivity {

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

                                                  Intent newIntent = new Intent(getApplicationContext(), TimeTrial.class);
                                                  newIntent.putExtra("category", category);
                                                  newIntent.putExtra("difficulty", "Easy");
                                                  DifficultyView.this.startActivity(newIntent);
                                                  finish();
                                              }
                                          }
        );


        Button difficultyMedium = findViewById(R.id.difficultyMedium);
        difficultyMedium.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                    Intent newIntent = new Intent(getApplicationContext(), TimeTrial.class);
                                                    newIntent.putExtra("category", category);
                                                    newIntent.putExtra("difficulty", "Medium");
                                                    DifficultyView.this.startActivity(newIntent);
                                                    finish();
                                                }
                                            }
        );

        Button difficultyHard = findViewById(R.id.difficultyHard);
        difficultyHard.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {

                                                  Intent newIntent = new Intent(getApplicationContext(), TimeTrial.class);
                                                  newIntent.putExtra("category", category);
                                                  newIntent.putExtra("difficulty", "Hard");
                                                  DifficultyView.this.startActivity(newIntent);
                                                  finish();
                                              }
                                          }
        );
    }

}
