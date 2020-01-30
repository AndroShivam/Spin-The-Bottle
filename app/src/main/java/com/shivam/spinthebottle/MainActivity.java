package com.shivam.spinthebottle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView bottle;
    private Toolbar toolbar;
    private Random random = new Random();
    private int lastDirection;
    private boolean spinning;
    int item_selection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spin The Bottle");
        toolbar.setTitleTextColor(Color.WHITE);
        bottle = findViewById(R.id.bottle);
    }




    public void spinBottle(View v) {

        if (!spinning) {
            int newDirection = random.nextInt(10000);
            float pivotX = bottle.getWidth() / 2;
            float pivotY = bottle.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastDirection, newDirection, pivotX, pivotY);
            rotate.setDuration(5000);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            lastDirection = newDirection;
            bottle.startAnimation(rotate);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items, menu);

        MenuItem cocacola = menu.findItem(R.id.cocaCola);
        MenuItem pepsi = menu.findItem(R.id.pepsi);
        MenuItem fanta = menu.findItem(R.id.fanta);

        if (item_selection == 1)
            cocacola.setChecked(true);
        else if (item_selection == 2)
            pepsi.setChecked(true);
        else if (item_selection == 3)
            fanta.setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.cocaCola:
                bottle.setImageResource(R.drawable.cocacola);
                item.setChecked(true);
                item_selection = 1;
                break;
            case R.id.pepsi:
                bottle.setImageResource(R.drawable.pepsi);
                item.setChecked(true);
                item_selection = 2;
                break;
            case R.id.fanta:
                bottle.setImageResource(R.drawable.fanta);
                item.setChecked(true);
                item_selection = 3;
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;

    }
}
