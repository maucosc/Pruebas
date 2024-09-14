package com.example.pruebaandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConversionMoneda extends AppCompatActivity {

    private EditText etValor;
    private Button btnPesoChile, btnDolar, btnPesoArgentino, btnEuro, btnYen, btnSoles, btnRegresar;
    private TextView tvResultado;
    private RadioGroup rgMonedaOrigen;
    private RadioButton rdPesoChile, rdDolar, rdPesoArgentino, rdEuro, rdYen, rdSoles;

    //Tasas de conversion ficticias
    private double tasaPesochile = 0.0012;
    private double tasaDolar = 1.0;
    private double tasaPesoargentino = 0.009;
    private double tasaEuro = 0.0011;
    private double tasaYen = 0.0092;
    private double tasaSoles = 0.27;
    private double conversion = 0; //Seteamos la tasa de conversion a 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversion_moneda);

        //Vinculamos los botones con los elementos de la vista
        btnPesoChile = findViewById(R.id.btnPesoChile);
        btnDolar = findViewById(R.id.btnDolar);
        btnPesoArgentino = findViewById(R.id.btnPesoArgentino);
        btnEuro = findViewById(R.id.btnEuro);
        btnYen = findViewById(R.id.btnYen);
        btnSoles = findViewById(R.id.btnSoles);
        //Vinculamos los edittext con los elementos de la vista
        etValor = findViewById(R.id.etValor);
        //Vinculamos los textview con los elementos de la vista
        tvResultado = findViewById(R.id.tvResultado);
        //Vinculamos los radiogroup con los elementos de la vista
        rgMonedaOrigen = findViewById(R.id.rgMonedaOrigen);
        //Vinculamos los radiobutton con los elementos de la vista
        rdPesoChile = findViewById(R.id.rdPesoChile);
        rdDolar = findViewById(R.id.rdDolar);
        rdPesoArgentino = findViewById(R.id.rdPesoArgentino);
        rdEuro = findViewById(R.id.rdEuro);
        rdYen = findViewById(R.id.rdYen);
        rdSoles = findViewById(R.id.rdSoles);

        btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(view -> finish()); //Regresa a la vista anterior

        btnPesoChile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(tasaPesochile, "$");
            }
        });
        btnDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(tasaDolar, "$");
            }
        });
        btnPesoArgentino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(tasaPesoargentino, "₲");
            }
        });
        btnEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(tasaEuro, "€");
            }
        });
        btnYen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(tasaYen, "¥");
            }
        });
        btnSoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirMoneda(tasaSoles, "S/");
            }
        });


    }

    //Metodo para limpiar los campos
    private void limpiarCampos() {
        etValor.setText("");
        tvResultado.setText("");
    }

    private void convertirMoneda(double tasaObjetivo, String monedaObjetivo) {

        String valor = etValor.getText().toString(); //Obtenemos el valor de ingresado como un string

        if (!valor.isEmpty()) {
            double valorResp = Double.parseDouble(valor); // Convertimos el valor a double;
            int SelecMoneda = rgMonedaOrigen.getCheckedRadioButtonId(); // Obtenemos el id del radiobutton seleccionado

            if (SelecMoneda == -1) { //Si radioButton es igual a menos 1 significa que no se selecciono ningun radiobutton
                Toast.makeText(this, "Seleccione una moneda de origen", Toast.LENGTH_SHORT).show();
            } else {
                if (SelecMoneda == R.id.rdPesoChile){
                    conversion = tasaPesochile;
                } else if (SelecMoneda == R.id.rdDolar) {
                    conversion = tasaDolar;
                } else if (SelecMoneda == R.id.rdPesoArgentino) {
                    conversion = tasaPesoargentino;
                } else if (SelecMoneda == R.id.rdEuro) {
                    conversion = tasaEuro;
                } else if (SelecMoneda == R.id.rdYen) {
                    conversion = tasaYen;
                } else if (SelecMoneda == R.id.rdSoles) {
                    conversion = tasaSoles;
                }else {
                    Toast.makeText(this, "Moneda de origen no válida", Toast.LENGTH_SHORT).show();
                    return;
                }

                double resultado = valorResp * tasaObjetivo / conversion; // Conversion de la moneda de origen a la de destino
                tvResultado.setText(String.format("%.2f", resultado) + " " + monedaObjetivo);
            }
        } else {
            Toast.makeText(this, "Ingrese un valor", Toast.LENGTH_SHORT).show();
        }

    }
}