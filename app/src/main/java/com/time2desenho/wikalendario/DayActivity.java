package com.time2desenho.wikalendario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class DayActivity extends AppCompatActivity {

    private ArrayList<Event> events;
    protected RecyclerView recyclerView;
    private final Context context = this;
    //private EventDAO eventDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.events_day);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //setEvents(CSVReader.getEvents(this, R.raw.lista));

        Intent intent = getIntent();
        Date date = (Date) intent.getSerializableExtra("date");

        //TODO: Place real events
        events = new ArrayList<>();
        events.add(new Event("Evento1", "Teste1", "Turma1", date));
        events.add(new Event("Evento2", "Teste2", "Turma2", date));

        //ClassReader.getEvents(this, R.raw.turma);

//        eventDAO = new EventDAO();
//        setEvents(eventDAO.getEvents());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerEventView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

    }



    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public Context getContext(){
        return context;
    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.setAdapter(new EventAdapter(this, events, onClickEvent()));
    }

    public EventAdapter.EventOnClickListener onClickEvent(){
        return new EventAdapter.EventOnClickListener(){
            @Override
            public void onClickEvent(View view, int position){
                Event event = events.get(position);

                Intent intent = new Intent(getContext(), EventActivity.class);
                intent.putExtra("title", event.getEventTitle());
                intent.putExtra("description", event.getEventDescription());
                intent.putExtra("date", event.getEventDate());

                startActivity(intent);

                Toast.makeText(getContext(),"Event: " + event.getEventTitle() + ":" + event.getEventDescription()
                        , Toast.LENGTH_SHORT).show();
            }
        };
    }


}
