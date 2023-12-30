package co.com.voll.APi.infra.Errores;

public class ValidacionIntegridad extends RuntimeException {
    public ValidacionIntegridad(String s) {
        super(s);
    }
}
