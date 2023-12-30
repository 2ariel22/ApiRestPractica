package co.com.voll.APi.domain.Consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedico_IdAndFecha(Long aLong, LocalDateTime fecha);

}
