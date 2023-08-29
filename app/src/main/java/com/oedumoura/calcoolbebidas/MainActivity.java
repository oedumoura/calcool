package com.oedumoura.calcoolbebidas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMarca1;
    private Spinner spinnerMarca2;
    private EditText editTextOutraMarca1;
    private EditText editTextOutraMarca2;
    private TextView textViewResult1;
    private TextView textViewResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMarca1 = findViewById(R.id.spinnerMarca1);
        spinnerMarca2 = findViewById(R.id.spinnerMarca2);
        editTextOutraMarca1 = findViewById(R.id.editTextOutraMarca1);
        editTextOutraMarca2 = findViewById(R.id.editTextOutraMarca2);
        textViewResult1 = findViewById(R.id.textViewResult1);
        textViewResult2 = findViewById(R.id.textViewResult2);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.marcas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMarca1.setAdapter(adapter);
        spinnerMarca2.setAdapter(adapter);

        spinnerMarca1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedMarca = parentView.getItemAtPosition(position).toString();
                if (selectedMarca.equals("Outra")) {
                    editTextOutraMarca1.setVisibility(View.VISIBLE);
                } else {
                    editTextOutraMarca1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Não é necessário implementar nada aqui
            }
        });

        spinnerMarca2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedMarca = parentView.getItemAtPosition(position).toString();
                if (selectedMarca.equals("Outra")) {
                    editTextOutraMarca2.setVisibility(View.VISIBLE);
                } else {
                    editTextOutraMarca2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Não é necessário implementar nada aqui
            }
        });
    }

    public void calcularValores(View view) {
        EditText editTextValor1 = findViewById(R.id.editTextValor1);
        EditText editTextValor2 = findViewById(R.id.editTextValor2);
        EditText editTextMl1 = findViewById(R.id.editTextMl1);
        EditText editTextMl2 = findViewById(R.id.editTextMl2);

        double valor1 = Double.parseDouble(editTextValor1.getText().toString().replace(",", "."));
        double valor2 = Double.parseDouble(editTextValor2.getText().toString().replace(",", "."));

        int ml1 = Integer.parseInt(editTextMl1.getText().toString());
        int ml2 = Integer.parseInt(editTextMl2.getText().toString());

        double vporL1 = 1000 * valor1 / ml1;
        double vporL2 = 1000 * valor2 / ml2;

        String marcaSelecionada1 = spinnerMarca1.getSelectedItem().toString();
        if (marcaSelecionada1.equals("Outra")) {
            marcaSelecionada1 = editTextOutraMarca1.getText().toString();
        }

        String marcaSelecionada2 = spinnerMarca2.getSelectedItem().toString();
        if (marcaSelecionada2.equals("Outra")) {
            marcaSelecionada2 = editTextOutraMarca2.getText().toString();
        }

        textViewResult1.setText("O valor em Litros da " + marcaSelecionada1 + " é: " +
                String.format("%.2f", vporL1) + " reais por litro");
        textViewResult2.setText("O valor em Litros da " + marcaSelecionada2 + " é: " +
                String.format("%.2f", vporL2) + " reais por litro");

        if (vporL1 > vporL2) {
            textViewResult2.append("\nA bebida " + marcaSelecionada2 + " vale mais a pena que a " + marcaSelecionada1);

        } else if (vporL2 > vporL1) {
            textViewResult2.append("\nA bebida " + marcaSelecionada1 + " vale mais a pena que a " + marcaSelecionada2);

        } else {
            textViewResult2.append("\nPreço igual para as duas marcas");
        }
    }
}
