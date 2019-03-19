package pe.torganizagroup.easyhotelapp.Pojo;

public class Distrito {

    private String id;
    private String name;
    private String distrito;

    public Distrito(String id, String name, String distrito) {
        this.id = id;
        this.name = name;
        this.distrito = distrito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Distrito){
            Distrito p = (Distrito )obj;
            if(p.getName().equals(name) && p.getId()==id && p.getDistrito ()==distrito) return true;
        }

        return false;
    }
}
