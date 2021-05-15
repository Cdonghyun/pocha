package halla.icsw.pocha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.util.ArrayList;

class Regist extends AppCompatActivity {
    ArrayList menu = new ArrayList();
    EditText name,menuName,price;
    TextView text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);


    }

    public void menu(View v){



    }



    public void regist(View v){






/*

        try {
            PHPRequest request = new PHPRequest("http://101.101.210.207/insert.php");
            String result = request.PhPregist(String.valueOf(id.getText()), String.valueOf(pwd.getText()), String.valueOf(rb.getText()));

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
*/
    }


}
