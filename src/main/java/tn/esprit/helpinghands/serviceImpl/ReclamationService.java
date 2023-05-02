package tn.esprit.helpinghands.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.helpinghands.entities.Reclamation;
import tn.esprit.helpinghands.entities.ReclamationStatus;
import tn.esprit.helpinghands.repositories.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationService  implements IReclamation {
@Autowired
private ReclamationRepository reclamationRepository;

    @Override
    public Reclamation findReclamation(Long id) {
        return reclamationRepository.findById(id).orElse(null);
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        reclamation.setStatus(ReclamationStatus.PENDING);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);
    }

    @Override
    public List<Reclamation> findAllReclamation() {
        return reclamationRepository.findAll();
    }
}
