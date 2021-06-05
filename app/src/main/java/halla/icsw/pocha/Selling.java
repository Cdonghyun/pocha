package halla.icsw.pocha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Selling extends AppCompatActivity {
    SwitchCompat sw;
    TextView tx1,shopname;
    ArrayList<ArrayList> menu = new ArrayList();
    ArrayList m = new ArrayList();
    ArrayList<String> s = new ArrayList();
    ListView list;
    JSONArray jsonArray;
    JSONObject jsonObject;
    Button btAdd,btDel;
    EditText edM,edP;
    PHPRequest request;
    String result;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling);
        NetworkUtil.setNetworkPolicy();
        sw = (SwitchCompat)findViewById(R.id.sw);
        tx1 = (TextView)findViewById(R.id.ooptx);
        btAdd =(Button)findViewById(R.id.btAdd);
        btDel =(Button)findViewById(R.id.btDelete);
        shopname =(TextView)findViewById(R.id.shopname);
        mangeShop();

        ArrayAdapter adapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, s);
        list.setAdapter(adapter);
        SharedPreferences pref = getSharedPreferences("memberInformation",MODE_PRIVATE);
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray check= list.getCheckedItemPositions();
                int count = adapter.getCount() ;
                for (int i = count-1; i >= 0; i--) {
                    if (check.get(i)) {
                        try {
                            request = new PHPRequest("http://101.101.210.207/menuDelete.php");
                            result = request.PHPstate(pref.getString("id",""),String.valueOf(menu.get(i).get(0)));
                        }catch (MalformedURLException e){
                            e.printStackTrace();
                        }
                        s.remove(i);
                        menu.remove(i);
                    }
                }
                list.clearChoices() ;
                adapter.notifyDataSetChanged();
                if (result.equals("1")) {
                    Toast.makeText(getApplication(), "해당 메뉴를 삭제했습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.add(edM.getText().toString());
                m.add(edP.getText().toString());
                menu.add(m);
                m = new ArrayList<>();
                s.add(edM.getText()+"  ㅡ  "+edP.getText()+"원");
                edM.setText("");
                edP.setText("");
                try {
                        request = new PHPRequest("http://101.101.210.207/menuInsert.php");
                        result = request.PHPregist(String.valueOf(edM.getText()), String.valueOf(edP.getText()), String.valueOf(shopname.getText()), pref.getString("id",""));
                    if (result.equals("1")) {
                        Toast.makeText(getApplication(), "등록되었습니다", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),SellerMain.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(getApplication(), "등록 실패", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try {
                        PHPRequest request = new PHPRequest("http://101.101.210.207/statUpdate.php");
                        String result = request.PHPstate(pref.getString("id",""),"true");
                        if (result.equals("1")) {
                            Toast.makeText(getApplication(), "가게를 열었습니다", Toast.LENGTH_SHORT).show();
                        }
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        PHPRequest request = new PHPRequest("http://101.101.210.207/statUpdate.php");
                        String result = request.PHPstate(pref.getString("id",""),"false");
                        if (result.equals("1")) {
                            Toast.makeText(getApplication(), "가게를 닫았습니다", Toast.LENGTH_SHORT).show();
                        }
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    public void mangeShop(){
        SharedPreferences pref = getSharedPreferences("memberInformation",MODE_PRIVATE);
        try {
            PHPRequest request = new PHPRequest("http://101.101.210.207/getMenu.php");
            String result = request.getMenu(pref.getString("id",""));
            Log.i("데이터",result);
            try {
                jsonArray = new JSONArray(result);
                for(int i = 0 ; i<jsonArray.length(); i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    m.add(jsonObject.getString("menuname"));
                    m.add(jsonObject.getString("price"));
                    menu.add(m);
                    m = new ArrayList<>();
                    s.add(menu.get(i).get(0)+"      ㅡ      "+menu.get(i).get(1)+"원");
                }
                shopname.setText(jsonObject.getString("shopname"));
                if(jsonObject.getString("status").equals("0")){
                    tx1.setText("상태 : CLOSE");
                }else tx1.setText("상태 : OPEN");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }






}