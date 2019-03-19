package pe.torganizagroup.easyhotelapp.Pojo;

public class Provincia {

    private String id;
    private String name;
    private String departamento;

    public Provincia(String id, String name, String departamento){
        this.id = id;
        this.name = name;
        this.departamento = departamento;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Provincia){
            Provincia p = (Provincia )obj;
            if(p.getName().equals(name) && p.getId()==id && p.getDepartamento ()==departamento) return true;
        }

        return false;
    }
}
