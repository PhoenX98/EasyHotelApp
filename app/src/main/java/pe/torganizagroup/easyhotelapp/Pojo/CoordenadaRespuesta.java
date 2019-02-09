package pe.torganizagroup.easyhotelapp.Pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CoordenadaRespuesta {

    @SerializedName("data")
    @Expose
    private ArrayList<Coordenada> data;

    public CoordenadaRespuesta(){

    }

    public CoordenadaRespuesta(ArrayList<Coordenada> data) {
        this.data = data;
    }

    public ArrayList<Coordenada> getData() {
        return data;
    }

    public void setData(ArrayList<Coordenada> data) {
        this.data = data;
    }
}
