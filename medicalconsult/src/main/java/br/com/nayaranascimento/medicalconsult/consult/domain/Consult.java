package br.com.nayaranascimento.medicalconsult.consult.domain;

import br.com.nayaranascimento.medicalconsult.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CONSULTAS")
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSULTA")
    private Long idConsult;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_CONSULTA")
    private Date dataConsult;

    @Column(name = "ESPECIALIDADE")
    private String especialidade;

    @Column(name = "PROFISSIONAL")
    private String profissional;

    @ManyToOne
    @JoinColumn(name = "id_User")
    private User user;

    public Consult(long idConsult, Date dataConsult, String especialidade, String profissional, User user) {
        this.idConsult = idConsult;
        this.dataConsult = dataConsult;
        this.especialidade = especialidade;
        this.profissional = profissional;
        this.user = user;
    }
    public Consult() {

    }
}

