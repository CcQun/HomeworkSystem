package com.example.homework.db.model.pk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author CcQun
 * @Date 2020/6/15 12:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@Data
public class HomeworkPK implements Serializable {
    @Column(name = "homework_number")
    private Integer homework_number;
    @Column(name = "teacher_number")
    private Integer teacher_number;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HomeworkPK)) {
            return false;
        }

        HomeworkPK orderPK = (HomeworkPK) o;

        if (homework_number != null ? !homework_number.equals(orderPK.homework_number) : orderPK.homework_number != null) {
            return false;
        }
        return teacher_number != null ? teacher_number.equals(orderPK.teacher_number) : orderPK.teacher_number == null;
    }
}
