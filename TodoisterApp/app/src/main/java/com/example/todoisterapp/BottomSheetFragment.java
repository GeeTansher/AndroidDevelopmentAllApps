package com.example.todoisterapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoisterapp.model.Priority;
import com.example.todoisterapp.model.Task;
import com.example.todoisterapp.model.TaskViewModel;
import com.example.todoisterapp.model.sharedViewModel;
import com.example.todoisterapp.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

/*
 Todo: 1. To save prev due date until not changed while updating
  2. when bottom sheet appears for new Task after calling update
  once get rid of Text in TextView.
  3. change date if else like priority if else in itemClick
*/
public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private static final String TAG = "Item";
    Calendar date = Calendar.getInstance();   // to manage chips for date
    Calendar calendar = Calendar.getInstance(); // to create date
    private EditText enterTodo;
    private ImageButton calenderButton;
    private ImageButton priorityButton;
    private ImageButton saveButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private CalendarView calendarView;
    private Group calenderGroup;
    private Date dueDate;
    private sharedViewModel sharedViewModel;
    private boolean isEdit;
    private Priority priority;

    public BottomSheetFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        calendarView = view.findViewById(R.id.calendar_view);
        calenderGroup = view.findViewById(R.id.calendar_group);
        calenderButton = view.findViewById(R.id.today_calendar_button);
        enterTodo = view.findViewById(R.id.enter_todo_et);
        saveButton = view.findViewById(R.id.save_todo_button);
        priorityButton = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);

        date.add(Calendar.DAY_OF_YEAR, 0);

        Chip todayChip = view.findViewById(R.id.today_chip);
        todayChip.setOnClickListener(this);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        tomorrowChip.setOnClickListener(this);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this);

        return view;
    }

    // Because when we go out of fragment then on resume it will refresh the views
    @Override
    public void onResume() {
        super.onResume();
        if (sharedViewModel.getSelectItem().getValue() != null) {
            isEdit = sharedViewModel.isEdit();
            Task task = sharedViewModel.getSelectItem().getValue();
            if (isEdit)
                enterTodo.setText(task.getTask());
            else
                enterTodo.setText("");
//            Log.d(TAG, "onViewCreated: " + task.getTask());
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // This will have same values as in MainActivity's sharedViewModel object.
        sharedViewModel = new ViewModelProvider(requireActivity())
                .get(sharedViewModel.class);


        calenderButton.setOnClickListener(v -> {
            calenderGroup.setVisibility(
                    calenderGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

            Utils.hideSoftKeyboard(v);
        });

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year, month, dayOfMonth);
            dueDate = calendar.getTime();
        });

        priorityButton.setOnClickListener(v -> {
            priorityRadioGroup.setVisibility(priorityRadioGroup.getVisibility() ==
                    View.GONE ? View.VISIBLE : View.GONE);
            Utils.hideSoftKeyboard(v);
            priorityRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (priorityRadioGroup.getVisibility() == View.VISIBLE) {
                    selectedButtonId = checkedId;
                    selectedRadioButton = view.findViewById(selectedButtonId);
                    if (selectedRadioButton.getId() == R.id.radioButton_high) {
                        priority = Priority.HIGH;
                    } else if (selectedRadioButton.getId() == R.id.radioButton_med) {
                        priority = Priority.MEDIUM;
                    } else if (selectedRadioButton.getId() == R.id.radioButton_low) {
                        priority = Priority.LOW;
                    } else {
                        priority = Priority.LOW;
                    }
                } else {
                    priority = Priority.LOW;
                }
            });
        });

        saveButton.setOnClickListener(v -> {
            String task = enterTodo.getText().toString().trim();
            if (!TextUtils.isEmpty(task) && dueDate != null && priority != null) {
//                enterTodo.setText("");
                Task myTask = new Task(task, priority, dueDate,
                        Calendar.getInstance().getTime(),
                        false);
                if (isEdit) {
                    Task updateTask = sharedViewModel.getSelectItem().getValue();
                    assert updateTask != null;
                    updateTask.setTask(task);
                    updateTask.setDateCreated(Calendar.getInstance().getTime());
                    updateTask.setPriority(priority);
                    updateTask.setDueDate(dueDate);
                    TaskViewModel.updateTask(updateTask);
                    sharedViewModel.setEdit(false);
                } else {
                    TaskViewModel.insertTask(myTask);
                }
                enterTodo.setText("");
                if (this.isVisible()) {
                    this.dismiss();
                }

            } else {
                // TODO: show here a Toast or Snackbar, this is not working...
//                enterTodo.setText("");
//                if(this.isVisible()){
//                    this.dismiss();
//                }
                Snackbar.make(saveButton, R.string.empty_field, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.today_chip) {
            // set date for today
            calendar.clear();
            calendar.set(date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH));
            dueDate = calendar.getTime();
            Log.d("Time", "onClick: " + dueDate);
        } else if (id == R.id.tomorrow_chip) {
            // 1 day from today
            calendar.clear();
            calendar.set(date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH) + 1);
            dueDate = calendar.getTime();
            Log.d("Time", "onClick: " + dueDate);
        } else if (id == R.id.next_week_chip) {
            // 7 days from today

            // can also use this line instead of below some code line
            // but this has some flaws that if user click same button many times.
//            calendar.add(date.get(Calendar.DAY_OF_YEAR),7);

            calendar.clear();
            calendar.set(date.get(Calendar.YEAR),
                    date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH) + 7);
            dueDate = calendar.getTime();
            Log.d("Time", "onClick: " + dueDate);
        }

    }
}