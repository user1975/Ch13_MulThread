package kjs.mjc.test13_02;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer; //
    SeekBar sbPlay; //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = MediaPlayer.create(this, R.raw.song1); //raw 위치 다름 ?
        final Switch swPlay = findViewById(R.id.swPlay);

        swPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swPlay.isChecked() == true) {
                    mPlayer.start();
                    playThread(); //SeekBar를 음악과 싱크하여 플레이
                } else mPlayer.stop();
            }
        });

        sbPlay = findViewById(R.id.sbPlay);
        sbPlay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) { //이것만 추가하면 처음부터 재생됨 -> 1)
                    mPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void playThread() {
        new Thread() {
            @Override
            public void run() {
                while (mPlayer.isPlaying()) { //1) 음악이 재생중이라면 전체 길이와 현재지점을 얻어 제어할수 있도록 해야 함
                    sbPlay.setMax(mPlayer.getDuration()); //SeekBar를 음악길이로 설정(원래 길이는 그대로, 보통 음악을 재생해도 바 자체가 물리적으로 조정되진 않음. 바의 범위를 음악 길이만큼 지정하는 것임)
                    sbPlay.setProgress(mPlayer.getCurrentPosition()); //SeekBar의 현재 위치 지정
                    SystemClock.sleep(100);
                }
                sbPlay.setProgress(0);
            }
        }.start();
    }
}
