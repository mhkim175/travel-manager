package com.mhkim.tms.v1.board.entity;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.PersistenceConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "qna")
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime modifiedAt;

    @Builder
    @PersistenceConstructor
    public Qna(Long qnaId, String userName, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        checkArgument(isNotEmpty(userName), "username must be provided.");
        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(isNotEmpty(content), "content must be provided.");

        this.qnaId = qnaId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createdAt = defaultIfNull(createdAt, now());
        this.modifiedAt = defaultIfNull(modifiedAt, now());
    }

    public void updateQna(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
