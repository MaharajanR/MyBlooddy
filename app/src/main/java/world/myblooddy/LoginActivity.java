package world.myblooddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.button;

public class LoginActivity extends AppCompatActivity{
    private Button login,register;
    private EditText editTextPhone,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        editTextPhone = (EditText)findViewById(R.id.etUser);        
        editTextPassword= (EditText)findViewById(R.id.etPass);
        
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnReg);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                 startActivity(i);
            }
        });
    }


}

