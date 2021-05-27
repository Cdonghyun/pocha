package halla.icsw.pocha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Selling extends AppCompatActivity {
    SwitchCompat sw;
    TextView tx1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling);
        NetworkUtil.setNetworkPolicy();
        sw = (SwitchCompat)findViewById(R.id.sw);
        tx1 = (TextView)findViewById(R.id.ooptx);
        SharedPreferences pref = getSharedPreferences("memberInformation",MODE_PRIVATE);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try {
                        String[] result = {null};
                        PHPRequest request = new PHPRequest("http://101.101.210.207/statUpdate.php");
                        result[0] = request.PHPstate(pref.getString("id",""),"true");

                        if (result[0].equals("1")) {
                            Toast.makeText(getApplication(), "가게를 열었습니다", Toast.LENGTH_SHORT).show();
                        }

                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                }

                else{
                    try {
                        String[] result = {null};
                        PHPRequest request = new PHPRequest("http://101.101.210.207/statUpdate.php");
                        result[0] = request.PHPstate(pref.getString("id",""),"false");

                        if (result[0].equals("1")) {
                            Toast.makeText(getApplication(), "가게를 닫았습니다", Toast.LENGTH_SHORT).show();
                        }
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }

                }


            }
        });
    }


}