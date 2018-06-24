package quiz_aggregate.Model.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="timemanagedata")
public class TimeManageData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private long id;

    @Column
    private Boolean isNotAllowedOperation;

    @Column
    private Boolean isAnswearFirst;

    @Column
    private Boolean isAnswearSecond;

    @Column
    private Boolean isAnswearThird;

    @Column
    private Boolean isAnswerEnd;

    @Column int answearNumber;

    @Column
    private Long baseTime;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Boolean getIsAnswearFirst() {
        return isAnswearFirst;
    }

    public void setAnswearFirst(Boolean answearFirst) {
        isAnswearFirst = answearFirst;
    }

    public Boolean getAnswearSecond() {
        return isAnswearSecond;
    }

    public void setAnswearSecond(Boolean answearSecond) {
        isAnswearSecond = answearSecond;
    }

    public Boolean getAnswearThird() {
        return isAnswearThird;
    }

    public void setAnswearThird(Boolean answearThird) {
        isAnswearThird = answearThird;
    }

    public Boolean getAnswerEnd() {
        return isAnswerEnd;
    }

    public void setAnswerEnd(Boolean answerEnd) {
        isAnswerEnd = answerEnd;
    }

    public long getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(long baseTime) {
        this.baseTime = baseTime;
    }

    public Boolean getNotAllowedOperation() {
        return isNotAllowedOperation;
    }

    public void setNotAllowedOperation(Boolean notAllowedOperation) {
        isNotAllowedOperation = notAllowedOperation;
    }
}
