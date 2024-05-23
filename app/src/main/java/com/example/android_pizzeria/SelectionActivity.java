package com.example.android_pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_pizzeria.models.Pizza;
import com.example.android_pizzeria.models.PizzaType;
import com.example.android_pizzeria.models.SizeType;
import com.example.android_pizzeria.models.Topping;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionActivity extends AppCompatActivity {
    private PizzaType pizzaType;
    private List<SizeType> pizzaSizes = Arrays.asList(
            new SizeType("Small", 30, 1.0),
            new SizeType("Medium", 60, 1.5),
            new SizeType("Large", 100, 2.0)
    );
    private List<Topping> pizzaToppings = Arrays.asList(
            new Topping("Cheese", 1.0),
            new Topping("Pepperoni", 1.5),
            new Topping("Mushrooms", 1.0),
            new Topping("Corn", 0.5)
    );

    private RadioGroup radioGroupSize;
    private Button buttonNext;

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        pizzaType = getIntent().getParcelableExtra("pizzaType");
        buttonBack = findViewById(R.id.buttonBack);


        radioGroupSize = findViewById(R.id.radioGroupSize);
        buttonNext = findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupSize.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    Toast.makeText(SelectionActivity.this, "Please select a size", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    SizeType selectedSize = pizzaSizes.get(radioGroupSize.indexOfChild(selectedRadioButton));

                    List<Topping> selectedToppings = new ArrayList<>();
                    if (((SwitchMaterial) findViewById(R.id.switchCheeseMaterial)).isChecked()) {
                        selectedToppings.add(pizzaToppings.get(0));
                    }
                    if (((SwitchMaterial) findViewById(R.id.switchPepperoniMaterial)).isChecked()) {
                        selectedToppings.add(pizzaToppings.get(1));
                    }
                    if (((SwitchMaterial) findViewById(R.id.switchMushroomsMaterial)).isChecked()) {
                        selectedToppings.add(pizzaToppings.get(2));
                    }
                    if (((SwitchMaterial) findViewById(R.id.switchCornMaterial)).isChecked()) {
                        selectedToppings.add(pizzaToppings.get(3));
                    }

                    Pizza pizza = new Pizza(selectedSize, pizzaType);
                    pizza.setSize(selectedSize);
                    pizza.setType(pizzaType);
                    for (Topping topping : selectedToppings) {
                        pizza.addTopping(topping);
                    }

                    Intent intent = new Intent(SelectionActivity.this, CheckoutActivity.class);
                    intent.putExtra("pizza", pizza);
                    startActivity(intent);
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(pizzaType);
            }
        });
    }
    private void openMainActivity(PizzaType pizzaType) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}