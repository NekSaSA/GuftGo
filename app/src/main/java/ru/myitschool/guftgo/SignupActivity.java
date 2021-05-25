package ru.myitschool.guftgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailBox, passwordBox, nameBox;
    Button loginBtni, signupBtn,createBtn;

    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();


        emailBox = findViewById(R.id.emailBox); //делает поле с Emai адресом рабочим
        nameBox = findViewById(R.id.namebox);//делает поле с именем рабочим
        passwordBox = findViewById(R.id.passwordBox);//делает поле с паролем адресом рабочим

        loginBtni = findViewById(R.id.loginbtn); // делает кнопку рабочей
        signupBtn = findViewById(R.id.createBtn); // делает кнопку рабочей

        signupBtn.setOnClickListener(v -> {
            String email, pass, name;
            email = emailBox.getText().toString();
            pass = passwordBox.getText().toString();
            name = nameBox.getText().toString();

            User user = new User();
            user.setEmail(email);
            user.setPass(pass);
            user.setName(name);

            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    database.collection("Users")
                            .document().set(user).addOnCompleteListener(task1 -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
                    Toast.makeText(SignupActivity.this, "Account is created", Toast.LENGTH_SHORT).show();// создает новый аккаунт
                } else {
                    Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

        loginBtni.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }
}