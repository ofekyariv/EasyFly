package com.example.m;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister,btnBack;
    EditText etEmail,etPass ;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnBack= (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPass=(EditText) findViewById(R.id.etPass);
        progressDialog = new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();
    }

    public void register(){
        progressDialog.setMessage("Login Please Wait...");
        progressDialog.show();
        final String email = etEmail.getText().toString();
        final String password = etPass.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    newUser(email,password);
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Authentication successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    LoginActivity.email = etEmail.getText().toString();
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
    private void newUser(String email, String password) {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        DatabaseReference db = firebaseDatabase.getReference("Users/"+ firebaseAuth.getUid());
        User user=new User(email,password);
        db.setValue(user);
    }
    @Override
    public void onClick(View view) {
        if (view == btnRegister) {
           register();
        }
        if (view==btnBack){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
