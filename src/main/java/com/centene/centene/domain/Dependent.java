package com.centene.centene.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "dependent")
@SQLDelete(sql = "UPDATE enrollee SET is_deleted = true where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "is_deleted= false")
public class Dependent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "dob cannot be empty")
    @JsonFormat(pattern = "MM/dd/YYYY")
    private Date dob;

    @ManyToOne
    @JoinColumn(name = "enrolleeId")
    @JsonBackReference
    private Enrollee enrollee;

    boolean is_deleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    private Timestamp updatedDate;

}
