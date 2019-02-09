package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {

    private int number;

    @SerializedName("identificador")
    @Expose
    private Integer identificador;

    @SerializedName("nombreEmpresa")
    @Expose
    private String nombreEmpresa;

    @SerializedName("urlImagen")
    @Expose
    private String urlImagen;

    @SerializedName("tipoLocal")
    @Expose
    private String tipoLocal;

    @SerializedName("tarifaMinima")
    @Expose
    private Double tarifaMinima;

    @SerializedName("direccion")
    @Expose
    private String direccion;

    public Hotel(){
    }

    public Hotel(Integer identificador, String nombreEmpresa, String urlImagen, String tipoLocal, Double tarifaMinima, String direccion) {
        this.identificador = identificador;
        this.nombreEmpresa = nombreEmpresa;
        this.urlImagen = urlImagen;
        this.tipoLocal = tipoLocal;
        this.tarifaMinima = tarifaMinima;
        this.direccion = direccion;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(String tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public Double getTarifaMinima() {
        return tarifaMinima;
    }

    public void setTarifaMinima(Double tarifaMinima) {
        this.tarifaMinima = tarifaMinima;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public int getNumber() {
        String [] urlPartes = urlImagen.split ("/");
        //solucionar error
        return Integer.parseInt (urlPartes[urlPartes.length -1]) ;
//        String urlImagen = "https://t-organizagroup.com/ws_easyhotel/public/api/imagen/local/local4.JPG";

//        String [] urlPartes = urlImagen.split ("/");
//        String numeroStr = urlPartes[urlPartes.length -1];
//        int numero = Integer.parseInt(numeroStr.replaceAll("\\D+",""));
//        return numero;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
