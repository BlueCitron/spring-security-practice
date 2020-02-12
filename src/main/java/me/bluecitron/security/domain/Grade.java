package me.bluecitron.security.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Grade {

    @Id
    @GeneratedValue
    @Column(name = "grade_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "grade")
    private List<MemberGrade> memberGrades = new ArrayList<>();

    public Grade(String name) {
        this.name = name;
    }
}
