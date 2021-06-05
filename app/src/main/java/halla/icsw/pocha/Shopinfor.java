package halla.icsw.pocha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Shopinfor extends AppCompatActivity {

    TextView shopName;
    private ArrayList<String> menuList = new ArrayList<String>();
    private ArrayList<String>shopList = new ArrayList<String>();
    private ArrayList<String>priceList = new ArrayList<String>();
    private ArrayList<String>IDlist = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopinfor);

        shop();

    }

    public void shop() {
        shopName = (TextView)findViewById(R.id.shopName);
        SharedPreferences pref = getSharedPreferences("memberInformation",MODE_PRIVATE);

        try {
            PHPRequest request = new PHPRequest("http://101.101.210.207/getMenu.php");
            String result = request.getMenu(pref.getString("id",""));
            Log.i("데이터",result);
            try {
                JSONArray jsonArray = new JSONArray(result);
                for(int i = 0 ; i<jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    menuList.add(jsonObject.getString("shopname"));
                    shopList.add(jsonObject.getString("menuname"));
                    priceList.add(jsonObject.getString("price"));
                    IDlist.add(jsonObject.getString("id"));

                    if(IDlist.equals()) {

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            shopName.setText(menuList.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
