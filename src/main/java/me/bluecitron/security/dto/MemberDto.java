package me.bluecitron.security.dto;

import lombok.Builder;
import lombok.Data;
import me.bluecitron.security.domain.Member;

@Data
public class MemberDto {
    private Long id;
    private String account;
    private String password;
    private String grade;

    public Member toEntity() {
        return new Member(account, password);
    }

    @Builder
    public MemberDto(Long id, String account, String password, String grade) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.grade = grade;
    }
}
