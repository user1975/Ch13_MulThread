package kjs.mjc.myhandler_01;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;
    private static final int EVENT_COUNT = 1;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = findViewById(R.id.tvCount);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_COUNT:
                    for (int i = 0; i < 20; i++) {
                        Log.d("mycount", Integer.toString(count));
                        tvCount.setText((Integer.toString(count++)));
                        sendEmptyMessageDelayed(EVENT_COUNT, 1000);
                    }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(EVENT_COUNT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(EVENT_COUNT);
    }
}
