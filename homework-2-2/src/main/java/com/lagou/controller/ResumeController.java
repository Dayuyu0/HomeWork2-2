package com.lagou.controller;

import com.lagou.annotation.Security;
import com.lagou.dao.ResumeDao;
import com.lagou.pojo.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ResumeController {

    @Autowired
    private ResumeDao resumeDao;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(){
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, HttpSession session, Model model, RedirectAttributes redirectAttributes, String username, String password){
        if ("admin".equals(username) && "admin".equals(password)
                || "tom".equals(username) && "tom".equals(password)
                || "jerry".equals(username) && "jerry".equals(password)){
            // 将用户名添加到Session中
            session.setAttribute("USER_SESSION", username);
            response.addHeader("USER_SESSION", "username");
            // 重定向主页
            return "redirect:home";
        }

        // 否则跳转login 并提示用户密码错误
        model.addAttribute("msg","账号或密码错误"); // 重定向会失效
        redirectAttributes.addFlashAttribute("msg","账号或密码错误"); // 重定向会临时保存在session，跳转后销毁
        return "redirect:login";
    }

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/invalid")
    public String invalid(){

        /**
         * 此处经过拦截器重定向是无法获取参数的，只能通过转发
         * 因为此处无法像Handler一样使用 RedirectAttributes.addFlashAttribute
         */
        return "invalid";
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.GET)
    @Security(value = {"admin", "tom"})
    public ModelAndView saveOrUpdate(@RequestParam(value = "id", required = false) Long id){
        ModelAndView modelAndView = new ModelAndView();
        if (null != id) {
            Resume obj = resumeDao.findById(id).get();
            modelAndView.addObject("obj", obj);
            modelAndView.setViewName("saveOrUpdate");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    @Security(value = {"admin", "tom"})
    public Object saveOrUpdate(@RequestBody Resume resume){
        resumeDao.save(resume);
        Map<String, String > map = new HashMap<>();
        map.put("status", "ok");
        return map;//"redirect:list";
    }


    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response, HttpSession session){
        //清除session
        session.invalidate();
        response.setHeader("USER_SESSION", null);
        //重定向到登录页面的跳转方法
        return "redirect:login";
    }

    @RequestMapping("/list")
    @Security(value = {"admin", "tom"}) // 允许admin/tom访问，其他人不可访问
    public ModelAndView queryAll() {
        List<Resume> all = resumeDao.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",all);
        modelAndView.setViewName("list");
        return modelAndView;
    }


    @RequestMapping("/findById")
    @Security(value = {"admin", "jerry"}) // 允许admin/jerry访问，其他人不可访问
    public String findById(Model model, @RequestParam("id") Long id) {
        Resume resume = resumeDao.findById(id).get();
        model.addAttribute("obj", resume);
        return "single";
    }

    @RequestMapping("/deleteById")
    @Security(value = {"admin", "jerry", "tom"}) // 允许admin/tom访问，其他人不可访问
    public String deleteById(@RequestParam("id") Long id) {
        resumeDao.deleteById(id);
        return "redirect:list";
    }
}
