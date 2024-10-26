package br.com.nayaranascimento.medicalconsult.consult.services;

import br.com.nayaranascimento.medicalconsult.consult.domain.Consult;
import br.com.nayaranascimento.medicalconsult.consult.repositories.ConsultRepository;
import br.com.nayaranascimento.medicalconsult.exception.ExceptionDataIntegrityViolation;
import br.com.nayaranascimento.medicalconsult.user.domain.User;
import com.sun.jdi.ObjectCollectedException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultService {

    @Autowired
    private ConsultRepository consultRepository;

    public Consult cadastrarConsult(Consult consult){
        consult.setIdConsult(null);
        return consultRepository.save(consult);
    }

    public List<Consult> listarConsults(){
        return consultRepository.findAll();
    }

    public Consult buscarConsult(Long id){
        Optional<Consult> consult = consultRepository.findById(id);
        return consult.orElseThrow(
                () -> new ObjectNotFoundException("Especialidade não encontrada! ID: ", id));
    }
    public void deletarConsult(Long id){
        buscarConsult(id);
        try {
            consultRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionDataIntegrityViolation("Não é possivel excluir, porque há entidades relacionadas.");
        }
    }
    public Consult atualizarConsult(Consult consult){
        Consult novaConsult = buscarConsult(consult.getIdConsult());
        updateData(novaConsult, consult);
        return consultRepository.save(novaConsult);
    }
    private void updateData(Consult novaConsult, Consult consult){
        novaConsult.setDataConsult(consult.getDataConsult());
        novaConsult.setProfissional(consult.getProfissional());
        novaConsult.setEspecialidade(consult.getEspecialidade());
    }
}
