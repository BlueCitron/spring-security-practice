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
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String account;

    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberGrade> memberGrades = new ArrayList<>();

    public Member(String account, String password) {
        this.account = account;
        this.password = password;

    }

    public void addGrade(Grade grade) {
        this.memberGrades.add(new MemberGrade(this, grade));
    }

}
