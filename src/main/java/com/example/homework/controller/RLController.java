package com.example.homework.controller;

import com.example.homework.core.Utils;
import com.example.homework.core.response.BaseResponse;
import com.example.homework.db.model.Student;
import com.example.homework.db.model.Teacher;
import com.example.homework.db.service.StudentService;
import com.example.homework.db.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/15 12:41
 */
@Controller
@RequestMapping("/rl")
public class RLController {
    @Autowired
    private final StudentService studentService;
    @Autowired
    private final TeacherService teacherService;

    public RLController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    /**
     * 登录
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("/login")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            String password = req.getParameter("password");
            Integer ts = Integer.parseInt(req.getParameter("ts"));
            if(ts == 0){
                Student student = Student.builder().student_number(id).build();
                List<Student> list = studentService.findAll(student);
                if(list.size() > 0){
                    if(Utils.getMD5(password).equals(list.get(0).getStudent_password())){
                        req.setAttribute("code",1);
                        req.setAttribute("msg","登录成功");
                        req.getRequestDispatcher("/WEB-INF/jsp/student.jsp").forward(req,resp);
                    }else{
                        req.setAttribute("code",0);
                        req.setAttribute("msg","密码错误");
                        req.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp").forward(req,resp);
                    }
                }else{
                    req.setAttribute("code",0);
                    req.setAttribute("msg","账号不存在");
                    req.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp").forward(req,resp);
                }
            }else{
                Teacher teacher = Teacher.builder().teacher_number(id).build();
                List<Teacher> list = teacherService.findAll(teacher);
                if(list.size() > 0){
                    if(Utils.getMD5(password).equals(list.get(0).getTeacher_password())){
                        req.setAttribute("code",1);
                        req.setAttribute("msg","登录成功");
                        req.getRequestDispatcher("/WEB-INF/jsp/teacher.jsp").forward(req,resp);
                    }else{
                        req.setAttribute("code",0);
                        req.setAttribute("msg","密码错误");
                        req.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp").forward(req,resp);
                    }
                }else{
                    req.setAttribute("code",0);
                    req.setAttribute("msg","账号不存在");
                    req.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp").forward(req,resp);
                }
            }
        }catch(NumberFormatException e){
            req.setAttribute("code",0);
            req.setAttribute("msg","账号格式错误");
            req.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp").forward(req,resp);
        }
    }

    /**
     * 注册
     * @param req
     * @param resp
     */
    @RequestMapping("/register")
    public void register(HttpServletRequest req, HttpServletResponse resp){

    }
}
