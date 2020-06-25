package com.example.local;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClickedItemActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    //리스트에 나오는 메뉴들
    final static String[] LIST_MENU1 = {"농.축.수산물 업소1", "농.축.수산물 업소2", "농.축.수산물 업소3","농.축.수산물 업소4","농.축.수산물 업소5"};
    final static String[] LIST_MENU2 = {"슈퍼 업소1", "슈퍼 업소2", "슈퍼 업소3","슈퍼 업소4","슈퍼 업소5"};
    final static String[] LIST_MENU3 = {"매점 업소1", "매점 업소2", "매점 업소3","매점 업소4","매점 업소5"};
    final static String[] LIST_MENU4 = {"음료/디저트", "주류/주점", "한식","중식","일식","양식","반찬/도시락","야식","기타음식","분식","뷔페","그 외 아시아음식점"};
    final static String[] LIST_MENU5 = {"건강식품 업소1", "건강식품 업소2", "건강식품 업소3","건강식품 업소4","건강식품 업소5"};
    final static String[] LIST_MENU6 = {"유통급식업 업소1", "유통급식업 업소2", "유통급식업 업소3","유통급식업 업소4","유통급식업 업소5"};

    String[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_item);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.tvName);

        //상위카테고리에서 하나 클릭하면 전환되는 화면
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String selectedName = intent.getStringExtra("name");
            int selectedImage = intent.getIntExtra("image", 0);

            textView.setText(selectedName);
            imageView.setImageResource(selectedImage);

            switch(selectedName) {
                case "농.축.수산물":
                    list = LIST_MENU1;
                    break;
                case "슈퍼":
                    list = LIST_MENU2;
                    break;
                case "매점":
                    list = LIST_MENU3;
                    break;
                case "음식점업":
                    list = LIST_MENU4;
                    break;
                case "건강식품":
                    list = LIST_MENU5;
                    break;
                case "유통급식업":
                    list = LIST_MENU6;
                    break;
            }

        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.clicked_listview, list);

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position);

                //아이콘 클릭하면 지도로 넘어감
                Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapIntent);

            }
        });
    }
}