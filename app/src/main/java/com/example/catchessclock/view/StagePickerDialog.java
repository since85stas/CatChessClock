package com.example.catchessclock.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.catchessclock.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StagePickerDialog extends DialogFragment {

    int mHours;
    int mMin;
    int mSec;
    int mTurns;
    int mPos;
    boolean mIsRedact;

    @BindView(R.id.min_picker)
    NumberPicker mMinutePicker;

    @BindView(R.id.sec_picker)
    NumberPicker mSecPicker;

    @BindView(R.id.icr_radio_group)
    RadioGroup mRadioGroup;

    @BindView((R.id.hours_picker))
    NumberPicker mHoursPicker;

    @BindView(R.id.dialog_title)
    TextView mDialogTitle;

    @BindView(R.id.number_turns_layout)
    LinearLayout mLinearLayout;

    @BindView(R.id.number_turns_edit)
    EditText mEditText;

    public interface StagePickerListner {
        public void getStageTimes(int hours,int minutes,int sec, int turnLimit, boolean isreduct, int pos);
    }

    StagePickerListner mPickerListner;

    public StagePickerDialog ( boolean isRedact, int pos) {
        mIsRedact = isRedact;
        mPos = pos;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.increment_picker_lay, null);
        ButterKnife.bind(this,view);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int h = mHoursPicker.getValue();
                        int m = mMinutePicker.getValue();
                        int s = mSecPicker.getValue();
                        if ( m!=0 || h!=0 || s!=0) mPickerListner.getStageTimes(h,
                                m,
                                s,
                                Integer.parseInt(mEditText.getText().toString()),
                                mIsRedact,
                                mPos
                        ); else {
                            Toast.makeText(StagePickerDialog.this.getContext(),
                                    "Wrong time",Toast.LENGTH_LONG);
                        }
                        // sign in the user ...
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        StagePickerDialog.this.getDialog().cancel();
                    }
                });
        mLinearLayout.setVisibility(View.VISIBLE);
        mHoursPicker.setMinValue(0);
        mHoursPicker.setMaxValue(10);
        mHoursPicker.setValue(mHours);
        mMinutePicker.setMinValue(0);
        mMinutePicker.setMaxValue(60);
        mMinutePicker.setValue(mMin);
        mSecPicker.setMinValue(0);
        mSecPicker.setMaxValue(60);
        mSecPicker.setValue(mSec);
//        mEditText.setText(mTurns);
        mRadioGroup.setVisibility(View.GONE);
        return builder.create();
    }

    public void initPickersValues(int time, int turnLimit) {
        mHours = (int) (time/3600);
        mMin = (int) (time/60);
        mSec = time - mMin*60 - mHours*3600;
        mTurns = turnLimit;
//        mHoursPicker.setValue(hours);
//        mMinutePicker.setValue(minutes);
//        mSecPicker.setValue(minutes);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mPickerListner = (StagePickerListner) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
