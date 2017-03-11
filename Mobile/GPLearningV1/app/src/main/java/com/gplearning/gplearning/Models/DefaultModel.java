package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.annotation.Id;

class DefaultModel {
     @Id
    private long _id;

    public DefaultModel() {

    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}
