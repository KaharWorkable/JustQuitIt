package com.khr.justquitit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class smoker_profile extends AppCompatActivity {

    TextView tvusername, tvemail, tvage, tvstartdate, tvenddate;
    Button btnupdate;
    String email;
    FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseDatabase database;
    DatabaseReference userRef;
    private static final String USERS = "UserData";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoker_profile);

        //Intent intent = getIntent();
        //email = intent.getStringExtra("email");

        tvusername = findViewById(R.id.tv_username);
        tvemail = findViewById(R.id.tv_email);
        tvage = findViewById(R.id.tv_age);
        tvstartdate = findViewById(R.id.tv_startdate);
        tvenddate = findViewById(R.id.tv_enddate);
        btnupdate = findViewById(R.id.btn_update);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference();

        //show user information
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //successfully
                /*for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.child("email").getValue().equals(email)){
                        tvusername.setText(ds.child("username").getValue(String.class));
                        tvemail.setText(ds.child("email").getValue(String.class));
                        tvage.setText(ds.child("age").getValue(String.class));
                        tvstartdate.setText(ds.child("startDate").getValue(String.class));
                        tvenddate.setText(ds.child("endDate").getValue(String.class));
                    }
                }*/
                String username = dataSnapshot.child("UserData").child(fUser.getUid()).child("username").getValue(String.class);
                tvusername.setText(username);

                String email = dataSnapshot.child("UserData").child(fUser.getUid()).child("email").getValue(String.class);
                tvemail.setText(email);

                String age = dataSnapshot.child("UserData").child(fUser.getUid()).child("age").getValue(String.class);
                tvage.setText(age);
                String startdate = dataSnapshot.child("UserData").child(fUser.getUid()).child("startDate").getValue(String.class);
                tvstartdate.setText(startdate);
                String enddate = dataSnapshot.child("UserData").child(fUser.getUid()).child("endDate").getValue(String.class);
                tvenddate.setText(enddate);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}