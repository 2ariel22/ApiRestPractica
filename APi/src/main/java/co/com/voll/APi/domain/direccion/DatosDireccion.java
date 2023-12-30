package co.com.voll.APi.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(@NotBlank String Calle,
                             @NotBlank
                             String Distrito,
                             @NotBlank
                             String Ciudad,
                             @NotBlank
                             String Numero,
                             @NotBlank
                             String Complemento) {
}
