package com.example.keera.pharmacie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonClickListner();
    }

    public void checkBoxClick(View view){
        RadioButton rd = (RadioButton) view;
        TextView tv = (TextView) findViewById(R.id.titrequartier);
        Spinner sp = (Spinner) findViewById(R.id.selectquartier);

        if(rd.getId() == R.id.quartier){
            sp.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
        }else if(rd.getId() == R.id.tout){
            sp.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
        }
    }
    public void addButtonClickListner()
    {
        Button btnNavigator = (Button)findViewById(R.id.search);
        btnNavigator.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg)
            {
                Intent intent = new Intent(MainActivity.this, testini.class);
                startActivity(intent);
            }
        });
    }
  /*  public void checkBoxClick1(View view){
       RadioButton rd = (RadioButton) view;
        if(rd.getId() == R.id.tout){
            Intent intent = new Intent(this, Accueil.class);
            startActivity(intent);
        }else if(rd.getId() == R.id.quartier){

        }
    }*/
}
