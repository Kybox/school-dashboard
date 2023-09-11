package fr.kybox.school.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("person")
@Data
@NoArgsConstructor
public class Person {

    @Id
    private Long id;
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column("role_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long roleId;

    private String avatar;

    @Transient
    private Role role;

    @Column("subject_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long subjectId;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Subject subject;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Course> courseSubList;

    public Person(String name, String password, Long roleId, String avatar){
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.avatar = avatar;
    }

    public Person(String name, String password, Long roleId, Long subjectId, String avatar){
        this.name = name;
        this.password = password;
        this.roleId = roleId;
        this.subjectId = subjectId;
        this.avatar = avatar;
    }
}
