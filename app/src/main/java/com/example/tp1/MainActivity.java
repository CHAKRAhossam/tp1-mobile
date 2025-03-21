package com.example.tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private Spinner spinnerBaseFrom, spinnerBaseTo;
    private TextView resultText;
    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        spinnerBaseFrom = findViewById(R.id.spinnerBaseFrom);
        spinnerBaseTo = findViewById(R.id.spinnerBaseTo);
        resultText = findViewById(R.id.resultText);
        btnConvert = findViewById(R.id.btnConvert);

        // Options pour les bases numériques
        String[] bases = {"Décimal", "Binaire", "Octal", "Hexadécimal"};

        // Adapter pour les Spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bases);
        spinnerBaseFrom.setAdapter(adapter);
        spinnerBaseTo.setAdapter(adapter);

        // Action du bouton
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirNombre();
            }
        });
    }

    private void convertirNombre() {
        String nombreStr = inputNumber.getText().toString();
        if (nombreStr.isEmpty()) {
            resultText.setText("Veuillez entrer un nombre.");
            return;
        }

        int baseFrom = getBaseFromSelection(spinnerBaseFrom.getSelectedItem().toString());
        int baseTo = getBaseFromSelection(spinnerBaseTo.getSelectedItem().toString());

        try {
            int nombre = Integer.parseInt(nombreStr, baseFrom);
            String resultat = Integer.toString(nombre, baseTo).toUpperCase();
            resultText.setText("Résultat : " + resultat);
        } catch (NumberFormatException e) {
            resultText.setText("Format de nombre invalide.");
        }
    }

    private int getBaseFromSelection(String base) {
        switch (base) {
            case "Binaire":
                return 2;
            case "Octal":
                return 8;
            case "Hexadécimal":
                return 16;
            default:
                return 10;
        }
    }
}
