package co.com.voll.APi.domain.Medicos;

import co.com.voll.APi.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record DatosRegistroMedicos(@NotBlank String Nombre,
                                   @NotBlank
                                   @Email
                                   String Email,
                                   @NotBlank
                                   String Telefono,
                                   @NotBlank
                                   @Pattern(regexp = "\\d{4,6}")// esto para que solo lleguen numeros de 4 a 6 digitos
                                   String Documento,
                                   @NotNull
                                   Especialidad Especialida,
                                   @NotNull
                                   @Valid
                                   DatosDireccion Direccion) {

}
