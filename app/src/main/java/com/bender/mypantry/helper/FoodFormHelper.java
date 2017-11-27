package com.bender.mypantry.helper;

import android.widget.EditText;

import com.bender.mypantry.FoodListActivity;
import com.bender.mypantry.form.FoodFormActivity;
import com.bender.mypantry.R;
import com.bender.mypantry.model.Food;

import java.lang.reflect.Field;

/**
 * Created by Felipe Bender on 19/11/2017.
 */

public class FoodFormHelper {
    private final EditText nameField;
    private final EditText valueField;
    private final EditText qtdField;
    private final EditText typeField;

    private Long pantryId;
    private Food food;

    public FoodFormHelper(FoodFormActivity foodFormActivity) {
        nameField = (EditText) foodFormActivity.findViewById(R.id.form_food_name);
        valueField = (EditText) foodFormActivity.findViewById(R.id.form_food_value);
        qtdField = (EditText) foodFormActivity.findViewById(R.id.form_food_qtd);
        typeField = (EditText) foodFormActivity.findViewById(R.id.form_food_type);
        food = new Food();
    }

    public Food getFood() {
        food.setName(nameField.getText().toString());
        food.setValue(Double.parseDouble(valueField.getText().toString()));
        food.setQtd(Double.parseDouble(qtdField.getText().toString()));
        food.setType(typeField.getText().toString());
        return food;
    }

    public void fillForm(Food food) {
        nameField.setText(food.getName());
        valueField.setText(String.valueOf(food.getValue()));
        qtdField.setText(String.valueOf(food.getQtd()));
        typeField.setText(food.getType());
        pantryId = food.getPantryId();
        this.food = food;
    }
}
