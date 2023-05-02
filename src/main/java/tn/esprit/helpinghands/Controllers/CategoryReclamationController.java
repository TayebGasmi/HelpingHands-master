package tn.esprit.helpinghands.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.helpinghands.entities.CategoryReclamation;
import tn.esprit.helpinghands.serviceImpl.ICategoryReclamation;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/",maxAge = 3600)
@RestController
@RequestMapping("/categoryReclamation")

public class CategoryReclamationController {
    @Autowired
    private ICategoryReclamation categoryReclamationService;
    @PostMapping
    CategoryReclamation addCategoryReclamation(@RequestBody CategoryReclamation categoryReclamation){
        return categoryReclamationService.addCategoryReclamation(categoryReclamation);
    }
    @PutMapping
    CategoryReclamation updateCategoryReclamation(@RequestBody CategoryReclamation categoryReclamation){
        return categoryReclamationService.updateCategoryReclamation(categoryReclamation);
    }
    @DeleteMapping("/{id}")
    void deleteCategoryReclamation(@PathVariable Long id){
        categoryReclamationService.deleteCategoryReclamation(id);
    }
    @GetMapping("/{id}")
    CategoryReclamation findCategoryReclamation(@PathVariable Long id){
        return categoryReclamationService.findCategoryReclamation(id);
    }
    @GetMapping
    List<CategoryReclamation> findAllCategoryReclamation(){
        return categoryReclamationService.findAllCategoryReclamation();
    }

}
