package com.bender.mypantry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bender.mypantry.dao.FoodDAO;
import com.bender.mypantry.model.Food;

/**
 * Created by Felipe Bender on 19/11/2017.
 */

public class FormActivity extends AppCompatActivity {
    private FormHelper formHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        formHelper = new FormHelper(this);

        Intent intent = getIntent();
        Food food = (Food) intent.getSerializableExtra("food");
        if (food != null) {
            formHelper.fillForm(food);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_ok:
                Food food = formHelper.getFood();

                FoodDAO foodDAO = new FoodDAO(this);
                if (food.getId() != null) {
                    foodDAO.update(food);
                } else {
                    foodDAO.create(food);
                }
                foodDAO.close();
                Toast.makeText(FormActivity.this, "Food " + food.getName() + " was saved.", Toast.LENGTH_SHORT).show();

                finish();;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
