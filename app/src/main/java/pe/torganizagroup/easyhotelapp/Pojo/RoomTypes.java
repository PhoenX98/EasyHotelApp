package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomTypes {

    @SerializedName("01")
    @Expose
    private String _01;

    public RoomTypes(String _01) {
        this._01 = _01;
    }

    public String get01() {
        return _01;
    }

    public void set01(String _01) {
        this._01 = _01;
    }

}
