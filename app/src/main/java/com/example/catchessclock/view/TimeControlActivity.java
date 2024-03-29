package com.example.catchessclock.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catchessclock.R;
import com.example.catchessclock.interfaces.OnItemClickListner;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.TimeControlPresenter;
import com.example.catchessclock.presenter.TimingsSelectAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;


public class TimeControlActivity extends MvpAppCompatActivity implements TimeControlActivityView, 
        IncrementPickerDialog.IncrementPickerListner, OnItemClickListner, StagePickerDialog.StagePickerListner
{
    public static final String TAG = TimeControlActivity.class.getName();

    @InjectPresenter
    TimeControlPresenter mTimeControlPresenter;

    @OnClick(R.id.save_button)
    void onSaveClick() {
        mTimeControlPresenter.saveButtonClicked();
    }

    @BindView(R.id.time_control_name)
    EditText mEditText;

    @BindView(R.id.increament_button)
    TextView mIncrTextView;

    @OnClick(R.id.increament_button)
    void onIncrementClick() {
        IncrementPickerDialog dialog = new IncrementPickerDialog();
        dialog.show(getSupportFragmentManager(),"incremDialog");
//        dialog.show();
    }

    @OnClick(R.id.add_stage_button)
    void addStageClicked() {
//        mTimeControlPresenter.addStageClicked();
        StagePickerDialog dialog = new StagePickerDialog(false,0);
        dialog.show(getSupportFragmentManager(),"stagedialog");
    }

    @BindView(R.id.time_control_recycle)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_control);
        ButterKnife.bind(this);
    }

    @Override
    public void setActivityState(TimeControl control) {
        mEditText.setText(control.title);
        mIncrTextView.setText(control.getTimeStage().increment +" " +
                control.getTimeStage().incrementType);
    }

    @Override
    public void saveButtonClicked() {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void stageRedactorClicked() {

    }

    @Override
    public void incrementRedactorClicked() {

    }

    @Override
    public void onIncrPositButtonClick(int minutes, int sec, int incrType) {
        Log.d(TAG, "onIncrPositButtonClick: ");
        mTimeControlPresenter.changeIncreamnet(minutes,sec,incrType);
    }

    @Override
    public void setTimingsSelectList(TimingsSelectAdapter adapter) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.setOnClickListener(this);
//        mRecyclerView.setonc
    }

    @Override
    public void redactionStageTimesClicked(int timeLimit, int turnLimit, int stagePos) {
//        mTimeControlPresenter.redactionTimeStage(hours,minutes,sec,turnLimit,stagePos);
        StagePickerDialog dialog = new StagePickerDialog(true,stagePos);
        dialog.show(getSupportFragmentManager(),"redDialog");
        dialog.initPickersValues(timeLimit,turnLimit);
        Log.d(TAG, "redactionStageTimesClicked: ");
    }

    @Override
    public void getStageTimes(int hours, int minutes, int sec, int turnLimit, boolean isRedact, int pos) {
        if (!isRedact) {
            mTimeControlPresenter.addTimeStage(hours, minutes, sec, turnLimit);
        } else {
            mTimeControlPresenter.redactionTimeStage(hours,minutes,sec,turnLimit,pos);
        }
    }


}
