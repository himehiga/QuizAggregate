package quiz_aggregate.Model.Entity;

import org.springframework.context.annotation.Scope;
import sun.util.resources.LocaleData;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="userdata")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column
    private String uuid;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = true)
    private Long answer;

    @Column
    private LocalDateTime answerTime;

    @Column
    private LocalDateTime answerSecondTime;

    @Column
    private Long firstAnswerTime;

    @Column
    private Boolean isAnseredFirst = false;

    @Column
    private Long secondAnswerTime;

    @Column
    private Boolean isAnseredSecond = false;

    @Column
    private Boolean isAnseredThird = false;

    //正答数
    @Column
    private int correctNum;

    //順位
    @Column
    private int rank;

    @Column(nullable = true)
    @Min(0)
    private Long totaltime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }

    public Long getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(long totaltime) {
        this.totaltime = totaltime;
    }

    public LocalDateTime getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(LocalDateTime answerTime) {
        this.answerTime = answerTime;
    }

    public LocalDateTime getAnswerSecondTime() {
        return answerSecondTime;
    }

    public void setAnswerSecondTime(LocalDateTime answerSecondTime) {
        this.answerSecondTime = answerSecondTime;
    }

    public Long getFirstAnswerTime() {
        return firstAnswerTime;
    }

    public void setFirstAnswerTime(long firstAnswerTime) {
        this.firstAnswerTime = firstAnswerTime;
    }

    public Long getSecondAnswerTime() {
        return secondAnswerTime;
    }

    public void setSecondAnswerTime(long secondAnswerTime) {
        this.secondAnswerTime = secondAnswerTime;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public void setCorrectNum(int correctNum) {
        this.correctNum = correctNum;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public Boolean getAnseredFirst() {
        return isAnseredFirst;
    }

    public void setAnseredFirst(Boolean anseredFirst) {
        isAnseredFirst = anseredFirst;
    }

    public Boolean getAnseredSecond() {
        return isAnseredSecond;
    }

    public void setAnseredSecond(Boolean anseredSecond) {
        isAnseredSecond = anseredSecond;
    }

    public Boolean getAnseredThird() {
        return isAnseredThird;
    }

    public void setAnseredThird(Boolean anseredThird) {
        isAnseredThird = anseredThird;
    }
}
