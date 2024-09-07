package br.com.nayaranascimento.medicalconsult.user.resource;

import br.com.nayaranascimento.medicalconsult.user.domain.User;
import br.com.nayaranascimento.medicalconsult.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> cadastrarUser(@RequestBody User user){
        User novoUser = userService.cadastrarUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> listarUsers(){
        List<User> user = userService.listarUsers();
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> atualizarUser(@PathVariable Long id, @RequestBody User userAtualizado){
        User user = userService.atualizarUser(id, userAtualizado);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id){
        userService.deletarUser(id);
        return ResponseEntity.noContent().build();
    }

}
