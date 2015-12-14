package mr.lmd.personal.service_03;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button btnEnableOrDisableWIFI, btnGetVoice, btnGetPackageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //通过系统的服务来填充布局
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        setContentView(view);
        //setContentView(R.layout.activity_main);

        //判断网络是否连接
        //Button btnNetwork = (Button) findViewById(R.id.btnNetwork);

        //打开或者关闭WIFI
        btnEnableOrDisableWIFI = (Button) findViewById(R.id.btnEnableOrDisable_WIFI);
        //初始化按钮Text
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            btnEnableOrDisableWIFI.setText("关闭WIFI");
        } else {
            btnEnableOrDisableWIFI.setText("打开WIFI");
        }

        //获取以及修改系统的音量
        //准确地说，系统的音量是有这几个
        //Ringtone,Media,Alarm,Notification
        //btnGetVoice = (Button) findViewById(R.id.btnGetVoice);

        //获取当前应用程序的状态信息
        //比如获取当前应用程序的包名
        //btnGetPackageName = (Button) findViewById(R.id.btnGetPackageName);
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.btnNetwork:
                if (isNetWorkConnected(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "网络已经打开", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "网络未连接", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnEnableOrDisable_WIFI:
                WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                if (wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(false);
                    btnEnableOrDisableWIFI.setText("打开WIFI");
                    Toast.makeText(MainActivity.this, "WIFI已经关闭", Toast.LENGTH_SHORT).show();
                } else {
                    wifiManager.setWifiEnabled(true);
                    btnEnableOrDisableWIFI.setText("关闭WIFI");
                    Toast.makeText(MainActivity.this, "WIFI已经开启", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnGetVoice:
                AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(AUDIO_SERVICE);
                //获取系统的最大音量值
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                //获取系统当前音量值
                int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                //int ringVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                //int alarmVolume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                Toast.makeText(MainActivity.this, "最大音量值=" + maxVolume + " , 当前音量值=" + currentVolume, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnGetPackageName:
                Toast.makeText(MainActivity.this, "API版本问题还未解决", Toast.LENGTH_SHORT).show();
                //ActivityManager activityManager = (ActivityManager) MainActivity.this.getSystemService(ACTIVITY_SERVICE);
                //activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
                //activityManager.getRunningTasks()
                break;
            default:
                break;
        }
    }

    public boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
