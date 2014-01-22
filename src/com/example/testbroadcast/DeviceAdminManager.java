package com.example.testbroadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

public class DeviceAdminManager {

    // circle: execute device admin
    private static final int CIRCLE_TIME_DEVICE_ADMIN = 60 * 1000;
    public static final String ALARM_BROADCAST_ACTION = "com.vrv.edp.deviceadmin.AlarmAction";
    private static AlarmManager mAlarmManager;
    private static PendingIntent mAlarmPendintent;

    public static void alarmDeviceAdmin(Context context) {
        long firstTime = SystemClock.elapsedRealtime();
        if (null == mAlarmManager) {
            mAlarmManager = (AlarmManager) context
                    .getSystemService(Context.ALARM_SERVICE);
        }
        mAlarmPendintent = PendingIntent.getBroadcast(context, 0, new Intent(
                ALARM_BROADCAST_ACTION), 0);
        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                firstTime, CIRCLE_TIME_DEVICE_ADMIN, mAlarmPendintent);
    }

    public static void cancelAlarm() {
        mAlarmManager.cancel(mAlarmPendintent);
    }
}
