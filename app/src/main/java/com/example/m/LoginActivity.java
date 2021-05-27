package com.example.m;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogIn,btnForgotPass,btnRegister;
    EditText etEmail;
    EditText etPass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    public static String email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        btnForgotPass = (Button) findViewById(R.id.btnForgotPass);
        btnForgotPass.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        etEmail=(EditText)findViewById(R.id.EmailSignIn);
        etPass=(EditText)findViewById(R.id.PassSignIn);
    }
    public  void login()
    {
        progressDialog.setMessage("Login Please Wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(etEmail.getText().toString(),etPass.getText().toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                         Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        email = etEmail.getText().toString();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "auth_failed",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }
        );
    }

    @Override
    public void onClick(View view) {
        if (view==btnLogIn){
            login();
        }
        if (view==btnForgotPass){
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        }
        if (view==btnRegister){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}