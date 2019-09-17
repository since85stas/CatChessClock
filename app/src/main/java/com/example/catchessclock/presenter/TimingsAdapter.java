package com.example.catchessclock.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.R;
import com.example.catchessclock.model.TimeControl;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimingsAdapter extends RecyclerView.Adapter<TimingsAdapter.TimingsViewHolder> {

    private List<TimeControl> data;
    LayoutInflater inflater;

    RecyclerView mRecyclerView;

    int mInt;

    public class TimingsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.timings_item_title)
        TextView mTitleText;

        @BindView(R.id.checkbox)
        CheckBox mCheckBox;


        public TimingsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(onClick -> {
                if (mCheckBox.isChecked()) {
//                    mCheckBox.setChecked(false);
                } else {
                    mCheckBox.setChecked(true);
                }
                resetSelection(getAdapterPosition());
            });

            mCheckBox.setClickable(false);

//            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    if (isChecked) {
////                        resetSelection(getAdapterPosition());
//                    } else {
////                        mCheckBox.setChecked(true);
//                    }
////                    resetSelection(getAdapterPosition());
//                }
//            });
        }
    }


    public TimingsAdapter( List<TimeControl> data, int initTiming) {
        this.data = data;
        mInt = initTiming;
    }

    public void setRecycler(RecyclerView view) {
        mRecyclerView = view;
    }


    @NonNull
    @Override
    public TimingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timigs_item,parent,false);
        TimingsViewHolder holder = new TimingsViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull TimingsViewHolder holder, int position) {
        if (position == mInt) {
            holder.mCheckBox.setChecked(true);
        }
        String title = data.get(position).title + " " + data.get(position).timeLimit + " id=" +
                data.get(position).primaryKey;
        holder.mTitleText.setText(title);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    private void onItemClick(int position) {
        for (int i = 0; i < data.size(); i++) {

            if (i == position) {

            }
        }
    }


    public TimeControl getSelectedTime() {

        for (int i = 0; i < data.size(); i++) {
            RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(i);
            TimingsViewHolder timeHolder = (TimingsViewHolder)holder;
            if (timeHolder.mCheckBox.isChecked() ){
                return data.get(i);
            }
        }
        return null;
    }


    public void resetSelection( int selPos) {
        for (int i = 0; i < data.size(); i++) {
            if (i != selPos) {
                RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(i);
                TimingsViewHolder timeHolder = (TimingsViewHolder)holder;
                if (timeHolder.mCheckBox.isChecked() ){
                    timeHolder.mCheckBox.setChecked(false);
                }
            }
        }
    }

//    public void initCheckBox(int pos) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(pos);
//                RecyclerView.ViewHolder holder1 = mRecyclerView.findViewHolderForLayoutPosition(pos);
//                TimingsViewHolder timeHolder = (TimingsViewHolder)holder;
//                timeHolder.mCheckBox.setChecked(true);
//                notifyDataSetChanged();
//            }
//        },1);


//        for (int i = 0; i < data.size(); i++) {
//
//        }
//    }
}
