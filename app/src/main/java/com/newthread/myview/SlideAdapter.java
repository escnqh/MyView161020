package com.newthread.myview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 启航 on 2016/10/23 0023.
 */
public class SlideAdapter extends RecyclerView.Adapter{
    public static final int NORMAL = 1000;
    public static final int SLIDE = 2000;
    private int mState = NORMAL;
    private List<ItemBean> mItemBeans;
    private List<SlideViewHolder> mSlideViewHolders = new ArrayList<>();
   /* private LayoutInflater inflater;
    public SlideAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }*/

    public void openItemAnimation() {
        mState = SLIDE;
        for (SlideViewHolder holder : mSlideViewHolders) {
            holder.openItemAnimation();
        }
    }

    public void closeItemAnimation() {
        mState = NORMAL;
        for (SlideViewHolder holder : mSlideViewHolders) {
            holder.closeItemAnimation();
        }
    }

    public void setItemBeans(List<ItemBean> beans) {
        mItemBeans = beans;
        notifyDataSetChanged();
    }

    private class SlideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SlideRelativeLayout mSlideRelativeLayout;
        private CheckBox mCheckBox;
        private ItemBean mItemBean;

        public SlideViewHolder(View itemView) {
            super(itemView);
            mSlideRelativeLayout = (SlideRelativeLayout) itemView.findViewById(R.id.item_root);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
            itemView.setOnClickListener(this);
        }

        public void bind(ItemBean itemBean) {
            mItemBean = itemBean;
            mCheckBox.setChecked(itemBean.isChecked());
            switch (mState) {
                case NORMAL:
                    mSlideRelativeLayout.close();
                    break;

                case SLIDE:
                    mSlideRelativeLayout.open();
                    break;
            }
        }

        public void openItemAnimation() {
            mSlideRelativeLayout.openAnimation();
        }

        public void closeItemAnimation() {
            mSlideRelativeLayout.closeAnimation();
        }

        public void setCheckBox() {
            mCheckBox.setChecked(!mCheckBox.isChecked());
            mItemBean.setChecked(mCheckBox.isChecked());
        }

        @Override
        public void onClick(View view) {
            setCheckBox();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlideViewHolder slideViewHolder;
//        View view;
//        view=inflater.inflate(R.layout.item, parent,false);
        slideViewHolder = new SlideViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
        mSlideViewHolders.add(slideViewHolder);
        return slideViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SlideViewHolder) holder).bind(mItemBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemBeans == null ? 0 : mItemBeans.size();
    }



}
