package tm.project.local;

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
    private ArrayList<LocalShop> LS = new ArrayList<LocalShop>();

    public ReadCSV(InputStream s){
        int i = 0;
        this.stream = s;
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(stream, "UTF-8"));
            String[] temp;
            temp = reader.readNext();
            while((temp = reader.readNext())!=null){
                Log.v("내용1", temp[0] +" "+temp[8] +" "+temp[9]);
                if(temp[7].equals("")||temp[8].equals("") || temp[9].equals("")) continue;
                LS.add(new LocalShop(temp));
                Log.v("내용2", LS.get(LS.size()-1).getName());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    ArrayList<LocalShop> getLocalShop(){
        return LS;
    }
}
