package com.example.homework.controller;

import com.example.homework.core.Utils;
import com.example.homework.db.model.Homework;
import com.example.homework.db.model.Submit;
import com.example.homework.db.model.pk.SubmitPK;
import com.example.homework.db.service.HomeworkService;
import com.example.homework.db.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
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
    @Autowired
    private final SubmitService submitService;

    public TeacherController(HomeworkService homeworkService,SubmitService submitService) {
        this.homeworkService = homeworkService;
        this.submitService = submitService;
    }

    /**
     * 添加作业
     * @param req
     * @param resp
     * @throws Exception
     */
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
     * 获取我发布的作业
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/getMyHomework")
    public void getMyHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Integer teacher_number = Integer.parseInt(req.getParameter("teacher_number"));
        Homework homework = Homework.builder().teacher_number(teacher_number).build();
        List<Homework> list = homeworkService.findAll(homework);
        for(Homework hk:list) {
            hk.setHomework_state(Utils.getState(hk.getCreate_time(), hk.getEnd_time()));
        }
        homeworkService.saveAll(list);
        req.setAttribute("teacher_number",req.getParameter("teacher_number"));
        req.setAttribute("homework_list",list);
        req.getRequestDispatcher("/WEB-INF/jsp/getMyHomework.jsp").forward(req,resp);
    }

    @RequestMapping("/gotoSubmitCondition")
    public void gotoSubmitCondition(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Integer homework_number = Integer.parseInt(req.getParameter("homework_number"));
        SubmitPK submitPK = SubmitPK.builder().homework_number(homework_number).build();
        Submit submit = Submit.builder().submit_pk(submitPK).build();
        List<Submit> list = submitService.findAll(submit);
        if(list.size() <= 0){
            req.setAttribute("code",0);
            req.setAttribute("msg","作业" + homework_number +"暂时无人提交。");
            req.setAttribute("teacher_number",req.getParameter("teacher_number"));
            req.getRequestDispatcher("/WEB-INF/jsp/submitConditionResult.jsp").forward(req,resp);
        }else{
            req.setAttribute("submit_list",list);
            req.setAttribute("teacher_number",req.getParameter("teacher_number"));
            req.getRequestDispatcher("/WEB-INF/jsp/submitCondition.jsp").forward(req,resp);
        }
    }

    @RequestMapping("/submitGrade")
    public void submitGrade(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Integer homework_number = Integer.parseInt(req.getParameter("homework_number"));
        Integer student_number = Integer.parseInt(req.getParameter("student_number"));
        Integer grade = Integer.parseInt(req.getParameter("grade"));
        String comment = req.getParameter("comment");
        SubmitPK submitPK = SubmitPK.builder()
                .homework_number(homework_number)
                .student_number(student_number)
                .build();
        Submit submit = Submit.builder().submit_pk(submitPK).build();
        List<Submit> query = submitService.findAll(submit);
        query.get(0).setGrade(grade);
        query.get(0).setComment(comment);
        submitService.save(query.get(0));

        SubmitPK spk = SubmitPK.builder().homework_number(homework_number).build();
        Submit st = Submit.builder().submit_pk(spk).build();
        List<Submit> list = submitService.findAll(st);
        req.setAttribute("submit_list",list);
        req.setAttribute("teacher_number",req.getParameter("teacher_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/submitCondition.jsp").forward(req,resp);
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
