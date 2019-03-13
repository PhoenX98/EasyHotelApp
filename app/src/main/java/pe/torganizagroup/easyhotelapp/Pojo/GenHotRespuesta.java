package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GenHotRespuesta {

//    @SerializedName("JSON")
//    @Expose
    private List<GenHot> data;

    public GenHotRespuesta(){ }

    public GenHotRespuesta(List<GenHot> data) {
        this.data = data;
    }

    public List<GenHot> getData() {
        return data;
    }

    public void setData(ArrayList<GenHot> data) {
        this.data = data;
    }
}
