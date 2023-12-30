package co.com.voll.APi.domain.Medicos;

public record DatosListadoMedico(long Id,String nombre, String especialidad, String documento, String email) {

    public DatosListadoMedico(Medico medico) {
        this(medico.getId(),medico.getNombre(),medico.getEspecialida().toString(),medico.getDocumento(),
        medico.getEmail());
    }
}
