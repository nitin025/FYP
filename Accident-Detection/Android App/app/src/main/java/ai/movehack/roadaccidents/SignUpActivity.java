package ai.movehack.roadaccidents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    EditText etUsername,etEmail,etPassword,etPhoneNo,etAddress;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if(getSupportActionBar() !=null)
            getSupportActionBar().hide();

        etUsername =findViewById(R.id.etUsername);
        etEmail =findViewById(R.id.etEmail);
        etPassword =findViewById(R.id.etPassword);
        etPhoneNo =findViewById(R.id.etPhoneNo);
        etAddress =findViewById(R.id.etAddress);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etUsername.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String phone = etPhoneNo.getText().toString().trim();
                String address = etAddress.getText().toString().trim();


                if(isAlreadyPresent(email))
                {
                    Toast.makeText(getApplication(),"Email Already Registered", Toast.LENGTH_LONG).show();
                }
                else {
                    addUser(name, email, phone, password, address);
                }



            }
        });
    }
    public void addUser(String username,String email,String phoneNo,String password,String address){
        ParseObject registerObject = new ParseObject("UserDetails");
        registerObject.put("username", username);
        registerObject.put("email", email);
        registerObject.put("phone", phoneNo);
        registerObject.put("password", password);
        registerObject.put("address",address);

        registerObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                // Here you can handle errors, if thrown. Otherwise, "e" should be null
                if (e != null)
                    Log.e("ERROR", e.getMessage());
                else {
                    Log.e("STATUS", "SUCCESS");
                    Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                    finish();
                }
            }
        });
    }


    boolean isAlreadyPresent(String email){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
        query.whereEqualTo("email", email);

        try {
            List<ParseObject> value = query.find();
            if (value.size() == 1)
                return true;
            else
                return false;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }

}
