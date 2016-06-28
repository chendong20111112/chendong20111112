package com.chendong.toplevengit.giftshow;

import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chendong.toplevengit.giftshow.fragment.ClassFragment;
import com.chendong.toplevengit.giftshow.fragment.CompassFragment;
import com.chendong.toplevengit.giftshow.fragment.HotFragment;
import com.chendong.toplevengit.giftshow.fragment.MyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_fl)
    public FrameLayout frame;
    @BindView(R.id.main_rg)
    public  RadioGroup rgTitle;
    private OkHttpClient client = new OkHttpClient();
    private ArrayList<Fragment> listFragment;
    private int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initListener();
    }

    private void initListener() {
        rgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager manager=getSupportFragmentManager();
                int i=0;
                switch (checkedId){
                    case R.id.rb_chock1:
                        i=0;
                        FragmentTransaction transaction=manager.beginTransaction();
                        transaction.hide(listFragment.get(num));
                        if(listFragment.get(i).isAdded()){
                            transaction.show(listFragment.get(i));
                            Toast.makeText(MainActivity.this, "--------", Toast.LENGTH_SHORT).show();
                        }else {
                            transaction.add(R.id.main_fl, listFragment.get(i));
                        }
                        transaction.commit();
                        num=0;
                        break;
                    case R.id.rb_chock2:
                        i=1;
                        FragmentTransaction transaction1=manager.beginTransaction();
                        transaction1.hide(listFragment.get(num));
                        if(listFragment.get(i).isAdded()){
                            transaction1.show(listFragment.get(i));
                        }else {
                            transaction1.add(R.id.main_fl, listFragment.get(i));
                        }
                        transaction1.commit();
                        num=1;
                        break;
                    case R.id.rb_chock3:
                        i=2;
                        FragmentTransaction transaction2=manager.beginTransaction();
                        transaction2.hide(listFragment.get(num));
                        if(listFragment.get(i).isAdded()){
                            transaction2.show(listFragment.get(i));
                        }else {
                            transaction2.add(R.id.main_fl, listFragment.get(i));
                        }
                        transaction2.commit();
                        num=2;
                        break;
                    case R.id.rb_chock4:
                        i=3;
                        FragmentTransaction transaction3=manager.beginTransaction();
                        transaction3.hide(listFragment.get(num));
                        if(listFragment.get(i).isAdded()){
                            transaction3.show(listFragment.get(i));
                        }else {
                            transaction3.add(R.id.main_fl, listFragment.get(i));
                        }
                        transaction3.commit();
                        num=3;
                        break;
                }
            }
        });
    }

    private void initFragment() {
        listFragment=new ArrayList<Fragment>();
        listFragment.add(CompassFragment.newInstance());
        listFragment.add(HotFragment.newInstance());
        listFragment.add(ClassFragment.newInstance());
        listFragment.add(MyFragment.newInstance());
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.main_fl,listFragment.get(0));
        transaction.commit();
    }

    private void initView() {
        ButterKnife.bind(this);
    }


}
