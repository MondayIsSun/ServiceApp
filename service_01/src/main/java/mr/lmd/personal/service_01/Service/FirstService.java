package mr.lmd.personal.service_01.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FirstService extends Service {

    public FirstService() {
        System.out.println("FirstService ————》 Constructor");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        System.out.println("FirstService ——》 onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        System.out.println("FirstService ————》 onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("FirstService ————》 onStartCommand");
        System.out.println("flags ————》 " + flags);
        System.out.println("startId ————》 " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("FirstService ————》 onDestroy");
        super.onDestroy();
    }
}
