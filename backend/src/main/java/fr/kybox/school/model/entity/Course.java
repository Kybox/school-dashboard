package fr.kybox.school.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("course")
@Data
@NoArgsConstructor
public class Course {

    @Id
    private Long id;
    private String title;
    private String description;

    public Course(String title, String description, Long teacherId, Long subjectId){
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column("teacher_id")
    private Long teacherId;

    @Transient
    private Person teacher;

    @Transient
    private Subject subject;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column("subject_id")
    private Long subjectId;
}
