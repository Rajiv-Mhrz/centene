package com.centene.centene.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "enrollee")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE enrollee SET is_deleted = true where enrollee_Id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "is_deleted= false")
public class Enrollee extends RepresentationModel<Enrollee> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enrolleeId;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    private boolean status;

    @NotNull(message = "dob cannot be empty")
    @JsonFormat(pattern = "MM/dd/YYYY")
    private Date dob;

    @NotEmpty(message = "phone cannot be empty")
    private String phone;

    @OneToMany(mappedBy = "enrollee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Dependent> dependents = new HashSet<>();

    boolean is_deleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    private Timestamp updatedDate;
}
