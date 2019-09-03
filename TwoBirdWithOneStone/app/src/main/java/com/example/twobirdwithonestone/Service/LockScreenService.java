package com.example.twobirdwithonestone.Service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.twobirdwithonestone.Activity.LockScreenActivity;

public class LockScreenService extends Service {
    /*
    * 서비스는 백그라운드에서 실행되는 프로세스를 의미합니다 Activity와 동일한 기능을 할 수 있지만 화면에 보이는 것이 없이 background에서 돌아갑니다.
    * */

    private static final String TAG = "LockScreenService";
    /*
    * BroadCastReceiver 는 4대 컴포넌트 중에 하나이다. BroadCastReceiver 의 역할은 단말기 안에서 이루어지는 수많은 일들을 대신해서 알려준다.
    * 예를들어 배터리부족,SMS문자메시지,전화가온다거나 하는 일들을 방송알림 해준다.
    * */
    private boolean boolLockScreen = true;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(boolLockScreen && Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
                Intent i = new Intent(context, LockScreenActivity.class);
                /*
                * Service에서 activitity를 호출할 때는 새로운 태스크를 생성하도록 플래그를 추가해야 한다. 서비스는 화면이 없기 떄문에
                * 화면이 없는 서비스에서 화면이 있는 액티비티를 띄우려면 새로운 태스크를 만들어야 한다. 그리고 Activity 객체가 이미 만들어져 있을 때
                * 재사용하도록 Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP를 추가한다.
                * */
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(i);
            }
        }
    };
    public LockScreenService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //Service가 생성될 때 onCreate가 실행된다.
        super.onCreate();
        IntentFilter filter =  new IntentFilter(Intent.ACTION_SCREEN_OFF);
        //BroadcastReceiver의 onReceive()메서드는 intent-filter을 통해 걸러진 intent를 받아들이는 곳이다.
        registerReceiver(receiver,filter);
        Log.d(TAG, "onCreate() 호출됨.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Service의 onCreate가 실행된 이후, onStartCommand가 실행된다. <https://mailmail.tistory.com/9>
        //Main Activity가 생성될 때 startService(intent);를 호출한다.
        Log.d(TAG, "onStartCommand() 호출됨.");
        if(intent == null){
            //아래와 같은 값을 반환하면 서비스가 비정상 종료 되었을 때 시스템이 자동으로 재시작한다.
            //만약 재시작하지 않도록 하고 싶으면 다른 상수를 넣으면 된다.
            return Service.START_STICKY;
        }else{
            boolLockScreen = intent.getBooleanExtra("LockScreen", true);
            //Toast.makeText(getApplicationContext(), "boolLockScreen is " + boolLockScreen, Toast.LENGTH_LONG).show();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){



    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() 호출됨.");
        super.onDestroy();
        unregisterReceiver(receiver);
    }


}
