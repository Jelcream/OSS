package tm.project.locals;

import android.util.Log;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// res/raw 밑에 파일이 있어야함
//getResources().openRawResource(R.raw.filename); 의 함수를 s의 인자로 줄 것
//읽어온 줄들을 받으려면 getLines() 메소드를 이용할 것
//InputStream을 전달해줄 것
//InputStream stream;
//stream = getResources().openRawResource(R.raw.local3);
public class ReadCSV {
    private InputStream stream;
    private ArrayList<LocalShop> []LS = new ArrayList[6];

    public ReadCSV(InputStream s){
        int i = 0;
        this.stream = s;
        int index = 0;
        LocalShop tmp;
        for(int x = 0;x<6;x++) {
            LS[x] = new ArrayList<LocalShop>();
        }
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(stream, "UTF-8"));
            String[] temp;
            temp = reader.readNext();
            while((temp = reader.readNext())!=null){
                Log.v("내용1", temp[0] +" "+temp[8] +" "+temp[9]);
                if(temp[7].equals("")||temp[8].equals("") || temp[9].equals("")) continue;
                tmp = new LocalShop(temp);
                Log.v("log", tmp.getName() + " " + tmp.getCategory1()+"!!");
                if(tmp.getCategory1().equals("농.축.수산물"))index =0;
                else if(tmp.getCategory1().equals("슈퍼마켓"))index =1;
                else if(tmp.getCategory1().equals("음식점업"))index = 2;
                else if(tmp.getCategory1().equals("건강식품"))index = 3;
                else if(tmp.getCategory1().equals("매점"))index = 4;
                else if(tmp.getCategory1().equals("위탁급식업"))index=5;
                LS[index].add(tmp);
                Log.v("내용2", LS[index].get(LS[index].size()-1).getCategory1());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    ArrayList<LocalShop>[] getLocalShop(){
        return LS;
    }
}
