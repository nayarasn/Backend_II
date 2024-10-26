package br.com.nayaranascimento.medicalconsult.consult.resources;

import br.com.nayaranascimento.medicalconsult.consult.domain.Consult;
import br.com.nayaranascimento.medicalconsult.consult.services.ConsultService;
import br.com.nayaranascimento.medicalconsult.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/consults")
public class ConsultResource {

    @Autowired
    private ConsultService consultService;

    @PostMapping
    public ResponseEntity<Consult> cadastrarConsult(@RequestBody Consult consult){
        Consult novaConsult = consultService.cadastrarConsult(consult);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(novaConsult.getIdConsult()).toUri();
        return ResponseEntity.created(uri).body(novaConsult);
    }

    @GetMapping
    public ResponseEntity<List<Consult>> listarConsults(){
        List<Consult> consult = consultService.listarConsults();
        return ResponseEntity.ok().body(consult);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consult> buscarConsult(@PathVariable Long id){
        Consult consult = consultService.buscarConsult(id);
        return ResponseEntity.ok().body(consult);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Consult> atualizarConsult(@RequestBody Consult consult, @PathVariable Long id){
        consult.setIdConsult(id);
        Consult consultAtualizada = consultService.atualizarConsult(consult);
        return ResponseEntity.ok().body(consultAtualizada);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarConsult(@PathVariable Long id){
        consultService.deletarConsult(id);
        return ResponseEntity.noContent().build();
    }
}
