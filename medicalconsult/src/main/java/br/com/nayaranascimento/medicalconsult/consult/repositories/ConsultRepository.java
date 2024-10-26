package br.com.nayaranascimento.medicalconsult.consult.repositories;

import br.com.nayaranascimento.medicalconsult.consult.domain.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
}
