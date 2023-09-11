package fr.kybox.school.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("subscription")
public class Subscription {

    @Id
    private Long id;

    @Column("course_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long courseId;

    @Column("person_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long personId;

    @Transient
    private Course course;

    @Transient
    private Person person;

    public Subscription(Long courseId, Long personId){
        this.courseId = courseId;
        this.personId = personId;
    }
}
