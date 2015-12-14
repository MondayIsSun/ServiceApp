package mr.lmd.personal.service_02;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {

    @Override
    public void onCreate() {
        Log.i("info", "BindService--onCreate()");
        super.onCreate();
    }

    public class MyBinder extends Binder {

        //在这里面你可以想干嘛就干嘛啦
        public MyBindService getService() {
            return MyBindService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("info", "BindService--onBind()");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("info", "BindService--onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.i("info", "BindService--unbindService()");
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        Log.i("info", "BindService--onDestroy()");
        super.onDestroy();
    }

    //下面就是定义了这个Service当中完成的一些服务
    public void Play() {
        Log.i("info", "播放");
    }

    public void Pause() {
        Log.i("info", "暂停");
    }

    public void Previous() {
        Log.i("info", "上一首");
    }

    public void next() {
        Log.i("info", "下一首");
    }
}
