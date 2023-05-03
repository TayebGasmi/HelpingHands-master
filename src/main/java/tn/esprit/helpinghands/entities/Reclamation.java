package tn.esprit.helpinghands.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OneToOne(mappedBy = "reclamation", cascade = CascadeType.ALL)
    private Attachment attachment;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<CategoryReclamation> categoryReclamations;

}
