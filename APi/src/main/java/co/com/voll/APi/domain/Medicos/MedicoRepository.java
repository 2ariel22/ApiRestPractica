package co.com.voll.APi.domain.Medicos;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
    select m from Medico m
    where m.activo = true and m.Especialida = :especialidad 
    and m.Id not in (select c.medico.Id from Consulta c where c.fecha = :fecha)
    order by rand() 
    limit 1
""")
    Medico medicoRandomPorEspecialidad(@Param("especialidad") Especialidad especialidad, @Param("fecha") @NotNull LocalDateTime fecha);


    @Query(
        """
        SELECT m.activo from Medico m where m.Id =:id
        """
    )
    boolean findActivoById(@Param("id") Long id);
}
