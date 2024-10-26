package br.com.nayaranascimento.medicalconsult.user.services;

import br.com.nayaranascimento.medicalconsult.user.domain.User;
import br.com.nayaranascimento.medicalconsult.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void cadastrarUser(){
        User user = new User();
        user.setNameUser("Nayara");
        //configuração de componente em Mock
        when(userRepository.save(any(User.class))).thenReturn(user);

        //a execução do metodo a ser testado
        var resulto = userService.cadastrarUser(user);

        //validação do teste
        assertNotNull(user);
        assertEquals("Nayara",resulto.getNameUser());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void listarUser(){
        //criando o objeto users
        User user1 = new User();
        user1.setNameUser("Nayara");

        User user2 = new User();
        user2.setNameUser("Edilson");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        //configuração de componente em Mock
        when(userRepository.findAll()).thenReturn(users);

        //a execução do metodo a ser testado
        var resultado = userService.listarUsers();

        //validação do teste
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2,resultado.size()),
                () -> assertEquals("Nayara", resultado.get(0).getNameUser()),
                () -> assertEquals("Edilson", resultado.get(1).getNameUser())
        );
    }

    @Test
    void buscarUser() {
        //criando o objeto users
        User user1 = new User();
        user1.setNameUser("Nayara");
        User user2 = new User();
        user2.setNameUser("Edilson");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        //configuração de componente em Mock
        when(userRepository.findAll()).thenReturn(users);

        //a execução do metodo a ser testado
        var resultado = userService.listarUsers();

        //validação do teste
        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Nayara", resultado.get(0).getNameUser()),
                () -> assertEquals("Edilson", resultado.get(1).getNameUser())
        );
    }

    @Test
    void atualizarUser() {
        // Dados do usuário existente e do usuário atualizado
        User userExistente = new User();
        userExistente.setNameUser("Nayara");
        userExistente.setEmail("nayara@gmail.com");

        User userAtualizado = new User();
        userAtualizado.setNameUser("Edilson");
        userAtualizado.setEmail("edilson@gmail.com");

        // Configuração do mock
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(userExistente));
        when(userRepository.save(any(User.class))).thenReturn(userExistente);

        // Execução do método
        var resultado = userService.atualizarUser(1L, userAtualizado);

        // Validação do teste
        assertNotNull(resultado);
        assertEquals("Edilson", resultado.getNameUser());
        assertEquals("edilson@gmail.com", resultado.getEmail());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(userExistente);
    }

    @Test
    void deletarUser() {
        // Dados do usuário a ser deletado
        User user = new User();
        user.setNameUser("Edilson");

        // Configuração do mock
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Execução do método
        userService.deletarUser(1L);

        // Validação do teste
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }
}
