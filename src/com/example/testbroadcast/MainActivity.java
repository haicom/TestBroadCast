package com.example.testbroadcast;





import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.Menu;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE_ENABLE_ADMIN = 1;
    
    private ComponentName mVrvDeviceAdmin;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVrvDeviceAdmin = new ComponentName(this, VrvDeviceAdminReceiver.class);
        activeDeviceAdmin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    private void activeDeviceAdmin() {
        IntentFilter intentFilter = new IntentFilter(DeviceAdminManager.ALARM_BROADCAST_ACTION);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, intentFilter);
        DeviceAdminManager.alarmDeviceAdmin(this);
    }
    
    
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
       

        @Override
        public void onReceive(Context context, Intent intent) {
            if (null == intent || TextUtils.isEmpty(intent.getAction())) return;
            String action = intent.getAction();

            if (DeviceAdminManager.ALARM_BROADCAST_ACTION.equals(action)) {
                
                String deviceLive = EdpPreferenceUtil.getStringValue(context, EdpPreferenceUtil.ADMIN_STATE_KEY,
                        EdpPreferenceUtil.ADMIN_DEAD);
                if (DeviceAdminManager.ALARM_BROADCAST_ACTION.equals(intent.getAction()) && !EdpPreferenceUtil.ADMIN_LIVE.equals(deviceLive)) {
                    Intent adminIntent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                    adminIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    adminIntent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mVrvDeviceAdmin);
                    adminIntent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                            getString(R.string.add_admin_extra_app_text));
                    startActivityForResult(adminIntent, REQUEST_CODE_ENABLE_ADMIN);
                }
            }
            
        }
        
    };
    
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mReceiver);
    };

}
