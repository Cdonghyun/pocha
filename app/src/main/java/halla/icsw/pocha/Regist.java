package halla.icsw.pocha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class Regist extends AppCompatActivity {

    ArrayList<ArrayList> menu = new ArrayList();
    ArrayList m = new ArrayList();
    ArrayList<String> s = new ArrayList();
    EditText shopName, menuName, price;
    ListView list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);


    }

    public void menu(View v){
        menuName = (EditText) findViewById(R.id.edMenu);
        price = (EditText) findViewById(R.id.edPrice);
        list= (ListView) findViewById(R.id.storedMenu);

        m.add(menuName.getText().toString());
        m.add(price.getText().toString());
        menu.add(m);
        s.add(menuName.getText().toString()+"  ㅡ  "+price.getText().toString());
        ArrayAdapter itemsAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, s);
        list.setAdapter(itemsAdapter);

    }


    public void regist(View v) {
        shopName = (EditText)findViewById(R.id.edShopName);
        String sname = shopName.getText().toString();

        try {
            PHPRequest request = new PHPRequest("http://101.101.210.207/menuInsert.php");
            String result = request.PHPregist(menu,sname);

            if (result.equals("1")) {
                Toast.makeText(getApplication(), "등록되었습니다", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), SellerMain.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplication(), "등록 실패", Toast.LENGTH_SHORT).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



    }
}