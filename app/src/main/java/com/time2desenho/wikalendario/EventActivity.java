package com.time2desenho.wikalendario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
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


    @Override
    public void onClick(View view) {
        Class classUser = new Class();
        if (group) {
            EventGroup eventGroup = new EventGroup();
            eventGroup.setEventGroupTitle(etEventTitle.getText().toString());
            eventGroup.setEventGroupDescription(etEventDescription.getText().toString());
            eventGroup.setEventGroupClass(etEventClass.getText().toString());
            eventGroup.setEventGroupDate(etEventDate.getText().toString());

            SubjectDatabaseHelper s = new SubjectDatabaseHelper(this);

            /*try {
                //TODO salvar na turma correta

            } catch (SQLException e) {
               e.printStackTrace();
            }*/

        }
        else{
            EventClass eventClass = new EventClass();
            eventClass.setEventClassTitle(etEventTitle.getText().toString());
            eventClass.setEventClassDescription(etEventDescription.getText().toString());
            eventClass.setEventClass(etEventClass.getText().toString());
            eventClass.setEventClassDate(etEventDate.getText().toString());

        }
    }

}
