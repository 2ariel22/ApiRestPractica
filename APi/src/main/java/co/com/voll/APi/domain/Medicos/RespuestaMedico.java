package co.com.voll.APi.domain.Medicos;

import co.com.voll.APi.domain.direccion.Direccion;


public record RespuestaMedico(String Nombre,
        String Email,

        String Telefono,
        String Documento,

        Especialidad Especialida,

        Direccion direccion
) {
    public RespuestaMedico(Medico medico) {
        this(medico.getNombre(), medico.getEmail(),medico.getTelefono(),medico.getDocumento(),medico.getEspecialida(),medico.getDireccion());
    }
}
