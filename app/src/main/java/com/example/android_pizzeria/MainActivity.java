package com.example.android_pizzeria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_pizzeria.models.PizzaType;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<PizzaType> samplePizzaTypes = new ArrayList<>();
    private void loadImageIntoView(final int imageViewId, final String imageUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream in = new URL(imageUrl).openStream();
                    final Bitmap image = BitmapFactory.decodeStream(in);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageView imageView = findViewById(imageViewId);
                            imageView.setImageBitmap(image);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static List<PizzaType> getSamplePizzaTypes() {
        List<PizzaType> pizzaTypes = new ArrayList<>();

        pizzaTypes.add(new PizzaType("Quattro formaggi", 10.0, "https://kestepizzago.com/cdn/shop/files/QuattroFormaggi_800x.png?v=1684444690"));
        pizzaTypes.add(new PizzaType("Capricciosa", 12.0, "https://www.pizzaesfizio2.it/wp-content/uploads/2020/09/Tavola-da-disegno-6.png"));
        pizzaTypes.add(new PizzaType("Hawaiian", 11.0, "https://i0.wp.com/palms-restaurant.com.au/wp-content/uploads/2021/09/hawaiian.png?fit=600%2C600&ssl=1"));
        pizzaTypes.add(new PizzaType("Margherita", 9.0, "https://static.vecteezy.com/system/resources/previews/008/541/871/original/margherita-pizza-baked-food-transparent-png.png"));



        return pizzaTypes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        samplePizzaTypes = getSamplePizzaTypes();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadImageIntoView(R.id.pizza_1_image, samplePizzaTypes.get(0).getImageUrl());
        loadImageIntoView(R.id.pizza_2_image, samplePizzaTypes.get(1).getImageUrl());
        loadImageIntoView(R.id.pizza_3_image, samplePizzaTypes.get(2).getImageUrl());
        loadImageIntoView(R.id.pizza_4_image, samplePizzaTypes.get(3).getImageUrl());

        RelativeLayout buttonPizza1 = findViewById(R.id.button_pizza_1);
        buttonPizza1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity(samplePizzaTypes.get(0));
            }
        });

        RelativeLayout buttonPizza2 = findViewById(R.id.button_pizza_2);
        buttonPizza2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity(samplePizzaTypes.get(1));
            }
        });

        RelativeLayout buttonPizza3 = findViewById(R.id.button_pizza_3);
        buttonPizza3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity(samplePizzaTypes.get(2));
            }
        });

        RelativeLayout buttonPizza4 = findViewById(R.id.button_pizza_4);
        buttonPizza4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity(samplePizzaTypes.get(3));
            }
        });
    }

    private void openSelectionActivity(PizzaType pizzaType) {
        Intent intent = new Intent(this, SelectionActivity.class);
        intent.putExtra("pizzaType", pizzaType);
        startActivity(intent);
    }
}