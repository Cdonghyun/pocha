package halla.icsw.pocha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
RadioGroup rg;
EditText edId,edPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

    }
    public void signUpComplete(View v) {
        rg = (RadioGroup)findViewById(R.id.selectRg);
        edId = (EditText) findViewById(R.id.suId);
        edPwd = (EditText) findViewById(R.id.suPwd);

        int id = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton)findViewById(id);
        Toast.makeText(this,"가입 : "+edId.getText().toString()+" "+edPwd.getText().toString()+" "+rb.getText().toString(),Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);

    }
}