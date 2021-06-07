package halla.icsw.pocha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText loginId,loginPwd;
    Button loginBt;
    PHPRequest request;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkUtil.setNetworkPolicy();
    }

    public void logIn(View v) {
        loginId = (EditText) findViewById(R.id.edtId);
        loginPwd = (EditText) findViewById(R.id.edtPwd);
        String id = loginId.getText().toString();
        String pwd = loginPwd.getText().toString();

        // 공유 프레퍼런스로 id 저장
        SharedPreferences pref = getSharedPreferences("memberInformation",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("id",id);
        edit.commit();
        try {
            request = new PHPRequest("http://101.101.210.207/Login.php");
            result = request.Login(id,pwd);
            Log.i("아이디 타입",result);

            if(result.equals("구매자")){
                Toast.makeText(getApplication(), "구매자", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),BuyerMain.class);
                startActivity(i);
            }else if(result.equals("판매자")){
                Toast.makeText(getApplication(), "판매자", Toast.LENGTH_SHORT).show();

                try{
                    request = new PHPRequest("http://101.101.210.207/getRegisterd.php");
                    result = request.getRegisterd(id);
                    Log.i("등록?",result);
                    if(result.equals("1")){
                        Intent i = new Intent(getApplicationContext(),SellerMain.class);
                        startActivity(i);
                    }else {
                        Intent i = new Intent(getApplicationContext(),Regist.class);
                        startActivity(i);
                    }
                }catch (Exception e){
                    e.getStackTrace();
                }


            }else Toast.makeText(this, "ID와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void signUp(View v){
        Intent i = new Intent(getApplicationContext(),SignUp.class);
        startActivity(i);

    }


}