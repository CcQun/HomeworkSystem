package com.example.homework.controller;

import com.example.homework.core.Utils;
import com.example.homework.core.cm.HKInforSTT;
import com.example.homework.db.model.Homework;
import com.example.homework.db.model.Student;
import com.example.homework.db.model.Submit;
import com.example.homework.db.model.pk.SubmitPK;
import com.example.homework.db.service.HomeworkService;
import com.example.homework.db.service.StudentService;
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
    @Autowired
    private final StudentService studentService;

    public TeacherController(HomeworkService homeworkService, SubmitService submitService, StudentService studentService) {
        this.homeworkService = homeworkService;
        this.submitService = submitService;
        this.studentService = studentService;
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

    /**
     * 获取某作业的提交状况
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/submitCondition")
    public void submitCondition(HttpServletRequest req, HttpServletResponse resp) throws Exception{
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

    /**
     * 获取某作业的统计情况
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/statisticalCondition")
    public void statisticalCondition(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Integer homework_number = Integer.parseInt(req.getParameter("homework_number"));
        SubmitPK submitPK = SubmitPK.builder().homework_number(homework_number).build();
        Submit submit = Submit.builder().submit_pk(submitPK).build();
        List<Submit> list = submitService.findAll(submit);
        Integer to_evaluate_student_count = 0;
        Integer pass_student_count = 0;
        Integer submit_student_count = list.size();
        Double average = 0.0;
        Integer max_point = 0;
        Integer min_point = 10000;
        String max_point_owner = "";
        Integer sum_point = 0;
        for (int i = 0;i < list.size();i++){
            Integer grade = list.get(i).getGrade();
            if(grade == null){
                to_evaluate_student_count += 1;
            }else{
                if(grade >= 60){
                    pass_student_count += 1;
                }
                sum_point += grade;
                if(grade >= max_point){
                    max_point = grade;
                    Integer max_student_number = list.get(i).getSubmit_pk().getStudent_number();
                    Student stu = Student.builder().student_number(max_student_number).build();
                    max_point_owner = studentService.findAll(stu).get(0).getStudent_name();
                }
                if(grade <= min_point){
                    min_point = grade;
                }
            }
        }
        if(submit_student_count.equals(to_evaluate_student_count)){
            min_point = 0;
        }
        if(sum_point != 0) {
            average = Double.valueOf(sum_point / (submit_student_count - to_evaluate_student_count));
        }
        HKInforSTT hkInforSTT = HKInforSTT.builder()
                .average(average)
                .max_point(max_point)
                .min_point(min_point)
                .max_point_owner(max_point_owner)
                .pass_student_count(pass_student_count)
                .submit_student_count(submit_student_count)
                .to_evaluate_student_count(to_evaluate_student_count)
                .build();
        req.setAttribute("stt",hkInforSTT);
        req.setAttribute("teacher_number",req.getParameter("teacher_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/statisticalCondition.jsp").forward(req,resp);
    }

    @RequestMapping("/submitGrade")
    public void submitGrade(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Integer homework_number = Integer.parseInt(req.getParameter("homework_number"));
        Integer student_number = Integer.parseInt(req.getParameter("student_number"));
        String temp = req.getParameter("temp");
        Integer grade = Integer.parseInt(req.getParameter("grade" + temp));
        String comment = req.getParameter("comment" + temp);

        System.out.println(grade);
        System.out.println(comment);
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
