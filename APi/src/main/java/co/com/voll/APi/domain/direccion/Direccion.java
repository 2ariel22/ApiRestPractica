package co.com.voll.APi.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String Calle;
    private String Distrito;
    private String Ciudad;
    private String Numero;
    private String Complemento;

    public Direccion(DatosDireccion direccion) {
        this.Calle = direccion.Calle();
        this.Distrito = direccion.Distrito();
        this.Ciudad = direccion.Ciudad();
        this.Numero = direccion.Numero();
        this.Complemento = direccion.Complemento();
    }

    public void ActualizarDatos(DatosDireccion direccion) {
        this.Calle = direccion.Calle();
        this.Distrito = direccion.Distrito();
        this.Ciudad = direccion.Ciudad();
        this.Numero = direccion.Numero();
        this.Complemento = direccion.Complemento();
    }
}
