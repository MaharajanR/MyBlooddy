package world.myblooddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import world.myblooddy.DataStore.Requests;

import static world.myblooddy.DataStore.AppConstants.SERVER;

public class RegisterActivity extends AppCompatActivity  {
    private Button reg;
    private EditText etUser,etPass,etMobile;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser= (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        etMobile = (EditText) findViewById(R.id.et_mob_no);

        spinner = (Spinner) findViewById(R.id.et_spinner);


        reg = (Button) findViewById(R.id.btnReg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestParams params = new RequestParams();
                params.add("id",etMobile.getText().toString());
                params.add("name",etUser.getText().toString());
                params.add("password",etPass.getText().toString());
                params.add("group",spinner.getSelectedItem().toString());


                AsyncHttpClient client = new AsyncHttpClient();
                client.post(SERVER + "/user/register", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(getApplicationContext(), "Registration Success",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Requests.makeSnackbar(getCurrentFocus(),"Registration Failed");

                    }
                });

            }
        });

    }


    }
