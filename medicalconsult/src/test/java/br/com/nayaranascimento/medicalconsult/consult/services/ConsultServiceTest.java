package br.com.nayaranascimento.medicalconsult.consult.services;

import br.com.nayaranascimento.medicalconsult.consult.domain.Consult;
import br.com.nayaranascimento.medicalconsult.consult.repositories.ConsultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultServiceTest {

    @InjectMocks
    private ConsultService consultService;

    @Mock
    private ConsultRepository consultRepository;

    @Test
    void cadastrarConsult() {
        Consult consult = new Consult();
        consult.setEspecialidade("Endocrinologia");
        //configuração de componente em Mock
        when(consultRepository.save(any(Consult.class))).thenReturn(consult);

        //a execução do metodo a ser testado
        var resultado = consultService.cadastrarConsult(consult);

        //validação do teste
        assertNotNull(resultado);
        assertEquals("Endocrinologia", resultado.getEspecialidade());

        verify(consultRepository, times(1)).save(consult);
    }

    @Test
    void listarConsults() {
        //criando o objeto consulta
        Consult consult1 = new Consult();
        consult1.setEspecialidade("Endocrinologia");

        Consult consult2 = new Consult();
        consult2.setEspecialidade("Alergologia");

        List<Consult> consults = new ArrayList<>();
        consults.add(consult1);
        consults.add(consult2);

        //configuração de componente em Mock
        when(consultRepository.findAll()).thenReturn(consults);

        var resultado = consultService.listarConsults();

        //validação do teste
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Endocrinologia", resultado.get(0).getEspecialidade()),
                () -> assertEquals("Alergologia", resultado.get(1).getEspecialidade())
        );
    }

    @Test
    void buscarConsult() {
        //criando o objeto consulta
        Consult consult = new Consult();
        consult.setIdConsult(1L);
        consult.setEspecialidade("Alergologia");

        //configuração de componente em Mock
        when(consultRepository.findById(1L)).thenReturn(Optional.of(consult));

        //a execução do metodo a ser testado
        var resultado = consultService.buscarConsult(1L);

        //validação do teste
        assertNotNull(resultado);
        assertEquals("Alergologia", resultado.getEspecialidade());

        verify(consultRepository, times(1)).findById(1L);
    }

    @Test
    void atualizarConsult() {
        // Dados do consulta existente e do consulta atualizado
        Consult consultExistente = new Consult();
        consultExistente.setIdConsult(1L);
        consultExistente.setEspecialidade("Endocrinologia");

        Consult consultAtualizada = new Consult();
        consultAtualizada.setIdConsult(1L);
        consultAtualizada.setEspecialidade("Dermatologia");

        // Configuração do mock
        when(consultRepository.findById(1L)).thenReturn(Optional.of(consultExistente));
        when(consultRepository.save(any(Consult.class))).thenReturn(consultAtualizada);

        // Execução do método
        var resultado = consultService.atualizarConsult(consultAtualizada);

        //validação do teste
        assertNotNull(resultado);
        assertEquals("Dermatologia", resultado.getEspecialidade());

        verify(consultRepository, times(1)).findById(1L);
        verify(consultRepository, times(1)).save(consultAtualizada);
    }

    @Test
    void deletarConsult() {
        // Dados da consulta a ser deletado
        Consult consult = new Consult();
        consult.setEspecialidade("Alergologia");

        // Configuração do mock
        when(consultRepository.findById(1L)).thenReturn(Optional.of(consult));

        // Execução do método
        consultService.deletarConsult(1L);

        // Validação do teste
        verify(consultRepository, times(1)).findById(1L);
        verify(consultRepository, times(1)).deleteById(1L);
    }
}