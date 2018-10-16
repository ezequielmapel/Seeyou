package com.emapel.seeyou.seeyou.signIn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapterLogin extends FragmentStatePagerAdapter {
    private int numberTabs;

    public PageAdapterLogin(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentSignIn();

            case 1:

                return new FragmentSignUp();

                default:
                    return  new FragmentSignIn();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
