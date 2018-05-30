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
    @NotEmpty
    private String name;

    @Column(nullable = true)
    private Long answer;

    @Column
    private LocalDateTime answerTime;

    @Column
    private LocalDateTime answerSecondTime;

    @Column(nullable = true)
    @Min(0)
    private long totaltime;

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

    public long getTotaltime() {
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
}
