package com.emapel.seeyou.seeyou.otherviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.emapel.seeyou.seeyou.MapsActivity;

public class PageAdapter extends FragmentStatePagerAdapter {
    int numberTabs;
    public PageAdapter(FragmentManager fm, int numberTabs) {
        super(fm);
        this.numberTabs = numberTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
               //HomeFragment home = new HomeFragment();
                MapsActivity home = new MapsActivity();
               return home;

            case 1:
                // ANTES DE ENTRAR AQUI, VERIFICAR SE ESTE CELULAR É O COM RASTREAMENTO DE ORIGEM
                // SE N FOR, N DEVE TER AS MESMAS OPÇOES QUE O SEEFRAGMENT
                SeeFragment see = new SeeFragment();
                return see;

            case 2:
                SettingsFragment settings = new SettingsFragment();
                return settings;

            default:
                return new HomeFragment();

        }


    }

    @Override
    public int getCount() {
        return 3;
    }
}
