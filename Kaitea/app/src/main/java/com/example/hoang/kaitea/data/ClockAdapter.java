package com.example.hoang.kaitea.data;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hoang.kaitea.R;
import com.example.hoang.kaitea.TimeUpActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by HOANG on 8/29/2018.
 */

public class ClockAdapter extends ArrayAdapter
{
    private List<Timer> listData;
    private LayoutInflater layoutInflater;
    private Activity context;
    public long duration;

    public ClockAdapter(@NonNull Activity context, List<Timer> listData)
    {
        super(context, R.layout.clock_item_layout, listData);
        this.context = context;
        this.listData = listData;

    }


    public View getView(final int position, View view, ViewGroup parent)
    {
        layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.clock_item_layout,null, true);

        TextView teaName = (TextView)rowView.findViewById(R.id.tea_name_text_view);
        final TextView teaTime = (TextView) rowView.findViewById(R.id.edit_time);
        final Switch onTea = (Switch) rowView.findViewById(R.id.switch_button);
        Timer timer = this.listData.get(position);
        duration = timer.getTeaTime();
        teaName.setText(timer.getTeaName());
        onTea.setChecked(timer.isTeaOn());
        teaTime.setText(convertTime(duration));
        final CountDownTimer mCountdown = new CountDownTimer(listData.get(position).getTeaTime(), 1000)
        {

            public void onTick(long millisUntilFinished)
            {
                long millis = millisUntilFinished;
                //String hms = (TimeUnit.MILLISECONDS.toHours(millis)) + ":" + (TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                //counter.setTitle(hms);
                teaTime.setText(convertTime(millis));
                //counter.setTitle(output);


            }

            public void onFinish()  // khi on tick ket thuc
            {
                //counter.setTitle("0:00:00");

                //onTea.isChecked = false;
                //teaTime.setText("00:00:00");
                teaTime.setText(convertTime(listData.get(position).getTeaTime()));
                onTea.setChecked(false);
                //Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
               // Ringtone r = RingtoneManager.getRingtone(context, notification);
                //r.play();
                //startMyActivity();
                addNotification(position);



                //alertDialog(position);

            }
        };

        onTea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() // su kien switch on off
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {


                if (isChecked) mCountdown.start();
                else
                {
                    //mCountdown.onFinish();
                    mCountdown.cancel();
                    teaTime.setText(convertTime(listData.get(position).getTeaTime()));

                }


            }
        });


        //teaTime.setHour(TimeUnit.MILLISECONDSS.toHours(duration));
        return rowView;
    }

    public String convertTime(long duration)  // convert time ve dinh dang hh:mm:ss
    {
        String output = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));

        return output;
    }

    private void addNotification(int position)
    {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        android.support.v4.app.NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.dongho)
                        .setAutoCancel(true)

                        .setTicker(" "+listData.get(position).getTeaName()+" dây !")
                        .setContentTitle(" "+listData.get(position).getTeaName()+" dây !")
                        .setContentText("Hết thời gian rồi !");

        Intent notificationIntent = new Intent(getContext(), TimeUpActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //builder.setContentIntent(ClockAcitivity.class);
        builder.build().flags |= Notification.FLAG_AUTO_CANCEL;
        context.startActivity(notificationIntent);
        // Add as notification
        // toi khong biet code o duoi co tac dung gi -.-
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() +
                60000 * 1000, contentIntent);
        //context.finish();

        //builder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
    }

    public void alertDialog(int position)
    {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Hết thời gian ủ "+listData.get(position).getTeaName());
        alertDialog.setMessage("Đổ trà nào !");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //final Ringtone r = RingtoneManager.getRingtone(getContext(), alarmSound);
        final MediaPlayer player = MediaPlayer.create(getContext(), alarmSound);

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






