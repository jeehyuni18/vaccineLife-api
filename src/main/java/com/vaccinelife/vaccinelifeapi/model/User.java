package com.vaccinelife.vaccinelifeapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Boolean isVaccine;


    @Column(nullable = true)
    private String type;

    @Column(nullable = true)
    private Integer degree;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private Boolean disease;

    @Column(nullable = true)
    private String afterEffect;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"comment"})
    private List<Comment> comment = new ArrayList<>();
    public void add(Comment comment) {
        comment.setUser(this);
        this.comment.add(comment);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private Set<VacBoard> vacBoard = new HashSet<>();
    public void add(VacBoard vacBoard){
        vacBoard.setUser(this);
        this.vacBoard.add(vacBoard);
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;


    public User(String username, String password, UserRole role, String nickname, Boolean isVaccine, String type,Integer degree, String gender, Integer age, Boolean disease, String afterEffect) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.isVaccine=isVaccine;
        this.type=type;
        this.degree=degree;
        this.gender=gender;
        this.age= age;
        this.disease= disease;
        this.afterEffect=afterEffect;

    }



}