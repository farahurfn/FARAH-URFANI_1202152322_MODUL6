package com.example.android.farah_1202152322_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //deklarasi variable
    private EditText username, password;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisiasi variable
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    //method button Daftar
    public void Daftar(View view) {
        (firebaseAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show(); // jika sukses, maka akan keluar toast "Registration Successfull"
                        }
                        else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    //method button Masuk
    public void Login(View view) {
        (firebaseAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}