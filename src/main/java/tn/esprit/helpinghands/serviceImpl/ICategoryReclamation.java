package tn.esprit.helpinghands.serviceImpl;

import tn.esprit.helpinghands.entities.CategoryReclamation;

import java.util.List;

public interface ICategoryReclamation {
    CategoryReclamation addCategoryReclamation(CategoryReclamation categoryReclamation);
    CategoryReclamation updateCategoryReclamation(CategoryReclamation categoryReclamation);
    void deleteCategoryReclamation(Long id);
    CategoryReclamation findCategoryReclamation(Long id);
    List<CategoryReclamation> findAllCategoryReclamation();
}
