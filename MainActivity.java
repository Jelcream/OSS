package com.example.local;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {
    ListView listView;
    String mTitle[] = {"농.축.수산물","슈퍼마켓","음식점업","건강식품","매점","위탁급식업"};
    String mDescription[] = {"내 위치 주변 농.축.수산물업소를 확인하세요 !","내 위치 주변 슈퍼마켓을 확인하세요!","음료/디저트, 주류/주점, 한식, 중식, 일식, 양식, 반찬/도시락, 야식, 기타음식, 분식, 뷔폐, 그 외 아시아 음식점","내 위치 주변 건강식품업소를 확인하세요!","내 위치 주변 매점을 확인하세요!","내 위치 주변 위탁급식업소를 확인하세요!"};
    int images[] = {R.drawable.vegetable, R.drawable.market, R.drawable.restaurant,R.drawable.health,R.drawable.market2,R.drawable.deliveryfood};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_action, menu) ;

        return true ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    String selectedName = mTitle[position];
                    int selectedImage = images[position];

                    //putExtra : 다른 Activity의 데이터 전달 , getExtra 로 데이터 전달받음
                    startActivity(new Intent(MainActivity.this,ClickedItemActivity.class).putExtra("name",selectedName).putExtra("image",selectedImage));
                }
                else{
                    //아이콘 클릭하면 지도로 넘어감
                    Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(mapIntent);
                }
                /*if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), ClickedItemActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[0]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[0]);
                    intent.putExtra("description", mDescription[0]);
                    intent.putExtra("position",""+0);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), ClickedItemActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[1]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[1]);
                    intent.putExtra("description", mDescription[1]);
                    intent.putExtra("position",""+1);
                    startActivity(intent);                }
                if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), ClickedItemActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[2]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[2]);
                    intent.putExtra("description", mDescription[2]);
                    intent.putExtra("position",""+2);
                    startActivity(intent);                }
                if (position == 3) {
                    Intent intent = new Intent(getApplicationContext(),ClickedItemActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[3]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[3]);
                    intent.putExtra("description", mDescription[3]);
                    intent.putExtra("position",""+3);
                    startActivity(intent);                }
                if (position == 4) {
                    Intent intent = new Intent(getApplicationContext(), ClickedItemActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", images[4]);
                    intent.putExtras(bundle);
                    intent.putExtra("title", mTitle[4]);
                    intent.putExtra("description", mDescription[4]);
                    intent.putExtra("position",""+4);
                    startActivity(intent);
                }*/
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            return row;




        }
    }
}