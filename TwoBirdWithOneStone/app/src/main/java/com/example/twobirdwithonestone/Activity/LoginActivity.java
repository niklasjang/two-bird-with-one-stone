package com.example.twobirdwithonestone.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
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
        if(etEmail.getText().toString().matches("")){
            Toast.makeText(LoginActivity.this,"이메일을 입력하세요.",Toast.LENGTH_LONG).show();
            return;
        }
        if(etPassword.getText().toString().matches("")){
            Toast.makeText(LoginActivity.this,"비밀번호를 입력하세요.",Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString())

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"회원가입에 성공하였습니다.",Toast.LENGTH_LONG).show();
                        }
                        else{
                            try {
                                throw task.getException();
                            } catch(FirebaseAuthWeakPasswordException e) {
                                Toast.makeText(LoginActivity.this,"비밀번호가 보안에 취약합니다.",Toast.LENGTH_LONG).show();
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(LoginActivity.this,"사용 불가능한 이메일입니다.",Toast.LENGTH_LONG).show();
                            } catch(FirebaseAuthUserCollisionException e) {
                                Toast.makeText(LoginActivity.this,"이미 존재하는 계정입니다.",Toast.LENGTH_LONG).show();
                            } catch(Exception e) {
                                Toast.makeText(LoginActivity.this,"실패 :"+e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

    }

    private void loginId(){
        EditText etEmail = (EditText) findViewById(R.id.et_id);
        EditText etPassword = (EditText) findViewById(R.id.et_password);
        if(etEmail.getText().toString().matches("")){
            Toast.makeText(LoginActivity.this,"이메일을 입력하세요.",Toast.LENGTH_LONG).show();
            return;
        }
        if(etPassword.getText().toString().matches("")){
            Toast.makeText(LoginActivity.this,"비밀번호를 입력하세요.",Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"로그인에 성공하였습니다.",Toast.LENGTH_LONG).show();
                }
                else{
                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                    switch (errorCode) {

                        case "ERROR_INVALID_CUSTOM_TOKEN":
                            Toast.makeText(LoginActivity.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_CUSTOM_TOKEN_MISMATCH":
                            Toast.makeText(LoginActivity.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_INVALID_CREDENTIAL":
                            Toast.makeText(LoginActivity.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_INVALID_EMAIL":
                            Toast.makeText(LoginActivity.this, "이메일 형식을 확인하세요.", Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_WRONG_PASSWORD":
                            Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다. ", Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_USER_MISMATCH":
                            Toast.makeText(LoginActivity.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_REQUIRES_RECENT_LOGIN":
                            Toast.makeText(LoginActivity.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                            Toast.makeText(LoginActivity.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_EMAIL_ALREADY_IN_USE":
                            Toast.makeText(LoginActivity.this, "이미 다른 유저에서 사용중인 이메일입니다.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                            Toast.makeText(LoginActivity.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                            break;
                        case "ERROR_USER_DISABLED":
                            Toast.makeText(LoginActivity.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_USER_TOKEN_EXPIRED":
                            Toast.makeText(LoginActivity.this, "The user's credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_USER_NOT_FOUND":
                            Toast.makeText(LoginActivity.this, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_INVALID_USER_TOKEN":
                            Toast.makeText(LoginActivity.this, "The user's credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_OPERATION_NOT_ALLOWED":
                            Toast.makeText(LoginActivity.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                            break;

                        case "ERROR_WEAK_PASSWORD":
                            Toast.makeText(LoginActivity.this, "비밀번호가 취약합니다.", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(LoginActivity.this, "실패 : "+errorCode , Toast.LENGTH_LONG).show();

                    }
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
