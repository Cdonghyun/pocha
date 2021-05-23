package halla.icsw.pocha;

import android.content.Intent;
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
import java.util.ArrayList;

public class Selling extends AppCompatActivity {
    SwitchCompat sw;
    TextView tx1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        NetworkUtil.setNetworkPolicy();
        sw = (SwitchCompat)findViewById(R.id.sw);
        tx1 = (TextView)findViewById(R.id.ooptx);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplication(), "가게를 열었습니다", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplication(), "가게를 닫았습니다", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }


}