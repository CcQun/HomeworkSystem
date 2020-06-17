package com.example.homework.controller;

import com.example.homework.core.Utils;
import com.example.homework.db.model.Homework;
import com.example.homework.db.model.Submit;
import com.example.homework.db.model.Teacher;
import com.example.homework.core.cm.HKWithTNS;
import com.example.homework.db.model.pk.SubmitPK;
import com.example.homework.db.service.HomeworkService;
import com.example.homework.db.service.SubmitService;
import com.example.homework.db.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/15 12:13
 */
@RestController()
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private final HomeworkService homeworkService;
    @Autowired
    private final TeacherService teacherService;
    @Autowired
    private final SubmitService submitService;

    public StudentController(HomeworkService homeworkService, TeacherService teacherService, SubmitService submitService) {
        this.homeworkService = homeworkService;
        this.teacherService = teacherService;
        this.submitService = submitService;
    }

    /**
     * 前往获取作业的页面
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/getHomework")
    public void gotoGetHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        List<Homework> list = homeworkService.findAll();
        List<HKWithTNS> res = new ArrayList<HKWithTNS>();
        for(Homework homework:list){
            homework.setHomework_state(Utils.getState(homework.getCreate_time(),homework.getEnd_time()));
            Teacher teacher = Teacher.builder().teacher_number(homework.getTeacher_number()).build();
            List<Teacher> query1 = teacherService.findAll(teacher);
            Integer student_number = Integer.parseInt(req.getParameter("student_number"));
            SubmitPK submitPK = SubmitPK.builder()
                    .student_number(student_number)
                    .homework_number(homework.getHomework_number())
                    .build();
            List<Submit> query2 = submitService.findAll(Submit.builder().submit_pk(submitPK).build());
            String submit_state = "";
            if(query2.size() > 0){
                submit_state = "已提交";
            }else{
                submit_state = "未提交";
            }
            HKWithTNS hkWithTName = HKWithTNS.builder()
                    .homework_number(homework.getHomework_number())
                    .teacher_name(query1.get(0).getTeacher_name())
                    .homework_title(homework.getHomework_title())
                    .homework_content(homework.getHomework_content())
                    .homework_state(homework.getHomework_state())
                    .submit_state(submit_state)
                    .create_time(homework.getCreate_time())
                    .update_time(homework.getUpdate_time())
                    .end_time(homework.getEnd_time())
                    .build();
            res.add(hkWithTName);
        }
        homeworkService.saveAll(list);

        req.setAttribute("homework_list",res);
        req.setAttribute("student_number",req.getParameter("student_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/getHomework.jsp").forward(req,resp);
    }

    /**
     * 提交作业
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/submitHomework")
    public void submitHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        SubmitPK submitPK = SubmitPK.builder()
                .homework_number(Integer.parseInt(req.getParameter("homework_number")))
                .student_number(Integer.parseInt(req.getParameter("student_number")))
                .build();
        Submit submit = Submit.builder()
                .submit_pk(submitPK)
                .submit_title(req.getParameter("homework_title"))
                .submit_content(req.getParameter("homework_content"))
                .create_time(new Date())
                .update_time(new Date())
                .build();
        submitService.save(submit);
        req.setAttribute("code", 1);
        req.setAttribute("msg", "作业" + req.getParameter("homework_number") + "成功提交");
        req.setAttribute("student_number",req.getParameter("student_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/submitHomeworkResult.jsp").forward(req,resp);
    }

    /**
     * 编辑作业
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/editHomework")
    public void editHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        SubmitPK submitPK = SubmitPK.builder()
                .homework_number(Integer.parseInt(req.getParameter("homework_number")))
                .student_number(Integer.parseInt(req.getParameter("student_number")))
                .build();
        List<Submit> query = submitService.findAll(Submit.builder().submit_pk(submitPK).build());
        Submit submit = Submit.builder()
                .submit_pk(submitPK)
                .submit_title(req.getParameter("homework_title"))
                .submit_content(req.getParameter("homework_content"))
                .create_time(query.get(0).getCreate_time())
                .update_time(new Date())
                .build();
        submitService.save(submit);
        req.setAttribute("code", 1);
        req.setAttribute("msg", "编辑作业" + req.getParameter("homework_number") + "成功");
        req.setAttribute("student_number",req.getParameter("student_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/editHomeworkResult.jsp").forward(req,resp);
    }

    @RequestMapping("/mySubmit")
    public void mySubmit(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Integer homework_number = Integer.parseInt(req.getParameter("homework_number"));
        Integer student_number = Integer.parseInt(req.getParameter("student_number"));
        SubmitPK submitPK = SubmitPK.builder()
                .homework_number(homework_number)
                .student_number(student_number)
                .build();
        Submit submit = Submit.builder().submit_pk(submitPK).build();
        List<Submit> list = submitService.findAll(submit);
        req.setAttribute("submit",list.get(0));
        req.setAttribute("student_number",req.getParameter("student_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/mySubmit.jsp").forward(req,resp);
    }
}
