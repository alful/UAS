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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static java.sql.Types.NULL;

public class EntryData extends AppCompatActivity {
    DatabaseReference reference;
    EditText ETKode, ETNamaMatakuliah, ETSKS, ETNAngka, ETNHuruf, ETPredikat;
    TextView TVHuruf, TVPredikat;
    Button BSave, BCancel;
    ArrayList<Integer> arrayListNo=new ArrayList<>();
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_data);

        ETKode=findViewById(R.id.idETKode);
        ETNamaMatakuliah=findViewById(R.id.idETMataKuliah);
        ETSKS=findViewById(R.id.idETSKS);
        ETNAngka=findViewById(R.id.idETNAngka);
        ETNHuruf=findViewById(R.id.idETNHuruf);
        ETPredikat=findViewById(R.id.idETPredikat);

        TVHuruf=findViewById(R.id.idETNHuruf);
        TVPredikat=findViewById(R.id.idETPredikat);

        BSave=findViewById(R.id.idBSave);
        BCancel=findViewById(R.id.idBCancel);

        reference= FirebaseDatabase.getInstance().getReference().child("khs");

        for(Integer i=0; i<=7; i++){
            arrayListNo.add(i);
        }//max matkul

        ETNHuruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Double.parseDouble(ETNAngka.getText().toString())==0){
                    TVHuruf.setText("E");
                    TVPredikat.setText("Elek");
                }
                else if(Double.parseDouble(ETNAngka.getText().toString())>0&&Double.parseDouble(ETNAngka.getText().toString())<0.9){
                    TVHuruf.setText("D");
                    TVPredikat.setText("Delek");
                }
                else if(Double.parseDouble(ETNAngka.getText().toString())>0.9&&Double.parseDouble(ETNAngka.getText().toString())<1.9){
                    TVHuruf.setText("C");
                    TVPredikat.setText("Celek");
                }
                else if(Double.parseDouble(ETNAngka.getText().toString())>1.9&&Double.parseDouble(ETNAngka.getText().toString())<2.49){
                    TVHuruf.setText("BC");
                    TVPredikat.setText("BCelek");
                }
                else if(Double.parseDouble(ETNAngka.getText().toString())>2.49&&Double.parseDouble(ETNAngka.getText().toString())<2.9){
                    TVHuruf.setText("B");
                    TVPredikat.setText("Baik");
                }
                else if(Double.parseDouble(ETNAngka.getText().toString())>2.9&&Double.parseDouble(ETNAngka.getText().toString())<3.49){                    TVHuruf.setText("AB");
                    TVHuruf.setText("AB");
                    TVPredikat.setText("ApikBaik");
                }
                else if(Double.parseDouble(ETNAngka.getText().toString())>3.49&&Double.parseDouble(ETNAngka.getText().toString())<=4){
                    TVHuruf.setText("A");
                    TVPredikat.setText("Apik");
                }
                else{
                    TVHuruf.setText("Error");
                    TVPredikat.setText("Error");
                }
            }
        });


        BSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        index++;
//                        khs.setNo(arrayListNo.get(index));
//                        khs.setKodemk(ETKode.getText().toString().trim());
//                        khs.setMatakuliah(ETNamaMatakuliah.getText().toString().trim());
//                        khs.setSks(ETSKS.getText().toString().trim());
//                        khs.setNilai_angka(ETNAngka.getText().toString().trim());
//                        khs.setNilai_huruf(ETNHuruf.getText().toString().trim());
//                        khs.setPredikat(ETPredikat.getText().toString().trim());
                        //reference.push().setValue(khs);

                        reference.push().setValue(new KHS(
                                arrayListNo.get(index),
                                ETKode.getText().toString(),
                                ETNamaMatakuliah.getText().toString(),
                                Integer.parseInt(ETSKS.getText().toString()),
                                Double.parseDouble(ETNAngka.getText().toString()),
                                ETNHuruf.getText().toString(),
                                ETPredikat.getText().toString()));
            }
        });

        BCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EntryData.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}