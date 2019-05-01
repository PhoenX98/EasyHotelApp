package pe.torganizagroup.easyhotelapp.Interfaces;

import java.util.List;

public interface FragmentComunicator {
    void respond(int position, String name, String address, String price, List<String> photos);
}
