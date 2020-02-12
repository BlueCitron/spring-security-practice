package me.bluecitron.security.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MemberGrade {

    @Id
    @GeneratedValue
    @Column(name = "member_grade_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public MemberGrade(Member member, Grade grade) {
        this.member = member;
        this.grade = grade;
        member.getMemberGrades().add(this);
        grade.getMemberGrades().add(this);
    }
}
