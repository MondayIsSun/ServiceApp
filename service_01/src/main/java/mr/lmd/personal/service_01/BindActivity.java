package mr.lmd.personal.service_01;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import mr.lmd.personal.service_01.Service.boundService.MyBoundService;


public class BindActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        findView();
    }

    private void findView() {

        //绑定Service
        Button btnBind = (Button) findViewById(R.id.btnBind);
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindActivity.this, MyBoundService.class);
                System.out.println("************BindActivity ---> bindService");
                BindActivity.this.bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        //解除绑定
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindActivity.this.unbindService(serviceConnection);
            }
        });

    }

    //ServiceConnection
    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("************BindActivity ---> serviceConnection ---> Connected");
            MyBoundService.FirstBinder firstBinder = (MyBoundService.FirstBinder) service;
            String data = firstBinder.getData();
            System.out.println("data ——》 " + data);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("************BindActivity ---> serviceConnection ---> Disconnected");
        }
    };


}
