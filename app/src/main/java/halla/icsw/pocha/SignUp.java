package halla.icsw.pocha;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.text.TextUtils;
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
    private EditText id,pwd,chkpwd;
    private Button btn_send;
    RadioGroup rg;
    RadioButton selB,selS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        NetworkUtil.setNetworkPolicy();
        id = (EditText)findViewById(R.id.edId);
        pwd = (EditText)findViewById(R.id.edPwd);
        chkpwd = (EditText)findViewById(R.id.chkPwd);
        rg = (RadioGroup)findViewById(R.id.selectRg);
        btn_send = (Button)findViewById(R.id.signbt);
        selB = (RadioButton)findViewById(R.id.selB);
        selS = (RadioButton)findViewById(R.id.selS);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rgid = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(rgid);
                if(TextUtils.isEmpty(id.getText().toString())){
                    Toast.makeText(getApplication(),"ID가 입력되지 않았습니다",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(getApplication(),"비밀번호가 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
                }else if(!pwd.getText().toString().equals(chkpwd.getText().toString())){
                    Toast.makeText(getApplication(),"비밀번호가 일치하지 않았습니다",Toast.LENGTH_SHORT).show();
                }else if(selB.isChecked()==false&&selS.isChecked()==false){
                    Toast.makeText(getApplication(),"유형을 선택하세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                    PHPRequest request = new PHPRequest("http://101.101.210.207/insert.php");
                    String result = request.PhPsignup(String.valueOf(id.getText()), String.valueOf(pwd.getText()), String.valueOf(rb.getText()));

                        PHPRequest request1 = new PHPRequest("http://101.101.210.207/getLocation.php");
                        String result1 = request1.getLocation();
                        System.out.println(result1);


                        if (result.equals("1")) {
                        Toast.makeText(getApplication(), "가입되었습니다", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplication(), "이미 있는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                }

            }
        });
    }
}