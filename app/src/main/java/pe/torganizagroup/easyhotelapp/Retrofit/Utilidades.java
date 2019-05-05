package pe.torganizagroup.easyhotelapp.Retrofit;

public class Utilidades {

//    public static final String COORDENADA_URL = "https://t-organizagroup.com/ws_easyhotel/public/api/";
    public static final String NEW_TEST_URL = "http://api.easyhotel.pe:1337/";
//    public static final String NEW_TEST_URL = "http://104.130.217.167:1337/";

    public static HotelLista getService() {
        return retrofitCliente.getCliente (NEW_TEST_URL).create (HotelLista.class);
    }

    public static HotelDetalle getData(){
        return detalleCliente.getDetalle (NEW_TEST_URL).create (HotelDetalle.class);
    }
}