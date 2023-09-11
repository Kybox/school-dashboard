package fr.kybox.school.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    private Long id;
    private String label;
}
