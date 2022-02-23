package com.example.c1943625;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//This is how the tabs change from fragment to fragment.
public class TabsAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public TabsAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HFragment hFragment = new HFragment();
                return hFragment;
            case 1:
                UsefulLinkFragment usefulLinkFragment = new UsefulLinkFragment();
                return usefulLinkFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
