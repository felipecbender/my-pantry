package com.bender.mypantry.form;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bender.mypantry.R;
import com.bender.mypantry.dao.FoodDAO;
import com.bender.mypantry.dao.PantryDAO;
import com.bender.mypantry.helper.PantryFormHelper;
import com.bender.mypantry.model.Pantry;

/**
 * Created by bender on 21/11/17.
 */

public class PantryFormActivity extends AppCompatActivity {

    private PantryFormHelper pantryFormHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_form);

        pantryFormHelper = new PantryFormHelper(this);

        Intent intent = getIntent();
        Pantry pantry = (Pantry) intent.getSerializableExtra("pantry");
        if (pantry != null) {
            pantryFormHelper.fillForm(pantry);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pantry_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_pantry_ok:
                Pantry pantry = pantryFormHelper.getPantry();

                PantryDAO pantryDAO = new PantryDAO(this);
                if (pantry.getId() != null) {
                    pantryDAO.update(pantry);
                } else {
                    pantryDAO.create(pantry);
                }
                pantryDAO.close();
                Toast.makeText(PantryFormActivity.this, "Food " + pantry.getName() + " was saved.", Toast.LENGTH_SHORT).show();

                finish();;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
