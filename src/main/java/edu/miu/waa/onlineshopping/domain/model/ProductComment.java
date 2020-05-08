package edu.miu.waa.onlineshopping.domain.model;

import edu.miu.waa.onlineshopping.domain.vo.CommentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductComment {
    private Integer commentId;
    private String comment;
    private Integer rating;
    private User reviewUser;
    private LocalDate reviewDate;
    private CommentStatus commentStatus;

    private ProductComment(Integer commentId, String comment, Integer rating, User reviewUser, LocalDate reviewDate, CommentStatus commentStatus) {
        this.commentId = commentId;
        this.comment = comment;
        this.rating = rating;
        this.reviewUser = reviewUser;
        this.reviewDate = reviewDate;
        this.commentStatus = commentStatus;
    }

    public static ProductComment of(Integer commentId, String comment, Integer rating, User reviewUser, LocalDate reviewDate, CommentStatus commentStatus) {
        return new ProductComment(commentId, comment, rating, reviewUser, reviewDate, commentStatus);
    }
}
