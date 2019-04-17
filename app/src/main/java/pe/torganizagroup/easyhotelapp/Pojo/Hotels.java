package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotels {

    @SerializedName("asignedPortfolio")
    @Expose
    private String asignedPortfolio;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("coor")
    @Expose
    private String coor;
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
    private String qualification;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("ubications")
    @Expose
    private Ubications ubications;

    public Hotels(){

    }

    public Hotels(String asignedPortfolio, String userId, Integer status, String coor, String id,
                  String nameHotel, String address, String addressReference, String department,
                  String province, String district, String category, String qualification, String length,
                  String latitude, Ubications ubications) {
        this.asignedPortfolio = asignedPortfolio;
        this.userId = userId;
        this.status = status;
        this.coor = coor;
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
    }

    public String getAsignedPortfolio() {
        return asignedPortfolio;
    }

    public void setAsignedPortfolio(String asignedPortfolio) {
        this.asignedPortfolio = asignedPortfolio;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCoor() {
        return coor;
    }

    public void setCoor(String coor) {
        this.coor = coor;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
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

}
