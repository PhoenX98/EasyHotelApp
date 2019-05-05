package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelDetails {

    @SerializedName("hotelId")
    @Expose
    private String hotelId;
    @SerializedName("webSite")
    @Expose
    private String webSite;
    @SerializedName("service")
    @Expose
    private List<String> service = null;
    @SerializedName("room_Photos")
    @Expose
    private String[] roomPhotos = null;
//    private List<String> roomPhotos = null;
    @SerializedName("room_Types")
    @Expose
    private RoomTypes roomTypes;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ruc")
    @Expose
    private String ruc;
    @SerializedName("supplierParty")
    @Expose
    private String supplierParty;
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

    public HotelDetails(String hotelId, String webSite, List<String> service, String[] roomPhotos, RoomTypes roomTypes, String id, String ruc, String supplierParty, String registrationAddress, String localTelephone, String localMobile, String localMail, String facebook, String instagram, String twitter, String asignedPortfolio) {
        this.hotelId = hotelId;
        this.webSite = webSite;
        this.service = service;
        this.roomPhotos = roomPhotos;
        this.roomTypes = roomTypes;
        this.id = id;
        this.ruc = ruc;
        this.supplierParty = supplierParty;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public List<String> getService() {
        return service;
    }

    public void setService(List<String> service) {
        this.service = service;
    }

    public String[] getRoomPhotos() {
        return roomPhotos;
    }

    public void setRoomPhotos(String[] roomPhotos) {
        this.roomPhotos = roomPhotos;
    }

    public RoomTypes getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(RoomTypes roomTypes) {
        this.roomTypes = roomTypes;
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

    public String getSupplierParty() {
        return supplierParty;
    }

    public void setSupplierParty(String supplierParty) {
        this.supplierParty = supplierParty;
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
