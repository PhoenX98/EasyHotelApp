package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelDetails {

    @SerializedName("hotelId")
    @Expose
    private String hotelId;
    @SerializedName("supplierPartyl")
    @Expose
    private String supplierPartyl;
    @SerializedName("webSite")
    @Expose
    private String webSite;
    @SerializedName("service")
    @Expose
    private Object service;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ruc")
    @Expose
    private String ruc;
    @SerializedName("registrationAddress")
    @Expose
    private String registrationAddress;
    @SerializedName("localTelephone")
    @Expose
    private String localTelephone;
    @SerializedName("localMobile")
    @Expose
    private String localMobile;
    @SerializedName("localMail")
    @Expose
    private String localMail;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("asignedPortfolio")
    @Expose
    private String asignedPortfolio;

    public HotelDetails(){

    }

    public HotelDetails(String hotelId, String supplierPartyl, String webSite, Object service,
                        String id, String ruc, String registrationAddress, String localTelephone,
                        String localMobile, String localMail, String facebook, String instagram,
                        String twitter, String asignedPortfolio) {

        this.hotelId = hotelId;
        this.supplierPartyl = supplierPartyl;
        this.webSite = webSite;
        this.service = service;
        this.id = id;
        this.ruc = ruc;
        this.registrationAddress = registrationAddress;
        this.localTelephone = localTelephone;
        this.localMobile = localMobile;
        this.localMail = localMail;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.asignedPortfolio = asignedPortfolio;

    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getSupplierPartyl() {
        return supplierPartyl;
    }

    public void setSupplierPartyl(String supplierPartyl) {
        this.supplierPartyl = supplierPartyl;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Object getService() {
        return service;
    }

    public void setService(Object service) {
        this.service = service;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getLocalTelephone() {
        return localTelephone;
    }

    public void setLocalTelephone(String localTelephone) {
        this.localTelephone = localTelephone;
    }

    public String getLocalMobile() {
        return localMobile;
    }

    public void setLocalMobile(String localMobile) {
        this.localMobile = localMobile;
    }

    public String getLocalMail() {
        return localMail;
    }

    public void setLocalMail(String localMail) {
        this.localMail = localMail;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getAsignedPortfolio() {
        return asignedPortfolio;
    }

    public void setAsignedPortfolio(String asignedPortfolio) {
        this.asignedPortfolio = asignedPortfolio;
    }

}
