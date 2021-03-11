package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OCEntry(View view){
        Intent intent=new Intent(MainActivity.this, EntryData.class);
        startActivity(intent);
    }
    public void OCTampil(View view){
        Intent intent=new Intent(MainActivity.this, tampil.class);
        startActivity(intent);
    }
    public void OCTentang(View view){
        Intent intent=new Intent(MainActivity.this, tentang.class);
        startActivity(intent);
    }

}