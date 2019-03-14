package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class GenHot {

    @SerializedName("latitud")
    @Expose
    private Double latitud;
    @SerializedName("longitud")
    @Expose
    private Double longitud;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("calificacion")
    @Expose
    private String calificacion;
    @SerializedName("tarifaMinima")
    @Expose
    private Integer tarifaMinima;
    @SerializedName("distrito")
    @Expose
    private String distrito;
    @SerializedName("distancia")
    @Expose
    private Integer distancia;
    @SerializedName("nombreLocal")
    @Expose
    private String nombreLocal;
//    @SerializedName("location")
//    @Expose
//    private List<Location> location;
//    @SerializedName("locacion")
//    @Expose
//    private String locacion;

    public GenHot(){

    }

    public GenHot(Double latitud, Double longitud, String image, String createdAt, String updatedAt, String id, String nombre, String direccion, String calificacion, Integer tarifaMinima, String distrito, Integer distancia, String nombreLocal) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.tarifaMinima = tarifaMinima;
        this.distrito = distrito;
        this.distancia = distancia;
        this.nombreLocal = nombreLocal;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getTarifaMinima() {
        return tarifaMinima;
    }

    public void setTarifaMinima(Integer tarifaMinima) {
        this.tarifaMinima = tarifaMinima;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }
}
