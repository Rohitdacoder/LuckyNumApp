package com.example.luckynumapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Receiving data from MainActivity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        //Generating Random Number
        int Random_num = GeneratRandomNum();
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(""+Random_num);

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, Random_num);
            }
        });

    }

    public int GeneratRandomNum(){
        Random random = new Random();
        int UpperLimit = 1000;

        int randomNumberGenerator = random.nextInt(UpperLimit);
        return randomNumberGenerator;
    }

    public void shareData(String userName , int Random_num)
    {
        //Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        //Additional Info when we share our result
        i.putExtra(Intent.EXTRA_SUBJECT,userName+"got lucky today");
        i.putExtra(Intent.EXTRA_TEXT,"His/Her Lucky Number is: "+Random_num);

        startActivity(Intent.createChooser(i,"Choose a Platfrom"));
    }

}