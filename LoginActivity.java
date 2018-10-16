package com.emapel.seeyou.seeyou;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.emapel.seeyou.seeyou.signIn.FragmentSignIn;
import com.emapel.seeyou.seeyou.signIn.FragmentSignUp;
import com.emapel.seeyou.seeyou.signIn.PageAdapterLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements FragmentSignIn.OnFragmentInteractionListener, FragmentSignUp.OnFragmentInteractionListener{
    private ViewPager viewPager;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewPager = findViewById(R.id.vpLogin);
        TabLayout tabLayout = findViewById(R.id.tabLogin);
        tabLayout.getTabAt(0).setText("LOGAR");
        tabLayout.getTabAt(1).setText("REGISTAR");
        PageAdapterLogin pageAdapter = new PageAdapterLogin(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        // LOGIN
//        userEmail = findViewById(R.id.etLoginUser);
//        userPass = findViewById(R.id.etLoginPass);
//
          mAuth = FirebaseAuth.getInstance();
//
//        Button btnAcess = findViewById(R.id.btnLoginAcess);



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


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
