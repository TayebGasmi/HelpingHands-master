package tn.esprit.helpinghands.serviceImpl;

import tn.esprit.helpinghands.entities.Reclamation;

import java.util.List;

public interface IReclamation  {

    Reclamation findReclamation(Long id);
    Reclamation addReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation);
    void deleteReclamation(Long id);
    List<Reclamation> findAllReclamation();
}
