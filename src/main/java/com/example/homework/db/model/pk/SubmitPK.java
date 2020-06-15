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
 * @Date 2020/6/15 12:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@Data
public class SubmitPK implements Serializable {
    @Column(name = "homework_number")
    private Integer homework_number;
    @Column(name = "student_number")
    private Integer student_number;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubmitPK)) {
            return false;
        }

        SubmitPK orderPK = (SubmitPK) o;

        if (homework_number != null ? !homework_number.equals(orderPK.homework_number) : orderPK.homework_number != null) {
            return false;
        }
        return student_number != null ? student_number.equals(orderPK.student_number) : orderPK.student_number == null;
    }
}
