package com.example.trivialy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.responses.Category;

public class DifficultyView extends AppCompatActivity {

    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dificulty_menu);

        Intent  i = getIntent();
        String category = (String)i.getSerializableExtra("savedString");

        textView1= (TextView) findViewById(R.id.probaid);
        textView1.setText(category);
    }

}
