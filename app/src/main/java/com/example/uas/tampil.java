package com.example.uas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

public class tampil extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    TextView TVIPK, TVTotal,TVJumlahMatkul;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayListKHS=new ArrayList<>();
    ArrayList<String> arrayKey=new ArrayList<>();
    ArrayList<Integer> arrayNo=new ArrayList<>();
    ArrayList<String> arraykode=new ArrayList<>();
    ArrayList<String> arraymatakuliah=new ArrayList<>();
    ArrayList<Integer> arraysks=new ArrayList<>();
    ArrayList<Double> arraynilaiangka=new ArrayList<>();
    ArrayList<String> arraynilaihuruf=new ArrayList<>();
    ArrayList<String> arraypredikat=new ArrayList<>();

    ArrayList<KHS> khsArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        listView=findViewById(R.id.idLVdatakhs);
        TVIPK=findViewById(R.id.idTVIPK);
        TVTotal=findViewById(R.id.idTVTotal);
        TVJumlahMatkul=findViewById(R.id.idTVJumlahMatKul);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("khs");
        arrayAdapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,arrayListKHS);
        listView.setAdapter(arrayAdapter);

//        Customadapter customadapter=new Customadapter(tampil.this, khsArrayList);
//        listView.setAdapter(customadapter);

        databaseReference.addChildEventListener(new ChildEventListener() {//referensi firebase
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String hasil=snapshot.getValue(KHS.class).toPrint();
                Integer no=snapshot.getValue(KHS.class).getNo();
                String kodemk=snapshot.getValue(KHS.class).getKodemk();
                String matakuliah=snapshot.getValue(KHS.class).getMatakuliah();
                Integer sks=snapshot.getValue(KHS.class).getSks();
                Double nilai_angka=snapshot.getValue(KHS.class).getNilai_angka();
                String nilai_huruf=snapshot.getValue(KHS.class).getNilai_huruf();
                String predikat=snapshot.getValue(KHS.class).getPredikat();
                String key=snapshot.getKey();//ambil push() key

                KHS khs=new KHS(no, kodemk, matakuliah, sks, nilai_angka, nilai_huruf, predikat);
                khsArrayList.add(khs);

                arrayKey.add(key);
                arrayNo.add(no);
                arraykode.add(kodemk);
                arraymatakuliah.add(matakuliah);
                arraysks.add(sks);
                arraynilaiangka.add(nilai_angka);
                arraynilaihuruf.add(nilai_huruf);
                arraypredikat.add(predikat);
                arrayListKHS.add(hasil);

                int jsks=0;
                for(int i=0; i<arraysks.size(); i++){
                    jsks=jsks+arraysks.get(i);
                }
                TVTotal.setText(""+jsks); //TVTotal.setText(String.valueOf(jsks));

                int max=arrayNo.get(0);
                for(int i=0; i<arrayNo.size(); i++){
                    if (arrayNo.get(i)>max){
                        max=arrayNo.get(i);
                    }
                }
                TVJumlahMatkul.setText(""+max);

                Double jnilai=0.00;
                for(int i=0; i<arraynilaiangka.size(); i++){
                    jnilai=jnilai+(arraynilaiangka.get(i)*arraysks.get(i));//jnilai=jnilai+arraynilaiangka.get(i);
                }
                Double ipk=jnilai/jsks;
                TVIPK.setText(""+ipk);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String hasil=snapshot.getValue(KHS.class).toPrint();
                String key=snapshot.getKey();
                int noindex=arrayKey.indexOf(key);
                arrayListKHS.set(noindex, hasil);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key=snapshot.getKey();
                int noindex=arrayKey.indexOf(key);
                arrayListKHS.remove(noindex);
                arrayKey.remove(noindex);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //final String selectedFromList=arrayList.get(position);
                final String SListKeyDPilihan=arrayKey.get(position);
                final String SListkodeDPilihan=arraykode.get(position);
                final String SListmatakuliahDPilihan=arraymatakuliah.get(position);
                final Integer SListsksDPilihan=arraysks.get(position);
                final Double SListnangkaDPilihan=arraynilaiangka.get(position);
                final String SListnhurufDPilihan=arraynilaihuruf.get(position);
                final String SListpredikatDPilihan=arraypredikat.get(position);
                databaseReference.orderByChild(SListKeyDPilihan).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String keyd=dataSnapshot.child(SListKeyDPilihan).getKey();
                            Intent intent=new Intent(tampil.this, detail.class);
                            intent.putExtra("key",keyd);//bebas keyd / SListKeyDPilihan
                            intent.putExtra("kodemk",SListkodeDPilihan);
                            intent.putExtra("matakuliah",SListmatakuliahDPilihan);
                            intent.putExtra("sks",SListsksDPilihan);
                            intent.putExtra("nilai_angka",SListnangkaDPilihan);
                            Toast.makeText(tampil.this, ""+SListnangkaDPilihan, Toast.LENGTH_SHORT).show();
                            intent.putExtra("nilai_huruf",SListnhurufDPilihan);
                            intent.putExtra("predikat",SListpredikatDPilihan);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });


            }
public int sum(){
        int sum=0;
        for(int i=0; i<arrayNo.size(); i++){
            sum+=arrayNo.get(i);
        }
        return sum;
}
}