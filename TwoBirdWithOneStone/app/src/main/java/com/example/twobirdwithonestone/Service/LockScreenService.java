package com.example.twobirdwithonestone.Service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.example.twobirdwithonestone.Activity.LockScreenActivity;

public class LockScreenService extends Service {
    /*
    * 서비스는 백그라운드에서 실행되는 프로세스를 의미합니다 Activity와 동일한 기능을 할 수 있지만 화면에 보이는 것이 없이 background에서 돌아갑니다.
    * */

    private static final String TAG = "LockScreenService";
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
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
        super.onCreate();
        IntentFilter filter =  new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver,filter);
        Log.d(TAG, "onCreate() 호출됨.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨.");
        if(intent == null){
            //아래와 같은 값을 반환하면 서비스가 비정상 종료 되었ㅇ르 때 시스템이 자동으로 재시작한다.
            //만약 재시작하지 않도록 하고 싶으면 다른 상수를 넣으면 된다.
            return Service.START_STICKY;
        }else{
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG, "Command: " + command + ", name : " + name);
        //5초 동안 1초에 한 번 로그를 찍는다.
        for(int i=0; i<5; i++){
            try{
                Thread.sleep(1000);
            }catch(Exception e){ Log.d(TAG, "Empty");}
            Log.d(TAG, "Waiting " + i + " seconds.");
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() 호출됨.");
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
