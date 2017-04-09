package world.myblooddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import world.myblooddy.DataStore.AppConstants;
import world.myblooddy.DataStore.Requests;

import static android.R.attr.button;
import static world.myblooddy.DataStore.AppConstants.SERVER;

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


               final String mobile =  editTextPhone.getText().toString();
                String passwrod = editTextPassword.getText().toString();

                AsyncHttpClient client = new AsyncHttpClient();

                RequestParams params = new RequestParams();
                params.add("id",mobile);
                params.add("password",passwrod);

                Toast.makeText(getApplicationContext(), "Logging in, Please wait..",Toast.LENGTH_SHORT).show();

                client.post(SERVER + "/user/login", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        if(Integer.parseInt(new String(responseBody)) == 1){

                            AppConstants.id = mobile;

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        }else{
                            Requests.makeSnackbar(getCurrentFocus(),"Invalid Credentials");
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), "Error occurred, Try again",Toast.LENGTH_SHORT).show();
                    }
                });

               
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

