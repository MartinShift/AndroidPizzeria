package com.example.android_pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_pizzeria.models.Pizza;
import com.example.android_pizzeria.models.PizzaType;
import com.example.android_pizzeria.models.Topping;

import java.util.stream.Collectors;

public class CheckoutActivity extends AppCompatActivity {

    private TextView textViewPizzaType;
    private TextView textViewPizzaSize;
    private TextView textViewPizzaToppings;
    private TextView textViewPizzaPrice;
    private EditText editTextName;
    private EditText editTextPhone;
    private Button buttonSubmit;

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Pizza pizza = getIntent().getParcelableExtra("pizza");

        textViewPizzaType = findViewById(R.id.textViewPizzaType);
        textViewPizzaSize = findViewById(R.id.textViewPizzaSize);
        textViewPizzaToppings = findViewById(R.id.textViewPizzaToppings);
        textViewPizzaPrice = findViewById(R.id.textViewPizzaPrice);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity(pizza.getType());
            }
        });

        try {
            textViewPizzaType.setText("Pizza: " + pizza.getType().getName());
            if (pizza.getSize() != null) {
                textViewPizzaSize.setText("Size: " + pizza.getSize().getName());
            } else {
                textViewPizzaSize.setText("Size: Not selected");
            }
            String toppings = pizza.getToppings().stream().map(Topping::getName).collect(Collectors.joining(", "));
            textViewPizzaToppings.setText("Toppings: " + (toppings == "" ? "None" : toppings));
            textViewPizzaPrice.setText("Total Price: " + pizza.getPrice() + "$");
        }
        catch (Exception e) {
            Log.e("CheckoutActivity", "Error: " + pizza.toString() + " " + e.getMessage());
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void openSelectionActivity(PizzaType pizzaType) {
        Intent intent = new Intent(this, SelectionActivity.class);
        intent.putExtra("pizzaType", pizzaType);
        startActivity(intent);
    }
}