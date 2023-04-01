package com.example.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.datastorage.model.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //config Realm
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                                    .allowQueriesOnUiThread(true)
                                    .allowWritesOnUiThread(true)
                                    .deleteRealmIfMigrationNeeded()
                                    .build();
        Realm.setDefaultConfiguration(config);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nama","Budi");
        editor.apply();

        Button btnAct1 = (Button) findViewById(R.id.Activity1);
        Button btnAct2 = (Button) findViewById(R.id.Activity2);
        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);
        Button btnCetak = (Button) findViewById(R.id.btnCetak);
        Button btninq = (Button) findViewById(R.id.btnInq2);

        btninq.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getApplicationContext(),UserInquiryActivity.class);
                   startActivity(intent);
               }
           }

        );

        btnAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("nama","Budi");
                startActivity(intent);
            }
        }

        );
        btnAct2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                   intent.putExtra("nama","Budi");
                   startActivity(intent);
               }
           }

        );
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tag","Primary Key Sudah Ada + " + "tes");
                tambahDataUser();
            }
        });

        btnCetak.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              cetakDataUser();
          }
      }

        );
    }

    public void tambahDataUser(){
        Realm realm = Realm.getDefaultInstance();
        //penyimpanan data
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    User user01 = realm.createObject(User.class,"23237273899");
                    user01.setNama("Budi");
                    User user02 = realm.createObject(User.class,"089506803074");
                    user02.setNama("Alex");
                }
                catch (RealmPrimaryKeyConstraintException e){
                    Log.d("Tag","Primary Key Sudah Ada + " + e.getMessage().toString());
                }

            }
        }
        );
        realm.close();
    }
    public void cetakDataUser(){
        Realm realm = Realm.getDefaultInstance();
        //penarikan data
        RealmResults<User> users =
                realm.where(User.class).findAll();
        //menapilkan data
        for(User user : users){
            Log.d("TAG","Nama :"+user.getNama()
                    + ", Nomor Telp"+ user.getNotlp());
        }
        realm.close();
    }
}