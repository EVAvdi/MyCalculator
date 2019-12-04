package ru.netology.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String empty = "";
    ComputationClass com = new ComputationClass();
    private String sNumber;
    private String calculationString;
    private String computationString;

    private TextView operationDisplay;
    private TextView resultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        Button btn0 = findViewById(R.id.btn_zero);
        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Button btn7 = findViewById(R.id.btn_7);
        Button btn8 = findViewById(R.id.btn_8);
        Button btn9 = findViewById(R.id.btn_9);
        Button btnPoint = findViewById(R.id.point);

        Button btnDel = findViewById(R.id.delete);
        Button btnPlusMinus = findViewById(R.id.plus_or_minus);
        Button btnPercent = findViewById(R.id.percent);
        Button btnDevide = findViewById(R.id.division);
        Button btnMult = findViewById(R.id.btn_multiply);
        Button btnMinus = findViewById(R.id.subtraction);
        Button btnPlus = findViewById(R.id.plus);
        Button btnEquals = findViewById(R.id.equals);

        operationDisplay = findViewById(R.id.operation);
        resultDisplay = findViewById(R.id.result);

        btn0.setOnClickListener(NumberOnClickListener);
        btn1.setOnClickListener(NumberOnClickListener);
        btn2.setOnClickListener(NumberOnClickListener);
        btn3.setOnClickListener(NumberOnClickListener);
        btn4.setOnClickListener(NumberOnClickListener);
        btn5.setOnClickListener(NumberOnClickListener);
        btn6.setOnClickListener(NumberOnClickListener);
        btn7.setOnClickListener(NumberOnClickListener);
        btn8.setOnClickListener(NumberOnClickListener);
        btn9.setOnClickListener(NumberOnClickListener);
        btnPoint.setOnClickListener(TeachOnClickListener);
        btnDel.setOnClickListener(DeleteOnClickListener);
        btnPlusMinus.setOnClickListener(TeachOnClickListener);
        btnPercent.setOnClickListener(TeachOnClickListener);
        btnDevide.setOnClickListener(OperationOnClickListener);
        btnMult.setOnClickListener(OperationOnClickListener);
        btnMinus.setOnClickListener(OperationOnClickListener);
        btnPlus.setOnClickListener(OperationOnClickListener);
        btnEquals.setOnClickListener(EqualsOnClickListener);
    }

    View.OnClickListener NumberOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_1:
                sNumber="1";
                break;
            case R.id.btn_2:
                sNumber="2";
                break;
            case R.id.btn_3:
                sNumber="3";
                break;
            case R.id.btn_4:
                sNumber="4";
                break;
            case R.id.btn_5:
                sNumber="5";
                break;
            case R.id.btn_6:
                sNumber="6";
                break;
            case R.id.btn_7:
                sNumber="7";
                break;
            case R.id.btn_8:
                sNumber="8";
                break;
            case R.id.btn_9:
                sNumber="9";
                break;
            case R.id.btn_zero:
                sNumber="0";
                break;
        }
        computationString = resultDisplay.getText().toString();
        computationString = com.numbers(sNumber, computationString);
        resultDisplay.setText(computationString);
        }
    };

    View.OnClickListener TeachOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            computationString = resultDisplay.getText().toString();
            switch (v.getId()){
                case R.id.plus_or_minus:
                    computationString = com.getOpposite(computationString);
                    break;
                case R.id.point:
                    computationString = com.setDot(computationString);
                    break;
                case R.id.percent:
                    computationString = com.setPercent(computationString);
                    break;
            }
            resultDisplay.setText(computationString);
        }
    };

    View.OnClickListener OperationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computationString = resultDisplay.getText().toString();
            calculationString = operationDisplay.getText().toString();
            switch (v.getId()){
                case R.id.btn_multiply:
                    sNumber ="×";
                    break;
                case R.id.division:
                    sNumber="÷";
                    break;
                case R.id.plus:
                    sNumber="+";
                    break;
                case  R.id.subtraction:
                    sNumber="-";
                    break;
            }
            calculationString = com.setText(sNumber, calculationString, computationString);
            resultDisplay.setText(empty);
            operationDisplay.setText(calculationString);
        }
    };

    View.OnClickListener DeleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resultDisplay.setText(empty);
            operationDisplay.setText(empty);
            com.clear();
        }
    };

    View.OnClickListener EqualsOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computationString = com.equal(computationString);
            operationDisplay.setText(empty);
            resultDisplay.setText(computationString);
        }
    };
}


