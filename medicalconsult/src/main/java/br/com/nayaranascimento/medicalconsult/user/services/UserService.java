package br.com.nayaranascimento.medicalconsult.user.services;

import br.com.nayaranascimento.medicalconsult.user.domain.User;
import br.com.nayaranascimento.medicalconsult.user.repository.UserRepository;
import com.sun.jdi.ObjectCollectedException;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User cadastrarUser(User user){
        return userRepository.save(user);
    }

    public List<User> listarUsers(){
        return userRepository.findAll();
    }

    public User buscarUser(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ObjectCollectedException("Usuário não Encontrado!"));
    }

    public User atualizarUser(Long id, User userAtualizado){
        User userExistente = userRepository.findById(id).orElseThrow(() -> new ObjectCollectedException("Usuário não Encontrado"));
        userExistente.setNameUser(userAtualizado.getNameUser());
        userExistente.setEmail(userAtualizado.getEmail());
        return userRepository.save(userExistente);
    }
    public void deletarUser(Long id){
        User user = userRepository.findById(id).orElseThrow( () -> new ObjectCollectedException("Usuário não encontrado!"));
        userRepository.delete(user);
    }
}
