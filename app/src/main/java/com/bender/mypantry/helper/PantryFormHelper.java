package com.bender.mypantry.helper;

import android.widget.EditText;

import com.bender.mypantry.R;
import com.bender.mypantry.form.PantryFormActivity;
import com.bender.mypantry.model.Pantry;

/**
 * Created by bender on 21/11/17.
 */

public class PantryFormHelper {
    private final EditText nameField;
    private final EditText supermarketField;

    private Pantry pantry;

    public PantryFormHelper(PantryFormActivity pantryFormActivity) {
        nameField = (EditText) pantryFormActivity.findViewById(R.id.form_pantry_name);
        supermarketField = (EditText) pantryFormActivity.findViewById(R.id.form_pantry_supermarket);
        pantry = new Pantry();
    }

    public Pantry getPantry() {
        pantry.setName(nameField.getText().toString());
        pantry.setSupermarket(supermarketField.getText().toString());
        return pantry;
    }

    public void fillForm(Pantry pantry) {
        nameField.setText(pantry.getName());
        supermarketField.setText(String.valueOf(pantry.getSupermarket()));
        this.pantry = pantry;
    }
}
