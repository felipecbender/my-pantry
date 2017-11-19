package com.bender.mypantry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.bender.mypantry.dao.FoodDAO;
import com.bender.mypantry.model.Food;

import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    private ListView foodList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        foodList = (ListView) findViewById(R.id.food_list);

        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = (Food) foodList.getItemAtPosition(position);
                Intent intentGoToForm = new Intent(FoodListActivity.this, FormActivity.class);
                intentGoToForm.putExtra("food", food);
                startActivity(intentGoToForm);
            }
        });

        Button newFoodButton = (Button) findViewById(R.id.new_food);
        newFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToForm = new Intent(FoodListActivity.this, FormActivity.class);
                startActivity(intentGoToForm);
            }
        });
        registerForContextMenu(foodList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFoodList();
    }

    private void loadFoodList() {
        FoodDAO foodDAO = new FoodDAO(this);
        List<Food> listFood = foodDAO.readListFood();
        foodDAO.close();

        ArrayAdapter<Food> adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, listFood);
        foodList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        final Food food = (Food) foodList.getItemAtPosition(info.position);

        MenuItem itemDelete = menu.add("Delete");

        itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FoodDAO foodDAO = new FoodDAO(FoodListActivity.this);
                foodDAO.delete(food);
                foodDAO.close();

                Toast.makeText(FoodListActivity.this, "Item: " + food.getName() + " was deleted.", Toast.LENGTH_SHORT).show();

                loadFoodList();
                return false;
            }
        });
    }
}
