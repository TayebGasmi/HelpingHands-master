package tn.esprit.helpinghands.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt=new Date();
    @NotBlank
    private String description;
    @Enumerated(EnumType.STRING)
    ReclamationStatus status;
    @ManyToOne
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL)
    private Set<Attachment> attachments;
    @ManyToMany
    private Set<CategoryReclamation> categoryReclamations;

}
