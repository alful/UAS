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

public class detail extends AppCompatActivity {
    DatabaseReference databaseReference;
    String Skey, Skode, Snamakuliah, SNHuruf;
    Double SNAngka;//int SNAngka
    TextView TVkode, TVMataKuliah, TVNAngka, TVNHuruf;
    Button BHapus, BBatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("khs");
        TVkode=findViewById(R.id.idTVKode);
        TVMataKuliah=findViewById(R.id.idTVMataKuliah);
        TVNAngka=findViewById(R.id.idTVNAngka);
        TVNHuruf=findViewById(R.id.idTVNHuruf);

        BHapus=findViewById(R.id.idBHapus);
        BBatal=findViewById(R.id.idBBatal);

        Intent intent = getIntent();
        Skey = intent.getStringExtra("key");
        Skode = intent.getStringExtra("kodemk");
        Snamakuliah = intent.getStringExtra("matakuliah");
        SNAngka = intent.getDoubleExtra("nilai_angka", 0);
        SNHuruf = intent.getStringExtra("nilai_huruf");

        TVkode.setText(Skode);
        TVMataKuliah.setText(Snamakuliah);
        TVNAngka.setText(""+SNAngka);
        TVNHuruf.setText(SNHuruf);

        BHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HapusData();
            }
        });

        BBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(detail.this, tampil.class);
                startActivity(intent);
            }
        });
    }

        private void HapusData(){
            databaseReference.child(Skey).removeValue();
            Toast.makeText(detail.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(detail.this, tampil.class);
            startActivity(intent);
        }


}