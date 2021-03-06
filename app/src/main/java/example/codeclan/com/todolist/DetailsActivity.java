package example.codeclan.com.todolist;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import static example.codeclan.com.todolist.R.drawable.chore;
import static example.codeclan.com.todolist.R.drawable.diy;
import static example.codeclan.com.todolist.R.drawable.exercise;
import static example.codeclan.com.todolist.R.drawable.other;
import static example.codeclan.com.todolist.R.drawable.shopping;
import static example.codeclan.com.todolist.R.drawable.work;

/**
 * Created by user on 24/04/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    private CheckBox check1;
    private RadioButton check2;
    private RadioButton check3;
    private RadioButton check4;
    private RadioButton check5;
    private RadioButton check6;
    private RadioButton check7;
    private CheckBox check8;
    private Button button_done_sel;
    private Button button_go_to_tasks;
    ListView listView;
    RadioGroup radioGroup;
    DatePicker datePicker;
    int checked;
    Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_list);
        addListenerOnButton();
        addListenerToCheckBox();
        final DatePicker datePicker = (DatePicker) findViewById(R.id.Datepick);


        final Button button = (Button) findViewById(R.id.set);

        String taskAsString = getIntent().getExtras().getString("task");

        Gson gson = new Gson();

        final Task task = gson.fromJson(taskAsString, Task.class);

        TextView textView = (TextView) findViewById(R.id.detail);

        textView.setText(task.getDetail());


        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                checked = radioGroup.indexOfChild(findViewById(checkedId));
                switch (checked) {
                    case 0:
                        Toast.makeText(DetailsActivity.this, "DIY Category has been selected", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(DetailsActivity.this, "Chore Category has been selected", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(DetailsActivity.this, "Shopping Category has been selected", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(DetailsActivity.this, "Exercise Category has been selected", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(DetailsActivity.this, "Work Category has been selected", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(DetailsActivity.this, "Other Category is selected", Toast.LENGTH_LONG).show();
                        break;
                    default:

                        break;

                }

            }

        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int day = datePicker.getDayOfMonth();
                int month = (datePicker.getMonth() + 1);
                int year = datePicker.getYear();
                String dayAsString =  Integer.toString(day);
                String monthAsString =  Integer.toString(month);
                String yearAsString =  Integer.toString(year);
                String date = yearAsString + "/" + (monthAsString) + "/" + dayAsString;
                Toast.makeText(DetailsActivity.this, date,Toast.LENGTH_LONG).show();

                ArrayList<Task> taskList = SavedTextPreferences.getTasks(DetailsActivity.this);

                task.setDate(date);

                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).getTask().equals(task.getTask())) {
                        taskList.remove(i);
                    }
                }

                taskList.add(task);
                SavedTextPreferences.setTasks(DetailsActivity.this, taskList);

            }
        });
    }

    public void addListenerOnButton() {
        final SavedTextPreferences savedTextPreferences = new SavedTextPreferences();
        final ArrayList<Task> taskList = savedTextPreferences.getTasks(this);
        check1 = (CheckBox) findViewById(R.id.checkbox_done);
        check2 = (RadioButton) findViewById(R.id.checkBox_diy);
        check3 = (RadioButton) findViewById(R.id.checkBox_chore);
        check4 = (RadioButton) findViewById(R.id.checkBox_shopping);
        check5 = (RadioButton) findViewById(R.id.checkBox_exercise);
        check6 = (RadioButton) findViewById(R.id.radioButton_work);
        check7 = (RadioButton) findViewById(R.id.checkBox_other);
        check8 = (CheckBox) findViewById(R.id.checkbox_delete);
        button_done_sel = (Button) findViewById(R.id.button);
        button_go_to_tasks = (Button) findViewById(R.id.button_go_to_tasks);
        final Context context = this;


        button_done_sel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View variable) {

                        StringBuffer result = new StringBuffer();
                        result.append("Task : ").append(check1.isChecked());

                        Toast.makeText(DetailsActivity.this, result.toString(), Toast.LENGTH_LONG).show();

                        String taskAsString = getIntent().getExtras().getString("task");

                        Gson gson = new Gson();

                        if (check1.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setToDone();
                            taskList.add(task);
                            savedTextPreferences.setTasks(context, taskList);
                        }
                        if (check2.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setImage(diy);
                            taskList.add(task);
                            savedTextPreferences.setTasks(context, taskList);
                        }
                        if (check3.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setImage(chore);
                            taskList.add(task);
                            savedTextPreferences.setTasks(context, taskList);
                        }
                        if (check4.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setImage(shopping);
                            taskList.add(task);
                            savedTextPreferences.setTasks(context, taskList);
                        }
                        if (check5.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setImage(exercise);
                            taskList.add(task);
                            savedTextPreferences.setTasks(context, taskList);
                        }
                        if (check6.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setImage(work);
                            taskList.add(task);
                            savedTextPreferences.setTasks(context, taskList);
                        }
                        if (check7.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setImage(other);
                            taskList.add(task);
                            savedTextPreferences.setTasks(context, taskList);
                        }
                        if (check8.isChecked()) {
                            Task task = gson.fromJson(taskAsString, Task.class);

                            for (int i = 0; i < taskList.size(); i++) {
                                if (taskList.get(i).getTask().equals(task.getTask())) {
                                    taskList.remove(i);
                                }
                            }
                            task.setToDone();
                            savedTextPreferences.setTasks(context, taskList);
                        }
                    }
                });

    }

    public void gotoTaskList(View view) {
        this.finish();
    }

    public void addListenerToCheckBox(){
        check1 = (CheckBox) findViewById(R.id.checkbox_done);

        check1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View variable){
                        if(((CheckBox)variable).isChecked()){
                            Toast.makeText(DetailsActivity.this,"Task is Checked done",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        check8.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View variable){
                        if(((CheckBox)variable).isChecked()){
                            Toast.makeText(DetailsActivity.this,"Task is been deleted !",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }

}
