package com.example.catchessclock.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.R;
import com.example.catchessclock.model.TimeControl;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimingsAdapter extends RecyclerView.Adapter<TimingsAdapter.TimingsViewHolder> {

    private List<TimeControl> data;
    LayoutInflater inflater;


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
                    mCheckBox.setChecked(false);
                } else {
                    mCheckBox.setChecked(true);
                }

                int id = getLayoutPosition();
                for (int i = 0; i < data.size(); i++) {
//                    mc
                }
            });
        }
    }


    public TimingsAdapter( List<TimeControl> data) {
        this.data = data;
//        inflater = LayoutInflater.from(context);
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
        String title = data.get(position).title + " " + data.get(position).timeLimit;
        holder.mTitleText.setText(title);

    }


    @Override
    public int getItemCount() {
        return data.size();
    }



}
