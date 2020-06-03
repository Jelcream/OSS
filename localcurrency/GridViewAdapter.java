package com.example.localcurrencyfranchise;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    List<String> listsource;
    Context mcontext;

    public GridViewAdapter(List<String> listsource, Context mcontext) {
        this.listsource = listsource;
        this.mcontext = mcontext;
    }
    // //BaseAdapter는 추상 클래스이므로, 아래 네 함수들을 반드시 정의한다.

    @Override
    public int getCount() {
        return listsource.size(); //배열의 길이
    }

    //poisition번째의 아이템을 가져온다.
    @Override
    public Object getItem(int position) {
        return listsource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //여기서 리턴하는 뷰가 실제 표시되는 뷰이다.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;
        if(convertView == null){
            btn = new Button(mcontext);
            btn.setLayoutParams(new GridView.LayoutParams(60, 60)); //버튼의 가로, 세로 폭 (60px)
            btn.setPadding(4, 4, 4, 4 );//View 내의 글자의 여백
            btn.setText(listsource.get(position));
            btn.setBackgroundColor(Color.BLUE);
            btn.setTextColor(Color.YELLOW);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //버튼 클릭했을 때
                }
            });
        }
        else {
            btn = (Button)convertView;
        }
        return btn;
    }
}
