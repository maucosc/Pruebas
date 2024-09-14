package com.example.pruebaandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class NumeroAzar extends AppCompatActivity {

    //Declaramos los botones, edittext y textview
    private Button btnSortear, btnRegresar;
    private EditText etPrimerNumero, etSegundoNumero;
    private TextView tvResultado;
    private boolean vacio = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numero_azar);

        //Vinculamos los botones con los elementos de la vista
        btnSortear = findViewById(R.id.btnSortear);
        etPrimerNumero = findViewById(R.id.etPrimerNumero);
        etSegundoNumero = findViewById(R.id.etSegundoNumero);
        tvResultado = findViewById(R.id.tvResultado);

        btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(view -> finish()); //Regresa a la vista anterior

        //Vinculamos el boton con el metodo generarNumeroAzar
        btnSortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generarNumeroAzar();
            }
        });
    }

    private void generarNumeroAzar() {
        if (vacio == true) {tvResultado.setText("Resultado:");} //En el caso de que existan numeros en el textview se vaciara (Innesesario pero ta chistoso)

        String numero1 = etPrimerNumero.getText().toString(); //Obtenemos el primero numero ingresado como un string
        String numero2 = etSegundoNumero.getText().toString(); //Obtenemos el segundo numero ingresado como un string

        //Para poder validar que los campos no esten vacios debe ser un string
        if (numero1.isEmpty() && numero2.isEmpty()) { //Si ambos campos estan vacios se muestra un mensaje de error
            Toast.makeText(this, "No existe rango de numeros", Toast.LENGTH_SHORT).show();
            return;
        }else if (numero1.isEmpty()){ //Si el primer campo esta vacio se muestra un mensaje de error
            Toast.makeText(this, "El primer valor esta vacio", Toast.LENGTH_SHORT).show();
            return;
        }else if (numero2.isEmpty()){ //Si el segundo campo esta vacio se muestra un mensaje de error
            Toast.makeText(this, "El segundo valor esta vacio", Toast.LENGTH_SHORT).show();
            return;
        }

        //Truncamos los valores a enteros
        int min = Integer.parseInt(numero1);
        int max = Integer.parseInt(numero2);


        if (min > max) { //Si el primer numero ingresado es mayor al segundo se muestra un mensaje de error
            Toast.makeText(this, "El primer valor no puede ser mayor que el segundo", Toast.LENGTH_SHORT).show();
        } else if (min == max) {//Si el primer valor ingresados es igual al segundo se muestra un mensaje de error
            Toast.makeText(this, "Los numeros son iguales", Toast.LENGTH_SHORT).show();
        } else {
            //Variable random para generar el numero aleatorio
            Random ran = new Random();
            int numeroAzar = ran.nextInt(max - min + 1) + min; //Bound regresa valor aletorio -1 por eso se le suma 1

            String resultado = "Rango de numeros: "; //String que se concatena a resultado

            //Recorriendo el rango de numero para concatenarlo
            for (int i = min ; i <= max; i++) {
                resultado += i + "-"; //Por cada vuelta imprime el numero y el guion
            }

            resultado = resultado.substring(0, resultado.length() - 1); //Quita el ultimo caracter que es un - para que solo quede el numero

            //Concatenar resultado, salto de linea y concatenar el numero aleatorio
            tvResultado.setText("El numero aletorio es: " + numeroAzar + "\n" + resultado);
            vacio = true;// si es true al dar una vuelta vaciara el textview
        }
    }
}