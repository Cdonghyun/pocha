package halla.icsw.pocha;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.net.MalformedURLException;

public class SignUp extends AppCompatActivity {
    private EditText id,pwd,type;
    private Button btn_send;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        NetworkUtil.setNetworkPolicy();
        id = (EditText)findViewById(R.id.edId);
        pwd = (EditText)findViewById(R.id.edPwd);
        rg = (RadioGroup)findViewById(R.id.selectRg);

        btn_send = (Button)findViewById(R.id.signbt);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rgid = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(rgid);
                try {
                    PHPRequest request = new PHPRequest("http://101.101.210.207/insert.php");
                    String result = request.PhPpocha(String.valueOf(id.getText()),String.valueOf(pwd.getText()),String.valueOf(rb.getText()));

                    if(result.equals("1")){
                        Toast.makeText(getApplication(),"들어감",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplication(),"안 들어감",Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}

