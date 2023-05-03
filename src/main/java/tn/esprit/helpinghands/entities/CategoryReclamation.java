package tn.esprit.helpinghands.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class    CategoryReclamation {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @JsonIgnore
    @ManyToMany(mappedBy = "categoryReclamations")
    private Set<Reclamation> reclamations;
}
