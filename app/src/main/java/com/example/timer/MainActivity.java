package com.example.timer;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    private EditText inputTime;
    private Button startButton;
    private TextView timerText;


    private Handler handler = new Handler();
    private int currentTime = 0;
    private int targetTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTime = findViewById(R.id.inputTime);
        startButton = findViewById(R.id.startButton);
        timerText = findViewById(R.id.timerText);


        startButton.setOnClickListener(v -> {
            String input = inputTime.getText().toString().trim();


            if (TextUtils.isEmpty(input)) {
                Toast.makeText(this, "Digite um tempo válido", Toast.LENGTH_SHORT).show();
                return;
            }


            try {
                targetTime = Integer.parseInt(input);
                if (targetTime <= 0) {
                    Toast.makeText(this, "Digite um número maior que 0", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
                return;
            }


            currentTime = 0;
            timerText.setText("0");
            handler.removeCallbacks(timerRunnable); 
            handler.postDelayed(timerRunnable, 1000); 
        });
    }


    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            currentTime++;
            timerText.setText(String.valueOf(currentTime));


            if (currentTime < targetTime) {
                handler.postDelayed(this, 1000);
            } else {
                Toast.makeText(MainActivity.this, "Tempo alcançado!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
