package mr.lmd.personal.service_01.Service.boundService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBoundService extends Service {

    public MyBoundService() {
    }

    //当其他的应用组件绑定到这个Service的时候，就会调用这个方法
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("************MyBoundService ---> onBind");
        //IBinder binder = new FirstBinder();
        return new FirstBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("************MyBoundService ---> onUnbind");
        return super.onUnbind(intent);
    }

    public class FirstBinder extends Binder {
        public String getData() {
            return "test data";
        }
    }

    //?
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("************MyBoundService ---> onStartCommand");
        System.out.println("MyBoundService ————》 onStartCommand");
        System.out.println("flags ————》 " + flags);
        System.out.println("startId ————》 " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("MyBoundService ————》 onDestroy");
        super.onDestroy();
    }


}
