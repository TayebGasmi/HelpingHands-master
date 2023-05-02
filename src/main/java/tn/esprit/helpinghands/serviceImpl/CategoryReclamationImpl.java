package tn.esprit.helpinghands.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.helpinghands.entities.CategoryReclamation;
import tn.esprit.helpinghands.repositories.CategoryReclamationRepository;

import java.util.List;

@Service
public class CategoryReclamationImpl implements ICategoryReclamation {
    @Autowired
    private CategoryReclamationRepository categoryReclamationRepository;
    @Override
    public CategoryReclamation addCategoryReclamation(CategoryReclamation categoryReclamation) {
        return categoryReclamationRepository.save(categoryReclamation);
    }

    @Override
    public CategoryReclamation updateCategoryReclamation(CategoryReclamation categoryReclamation) {
        return categoryReclamationRepository.save(categoryReclamation);
    }

    @Override
    public void deleteCategoryReclamation(Long id) {
        categoryReclamationRepository.deleteById(id);
    }

    @Override
    public CategoryReclamation findCategoryReclamation(Long id) {
        return categoryReclamationRepository.findById(id).orElse(null);
    }

    @Override
    public List<CategoryReclamation> findAllCategoryReclamation() {
        return categoryReclamationRepository.findAll();
    }
}
