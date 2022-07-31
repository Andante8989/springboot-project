package com.lch.book.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter //
@NoArgsConstructor
@Entity  // 테이블과 링크될 클래스임을 나타냄
public class Posts {

    @Id // 해당 테이블의 pk임을 타타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY 추가해야만 auth increment 가능
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
