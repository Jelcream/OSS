package tm.project.locals;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;


public class LocalShop {
    private String name;
    private Double Latitude, Longitude;
    private String address1, address2;
    private int postNumber;
    private String category1;
    private String category2;
    private String simAdd;
    private String phoneNumber;
    private String main_menu;  //11번
    private String repItem;
    private String change;
    private String detail;
    private String opening;
    private boolean delivery;
    private boolean parking;  //17번
    private int Category2_index;
    private LatLng location;//distanceTo로 거리 구하기
    private Location location2;
    private boolean kind = false;
    private boolean Exemplary = false;

    public LocalShop(String[] s){
        simAdd = s[1];
        name = s[2];
        category1 = s[3];
        String[] temp = category1.split("-");
        Log.v("tag", " "+temp.length);
        category1 = temp[0].trim();
        if(temp.length == 2) category2 = temp[1].trim();
        else category2 = null;
        address1 = s[4];
        address2 = s[5];
        phoneNumber = s[6];
        Log.d("LocalShop",s[8]+" "+s[9]);
        postNumber = Integer.parseInt(s[7]);
        Latitude = Double.parseDouble(s[8]);
        Longitude = Double.parseDouble(s[9]);
        if(s[11].equals("0")) main_menu = "없음";
        else {
            main_menu = s[11];
            Exemplary = true;
        }
        if(s[12].equals("0"))repItem = "없음";
        else{
            repItem = s[12];
            kind = true;
        }
        if(s[13].equals("0")) change = "없음";
        else change = s[13];
        if(s[14].equals("0"))  detail = "없음";
        else detail = s[14];
        if(s[15].equals("0")) opening = "없음";
        else opening = s[15];
        if(!s[16].equals("Y")) delivery = false;
        else delivery = true;
        if(!s[17].equals("Y")) parking = false;
        else parking = true;
        location = new LatLng(Latitude, Longitude);
        location2 = new Location(name);
        location2.setLongitude(Longitude);
        location2.setLatitude(Latitude);

    }
    String getName(){
        return name;
    }
    Double getLatitude(){
        return Latitude;
    }
    Double getLongitude(){
        return Longitude;
    }
    String getAddress1(){
        return address1;
    }
    String getAddress2(){
        return address2;
    }
    int getPostNumber(){
        return postNumber;
    }
    String getCategory1(){
        return category1;
    }
    String getCategory2(){
        return category2;
    }
    String getSimAdd(){
        return simAdd;
    }
    String getPhoneNumber(){
        return phoneNumber;
    }
    String getMain_menu(){
        return main_menu;
    }
    String getRepItem(){
        return repItem;
    }
    String getChange(){
        return change;
    }
    String getDetail(){
        return detail;
    }
    String getOpening(){
        return opening;
    }
    boolean getDelivery(){
        return delivery;
    }
    boolean getParking(){
        return parking;
    }
    LatLng getLocation(){
        return location;
    }
    Location getLocal(){
        return location2;
    }
    int getCategory2_index(){return Category2_index;}
    boolean getKind(){return kind;}
    boolean getExemplary(){return Exemplary;}
}
