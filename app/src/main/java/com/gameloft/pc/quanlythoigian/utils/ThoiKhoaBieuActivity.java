package com.gameloft.pc.quanlythoigian.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;

import com.gameloft.pc.quanlythoigian.R;
import com.gameloft.pc.quanlythoigian.adapters.ViewPagerAdapter;

public class ThoiKhoaBieuActivity extends AppCompatActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    TabHost tabHost;
    private ViewPagerAdapter adapter;
//    public DatabaseAdapter db;
 //   List<MonHoc> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_khoa_bieu);

        init();
        getWidgets();
        setWidgets();
        addWidgetsListener();

    }

    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
    }

    private void getWidgets() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabHost = (TabHost) findViewById(R.id.tab);

    }

    private void setWidgets() {
        viewPager.setAdapter(adapter);
        tabHost.setup();

        TabHost.TabSpec tab_mon = tabHost.newTabSpec("MON");
        tab_mon.setIndicator("", getResources().getDrawable(R.drawable.mon));
        tab_mon.setContent(new FakeContentTab(this));

        TabHost.TabSpec tab_tue = tabHost.newTabSpec("TUE");
        tab_tue.setIndicator("", getResources().getDrawable(R.drawable.tue));
        tab_tue.setContent(new FakeContentTab(this));

        TabHost.TabSpec tab_wed = tabHost.newTabSpec("WED");
        tab_wed.setIndicator("", getResources().getDrawable(R.drawable.wed));
        tab_wed.setContent(new FakeContentTab(this));

        TabHost.TabSpec tab_thu = tabHost.newTabSpec("THU");
        tab_thu.setIndicator("", getResources().getDrawable(R.drawable.thu));
        tab_thu.setContent(new FakeContentTab(this));

        TabHost.TabSpec tab_fri = tabHost.newTabSpec("FRI");
        tab_fri.setIndicator("", getResources().getDrawable(R.drawable.fri));
        tab_fri.setContent(new FakeContentTab(this));

        TabHost.TabSpec tab_sat = tabHost.newTabSpec("SAT");
        tab_sat.setIndicator("", getResources().getDrawable(R.drawable.sat));
        tab_sat.setContent(new FakeContentTab(this));

        TabHost.TabSpec tab_sun = tabHost.newTabSpec("SUN");
        tab_sun.setIndicator("", getResources().getDrawable(R.drawable.sun));
        tab_sun.setContent(new FakeContentTab(this));

        tabHost.addTab(tab_mon);
        tabHost.addTab(tab_tue);
        tabHost.addTab(tab_wed);
        tabHost.addTab(tab_thu);
        tabHost.addTab(tab_fri);
        tabHost.addTab(tab_sat);
        tabHost.addTab(tab_sun);

        tabHost.setOnTabChangedListener(this);
        viewPager.setOffscreenPageLimit(8);
        viewPager.setOnPageChangeListener(this);
    }

    private void addWidgetsListener() {
    }

    @Override
    public void onTabChanged(String s) {
        int position = tabHost.getCurrentTab();
        viewPager.setCurrentItem(position);
      //  list = db.getData(2);
     //   Toast.makeText(this,position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FakeContentTab implements TabHost.TabContentFactory{
        Context context;
        public FakeContentTab(Context context){
            this.context = context;
        }
        @Override
        public View createTabContent(String s) {
            View view = new View(context);
            view.setMinimumHeight(0);
            view.setMinimumWidth(0);
            return view;
        }
    }
}
