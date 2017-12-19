package com.example.folfo93.simondice;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    TextView Text;
    Button ButtonGreen, ButtonYellow, ButtonRed, ButtonBlue, Start;
    TimerTask JobTimer;
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Start = (Button) findViewById(R.id.buttonStart);
        ButtonGreen = (Button) findViewById(R.id.buttonGreen);
        ButtonRed = (Button) findViewById(R.id.buttonRed);
        ButtonBlue = (Button) findViewById(R.id.buttonBlue);
        ButtonYellow = (Button) findViewById(R.id.buttonYellow);
        Text = (TextView) findViewById(R.id.textView);


    }

    ArrayList<Integer> randomColor = new ArrayList();
    ArrayList<Integer> ChosenColor = new ArrayList();
    protected static int LVL = 3; // Marcará el nivel(cuanto mayor mas veces parpadeará).
    protected static int CONT = 0;// Cuenta las veces que pulsamos un botón.

    int Level = 1;

    void StartEvent(View a) { //Ejecuta el juego y marca en un TextView el nivel actual.

        CONT = 0;
        StartTimer();
        Text.setText("Level : " + Level);

    }

    void InitTimer() { // Metodo que hace que los botones no colapsen al cambiar de color
        JobTimer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int numero = Random();
                        randomColor.add(numero);

                        if (numero == 0) {
                            ButtonGreen.setBackgroundColor(Color.GREEN);
                            ButtonGreen.postDelayed(new Runnable() {
                                public void run() {
                                    ButtonGreen.setBackgroundColor(Color.parseColor("#1EA307"));
                                }
                            }, 500);

                        }

                        if (numero == 1) {
                            ButtonRed.setBackgroundColor(Color.RED);
                            ButtonRed.postDelayed(new Runnable() {
                                public void run() {
                                    ButtonRed.setBackgroundColor(Color.parseColor("#CD3813"));
                                }
                            }, 500);


                        }
                        if (numero == 2) {
                            ButtonBlue.setBackgroundColor(Color.BLUE);
                            ButtonBlue.postDelayed(new Runnable() {
                                public void run() {
                                    ButtonBlue.setBackgroundColor(Color.parseColor("#136CF1"));
                                }
                            }, 500);

                        }
                        if (numero == 3) {
                            ButtonYellow.setBackgroundColor(Color.YELLOW);
                            ButtonYellow.postDelayed(new Runnable() {
                                public void run() {
                                    ButtonYellow.setBackgroundColor(Color.parseColor("#D4E113"));
                                }
                            }, 500);


                        }
                        CONT++;
                        if (CONT == LVL) {
                            StopTimer();
                            CONT = 0;
                        }

                    }
                });

            }
        };
        LVL++;


    }

    void GreenEvent(View gr) {
        ButtonGreen = (Button) findViewById(R.id.buttonGreen);
        ChosenColor.add(0);
        ButtonGreen.setBackgroundColor(Color.GREEN);

        final long changeTime = 200L;
        ButtonGreen.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonGreen.setBackgroundColor(Color.parseColor("#1EA307"));

            }
        }, changeTime);
        CONT++;
        Check();

    }

    void RedEvent(View r) {
        final Button ButtonRed = (Button) findViewById(R.id.buttonRed);
        ChosenColor.add(1);
        ButtonRed.setBackgroundColor(Color.RED);
        final long changeTime = 200L;
        ButtonRed.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonRed.setBackgroundColor(Color.parseColor("#CD3813"));

            }
        }, changeTime);
        CONT++;
        Check();

    }

    void BlueEvent(View bl) {
        final Button ButtonBlue = (Button) findViewById(R.id.buttonBlue);
        ChosenColor.add(2);
        ButtonBlue.setBackgroundColor(Color.BLUE);
        final long changeTime = 200L;
        ButtonBlue.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonBlue.setBackgroundColor(Color.parseColor("#136CF1"));

            }
        }, changeTime);
        CONT++;
        Check();

    }

    void YellowEvent(View ye) {
        final Button ButtonYellow = (Button) findViewById(R.id.buttonYellow);
        ChosenColor.add(3);
        ButtonYellow.setBackgroundColor(Color.YELLOW);
        final long changeTime = 200L;
        ButtonYellow.postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonYellow.setBackgroundColor(Color.parseColor("#D4E113"));

            }
        }, changeTime);
        CONT++;
        Check();

    }


    public void StartTimer() { // Llama a InitTimer e indica el tiempo de ejecucion de cada boton.
        timer = new Timer();
        InitTimer();
        timer.schedule(JobTimer, 100, 1000);
    }

    public void StopTimer() { // Metodo que pausa el contador.
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    void Check() { // Comprueba los Arrays de los colores generados aleatoriamente y los escogidos.
        if (CONT == LVL) { //Al ganar se añade un color mas al array y borra los anteriores para la nueva ejecucion.
            String ran = randomColor.toString();
            String chos = ChosenColor.toString();
            if (ran.equals(chos)) {
                Toast.makeText(getApplicationContext(), "HAS GANADO", Toast.LENGTH_SHORT).show();
                int numColor = (int) Math.floor(Math.random() * 4);
                randomColor.add(numColor);
                randomColor.clear();
                ChosenColor.clear();
                Level++;
            } else { // Al perder se borran los arrays y se reinicia al nivel 1.
                Toast.makeText(getApplicationContext(), "HAS PERDIDO", Toast.LENGTH_SHORT).show();
                CONT = 0;

                LVL = 3;
                Level = 1;
            }
            ChosenColor.clear();

        }
    }

    public int Random() { // Genera numeros aleatorios a los que se les asigna un valor.
        int numColor = (int) Math.floor(Math.random() * 4);
        return numColor;


    }


}
