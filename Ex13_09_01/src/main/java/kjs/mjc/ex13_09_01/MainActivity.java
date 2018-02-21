package kjs.mjc.ex13_09_01;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import kjs.mjc.ex13_09_01.R;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb1, pb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.pb1);
        pb2 = findViewById(R.id.pb2);

        findViewById(R.id.btnThread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pb1Start pb1Start = new Pb1Start();
                pb1Start.start();

                Pb2Start pb2Start = new Pb2Start();
                pb2Start.start();
            }
        });
    }


    class Pb1Start extends Thread { //(클래스로 구현)
        @Override
        public void run() {
            for (int i = pb1.getProgress(); i < 100; i =+ 2) {
                pb1.setProgress(pb1.getProgress() + 2);
                SystemClock.sleep(100); //for delay
            }
        }
    }

    class Pb2Start extends Thread {
        @Override
        public void run() {
            for (int i = pb2.getProgress(); i < 100; i++) {
                pb2.setProgress(pb2.getProgress() + 1);
                try {Thread.sleep(100); } catch (InterruptedException e){}
            }
        }
    }
}
