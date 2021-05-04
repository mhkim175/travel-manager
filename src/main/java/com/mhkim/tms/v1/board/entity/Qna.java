package com.mhkim.tms.v1.board.entity;

import com.mhkim.tms.common.BaseTime;
import com.mhkim.tms.v1.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
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
    private Long qnaIdx;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Builder
    public Qna(Long qnaIdx, String title, String content, User user) {

        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(isNotEmpty(content), "content must be provided.");
        checkNotNull(user, "user must be provided.");

        this.qnaIdx = qnaIdx;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateQna(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
