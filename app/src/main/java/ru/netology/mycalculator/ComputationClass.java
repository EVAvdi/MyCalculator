package ru.netology.mycalculator;

import java.util.HashMap;

public class ComputationClass {
    private static final String ERROR = "ERROR";
    private static final String zero = "0";
    private static final String plus = "+";
    private static final String minus = "-";
    private static final String division = "÷";
    private static final String multiplication = "×";
    private static final String empty = "";
    private static final String dot = ".";
    private HashMap<Integer, String> expression = new HashMap<>();
    private static int key = 1;
    private static final int expKey = 0;
    private String curr1;

    DoubleToString doub = new DoubleToString();

    public String numbers(String sNumber, String computationString) {
        expression.put(expKey, "0");
        if (computationString.equals(ERROR) || computationString.equals("0") || computationString.equals("-0")) {
            computationString = sNumber;
        } else {
            computationString = computationString + sNumber;
        }
        return computationString;
    }

    private void checkString() {
        String last = expression.get(key - 1);
        if (last.equals(plus) || last.equals(minus) || last.equals(multiplication) || last.equals(division)) {
            expression.remove(key - 1);
            key--;
        }
    }

    //То что срабатывает при нажатии на =
    public String equal(String computationString) {
        //добавляем ячейку "решение" 0
        expression.put(expKey, expression.get(1));

        //Если на основном экране есть данные, добавляем их в вычисления
        if (!computationString.equals(empty)) {
            expression.put(key, computationString);
            key++;
        }
        checkString();
        findOperations(1, key);
        //забираем в строку решение, очищаем мэп и обнуляем переменную ключей
        computationString = expression.get(0);
        expression.clear();
        key = 1;
        return computationString;
    }

    //определяем наличие операторов
    public void findOperations(int start, int end) {
        //если находим умножение или деление...
        for (int i = start; i <= end; i++) {
            if (expression.get(i) != null) {
                if (expression.get(i).equals(multiplication) || expression.get(i).equals(division)) {
                    //...запускаем вычисление умножения и деления
                    order(start, end, i);
                }
            }
        }
        //если не находим, то ищем сложение и вычитание и по аналогии
        for (int i = start; i <= end; i++) {
            if (expression.get(i) != null) {
                if (expression.get(i).equals(plus) || expression.get(i).equals(minus)) {
                    order(start, end, i);
                }
            }
        }
    }

    public void order(int start, int end, int operator) {
        int first = start;
        int second = end;

        for (int i = operator - 1; i >= start; i--) {
            if (expression.get(i) != null) {
                first = i;
                break;
            }
        }
        for (int i = operator + 1; i <= end; i++) {
            if (expression.get(i) != null) {
                second = i;
                break;
            }
        }
        curr1 = solution(operator, first, second);
        expression.put(expKey, curr1);
        findOperations(start, end);
    }

    public String solution(int operator, int start, int end) {
        Double number1 = Double.parseDouble(expression.get(start));
        Double number2 = Double.parseDouble(expression.get(end));
        char operation = expression.get(operator).charAt(0);
        double solution;

        switch (operation) {
            case '÷':
                if (number2 == 0D) {
                    expression.clear();
                    key = 1;
                    expression.put(expKey, ERROR);
                    return ERROR;
                }
                solution = number1 / number2;
                break;
            case '×':
                solution = number1 * number2;
                break;
            case '-':
                solution = number1 - number2;
                break;
            case '+':
                solution = number1 + number2;
                break;
            default:
                solution = 0;
        }
        curr1 = doub.value(solution);
        expression.put(start, curr1);
        expression.remove(end);
        expression.remove(operator);
        return curr1;
    }

    public String setText(String sNumber, String calculationString, String computationString) {

        curr1 = expression.get(key - 1);

        int check = isError(calculationString, computationString);

        if (check == -1 || check == 0) {
            calculationString = empty;
        } else if (check == 1) {
            checkString();
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString.substring(0, calculationString.length() - 1) + sNumber;
        } else if (check == 2 && !computationString.equals(empty)) {
            expression.put(key, computationString);
            key++;
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString + computationString + sNumber;
        } else {
            expression.put(key, computationString);
            key++;
            expression.put(key, sNumber);
            key++;
            calculationString = calculationString + computationString + sNumber;
        }

        return calculationString;
    }

    public int isError(String calculationString, String computationString) {
        int check;
        if (computationString.equals(ERROR)) {
            check = -1;
            return check;
        } else if (computationString.equals(empty) && calculationString.equals(empty)) {
            check = 0;
            return check;
        } else if (computationString.equals(empty)) {
            check = 1;
            return check;
        } else if (calculationString.equals(empty)) {
            check = 2;
            return check;
        } else {
            check = 3;
            return check;
        }
    }

    public String setDot(String computationString) {
        char dotChar;
        computationString = replaceError(computationString);
        for (int i = 0; i < computationString.length(); i++) {
            dotChar = computationString.charAt(i);
            if (dotChar == dot.charAt(0)) {
                return computationString;
            }
        }
        computationString = computationString + dot;
        return computationString;
    }

    public String setPercent(String computationString) {
        double calculationDouble;
        computationString = replaceError(computationString);
        calculationDouble = Double.parseDouble(computationString) / 100;
        computationString = doub.value(calculationDouble);
        return computationString;
    }

    public String getOpposite(String computationString) {
        computationString = replaceError(computationString);
        char first = computationString.charAt(0);
        if (first == minus.charAt(0)) {
            computationString = computationString.substring(1);
        } else {
            computationString = minus + computationString;
        }
        return computationString;
    }

    public String replaceError(String calculationString) {
        if (calculationString.equals(ERROR) || calculationString.equals(empty)) {
            calculationString = zero;
        }
        return calculationString;
    }
    public void clear() {
        expression.clear();
        key = 1;
    }
}
