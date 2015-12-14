package mr.lmd.personal.service_01.transact;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mr.lmd.personal.service_01.R;

public class TransactActivity extends Activity {

    private Binder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transact);
        findView();
    }

    private void findView() {

        //绑定Service
        Button btnBind = (Button) findViewById(R.id.btnBind);
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TransactActivity.this, TransactService.class);
                System.out.println("===========>TransactActivity ---> bindService");
                TransactActivity.this.bindService(intent, conn, BIND_AUTO_CREATE);
                Toast.makeText(TransactActivity.this, "开始绑定了...", Toast.LENGTH_SHORT).show();
            }
        });

        //开始交互
        Button btnDo = (Button) findViewById(R.id.btnDo);
        btnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcel request = Parcel.obtain();
                Parcel response = Parcel.obtain();
                request.writeString("from activity ---> data");
                try {
                    //binder.transact(code,data,reply,flag);
                    binder.transact(0, request, response, 0);//--->binder.onTransact()

                    String replyStr = response.readString();
                    System.out.println("TransactActivity: " + replyStr);
                    Toast.makeText(TransactActivity.this, replyStr, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        //再次交互
        Button btnAgain = (Button) findViewById(R.id.btnAgain);
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcel request = Parcel.obtain();
                Parcel response = Parcel.obtain();
                request.writeString("from activity ---> data_again");
                try {
                    //binder.transact(code,data,reply,flag);
                    binder.transact(0, request, response, 0);//--->binder.onTransact()

                    String replyStr = response.readString();
                    System.out.println("TransactActivity: " + replyStr);
                    Toast.makeText(TransactActivity.this, replyStr, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            System.out.println("===========>ServiceConnection ---> Connected");
            TransactActivity.this.binder = (Binder)binder;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



}
