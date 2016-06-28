package com.chendong.toplevengit.giftshow.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chendong.toplevengit.giftshow.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class CompassFragmentOne extends Fragment {

    @BindView(R.id.compass_fragment_one_lv)
    public ExpandableListView listView;
    private HashMap<String, ArrayList<String>> map;
    private ArrayList<String> list;
    private Context mContext;
    private ListAdapter adapter;

    public static CompassFragmentOne newInstance(){
        return new CompassFragmentOne();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initData();
    }

    private void initData() {
        map=new HashMap<>();
        list=new ArrayList<String>();
        for(int i=0;i<5;i++){
            list.add("chendong"+i);
            ArrayList<String> lists=new ArrayList<>();
            for(int j=1;j<6;j++){
               lists.add("cd"+i+" "+j);
            }
            map.put(list.get(i),lists);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.compass_one_layout,null);
        ButterKnife.bind(this,view);
        mContext = getActivity();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdapter();
        bindAdapter();
        initListener();
    }

    private void initListener() {
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        for(int i=0;i<list.size();i++){
            listView.expandGroup(i);
        }
    }

    private void bindAdapter() {
        listView.setAdapter(adapter);
    }

    private void initAdapter() {
        adapter=new ListAdapter();
    }

    class  ListAdapter extends BaseExpandableListAdapter{

        @Override
        public int getGroupCount() {
            return map==null?0:list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            ArrayList<String> listChile=map.get(list.get(groupPosition));
            return listChile==null?0:listChile.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return list.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            ArrayList<String> listChile=map.get(list.get(groupPosition));
            return listChile.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder group;
            if(convertView==null){
                convertView=LayoutInflater.from(mContext).inflate(R.layout.group_view,null);
                group=new GroupViewHolder(convertView);
            }else{
                group=(GroupViewHolder)convertView.getTag();
            }
            group.left.setText(list.get(groupPosition));
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder child;
            if(convertView==null){
                convertView=LayoutInflater.from(mContext).inflate(R.layout.child_view,null);
                child=new ChildViewHolder(convertView);
            }else{
                child=(ChildViewHolder)convertView.getTag();
            }
           child.mImageView.setBackgroundColor(Color.BLACK);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

    class  GroupViewHolder{
        @BindView(R.id.group_left_tv)
        TextView left;
        @BindView(R.id.grou_right_tv)
        TextView right;
        public GroupViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }

    class ChildViewHolder{
        @BindView(R.id.child_image)
        ImageView mImageView;
        public ChildViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
