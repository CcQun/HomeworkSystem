package com.example.homework.db.model;

import com.example.homework.db.model.pk.HomeworkPK;
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
 * @Date 2020/6/15 12:19
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="Homework")
public class Homework implements Persistable{
    @EmbeddedId
    private HomeworkPK homework_pk;
    private String homework_title;
    private String homework_content;
    private Integer homework_state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date create_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date update_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date end_time;

    @Override
    public Object getId() {
        return homework_pk;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
