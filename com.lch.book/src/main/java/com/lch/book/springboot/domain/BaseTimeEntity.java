package com.lch.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass    // JPA클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼을 인식하도록 함
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate     // Entity가 생성되어 저장될때 시간이 자동 저장하게 해줌
    private LocalDateTime createdDate;

    @LastModifiedDate    // 조회한 Entity의 값을 변경할 때 시간이 자동 저장합니다.
    private LocalDateTime modifiedDate;
}
