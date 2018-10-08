package com.emapel.seeyou.seeyou;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplahActivity extends AppCompatActivity {
    private final int SPLASH_DELAY_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplahActivity.this.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_splah);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplahActivity.this.startActivity(new Intent(SplahActivity.this, LoginActivity.class));
                SplahActivity.this.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                SplahActivity.this.finish();
            }
        }, SPLASH_DELAY_TIME);

    }
}
