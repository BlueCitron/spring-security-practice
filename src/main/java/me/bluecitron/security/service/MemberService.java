package me.bluecitron.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.bluecitron.security.domain.Grade;
import me.bluecitron.security.domain.Member;
import me.bluecitron.security.dto.MemberDto;
import me.bluecitron.security.repository.GradeRepository;
import me.bluecitron.security.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final GradeRepository gradeRepository;

    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = memberDto.toEntity();
        Grade basic = gradeRepository.findByName("ROLE_MEMBER");
        member.addGrade(basic);
        return memberRepository.save(member).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = memberRepository.findByAccount(account);

        if (memberWrapper.isPresent()) {
            Member member = memberWrapper.get();

            List<GrantedAuthority> authorities = member.getMemberGrades()
                    .stream()
                    .map(memberGrade -> memberGrade.getGrade())
                    .map(grade -> new SimpleGrantedAuthority(grade.getName()))
                    .collect(Collectors.toList());

            log.info("Autorieis : {}", authorities.toString());

            return new User(member.getAccount(), member.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("The account is not found. [" + account +"]");
        }
    }
}
