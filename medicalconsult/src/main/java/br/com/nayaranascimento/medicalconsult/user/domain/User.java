package br.com.nayaranascimento.medicalconsult.user.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long idUser;

    @Column(name = "USER_NAME")
    private String nameUser;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CPF")
    private  String cpf;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    public User() {
    }

    public User(Long idUser, String nomeUser, String email, String cpf, String telefone, Date dataNascimento) {
        this.idUser = idUser;
        this.nameUser = nomeUser;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
}
