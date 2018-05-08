package quiz_aggregate.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAnswer {

    private String userAccount;
    private Integer questionNum;
    private LocalDateTime answerTime;

}
