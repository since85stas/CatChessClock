package com.example.catchessclock.presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.R;
import com.example.catchessclock.Utils.StringUtils;
import com.example.catchessclock.interfaces.OnItemClickListner;
import com.example.catchessclock.model.TimeControl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimingsSelectAdapter extends RecyclerView.Adapter<TimingsSelectAdapter.TimingsSelectViewHolder> {

    List<TimeControl.TimeStage> mData;

    public OnItemClickListner mListner;

    public class TimingsSelectViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.select_timings_textView)
        TextView mTextView;

        @BindView(R.id.redaction_button)
        ImageButton mRedactionButton;

        @OnClick(R.id.redaction_button)
        void onClickRedaction() {

        }

        @BindView(R.id.delete_button)
        ImageButton mDelButton;

        @OnClick(R.id.delete_button)
        void onClickDelete() {
            mData.remove(getAdapterPosition());
            notifyDataSetChanged();
        }

        public TimingsSelectViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mDelButton.setVisibility(View.GONE);
            mRedactionButton.setOnClickListener(I -> {
                mListner.redactionStageTimesClicked(mData.get(getAdapterPosition()).timeLimit,
                        mData.get(getAdapterPosition()).turnLimit,
                        getAdapterPosition());
            });

        }
    }

    public TimingsSelectAdapter (List<TimeControl.TimeStage> data) {
        mData = data;
    }

    @Override
    public TimingsSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timings_select_item,parent,false);
        TimingsSelectViewHolder holder = new TimingsSelectViewHolder(view);
        mListner = (OnItemClickListner)parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimingsSelectViewHolder holder, int position) {
        if (position != 0) {
            holder.mDelButton.setVisibility(View.VISIBLE);
        }
        holder.mTextView.setText(StringUtils.getSelectedTimingString(mData.get(position).timeLimit));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
