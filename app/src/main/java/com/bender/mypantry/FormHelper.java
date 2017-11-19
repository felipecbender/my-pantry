package com.bender.mypantry;

import android.widget.EditText;

import com.bender.mypantry.model.Food;

/**
 * Created by Felipe Bender on 19/11/2017.
 */

public class FormHelper {
    private final EditText nameField;
    private final EditText valueField;
    private final EditText qtdField;
    private final EditText typeField;

    private Food food;

    public FormHelper(FormActivity formActivity) {
        nameField = (EditText) formActivity.findViewById(R.id.form_food_name);
        valueField = (EditText) formActivity.findViewById(R.id.form_food_value);
        qtdField = (EditText) formActivity.findViewById(R.id.form_food_qtd);
        typeField = (EditText) formActivity.findViewById(R.id.form_food_type);
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
        this.food = food;
    }
}
