package halla.icsw.pocha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Shopinfor extends AppCompatActivity {
    ArrayList<ArrayList> menu = new ArrayList();
    ArrayList m = new ArrayList();
    ArrayList<String> s = new ArrayList();
    TextView shopName,state;
    ListView list;
    private ArrayList<String> menuList = new ArrayList<String>();
    private ArrayList<String>shopList = new ArrayList<String>();
    private ArrayList<String>priceList = new ArrayList<String>();
    JSONArray jsonArray;
    JSONObject jsonObject;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopinfor);
        NetworkUtil.setNetworkPolicy();
        list=(ListView)findViewById(R.id.buymenulist);
        shopName = (TextView)findViewById(R.id.shopName);
        state = (TextView)findViewById(R.id.state);

        shop();
        ArrayAdapter adapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, s);
        list.setAdapter(adapter);
    }

    public void shop() {
        SharedPreferences pref = getSharedPreferences("shopID",MODE_PRIVATE);
        try {
            PHPRequest request = new PHPRequest("http://101.101.210.207/getMenu.php");
            String result = request.getMenu(pref.getString("shopid",""));
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
                shopName.setText(jsonObject.getString("shopname"));
                if(jsonObject.getString("ststus").equals("0")){
                    state.setText("상태 : CLOSE");
                }else state.setText("상태 : OPEN");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
