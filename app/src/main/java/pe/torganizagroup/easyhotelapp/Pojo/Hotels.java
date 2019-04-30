package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotels {

    @SerializedName("asignedPortfolio")
    @Expose
    private String asignedPortfolio;
    @SerializedName("coor")
    @Expose
    private String coor;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nameHotel")
    @Expose
    private String nameHotel;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("addressReference")
    @Expose
    private String addressReference;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("qualification")
    @Expose
    private Integer qualification;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("ubications")
    @Expose
    private Ubications ubications;
    @SerializedName("minimalRate")
    @Expose
    private String minimalRate;
    @SerializedName("maximumRate")
    @Expose
    private String maximumRate;
//    @SerializedName("associatedType")
//    @Expose
//    private Integer associatedType;
    @SerializedName("photos")
    @Expose
    private List<String> photos = null;

    public Hotels(){

    }

    public Hotels(String asignedPortfolio, String coor, Integer status, String userId, String id, String nameHotel, String address, String addressReference, String department, String province, String district, String category, Integer qualification, String length, String latitude, Ubications ubications, String minimalRate, String maximumRate, Integer associatedType, List<String> photos) {
        this.asignedPortfolio = asignedPortfolio;
        this.coor = coor;
        this.status = status;
        this.userId = userId;
        this.id = id;
        this.nameHotel = nameHotel;
        this.address = address;
        this.addressReference = addressReference;
        this.department = department;
        this.province = province;
        this.district = district;
        this.category = category;
        this.qualification = qualification;
        this.length = length;
        this.latitude = latitude;
        this.ubications = ubications;
        this.minimalRate = minimalRate;
        this.maximumRate = maximumRate;
//        this.associatedType = associatedType;
        this.photos = photos;
    }

    public String getAsignedPortfolio() {
        return asignedPortfolio;
    }

    public void setAsignedPortfolio(String asignedPortfolio) {
        this.asignedPortfolio = asignedPortfolio;
    }

    public String getCoor() {
        return coor;
    }

    public void setCoor(String coor) {
        this.coor = coor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameHotel() {
        return nameHotel;
    }

    public void setNameHotel(String nameHotel) {
        this.nameHotel = nameHotel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressReference() {
        return addressReference;
    }

    public void setAddressReference(String addressReference) {
        this.addressReference = addressReference;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Ubications getUbications() {
        return ubications;
    }

    public void setUbications(Ubications ubications) {
        this.ubications = ubications;
    }

    public String getMinimalRate() {
        return minimalRate;
    }

    public void setMinimalRate(String minimalRate) {
        this.minimalRate = minimalRate;
    }

    public String getMaximumRate() {
        return maximumRate;
    }

    public void setMaximumRate(String maximumRate) {
        this.maximumRate = maximumRate;
    }

//    public Integer getAssociatedType() {
//        return associatedType;
//    }
//
//    public void setAssociatedType(Integer associatedType) {
//        this.associatedType = associatedType;
//    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

}
