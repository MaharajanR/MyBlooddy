package world.myblooddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity  {
    private Button reg;
    private TextView tvLogin;
    private EditText etUser,etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       // tvLogin=(TextView)findViewById(R.id.tvLogin);
        etUser= (EditText) findViewById(R.id.etUser);
        reg = (Button) findViewById(R.id.btnReg);
//        reg.setOnClickListener(this);
//        tvLogin.setOnClickListener(this);

    }


    }
