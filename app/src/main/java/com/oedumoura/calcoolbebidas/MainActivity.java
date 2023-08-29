package com.oedumoura.calcoolbebidas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText editTextValor1;
    private EditText editTextMl1;
    private EditText editTextValor2;
    private EditText editTextMl2;
    private TextView textViewResult1;
    private TextView textViewResult2;
    private TextView textViewComparison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValor1 = findViewById(R.id.editTextValor1);
        editTextMl1 = findViewById(R.id.editTextMl1);
        editTextValor2 = findViewById(R.id.editTextValor2);
        editTextMl2 = findViewById(R.id.editTextMl2);
        textViewResult1 = findViewById(R.id.textViewResult1);
        textViewResult2 = findViewById(R.id.textViewResult2);
        textViewComparison = findViewById(R.id.textViewComparison);
    }

    public void calculateButtonClicked(View view) {

        String valor1Str = editTextValor1.getText().toString().replace(",", ".");
        String ml1Str = editTextMl1.getText().toString();
        String valor2Str = editTextValor2.getText().toString().replace(",", ".");
        String ml2Str = editTextMl2.getText().toString();

        if (valor1Str.isEmpty() || ml1Str.isEmpty() || valor2Str.isEmpty() || ml2Str.isEmpty()) {
            textViewResult1.setText("Preencha todos os campos");
            textViewResult2.setText("");
            textViewComparison.setText("");
            return;
        }

        double valor1 = Double.parseDouble(valor1Str);
        int ml1 = Integer.parseInt(ml1Str);
        double valor2 = Double.parseDouble(valor2Str);
        int ml2 = Integer.parseInt(ml2Str);



        double vporL1 = 1000 * valor1 / ml1;
        double vporL2 = 1000 * valor2 / ml2;

        textViewResult1.setText("O valor em Litros da SKOL é: " + String.format("%.2f", vporL1) + " reais");
        textViewResult2.setText("O valor em Litros da BUD é: " + String.format("%.2f", vporL2) + " reais");

        if (vporL1 > vporL2) {
            textViewComparison.setText("A bebida 2 vale mais a pena que a 1");
        } else if (vporL2 > vporL1) {
            textViewComparison.setText("A bebida 1 vale mais a pena que a 2");
        } else {
            textViewComparison.setText("Preço igual para os dois produtos");
        }
    }
}
