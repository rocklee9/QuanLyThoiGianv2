package com.gameloft.pc.quanlythoigian;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_friday;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_monday;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_saturday;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_sunday;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_thursday;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_tuesday;
import com.gameloft.pc.quanlythoigian.TabFragment.TabFragment_wednesday;

/**
 * Created by HOAN on 10/12/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabFragment_monday tabFragment_monday = new TabFragment_monday();
                return tabFragment_monday;
            case 1:
                TabFragment_tuesday tabFragment_tuesday = new TabFragment_tuesday();
                return tabFragment_tuesday;
            case 2:
                TabFragment_wednesday tabFragment_wednesday = new TabFragment_wednesday();
                return tabFragment_wednesday;
            case 3:
                TabFragment_thursday tabFragment_thursday = new TabFragment_thursday();
                return tabFragment_thursday;
            case 4:
                TabFragment_friday tabFragment_friday = new TabFragment_friday();
                return tabFragment_friday;
            case 5:
                TabFragment_saturday tabFragment_saturday = new TabFragment_saturday();
                return tabFragment_saturday;
            case 6:
                TabFragment_sunday tabFragment_sunday = new TabFragment_sunday();
                return tabFragment_sunday;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
