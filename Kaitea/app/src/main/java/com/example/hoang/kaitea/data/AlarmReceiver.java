package com.example.hoang.kaitea.data;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.widget.Toast;

/**
 * Created by HOANG on 8/31/2018.
 */

public class AlarmReceiver extends BroadcastReceiver
{

    private Context context;
    @Override
    public void onReceive(Context context, Intent intent)
    {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example

        wl.release();
        alertDialog();
    }

    public void alertDialog()
    {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setMessage("Đổ trà nào !");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //final Ringtone r = RingtoneManager.getRingtone(getContext(), alarmSound);
        final MediaPlayer player = MediaPlayer.create(context, alarmSound);

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
            }
        });
        alertDialog.show();

    }
}
