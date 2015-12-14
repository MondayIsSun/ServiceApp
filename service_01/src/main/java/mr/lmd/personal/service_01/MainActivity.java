package mr.lmd.personal.service_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mr.lmd.personal.service_01.Service.FirstService;
import mr.lmd.personal.service_01.transact.TransactActivity;

public class MainActivity extends Activity {

    private Parcel parcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        System.out.println("MainActivity ——》 onCreate()");
    }

    private void findView(){

        /****************************************************************************/

        //启动式的service

        //启动Service
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FirstService.class);
                MainActivity.this.startService(intent);
                /*
                注意这种方式启动的Service是没办法返回结果给Activity的
                这种方式叫做启动Service
                就是指我在Activity当中启动了一个Service了，
                然后把数据通过Intent交给Service来处理了，
                但是这个Service处理数据处理得如何这个Activity是不得而知的
                这就是启动Service的特点
                 */
            }
        });

        //停止Service
        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FirstService.class);
                MainActivity.this.stopService(intent);
            }
        });

        /****************************************************************************/

        //切换到BindService的Activity
        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BindActivity.class);
                startActivity(intent);
            }
        });

        /****************************************************************************/

        //Parcel对象的使用
        Button btnWriteParcel = (Button) findViewById(R.id.btnWrite);
        btnWriteParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcel = Parcel.obtain();
                parcel.writeInt(12);
                parcel.writeString("HelloParcel");
                parcel.writeFloat(0.04f);
            }
        });

        Button btnReadParcel = (Button) findViewById(R.id.btnRead);
        btnReadParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcel.setDataPosition(0);
                int i = parcel.readInt();
                String s = parcel.readString();
                float f = parcel.readFloat();
                String text = i + "----->" + s + "----->" + f;
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        /****************************************************************************/

        //跳转到Transact页面
        Button btnTransact = (Button) findViewById(R.id.btnTransact);
        btnTransact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TransactActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

}
