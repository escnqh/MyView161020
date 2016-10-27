package com.newthread.myview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private SlideAdapter mSlideAdapter;
    private List<ItemBean> mItemBeans = new ArrayList<>();
    private TextView mRightTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
        
    }
    private void initBean() {
        for (int x = 0; x < 30; x++) {
            mItemBeans.add(new ItemBean());
        }
    }

    private void initData() {
        initBean();
        mSlideAdapter = new SlideAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSlideAdapter);
        mSlideAdapter.setItemBeans(mItemBeans);
    }

    private void initListener() {
        mRightTV.setOnClickListener(this);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_root);
        mRightTV = (TextView) findViewById(R.id.right_tv);
    }
    private void editItems(){
        if ("编辑".equals(mRightTV.getText().toString())) {
            mRightTV.setText("取消");
            mSlideAdapter.openItemAnimation();
        } else if ("取消".equals(mRightTV.getText().toString())) {
            mRightTV.setText("编辑");
            mSlideAdapter.closeItemAnimation();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_tv:
                editItems();
                break;
        }
    }

}
