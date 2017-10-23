package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Calcul implements Serializable {
    private final ArrayList<Character> AUTHORIZED_OPERATORS = new ArrayList<Character>() {{
        add('+');
        add('-');
        add('*');
        add('/');
    }};
    private ArrayList<Double> nombres;
    private ArrayList<Character> signes;

    public ArrayList<Double> getNombres() {
        return nombres;
    }

    public ArrayList<Character> getSignes() {
        return signes;
    }

    public Calcul(String formule) {
        formule = formule.replace(" ", "");
        nombres = new ArrayList<>();
        signes = new ArrayList<>();
        ArrayList<Integer> operatorPositions = new ArrayList<>(AUTHORIZED_OPERATORS.size());
        boolean end = false;
        do {
            boolean foundOperator = false;
            for (char operator : AUTHORIZED_OPERATORS) {
                int pos = formule.indexOf(operator);
                if (pos < 0)
                    pos = formule.length();
                else
                    foundOperator = true;
                operatorPositions.add(pos);
            }
            if (foundOperator) {
                char operator = AUTHORIZED_OPERATORS.get(operatorPositions.indexOf(Collections.min(operatorPositions)));
                nombres.add(Double.valueOf(formule.substring(0, formule.indexOf(operator))));
                signes.add(operator);
                formule = formule.substring(formule.indexOf(operator) + 1);
            } else {
                nombres.add(Double.valueOf(formule));
                end = true;
            }
            operatorPositions.clear();
        } while (!end);
    }

    public static double solve(Calcul calcul) {
        double res = 0;
        ArrayList<Double> nombres = calcul.getNombres();
        ArrayList<Character> signes = calcul.getSignes();
        res += nombres.get(0);
        int nextPos = 1;
        for (char operator : signes) {
            switch (operator) {
                case '+':
                    res += nombres.get(nextPos++);
                    break;
                case '-':
                    res -= nombres.get(nextPos++);
                    break;
                case '*':
                    res *= nombres.get(nextPos++);
                    break;
                case '/':
                    if(nombres.get(nextPos)==0)
                        res = Double.MAX_VALUE;
                    else
                        res /= nombres.get(nextPos++);
                    break;
            }
        }
        return res;
    }
}
