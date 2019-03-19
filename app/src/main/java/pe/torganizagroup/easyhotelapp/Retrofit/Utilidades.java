package pe.torganizagroup.easyhotelapp.Retrofit;

public class Utilidades {

    public static final String COORDENADA_URL = "https://t-organizagroup.com/ws_easyhotel/public/api/";
    public static final String NEW_TEST_URL = "http://104.130.222.221:1337/";

    public static HotelLista getService() {
        return retrofitCliente.getCliente (NEW_TEST_URL).create (HotelLista.class);
    }

}