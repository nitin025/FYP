package ai.movehack.roadaccidents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    EditText etEmail,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();


        btnSignIn =findViewById(R.id.btnSignIn);
        btnSignUp =findViewById(R.id.btnSignUp);
        etEmail =findViewById(R.id.etEmail);
        etPassword =findViewById(R.id.etPassword);




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
                query.whereEqualTo("email", email);
                query.whereEqualTo("password",password);
                try {
                    List<ParseObject> value = query.find();
                    if (value.size() == 1) {
                        Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity.this,MainActivity.class));
                        finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Email/Password Incorrect \nPlease Try Again",Toast.LENGTH_SHORT).show();
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }




            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });
    }
}
