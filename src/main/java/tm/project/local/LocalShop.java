package tm.project.local;
import android.location.Location;


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

    private Location location;//distanceTo로 거리 구하기


    public LocalShop(String[] s){
        simAdd = s[1];
        name = s[2];
        category1 = s[3];
        String[] temp = category1.split("-");
        category1 = temp[0];
        if(temp.length == 2) category2 = temp[1];
        else category2 = null;
        address1 = s[4];
        address2 = s[5];
        phoneNumber = s[6];
        postNumber = Integer.parseInt(s[7]);
        Latitude = Double.parseDouble(s[8]);
        Longitude = Double.parseDouble(s[9]);
        if(s[11].equals("0")) main_menu = "없음";
        else main_menu = s[11];
        if(s[12].equals("0")) repItem = "없음";
        else repItem = s[12];
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
        location = new Location("LocalShop");
        location.setLatitude(Latitude);
        location.setLongitude(Longitude);

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
    Location getLocation(){
        return location;
    }
}
