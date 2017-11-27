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

import com.bender.mypantry.dao.FoodDAO;
import com.bender.mypantry.form.FoodFormActivity;
import com.bender.mypantry.model.Food;
import com.bender.mypantry.model.Pantry;

import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    private Long pantryId = null;
    private ListView foodList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        foodList = (ListView) findViewById(R.id.food_list);

        Intent intent = getIntent();
        final Pantry pantry = (Pantry) intent.getSerializableExtra("pantry");
        pantryId = pantry.getId();

        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = (Food) foodList.getItemAtPosition(position);
                Intent intentGoToForm = new Intent(FoodListActivity.this, FoodFormActivity.class);
                food.setPantryId(pantry.getId());
                intentGoToForm.putExtra("food", food);
                startActivity(intentGoToForm);
            }
        });

        Button newFoodButton = (Button) findViewById(R.id.new_food);
        newFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToForm = new Intent(FoodListActivity.this, FoodFormActivity.class);
                Food food = new Food();
                food.setPantryId(pantryId);
                intentGoToForm.putExtra("food", food);
                startActivity(intentGoToForm);
            }
        });
        registerForContextMenu(foodList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFoodList(pantryId);
    }

    private void loadFoodList(Long pantryId) {
        FoodDAO foodDAO = new FoodDAO(this);
        List<Food> foodList = foodDAO.readListFood(pantryId);
        foodDAO.close();

        ArrayAdapter<Food> adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, foodList);
        this.foodList.setAdapter(adapter);
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

                loadFoodList(pantryId);
                return false;
            }
        });
    }
}
