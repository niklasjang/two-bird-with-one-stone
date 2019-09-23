package com.example.twobirdwithonestone.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twobirdwithonestone.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.QuerySnapshot;

/***
email : gyoue200125@gmail.com
 name : park-gyu-young
 day : 2019 09 07
 description : google login display
 caution : 로그인 사용방법

 1, firebase login : 아이디, 비밀번호 칸에 값을 입력하고 signin한 후에, 똑같은 값을 입력하고 login하면 된다.
 2, 구글 login : 그냥 구글 로그인 하면 끝,
 3, 설정 창에 로그아웃 버튼이 존재,
***/
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authStateListener = null;
    private GoogleSignInClient mGoogleSignInClient;
    private DataBase db;
    static Context mContext;
    FirebaseUser user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        //***** Start LoadingActivity 2019-09-08 3AM Hz *****
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        //***** Finish LoadingActivity *****

        //btn id정리
        Button btnSign = (Button) findViewById(R.id.btn_signin);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        SignInButton btnGoogleLogin = (SignInButton) findViewById(R.id.btn_googleLogin);
        //구글 로그인 옵션
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //로그인 세션을 체크하는 부분
        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //회원가입 후 최초 로그인 시에만 db에 생성, 아닐 시에는 데이터를 만들 필요가 없기 때문에 넘어가게 된다.
                user = firebaseAuth.getCurrentUser(); //로그인한 사용자가 없으면 getCurrentUser는 null을 반환합니다.
                if(user != null){
                    db = new DataBase();
                    db.registerUserData("Users",user.getUid()).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(db.getUserData() == null){
                                db.setUserData("Users",user.getUid(),new UserData(user.getUid(),10,true));
                            }else{

                            }
                        }
                    });
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };


        //btn setclick
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEmailId();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginId();
            }
        });

        //구글 로그인 보내기
        btnGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,1);
            }
        });
    }

    private void createEmailId(){
        EditText etEmail = (EditText) findViewById(R.id.et_id);
        EditText etPassword = (EditText) findViewById(R.id.et_password);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"회원가입에 성공하였습니다.",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this,"회원가입에 실패하였습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loginId(){
        EditText etEmail = (EditText) findViewById(R.id.et_id);
        EditText etPassword = (EditText) findViewById(R.id.et_password);
        FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"로그인에 성공하였습니다.",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this,"로그인에 실패하였습니다.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(authStateListener != null) {
            FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(authStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //구글 로그인 성공시 토큰을 가져온다.
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //exception 캐스팅
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                //구글 로그인에 성공했다는 인증서를 받아서 넘겨준다.
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"구글 로그인에 성공하였습니다.",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"구글 로그인에 실패하였습니다.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }catch(ApiException e) {

            }
        }
    }
}
