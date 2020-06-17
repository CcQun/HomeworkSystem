package com.example.homework.controller;

import com.example.homework.db.model.Submit;
import com.example.homework.db.model.pk.SubmitPK;
import com.example.homework.db.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/15 14:35
 */
@Controller
@RequestMapping("/jsp")
public class JspController {
    @Autowired
    private final SubmitService submitService;

    public JspController(SubmitService submitService) {
        this.submitService = submitService;
    }

    /**
     * 前往登录页面
     * @return
     */
    @RequestMapping("/login")
    public String index(){
        return "login";
    }

    /**
     * 前往注册页面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 前往添加作业的页面
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/gotoAddHomework")
    public void gotoAddHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.setAttribute("teacher_number",req.getParameter("teacher_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/addHomework.jsp").forward(req,resp);
    }

    /**
     * 前往提交作业的页面
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/gotoSubmitHomework")
    public void gotoSubmitHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        SubmitPK submitPK = SubmitPK.builder()
                .homework_number(Integer.parseInt(req.getParameter("homework_number")))
                .student_number(Integer.parseInt(req.getParameter("student_number")))
                .build();
        Submit querySubmit = Submit.builder()
                .submit_pk(submitPK)
                .build();
        List<Submit> query = submitService.findAll(querySubmit);
        if(query.size() > 0){
            req.setAttribute("code", 0);
            req.setAttribute("msg", "作业" + req.getParameter("homework_number") + "已提交，无需重复提交。");
            req.setAttribute("student_number",req.getParameter("student_number"));
            req.getRequestDispatcher("/WEB-INF/jsp/submitHomeworkResult.jsp").forward(req,resp);
        }else{
            req.setAttribute("student_number",req.getParameter("student_number"));
            req.setAttribute("homework_number",req.getParameter("homework_number"));
            req.getRequestDispatcher("/WEB-INF/jsp/submitHomework.jsp").forward(req,resp);
        }
    }

    /**
     * 前往编辑作业的页面
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/gotoEditHomework")
    public void gotoEditHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Integer student_number = Integer.parseInt(req.getParameter("student_number"));
        Integer homework_number = Integer.parseInt(req.getParameter("homework_number"));
        SubmitPK submitPK = SubmitPK.builder().homework_number(homework_number)
                .student_number(student_number)
                .build();
        List<Submit> query = submitService.findAll(Submit.builder().submit_pk(submitPK).build());
        req.setAttribute("submit",query.get(0));
        req.setAttribute("student_number",req.getParameter("student_number"));
        req.setAttribute("homework_number",req.getParameter("homework_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/editHomework.jsp").forward(req,resp);
    }

    /**
     * 返回老师的主页
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/backTeacher")
    public void backTeacher(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.setAttribute("teacher_number",req.getParameter("teacher_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/teacher.jsp").forward(req,resp);
    }

    /**
     * 返回学生的主页
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/backStudent")
    public void backStudent(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.setAttribute("student_number",req.getParameter("student_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/student.jsp").forward(req,resp);
    }


}
