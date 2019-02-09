package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordenada {

    @SerializedName("identificador")
    @Expose
    private String identificador;

    @SerializedName("nombreEmpresa")
    @Expose
    private String nombreEmpresa;

    @SerializedName("longitud")
    @Expose
    private String longitud;

    @SerializedName("latitud")
    @Expose
    private String latitud;

    public Coordenada(){

    }

    public Coordenada(String identificador, String nombreEmpresa, String longitud, String latitud) {
        this.identificador = identificador;
        this.nombreEmpresa = nombreEmpresa;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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
}
