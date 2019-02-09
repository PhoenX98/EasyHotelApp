package pe.torganizagroup.easyhotelapp.Pojo;

import java.util.ArrayList;

public class LocalRespuesta {

    private ArrayList<Hotel> data;

    public LocalRespuesta(ArrayList<Hotel> data) {
        this.data = data;
    }

    public ArrayList<Hotel> getData() {
        return data;
    }

    public void setData(ArrayList<Hotel> data) {
        this.data = data;
    }
}
