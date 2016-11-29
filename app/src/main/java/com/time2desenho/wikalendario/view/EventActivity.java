package com.time2desenho.wikalendario.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.time2desenho.wikalendario.R;
import com.time2desenho.wikalendario.dao.SubjectDatabaseHelper;
import com.time2desenho.wikalendario.model.Class;
import com.time2desenho.wikalendario.model.Event;
import com.time2desenho.wikalendario.model.Group;
import com.time2desenho.wikalendario.activity.DatePickerFragment;

import java.util.Date;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables to this class.
    private EditText etEventTitle;
    private EditText etEventDescription;
    private EditText etEventClass;
    private EditText etEventDate;
    private Button eventCreate;
    private TextWatcher textWatcher;
    private Date date;
    private boolean group = false;

    private TextView switchGroup;
    private Switch switchAux;

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        init();

        switchAux.setChecked(true);
        //attach a listener to check for changes in state
        switchAux.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    switchGroup.setText("Seu evento é de grupo!");
                    group = true;
                }else{
                    switchGroup.setText("Seu evento é público");
                    group=false;
                }

            }
        });

        if(switchAux.isChecked()){
            switchGroup.setText("Seu evento é de grupo!");
            group = true;
        }else{
            switchGroup.setText("Seu evento é público");
            group=false;
        }
    }

    private void init() {
        etEventTitle = (EditText)findViewById(R.id.eventTitle);

        etEventDescription = (EditText)findViewById(R.id.eventDescription);

        etEventClass = (EditText)findViewById(R.id.eventClass);

        etEventDate = (EditText)findViewById(R.id.eventDate);

        eventCreate = (Button)findViewById(R.id.eventCreate);

        switchAux = (Switch) findViewById(R.id.switchGroup);
        switchGroup = (TextView) findViewById(R.id.switchStatus);

        eventCreate.setOnClickListener(this);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View view) {
        Event event = new Event();
        //TODO mostrar turmas que ele tem e escolher pelo ID
        Class eventClass = new Class();
        Date date = new Date();

        if(group){
            //TODO colocar grupo certo do cara, como faremos isso é um dos muitos mistérios da vida
            Group group = new Group();
            event.setGroup(group);
        }

        event.setTitle(etEventTitle.getText().toString());
        event.setDescription(etEventDescription.getText().toString());
        event.setEventClass(eventClass);
        event.setDate(date);

        SubjectDatabaseHelper s = new SubjectDatabaseHelper(this);

        /*try {
        //TODO salvar na turma correta

        } catch (SQLException e) {
           e.printStackTrace();
        }*/
    }

}