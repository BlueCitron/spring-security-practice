package me.bluecitron.security.config;

import lombok.RequiredArgsConstructor;
import me.bluecitron.security.domain.Grade;
import me.bluecitron.security.repository.GradeRepository;
import me.bluecitron.security.service.MemberService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppStarted implements ApplicationListener<ApplicationStartedEvent> {

    private final GradeRepository gradeRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        gradeRepository.save(new Grade("ROLE_MEMBER"));
        gradeRepository.save(new Grade("ROLE_ADMIN"));
    }
}
