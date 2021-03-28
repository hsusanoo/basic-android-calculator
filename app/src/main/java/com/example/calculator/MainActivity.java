package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultView;

    int val1 = Integer.MIN_VALUE, val2 = val1;
    String operator = "";
    boolean isOP1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get from layout
        resultView = findViewById(R.id.resultView);

    }

    /**
     * Une méthodequi affiche l’opérande en cours de saisie.
     * Utiliser la (si nécessaire) dans les méthodes qui suivent.
     */
    public void afficher() {
        boolean isVal1Valid = val1 != Integer.MIN_VALUE;
        resultView.setText(String.valueOf(isOP1 && isVal1Valid ? val1 : val2));
    }

    /**
     * Une méthode pour gérer les 4 boutons d’opérateurs (+, -, * et /). Cette méthode devra
     * prendre une View en paramètre et retrouver le bouton d’opération qui a été pressé pour agir
     * en conséquence. Pour ce faire, vous pouvez utiliser la méthode getId() présente dans toutes
     * les vues et qui permet de récupérer l'identifiant de la vue qui a généré l'événement.
     *
     * @param view
     */
    public void setOperator(View view) {
        Button operatorButton = (Button) view;
        operator = operatorButton.getText().toString();

        // We switch to the second operand after tapping an operator
        isOP1 = false;
    }

    /**
     * Une méthode qui gère les boutons associés aux
     * chiffres par conversion numérique de la valeur présente dans le texte du bouton (
     * ajouter un digit à un opérande revient ensuite à multiplier la valeur actuelle de
     * l'opérande par 10 et ajouter à y la valeur du nouveau digit).
     *
     * @param view
     */
    public void ajouterNbr(View view) {
        Button operatorButton = (Button) view;

        // Get numerical value from the number button
        int op = Integer.parseInt(operatorButton.getText().toString());

        if (isOP1) {
            val1 = val1 == Integer.MIN_VALUE ? op : val1 * 10 + op;
        } else {
            val2 = val2 == Integer.MIN_VALUE ? op : val2 * 10 + op;
        }

        afficher();
    }

    /**
     * Une méthode qui effectue l'opération demandée
     * entre les deux opérandes, stocker le résultat en premier opérande et mettre à jour
     * l'affichage.
     *
     * @param view
     */
    public void calculer(View view) {
        int result = 0;
        if (val1 != Integer.MIN_VALUE && val2 != Integer.MIN_VALUE && !operator.equals("")) {
            switch (operator) {
                case "+":
                    result = val1 + val2;
                    break;
                case "-":
                    result = val1 - val2;
                    break;
                case "*":
                    result = val1 * val2;
                    break;
                case "/":
                    result = val1 / val2;
                    break;
            }
        }
        // DEBUG
        System.out.println("Equation : " + val1 + operator + val2 + " = " + result);

        resultView.setText(String.valueOf(result));

        resetValues();
    }

    /**
     * Une méthodequi initialise l’affichage.
     */
    public void clean(View view) {
        resetValues();
        resultView.setText("");
    }

    /**
     * Reset variables to initial values
     */
    private void resetValues() {
        val1 = val2 = Integer.MIN_VALUE;
        isOP1 = true;
        operator = "";
    }
}