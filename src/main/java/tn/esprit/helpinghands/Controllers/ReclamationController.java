package tn.esprit.helpinghands.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.helpinghands.entities.Attachment;
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
    @PostMapping(path = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Reclamation addReclamation(@RequestParam(required = false) MultipartFile file,@ModelAttribute Reclamation reclamation,@PathVariable Long id){
        User user=userService.getUser(id);
        reclamation.setUser(user);
        reclamation.setId(0l);

        if(file!=null)
        {
            Attachment attachment= attachmentImpl.addAttachmentToReclamation(file);
            reclamation.setAttachment(attachment);
        }

        return reclamationService.addReclamation(reclamation);
    }
    @PutMapping
    Reclamation updateReclamation(@RequestParam(required = false) MultipartFile file,@ModelAttribute Reclamation reclamation)
    {
        if(file!=null)
        {
            Attachment attachment= attachmentImpl.addAttachmentToReclamation(file);
            reclamation.setAttachment(attachment);
        }
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
    @PutMapping("/accept/{id}")
    Reclamation acceptReclamation(@PathVariable Long id)
    {
        return reclamationService.acceptReclamation(id);
    }
    @PutMapping("/reject/{id}")
    Reclamation rejectReclamation(@PathVariable Long id)
    {
        return reclamationService.rejectReclamation(id);
    }
    @PutMapping("/assign/{idReclamation}/{idCategory}")
    Reclamation assigneReclamation(@PathVariable Long idReclamation,@PathVariable Long idCategory)
    {
        return reclamationService.assigneReclamation(idReclamation,idCategory);
    }
}
