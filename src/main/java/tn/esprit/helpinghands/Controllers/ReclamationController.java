package tn.esprit.helpinghands.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.entities.Reclamation;
import tn.esprit.helpinghands.entities.User;
import tn.esprit.helpinghands.serviceImpl.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/reclamation")
@Slf4j

public class ReclamationController {
    @Autowired
    ReclamationService reclamationService;
    @Autowired
    IAttachment attachmentImpl;
    @Autowired
    IUserService userService;
    @PostMapping(path = "/{id}",consumes = {"multipart/form-data"})
    Reclamation addReclamation(@RequestParam(required = false) MultipartFile[] files,Reclamation reclamation,@PathVariable Long id){
        User user=userService.getUser(id);
        reclamation.setUser(user);
        Reclamation savedReclamation=reclamationService.addReclamation(reclamation);
        Arrays.stream(files).forEach(file -> attachmentImpl.addAttachmentToReclamation(file, savedReclamation));
        return savedReclamation;
    }
    @PutMapping
    Reclamation updateReclamation(@RequestParam(required = false) MultipartFile[] files,Reclamation reclamation)
    {
        Arrays.stream(files).forEach(file -> attachmentImpl.addAttachmentToReclamation(file, reclamation));
        return reclamationService.updateReclamation(reclamation);
    }

    @GetMapping
    List<Reclamation> findAllReclamation(){
        return reclamationService.findAllReclamation();
    }
    @GetMapping("/{id}")
    Reclamation findReclamation(@PathVariable Long id){
        return reclamationService.findReclamation(id);
    }
    @DeleteMapping("/{id}")
    void deleteReclamation(@PathVariable Long id){
        reclamationService.deleteReclamation(id);
    }

}
