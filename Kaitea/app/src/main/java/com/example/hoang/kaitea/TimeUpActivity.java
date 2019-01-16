package com.example.hoang.kaitea;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TimeUpActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeup_layout);
        alertDialog();
    }

    public void alertDialog()
    {

        AlertDialog alertDialog = new AlertDialog.Builder(TimeUpActivity.this).create();
        alertDialog.setTitle("Hết thời gian ");
        alertDialog.setMessage("Hết thời gian !");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //final Ringtone r = RingtoneManager.getRingtone(getContext(), alarmSound);
        final MediaPlayer player = MediaPlayer.create(getApplicationContext(), alarmSound);

        //player.setLooping(true);

        player.setLooping(true);
        player.start();
        /**while (!player.isPlaying())
         {
         player.start();
         }
         */



        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                player.stop();
                finish();
            }
        });
        alertDialog.show();

    }
}
