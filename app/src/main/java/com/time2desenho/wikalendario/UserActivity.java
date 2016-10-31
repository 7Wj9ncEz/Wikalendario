package com.time2desenho.wikalendario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class UserActivity extends AppCompatActivity {

    private UserHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        helper = new UserHelper(this);

        Intent intent = getIntent();
        String mUsername = (String) intent.getSerializableExtra("username");
        if(mUsername != null){
            helper.fillForm(new User(mUsername));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sigin, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sigin_menu:
                User user = helper.getUser();

                UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(this);

                try {
                    Dao<User, Long> userDAO = userDatabaseHelper.getDAO();
                    userDAO.createOrUpdate(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Toast.makeText(UserActivity.this, user.getName() + " Created!", Toast.LENGTH_SHORT).show();

                Intent goToForm = new Intent(UserActivity.this, MainActivity.class);
                goToForm.putExtra("user", user);
                startActivity(goToForm);

                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
