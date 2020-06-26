package tm.project.locals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClickedItemActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    //리스트에 나오는 메뉴들
    final static String[] LIST_MENU1 = {"농.축.수산물 업소"};
    final static String[] LIST_MENU2 = {"슈퍼 업소"};
    final static String[] LIST_MENU3 = {"매점 업소"};
    final static String[] LIST_MENU4 = {"음료/디저트", "주류/주점", "한식","중식","일식","양식","반찬/도시락","야식","기타음식","분식","뷔페","그 외 아시아음식"};
    final static String[] LIST_MENU5 = {"건강식품 업소"};
    final static String[] LIST_MENU6 = {"유통급식업 업소"};
    int index = 0;
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
                    index = 0;
                    list = LIST_MENU1;
                    break;
                case "슈퍼":
                    index = 1;
                    list = LIST_MENU2;
                    break;
                case "매점":
                    index = 4;
                    list = LIST_MENU3;
                    break;
                case "음식점업":
                    index = 2;
                    list = LIST_MENU4;
                    break;
                case "건강식품":
                    index = 3;
                    list = LIST_MENU5;
                    break;
                case "유통급식업":
                    index = 5;
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
                Log.v("#####", strText);
                MapsActivity.index2 = 12;
                if(index == 2){
                    for(int i = 0 ;i<LIST_MENU4.length;i++)if(LIST_MENU4[i].equals(strText)) MapsActivity.index2 = i;
                }
                //아이콘 클릭하면 지도로 넘어감
                Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapIntent);
                MapsActivity.index = index;

            }
        });
    }
}