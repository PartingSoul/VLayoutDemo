package com.parting_soul.vlayout_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Class<?> clazz = null;

        switch (view.getId()) {
            case R.id.bt_recipe_home:
                clazz = RecipeHomeActivity.class;
                break;
            case R.id.bt_taobao_home:
                clazz = TaoBaoHomeActivity.class;
                break;
            default:
        }

        if (clazz != null) {
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        }
    }
}
