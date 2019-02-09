package pe.torganizagroup.easyhotelapp.Retrofit;

public class Utilidades {

    public static final String BASE_URL = "http://192.168.1.197/api/";
    public static final String COORDENADA_URL = "https://t-organizagroup.com/ws_easyhotel/public/api/";
    public static final String LISTA_URL = "https://t-organizagroup.com/ws_easyhotel/public/api/";

    public static LocalService getService() {

        return retrofitCliente.getCliente (LISTA_URL).create (LocalService.class);
    }


}