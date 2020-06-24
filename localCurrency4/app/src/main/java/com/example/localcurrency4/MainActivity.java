package com.example.localcurrency4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GridView gridView;//(BaseAdapter 상속받은) 어댑터를 그리드 뷰에 적용

    String[] names = {"농.축.수산물", "슈퍼", "매점", "음식점업", "건강식품", "유통급식업"};
    int[] images = {R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4, R.drawable.item5, R.drawable.item6};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_action, menu) ;

        return true ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        //커스텀 어댑터
        CustomAdapter customAdapter = new CustomAdapter(names, images, this);

        gridView.setAdapter(customAdapter);//어댑터를 그리드 뷰에 적용

        //그리드 뷰 아이템 클릭하면
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 /*
                String selectedName = names[i];
                int selectedImage = images[i];

                //putExtra : 다른 Activity의 데이터 전달 , getExtra 로 데이터 전달받음
                startActivity(new Intent(MainActivity.this,ClickedItemActivity.class).putExtra("name",selectedName).putExtra("image",selectedImage));

                if(i != 3){
                    //아이콘 클릭하면 지도로 넘어감
                    Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(mapIntent);
                }

                  */
                 if(i == 3){
                     String selectedName = names[i];
                     int selectedImage = images[i];

                     //putExtra : 다른 Activity의 데이터 전달 , getExtra 로 데이터 전달받음
                     startActivity(new Intent(MainActivity.this,ClickedItemActivity.class).putExtra("name",selectedName).putExtra("image",selectedImage));
                 }
                 else{
                     //아이콘 클릭하면 지도로 넘어감
                     Intent mapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                     startActivity(mapIntent);
                 }




            }
        });
    }

    public class CustomAdapter extends BaseAdapter{
        private String[] imageNames;
        private int[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater; //view를 만듦

        public CustomAdapter(String[] imageNames, int[] imagesPhoto, Context context) {
            this.imageNames = imageNames;
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view == null){
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);

            }

            TextView tvName = view.findViewById(R.id.tvName);
            ImageView imageView = view.findViewById(R.id.imageView);

            tvName.setText(imageNames[i]);
            imageView.setImageResource(imagesPhoto[i]);

            return view;

        }
    }

    public class AddActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);//뒤로가기 버튼 생성


        }
    }
}
