package com.example.core;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class QuestionPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    public QuestionPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragmet(Fragment fragment) {
        fragmentList.add(fragment);
    }

}
