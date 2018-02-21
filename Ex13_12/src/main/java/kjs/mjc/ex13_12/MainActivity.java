package kjs.mjc.ex13_12;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb1,pb2;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.pb1);
        pb2 = findViewById(R.id.pb2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        findViewById(R.id.btnThread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new Thread(){  //not working
                    @Override
                    public void run() {
                        for (int i=pb1.getProgress();i<100;i=i+2) {
                            pb1.setProgress(pb1.getProgress() + 2);
                            tv1.setText("1번 진행률: " + pb1.getProgress() + "%");
                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread(){
                    @Override
                    public void run() {
                        for (int i=pb2.getProgress();i<100;i=i+2) {
                            pb2.setProgress(pb2.getProgress() + 2);
                            tv2.setText("2번 진행률: " + pb2.ge
                            tProgress() + "%");
                            SystemClock.sleep(100);
                        }
                    }
                }.start();*/

                new Thread() { //working
                    @Override
                    public void run() {
                        for (int i=pb1.getProgress();i<100;i=+2) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb1.setProgress(pb1.getProgress() + 2);
                                    tv1.setText("1번 진행률: " + pb1.getProgress() + "%");
                                }
                            });
                            SystemClock.sleep(100); //UIThread 안에 해도 상관없음
                        }
                    }
                }.start();
                new Thread() {
                    @Override
                    public void run() {
                        for (int i=pb2.getProgress();i<100;i=+2) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb2.setProgress(pb2.getProgress() + 2);
                                    tv2.setText("2번 진행률: " + pb2.getProgress() + "%");
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });
    }
}
