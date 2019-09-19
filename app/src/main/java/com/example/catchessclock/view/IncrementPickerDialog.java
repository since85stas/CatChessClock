package com.example.catchessclock.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.catchessclock.R;
import com.example.catchessclock.model.TimeControl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncrementPickerDialog extends DialogFragment {

    @BindView(R.id.min_picker)
    NumberPicker mMinutePicker;

    @BindView(R.id.sec_picker)
    NumberPicker mSecPicker;

    @BindView(R.id.icr_radio_group)
    RadioGroup mRadioGroup;

    public interface IncrementPickerListner {
        void onIncrPositButtonClick(int minutes, int seconds , int incrType);
    }

    // Use this instance of the interface to deliver action events
    IncrementPickerListner mListener;

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
                        mListener.onIncrPositButtonClick(mMinutePicker.getValue(),
                                mSecPicker.getValue(),
                                getIncrementType());
                        // sign in the user ...
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        IncrementPickerDialog.this.getDialog().cancel();
                    }
                });
        mMinutePicker.setMinValue(0);
        mMinutePicker.setMaxValue(10);
        mSecPicker.setMinValue(0);
        mSecPicker.setMaxValue(60);
        mRadioGroup.check(R.id.delay_radio_but_fisher);
//        ButterKnife.bind(this)
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (IncrementPickerListner) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }


    private int getIncrementType() {
        int id = mRadioGroup.getCheckedRadioButtonId();
        switch (id){
            case R.id.delay_radio_but_fisher:
                return TimeControl.TYPE_FISHER;
            case R.id.delay_radio_but_del:
                return TimeControl.TYPE_DELAY;
            case R.id.delay_radio_but_bronsh:
                return TimeControl.TYPE_BRONSH;
        }
        return TimeControl.TYPE_FISHER;
    }
}
