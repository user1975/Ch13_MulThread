package kjs.mjc.ex13_09;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

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
                for (int i = 0; i < 100; i++) {  //not working
                    pb1.setProgress(pb1.getProgress() + 2);
                    pb2.setProgress(pb2.getProgress() + 1);
                    SystemClock.sleep(100);
                }
/*                new Thread() {  //working (익명 클래스로 구현)
                    @Override
                    public void run() {
                        for (int i = pb1.getProgress(); i < 100; i = i + 2) {
                            pb1.setProgress(pb1.getProgress() + 2);
                            SystemClock.sleep(100); //for delay
                        }
                    }
                }.start();

                new Thread(){
                    @Override
                    public void run() {
                        for (int i=pb2.getProgress();i<100;i++) {
                            pb2.setProgress(pb2.getProgress() + 1);
                            SystemClock.sleep(100); //for delay
                        }
                    }
                }.start();*/
            }
        });
    }
}
