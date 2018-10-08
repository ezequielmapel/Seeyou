package com.emapel.seeyou.seeyou;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPass;
    private TextView tvSignUp;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        // LOGIN
        userEmail = findViewById(R.id.etLoginUser);
        userPass = findViewById(R.id.etLoginPass);

        mAuth = FirebaseAuth.getInstance();

        Button btnAcess = findViewById(R.id.btnLoginAcess);



        // CLICK IN BUTTON 'SIGN UP' - CADASTRAR
        tvSignUp = findViewById(R.id.tvLoginSignUp);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUp.class));
            }
        });

        // CLICK IN BUTTON 'ACCESS'  - ACESSAR
        btnAcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(userEmail.getText().toString(), userPass.getText().toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        updateUI(currentuser);
    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            Toast.makeText(this, "Você está logado!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else{
            Toast.makeText(this, "Você não está logado!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginUser(String userEmail, String userPass){
        mAuth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("LOGIN", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                    updateUI(null);
                }
            }
        });
    }
}
