package com.example.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author CcQun
 * @Date 2020/6/15 14:35
 */
@Controller
@RequestMapping("/jsp")
public class JspController {
    @RequestMapping("/login")
    public String index(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/gotoAddHomework")
    public void gotoAddHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.setAttribute("teacher_number",req.getParameter("teacher_number"));
        req.getRequestDispatcher("/WEB-INF/jsp/addHomework.jsp").forward(req,resp);
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
