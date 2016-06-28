package com.chendong.toplevengit.giftshow.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chendong.toplevengit.giftshow.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class MyFragment extends Fragment {

    @BindView(R.id.compass_tbl)
    public TabLayout tabLayout;
    @BindView(R.id.compass_vp)
    public ViewPager viewPager;
    private ArrayList<String> list;
    private ArrayList<Fragment> listFragment;
    private VpAdapter adapter;
    private String[] strings=new String[]{};

    public static MyFragment newInstance(){
        return new MyFragment();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initData();

    }

    private void initData() {
         list=new ArrayList<String>();
         listFragment=new ArrayList<>();
         for (int i=0;i<10;i++){
            list.add("淘宝"+i);
        }
        listFragment.add(CompassFragmentOne.newInstance());
        for (int i=0;i<9;i++){
            listFragment.add(CompassFragmentTow.newInstance());
        }
       adapter=new VpAdapter(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.compass_layout,null);
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class VpAdapter extends FragmentPagerAdapter {

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
}
