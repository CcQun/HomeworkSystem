package com.example.homework.controller;

import com.example.homework.core.Utils;
import com.example.homework.db.model.Homework;
import com.example.homework.db.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/15 12:12
 */
@RestController()
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private final HomeworkService homeworkService;

    public TeacherController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @RequestMapping("/addHomework")
    public void addHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (Utils.strToDateLong(req.getParameter("create_time")).before(Utils.strToDateLong(req.getParameter("end_time")))) {
            DateFormat dateFormat = null;
            System.out.println(req.getParameter("create_time"));
            Homework homework = Homework.builder()
                    .homework_number(getMaxHomeworkNumber() + 1)
                    .teacher_number(Integer.parseInt(req.getParameter("teacher_number")))
                    .homework_title(req.getParameter("homework_title"))
                    .homework_content(req.getParameter("homework_content"))
                    .homework_state(Utils.getState(Utils.strToDateLong(req.getParameter("create_time")), Utils.strToDateLong(req.getParameter("end_time"))))
                    .create_time(Utils.strToDateLong(req.getParameter("create_time")))
                    .update_time(Utils.strToDateLong(req.getParameter("create_time")))
                    .end_time(Utils.strToDateLong(req.getParameter("end_time")))
                    .build();
            homeworkService.save(homework);
            req.setAttribute("code", 1);
            req.setAttribute("msg", "添加作业成功");
            req.setAttribute("teacher_number", req.getParameter("teacher_number"));
            req.getRequestDispatcher("/WEB-INF/jsp/addHomeworkResult.jsp").forward(req, resp);
        } else {
            req.setAttribute("code", 0);
            req.setAttribute("msg", "时间错误");
            req.setAttribute("teacher_number", req.getParameter("teacher_number"));
            req.getRequestDispatcher("/WEB-INF/jsp/addHomeworkResult.jsp").forward(req, resp);
        }
    }

    /**
     * 获取当前的最大作业编号
     *
     * @return
     */
    private Integer getMaxHomeworkNumber() {
        List<Homework> homeworks = homeworkService.findAll();
        Integer maxHomeworkNumber = 0;
        for (int i = 0; i < homeworks.size(); i++) {
            if (homeworks.get(i).getHomework_number() > maxHomeworkNumber) {
                maxHomeworkNumber = homeworks.get(i).getHomework_number();
            }
        }
        return maxHomeworkNumber;
    }
}
