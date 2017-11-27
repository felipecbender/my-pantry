package com.bender.mypantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bender.mypantry.dao.PantryDAO;
import com.bender.mypantry.form.PantryFormActivity;
import com.bender.mypantry.model.Pantry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bender on 21/11/17.
 */

public class PantryListActivity extends AppCompatActivity {

    private ListView pantryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list);

        pantryList = (ListView) findViewById(R.id.pantry_list);

        pantryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Pantry pantry = (Pantry) pantryList.getItemAtPosition(position);
                Intent intentGoToFoodList = new Intent(PantryListActivity.this, FoodListActivity.class);
                intentGoToFoodList.putExtra("pantry", pantry);
                startActivity(intentGoToFoodList);
            }
        });

        Button newPantryButton = (Button) findViewById(R.id.new_pantry);
        newPantryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentGoToPantryForm = new Intent(PantryListActivity.this, PantryFormActivity.class);
                startActivity(intentGoToPantryForm);
            }
        });

        registerForContextMenu(pantryList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryList();
    }

    private void loadPantryList() {
        PantryDAO pantryDAO = new PantryDAO(this);
        List<Pantry> pantryList = pantryDAO.readListPantry();
        pantryDAO.close();

        ArrayAdapter<Pantry> adapter = new ArrayAdapter<Pantry>(this, android.R.layout.simple_list_item_1, pantryList);
        this.pantryList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        final Pantry pantry = (Pantry) pantryList.getItemAtPosition(info.position);

        MenuItem itemDelete = menu.add("Delete");

        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                PantryDAO pantryDAO = new PantryDAO(PantryListActivity.this);
                pantryDAO.delete(pantry);
                pantryDAO.close();

                Toast.makeText(PantryListActivity.this, "Item: " + pantry.getName() + " was deleted.", Toast.LENGTH_SHORT).show();

                loadPantryList();
                return false;
            }
        });

        MenuItem itemUpdate = menu.add("Update");

        itemUpdate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intentGoToPantryForm = new Intent(PantryListActivity.this, PantryFormActivity.class);
                startActivity(intentGoToPantryForm);
                return false;
            }
        });

    }
}
