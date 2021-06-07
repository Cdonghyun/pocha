package halla.icsw.pocha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Regist extends AppCompatActivity {
    ArrayList<ArrayList> menu = new ArrayList();
    ArrayList m = new ArrayList();
    ArrayList<String> s = new ArrayList();
    EditText shopName, menuName, price;
    ListView list;
    Button bt1,bt2,bt3;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        NetworkUtil.setNetworkPolicy();
        menuName = (EditText) findViewById(R.id.edMenu);
        price = (EditText) findViewById(R.id.edPrice);
        list= (ListView) findViewById(R.id.storedMenu);
        shopName = (EditText)findViewById(R.id.edShopName);
        bt1 =(Button)findViewById(R.id.btRegist);
        bt2 =(Button)findViewById(R.id.btSt);
        bt3 = (Button)findViewById(R.id.btDel);

        final String[] result = {null};
        SharedPreferences pref = getSharedPreferences("memberInformation",MODE_PRIVATE);
        ArrayAdapter adapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, s);
        list.setAdapter(adapter);


        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SparseBooleanArray check= list.getCheckedItemPositions();
                int count = adapter.getCount() ;

                for (int i = count-1; i >= 0; i--) {
                    if (check.get(i)) {
                        s.remove(i);
                        menu.remove(i);
                    }
                }
                list.clearChoices() ;
                adapter.notifyDataSetChanged();

            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname = shopName.getText().toString();
                PHPRequest request;
                try {
                    for(int i=0;i<menu.size();i++) {
                        request = new PHPRequest("http://101.101.210.207/menuInsert.php");
                        result[0] = request.PHPregist(String.valueOf(menu.get(i).get(0)), String.valueOf(menu.get(i).get(1)), String.valueOf(sname), pref.getString("id",""));
                    }
                    if (result[0].equals("1")) {
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
    }

    public void menu(View v) {
        m.add(menuName.getText().toString());
        m.add(price.getText().toString());
        menu.add(m);
        m = new ArrayList<>();
        s.add(menuName.getText()+"  ㅡ  "+price.getText()+"원");
        menuName.setText("");
        price.setText("");
    }
}
