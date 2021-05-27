package halla.icsw.pocha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText loginId,loginPwd;
    Button loginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void logIn(View v) {
        loginId = (EditText) findViewById(R.id.edtId);
        loginPwd = (EditText) findViewById(R.id.edtPwd);
        String id = loginId.getText().toString();
        String pwd = loginPwd.getText().toString();
        if (id.equals("구매자")){
            Intent i = new Intent(getApplicationContext(),BuyerMain.class);
            startActivity(i);}

        else if (id.equals("판매자")){
            Intent i = new Intent(getApplicationContext(),Selling.class);
            startActivity(i); }

        else Toast.makeText(this, "존재하지 않는 회원", Toast.LENGTH_SHORT).show();

    }

    public void signUp(View v){
        Intent i = new Intent(getApplicationContext(),SignUp.class);
        startActivity(i);

    }


}