package mr.lmd.personal.service_01.transact;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class TransactService extends Service {

    public TransactService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("===========>TransactService ---> onBind");
        return new MyBinder();
    }

    class MyBinder extends Binder {

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {

            System.out.println("===========>MyBinder ---> onTransact");

            System.out.println("---->"+code);
            String dataStr = data.readString();
            System.out.println("TransactService: "+dataStr);
            //Toast.makeText(TransactService.this,dataStr,Toast.LENGTH_SHORT).show();
            reply.writeString("from service ---> data" + Math.random());
            return super.onTransact(code, data, reply, flags);
        }
    }


}
