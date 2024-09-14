package com.example.pruebaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnConversorMoneda, btnNumeroAletorio; //Declaramos los botones


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Vinculamos los botones con los elementos de la vista
        btnConversorMoneda = findViewById(R.id.btnConversorMoneda);
        btnNumeroAletorio = findViewById(R.id.btnNumeroAletorio);


        btnConversorMoneda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarVistas(view);
            }

        });

        btnNumeroAletorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarVistas(view);
            }

        });

    }
    //Metodo para cambiar de vista
    private void cambiarVistas(View v) {

            if (v.getId() == R.id.btnConversorMoneda){ //Cambiar a vista de conversion de moneda
            startActivity(new Intent(this, ConversionMoneda.class));
        } else if (v.getId() == R.id.btnNumeroAletorio){ //Cambiar a vista de numero aleatorio
            startActivity(new Intent(this, NumeroAzar.class));
        }
    }
}