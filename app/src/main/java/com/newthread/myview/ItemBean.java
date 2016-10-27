package com.newthread.myview;

import java.io.Serializable;

/**
 * Created by 启航 on 2016/10/23 0023.
 */
public class ItemBean implements Serializable {
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
