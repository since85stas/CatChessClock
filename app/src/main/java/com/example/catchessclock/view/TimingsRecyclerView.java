package com.example.catchessclock.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.TimingsAdapter;

public class TimingsRecyclerView extends RecyclerView {

    TimingsAdapter mAdapter;

    public TimingsRecyclerView(@NonNull Context context) {
        super(context);
    }

    public TimingsRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//
//    public void setAdapterTimings(TimingsAdapter adapterTimings) {
//        mAdapter = adapterTimings;
//    }
//
//    public TimeControl getSelectetElem() {
//        for (int i = 0; i < mAdapter.getItemCount() ;i++) {
//
//        }
//    }


}
