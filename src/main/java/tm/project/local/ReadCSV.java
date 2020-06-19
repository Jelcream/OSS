package tm.project.local;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// res/raw 밑에 파일이 있어야함
//getResources().openRawResource(R.raw.filename); 의 함수를 s의 인자로 줄 것
//읽어온 줄들을 받으려면 getLines() 메소드를 이용할 것

public class ReadCSV {
    private InputStream stream;
    private String[][] Line;

    public ReadCSV(InputStream s){
        int i = 0;
        this.stream = s;
        try{
            CSVReader reader = new CSVReader(new InputStreamReader(stream, "UTF-8"));
            Line = new String[160000][];
            String[] temp;
            while((temp = reader.readNext())!=null){
                Line[i++] = temp;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    String[][] getLines(){
        return Line;
    }
}
