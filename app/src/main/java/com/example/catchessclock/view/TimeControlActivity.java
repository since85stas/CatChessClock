package com.example.catchessclock.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.catchessclock.R;
import com.example.catchessclock.model.TimeControl;
import com.example.catchessclock.presenter.TimeControlPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;


public class TimeControlActivity extends MvpAppCompatActivity implements TimeControlActivityView, 
        IncrementPickerDialog.IncrementPickerListner
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
//        TimePickerDialog dialog = new TimePickerDialog(this,this,1,1,true);n
        IncrementPickerDialog dialog = new IncrementPickerDialog();
        dialog.show(getSupportFragmentManager(),"incremDialog");
//        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_control);
        ButterKnife.bind(this);
    }

    @Override
    public void setActivityState(TimeControl control) {
        mEditText.setText(control.title);
        mIncrTextView.setText(control.increment +" " + control.incrementType);
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


}
