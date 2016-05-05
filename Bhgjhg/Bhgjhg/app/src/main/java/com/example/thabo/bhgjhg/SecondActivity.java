package com.example.thabo.bhgjhg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String name;
        Intent i=getIntent();
        name=i.getStringExtra("name");
        TextView textView=(TextView)findViewById(R.id.WelcometextView);
        textView.setText("Welcome "+name);
        Button btnBack,btnClose;
        btnBack=(Button)findViewById(R.id.button);
        btnClose=(Button)findViewById(R.id.button2);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(SecondActivity.this,MainActivity.class);

                startActivity(i);


            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                System.exit(0);

            }
        });

    }
}
