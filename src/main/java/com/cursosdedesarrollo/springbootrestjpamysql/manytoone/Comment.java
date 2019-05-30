package com.cursosdedesarrollo.springbootrestjpamysql.manytoone;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    // Getters and Setters (Omitted for brevity)
}
