package fr.kybox.school.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    private Long id;
    private String label;

    public Role(String label){
        this.label = label;
    }
}
