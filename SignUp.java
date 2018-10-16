package com.emapel.seeyou.seeyou;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.emapel.seeyou.seeyou.classes.User;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity{

    // ELEMENTS
    private Button btnNext;
    private Button btnBack;
    private ImageButton btnBackLogin;
    private ViewPager viewPager;
    private User user;
    // STRINGS
    private String nameUser, pass1User, pass2User;
    private String emailUser, cpfUser;

    // FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //EDIT TEXT





        //BUTTONS
        btnNext = findViewById(R.id.btnProxSignup);
        btnBack = findViewById(R.id.btnBackSignup);
        btnBackLogin = findViewById(R.id.btnBackLogin);

        //FIREBASE
        mAuth = FirebaseAuth.getInstance();

        // ADAPTER
        final TabLayout tabLayout = findViewById(R.id.tabsSignUp);

        viewPager = findViewById(R.id.viewpagerSignUp);
        //final PageAdapterSignUp pageAdapter = new PageAdapterSignUp(getSupportFragmentManager(), tabLayout.getTabCount());
        //viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                changeButton(btnNext, btnBack,btnBackLogin, tabLayout);

                if(tab.getPosition()==2){
                    //getStringFromElements(etNameUser, etPass1User, etPass2user, etEmailUser, etCpfUser);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition()+1);
                changeButton(btnNext, btnBack,btnBackLogin, tabLayout);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition()-1);
                changeButton(btnNext, btnBack,btnBackLogin, tabLayout);
            }
        });

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void changeButton(Button btnNext,Button btnBack, ImageButton btnBackLogin, TabLayout tabLayout) {
        // This function change de button on SignUp activity
        if (tabLayout.getSelectedTabPosition() == 2) {
           btnNext.setVisibility(View.INVISIBLE);
           btnBackLogin.setVisibility(View.VISIBLE);

        }else if(tabLayout.getSelectedTabPosition() == 1){
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
            btnBackLogin.setVisibility(View.INVISIBLE);

            btnNext.setText("CONFIRMAR");
        } else{
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.INVISIBLE);
        }
    }

    private void getStringFromElements(EditText etNameUser, EditText etPass1User, EditText etPass2user, EditText etEmailUser, EditText etCpfUser){

        nameUser = etNameUser.getText().toString().trim();
        emailUser = etEmailUser.getText().toString().trim();
        pass1User = etPass1User.getText().toString().trim();
        pass2User = etPass2user.getText().toString().trim();
        cpfUser = etCpfUser.getText().toString().trim();

        checkPasswords(pass1User, pass2User);

        Toast.makeText(this, ""+nameUser, Toast.LENGTH_SHORT).show();
    }

    private void checkPasswords(String pass1User, String pass2User){
        if(pass1User != pass2User){
            Toast.makeText(this, "Senhas não conferem...", Toast.LENGTH_SHORT).show();
        }
    }

}
