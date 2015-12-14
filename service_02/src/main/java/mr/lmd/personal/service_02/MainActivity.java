package mr.lmd.personal.service_02;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    Intent intent1;
    Intent intent2;

    MyBindService service;

    ServiceConnection conn = new ServiceConnection() {

        //当启动源跟Service的连接意外丢失的时候会调用这个方法
        //比如当Service崩溃或者被强行杀死
        //但是注意 ---> 如果启动源解除了这个Service的绑定，那么这个方法是不会被调用的
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("info", "ServiceConnection--onServiceDisconnected()");
        }

        //当服务跟启动源连接的时候 会自动回调
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.i("info", "ServiceConnection--onServiceConnected()");
            service = ((MyBindService.MyBinder) binder).getService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                //启动式Service
                intent1 = new Intent(MainActivity.this, MyStartService.class);
                startService(intent1);
                break;
            case R.id.stop:
                stopService(intent1);
                break;
            case R.id.play:
                service.Play();
                break;
            case R.id.pause:
                service.Pause();
                break;
            case R.id.previous:
                service.Previous();
                break;
            case R.id.next:
                service.next();
                break;
            case R.id.bind:
                //绑定形式的Service
                intent2 = new Intent(MainActivity.this, MyBindService.class);
                //注意虽然是绑定的Service ---> 但是我即start又bind也是可以的
                //好处是 ---> 你既可以使用启动Service也可以使用BindService
                //坏处是 ---> 解绑定的时候稍微麻烦一些 ---> 先去停止掉服务，再去解绑定服务
                startService(intent2);
                bindService(intent2, conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                //解绑定
                //这里常遇到的问题是
                //1、解绑定只能解除一次，再次解除就会报异常 ---> 而停止启动式服务却可以多次调用stopService()
                //2、如果没有解除绑定就直接退出程序也会报错
                unbindService(conn);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        //startService(intent2);
        //bindService(intent2, conn, Service.BIND_AUTO_CREATE);
        //先去停止掉服务，再去解绑定服务
        stopService(intent2);
        unbindService(conn);
        super.onDestroy();
    }

}
