package co.com.voll.APi.domain.Medicos;

import co.com.voll.APi.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarMedico(@NotNull long Id,
                                    String Nombre,
                                    String Documento,

                                    DatosDireccion Direccion


                                    ) {

}
