package kjs.mjc.test13_01;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listV;
    Button btnPlay, btnStop, btnPause;
    TextView tVTitle;
    ProgressBar pbPlay;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/";

    MediaPlayer mPlayer;

    boolean PAUSED = false; //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 mp3플레이어(수정)");

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause); //
        btnStop = findViewById(R.id.btnStop);
        tVTitle = findViewById(R.id.tvTitle);
        pbPlay = findViewById(R.id.pbPlay);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        mp3List = new ArrayList<>(); //교재와 다름

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;

        for (File file : listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3); //mp3확장자 파일만 추가
            if (extName.equals("mp3")) {
                mp3List.add(fileName);
            }
        }

        listV = (ListView) findViewById(R.id.listV);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listV.setAdapter(adapter);
        listV.setItemChecked(0, true);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = mp3List.get(position);
            }
        });
        selectedMP3 = mp3List.get(0);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btnPlay.setClickable(false);
                    btnPause.setClickable(true); //

                    btnStop.setClickable(true);
                    tVTitle.setText("실행 중인 음악: " + selectedMP3);
                    pbPlay.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() { //
            @Override
            public void onClick(View v) {
                if (PAUSED == false) {
                    mPlayer.pause();
                    btnPause.setText("이어듣기");
                    PAUSED = true;
                    pbPlay.setVisibility(View.INVISIBLE);
                } else {
                    mPlayer.start();
                    btnPause.setText("일시정지");
                    PAUSED = false;
                    pbPlay.setVisibility(View.VISIBLE);
                }
            }
        });
        btnPause.setClickable(false); //

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
                mPlayer.reset();
                btnPlay.setClickable(true);
                btnStop.setClickable(false);

                btnPause.setClickable(false); //
                btnPause.setText("일시정지"); //
                PAUSED = false;

                tVTitle.setText("실행 중인 음악: ");
                pbPlay.setVisibility(View.INVISIBLE);
            }
        });

        btnStop.setClickable(false);
    }
}
