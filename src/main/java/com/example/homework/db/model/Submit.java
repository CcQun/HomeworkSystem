package com.example.homework.db.model;

import com.example.homework.db.model.pk.SubmitPK;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author CcQun
 * @Date 2020/6/15 12:31
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="Submit")
public class Submit implements Persistable {
    @EmbeddedId
    private SubmitPK submit_pk;
    private String submit_title;
    private String submit_content;
    private Integer grade;
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date create_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date update_time;


    @Override
    public Object getId() {
        return submit_pk;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
