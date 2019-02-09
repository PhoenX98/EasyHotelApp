package pe.torganizagroup.easyhotelapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocalData {

    @SerializedName("current_page")
    @Expose
    private Integer current_page;

    @SerializedName("data")
    @Expose
//    private List<LocalItem> data = new ArrayList<LocalItem> ();
    private ArrayList<LocalItem> data;

    @SerializedName("first_page_url")
    @Expose
    private String first_page_url;

    @SerializedName("from")
    @Expose
    private Integer from;

    @SerializedName("last_page")
    @Expose
    private Integer last_page;

    @SerializedName("last_page_url")
    @Expose
    private String last_page_url;

    @SerializedName("next_page_url")
    @Expose
    private String next_page_url;

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("per_page")
    @Expose
    private Integer per_page;

    @SerializedName("prev_page_url")
    @Expose
    private Object prev_page_url;

    @SerializedName("to")
    @Expose
    private Integer to;

    @SerializedName("total")
    @Expose
    private Integer total;

    public LocalData(){

    }

    public LocalData(Integer current_page, ArrayList<LocalItem> data, String first_page_url, Integer from, Integer last_page, String last_page_url, String next_page_url, String path, Integer per_page, Object prev_page_url, Integer to, Integer total) {
        this.current_page = current_page;
        this.data = data;
        this.first_page_url = first_page_url;
        this.from = from;
        this.last_page = last_page;
        this.last_page_url = last_page_url;
        this.next_page_url = next_page_url;
        this.path = path;
        this.per_page = per_page;
        this.prev_page_url = prev_page_url;
        this.to = to;
        this.total = total;
    }

    public Integer getCurrentPage() {
        return current_page;
    }

    public void setCurrentPage(Integer current_page) {
        this.current_page = current_page;
    }

    public ArrayList<LocalItem> getData() {
        return data;
    }

    public void setData(ArrayList<LocalItem> data) {
        this.data = data;
    }

    public String getFirstPageUrl() {
        return first_page_url;
    }

    public void setFirstPageUrl(String first_page_url) {
        this.first_page_url = first_page_url;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getLastPage() {
        return last_page;
    }

    public void setLastPage(Integer lastPage) {
        this.last_page = last_page;
    }

    public String getLastPageUrl() {
        return last_page_url;
    }

    public void setLastPageUrl(String last_page_url) {
        this.last_page_url = last_page_url;
    }

    public String getNextPageUrl() {
        return next_page_url;
    }

    public void setNextPageUrl(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPerPage() {
        return per_page;
    }

    public void setPerPage(Integer per_page) {
        this.per_page = per_page;
    }

    public Object getPrevPageUrl() {
        return prev_page_url;
    }

    public void setPrevPageUrl(Object prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
