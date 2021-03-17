package com.mhkim.tms.board.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue
    private Long boardId;
    private String userName;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    @PersistenceConstructor
    public Board(Long boardId, String userName, String title, String content, LocalDateTime createdAt,
            LocalDateTime modifiedAt) {

        checkArgument(isNotEmpty(userName), "username must be provided.");
        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(isNotEmpty(content), "content must be provided.");

        this.boardId = boardId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createdAt = defaultIfNull(createdAt, now());
        this.modifiedAt = defaultIfNull(modifiedAt, now());
    }

    public void setBoardUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
