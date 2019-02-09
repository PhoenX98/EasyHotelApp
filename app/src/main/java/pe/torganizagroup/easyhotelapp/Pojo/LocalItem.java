package pe.torganizagroup.easyhotelapp.Pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalItem {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("id_empresa")
    @Expose
    private Integer id_empresa;

    @SerializedName("id_distrito")
    @Expose
    private Integer id_distrito;

    @SerializedName("longitud")
    @Expose
    private String longitud;

    @SerializedName("latitud")
    @Expose
    private String latitud;

    @SerializedName("direccion")
    @Expose
    private String direccion;

    public LocalItem(){
    }

    public LocalItem(Integer id, Integer id_empresa, Integer id_distrito, String longitud, String latitud, String direccion) {
        this.id = id;
        this.id_empresa = id_empresa;
        this.id_distrito = id_distrito;
        this.longitud = longitud;
        this.latitud = latitud;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpresa() {
        return id_empresa;
    }

    public void setIdEmpresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public Integer getIdDistrito() {
        return id_distrito;
    }

    public void setIdDistrito(Integer id_distrito) {
        this.id_distrito = id_distrito;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
