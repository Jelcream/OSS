package com.example.localcurrencyfranchise;

import androidx.appcompat.app.AppCompatActivity;
import net.daum.mf.map.api.MapView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] array_character = {
            "양식", "일식", "한식", "패스트푸드", "스낵", "커피", "편의점"
    };
    List<String> listsource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridView);

        //실제 그리드 뷰 그리는 부분
        for(String item:array_character){
            listsource.add(item);
            GridViewAdapter adapter = new GridViewAdapter(listsource, this);
            gridView.setAdapter(adapter); //그리드 뷰에 어댑터 등록
        }

        //버튼을 클릭하면
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
            }
        });
    }


}
