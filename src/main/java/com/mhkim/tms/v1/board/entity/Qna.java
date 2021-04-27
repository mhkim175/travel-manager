package com.mhkim.tms.v1.board.entity;

import com.mhkim.tms.common.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@NoArgsConstructor
@ToString
@DynamicUpdate
@Entity
@Table(name = "qna")
public class Qna extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Builder
    public Qna(Long qnaId, String userName, String title, String content) {

        checkArgument(isNotEmpty(userName), "username must be provided.");
        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(isNotEmpty(content), "content must be provided.");

        this.qnaId = qnaId;
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public void updateQna(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
