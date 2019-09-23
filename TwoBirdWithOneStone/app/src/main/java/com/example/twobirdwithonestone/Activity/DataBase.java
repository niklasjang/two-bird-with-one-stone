package com.example.twobirdwithonestone.Activity;

/***
 email : gyoue200125@gmail.com
 name : park-gyu-young
 day : 2019 09 07
 description : DB access class
 caution : internet permittion 이 꼭 필요하다.

 ***/
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;

public class DataBase {
    private FirebaseFirestore db;
    private UserData currentUserData;
    public DataBase(){
        db = FirebaseFirestore.getInstance();
        currentUserData = null;
    }

    public Task<Void> setUserData(String collectionName,String documentName,UserData data){
        return FirebaseFirestore.getInstance().collection(collectionName).document(documentName).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("DB","Data set success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("DB","Data set failure");
            }
        });
    }
    //db를 읽는 방식은 두 가지, get 사용자가 요청할 때만 서버와 동기화 -> 프로필 정보 접근에 사용
    public Task<QuerySnapshot> registerUserData(String collectionName, String uid){
        return db.collection(collectionName).whereEqualTo("uid",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot>  task) {
                if(task.isSuccessful()) {
                    for (DocumentSnapshot item : task.getResult()) {
                        UserData userData = item.toObject(UserData.class);
                        currentUserData = userData;
                    }
                }else{
                    Log.d("DB","list upload fail");
                }
            }
        });
    }
    //firebase에 있는 user collection에 있는 정보 중에 해당 url에 맞는 정보를 가져온다.
    public UserData getUserData(){
        return currentUserData;
    }

    //increment만큼 point를 증가시키거나, 감소시킨다. -> 상품의 가격을 그대로 넣으면 된다.
    public boolean updateUserPoint(String collectionName, String uid, int increment){
        if(currentUserData != null || currentUserData.getPoint() + increment >= 0){
            db.collection(collectionName).document(uid).update("point", currentUserData.getPoint() + increment);
            return true;
        }else{
            return false;
        }
    }

    //현재의 스위치 값을 입력하면 이에 반대하는 값으로 변경
    public boolean updateUserSwitchLockScreen(String collectionName, String uid, boolean currentSwichLockScreenFlag) {
        if (currentUserData != null) {
            db.collection(collectionName).document(uid).update("switchLockScreen", !currentSwichLockScreenFlag);
            return true;
        } else {
            return false;
        }
    }
}
/**
 * 사용 방법 0, db handler 생성
 * DataBase db = new DataBase();
//사용 방법1, 데이터 클래스를 먼저 생성, uid, point, isSwitch 순서, 만약 db에 넣을 데이터 추가시 데이터 클래스에 getter, setter 맴버 변수 추가바람
//UserData userData = new UserData(FirebaseAuth.getInstance().getUid().toString(),8,false);

 사용방법 2 setUserData 함수를 이용시  db에 데이터를 생성하게 된다. 콜렉션이 더 큰 집합, document가 두 번째로 큰 집합, 이 안에 요소가 userData이다. 반환값은 task인데, 비동기 방식이므로 동기 방식으로
 만들기 위해서는 addOnCompleteListener를 추가하고, 아래와 같이 onComplete 함수에 동기 방식으로 작동시키고 싶은 코드를 작성할 것,
//db.setUserData("Users",FirebaseAuth.getInstance().getUid().toString(),userData).addOnCompleteListener(new OnCompleteListener<Void>() {
//    @Override
//    public void onComplete(@NonNull Task<Void> task) {
//        Log.d("DB", "DB SET");

            //마찬가지로 registerUserData 함수 역시 비동기이므로 동기식으로 작동시키기 위해서는 onComplete함수에 동기 방싣으로 작동시키고 싶은 코드를 작설할 것,
//        db.registerUserData("Users",FirebaseAuth.getInstance().getUid().toString()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                UserData value = db.getUserData();
//                Log.d("DB", String.valueOf(value.isSwitchLockScreen()));
//            }
//        });
//    }
//});

 위 코드는 UserData를 db에 추가시키고, 데이터를 따라 현재 프로그램에 가져온다.  UserData value = db.getUserData(); 부분에서 값을 가져와서 이 값을 통해서 렌더링을 하면 된다. 참고로 global 변수로
 UserData를 하나만들어서 관리하는게 좋다. registerUserData 함수를 통해서 local에 있는 UserData와 db의 UserData를 동기시키는 것이다.

다음은 db에 값을 업데이트 하는 방법이다.
 String currentUID = FirebaseAuth.getInstance().getUid().toString();

 updateUserPoint 함수는 증가할 양만큼 값을 넣어주면 그 만큼 포인트가 증가하게 된다.
 boolean value1 = db.updateUserPoint("Users",currentUID,10);

 updateUserSwitchLockScreen 함수는 잠금화면을 보이게 할지 안할지를 결정하는 플래그 변수를 다루므로 현재 값을 입력하면 이에 반대되는 값으로 바꾸어준다. 예를 들어 false이면 true가 된다.
 boolean value2 = db.updateUserSwitchLockScreen("Users",currentUID,false);
 **/
