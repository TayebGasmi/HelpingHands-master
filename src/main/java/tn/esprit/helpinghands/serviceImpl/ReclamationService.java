package tn.esprit.helpinghands.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.helpinghands.entities.CategoryReclamation;
import tn.esprit.helpinghands.entities.Reclamation;
import tn.esprit.helpinghands.entities.ReclamationStatus;
import tn.esprit.helpinghands.repositories.ReclamationRepository;

import java.util.List;

@Service
@Slf4j
public class ReclamationService  implements IReclamation {
@Autowired
private ReclamationRepository reclamationRepository;
    @Autowired
    private CategoryReclamationImpl categoryReclamationService;

    @Override
    public Reclamation findReclamation(Long id) {
        return reclamationRepository.findById(id).orElse(null);
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        reclamation.setStatus(ReclamationStatus.PENDING);
        log.info("Reclamation created {}",reclamation.getId());
        log.info("Reclamation created {}",reclamation.getTitle());
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        log.info("*****************************Reclamation updated {}",reclamation.getId());
        log.info("*****************************Reclamation updated {}",reclamation.getTitle());
        reclamation.setStatus(ReclamationStatus.PENDING);
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
    public Reclamation acceptReclamation(Long id)
    {
        Reclamation reclamation=reclamationRepository.findById(id).orElse(null);
        reclamation.setStatus(ReclamationStatus.ACCEPTED);
        return reclamationRepository.save(reclamation);
    }
    public Reclamation rejectReclamation(Long id)
    {
        Reclamation reclamation=reclamationRepository.findById(id).orElse(null);
        reclamation.setStatus(ReclamationStatus.REJECTED);
        return reclamationRepository.save(reclamation);
    }

    public Reclamation assigneReclamation(Long idReclamation, Long idCategory) {
        Reclamation reclamation=reclamationRepository.findById(idReclamation).orElse(null);
        CategoryReclamation categoryReclamation=categoryReclamationService.findCategoryReclamation(idCategory);
        reclamation.getCategoryReclamations().add(categoryReclamation);
        return reclamationRepository.save(reclamation);
    }
}
