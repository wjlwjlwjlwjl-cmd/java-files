package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.io.File;
import java.io.IOException;

class UserInfo {
    private String name;
    private int age;
    private String password;

    public UserInfo(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String toString() {
        return name + " - " + age + " - " + password;
    }

    public String getName() {
        return name;
    }

    public String getPassWord() {
        return password;
    }

    public int getAge() {
        return age;
    }
}

@RequestMapping("/request") // 类路径：该类中的所有方法都在这fang
@RestController
public class RequestController {

    @RequestMapping("/r1") // 方法路径：访问该方法的路径
    public String r1(String keyword) { // 参数名和请求字符串中的key命名需要相同
        return "接收参数：" + keyword;
    }

    @RequestMapping("/r2")
    public String r2(String userName, String passWord) {
        return userName + ": " + passWord;
    }

    @RequestMapping("/r3")
    public String r3(Integer i) {
        return i + "";
    }

    @RequestMapping("/r4")
    public String r4(int i) { // 基础类型不能为空，但是包装类可以
        return i + "";
    }

    @GetMapping("/r5")
    public String r5() {
        return "get";
    }

    @PostMapping("/r5")
    public String r6() {
        return "post";
    }

    @RequestMapping("/r7")
    public String r7(UserInfo userInfo) { // 像这样使用类作为参数，前端传参时对照类的构造函数
        return userInfo.toString();
    }

    // 当前后端的参数名称不方便统一时，就可以使用@RequestParams标签名，但是默认修饰后参数变为比选项，不选就400
    // 这种方式叫做参数绑定
    @RequestMapping("/r8")
    public String r8(@RequestParam(value = "k", required = false) String key) {
        return "r8: " + key;
    }

    // 键值对结构的数据必须使用RequestParam进行参数绑定
    @RequestMapping("/r9")
    public String r9(@RequestParam List<Integer> list) {
        return "r9";
    }

    // 接收json
    @RequestMapping("/r10")
    public String r10(@RequestBody UserInfo userInfo) {
        return userInfo.toString();
    }

    // 获取请求内容
    @RequestMapping("/r11/{type}/{articleID}")
    public String r11(@PathVariable String type, @PathVariable Integer articleID) {
        return "请求文章：" + type + "：" + articleID;
    }

    // 存储上传的文件
    // SpringBoot的特点就是各种注解
    @RequestMapping("/r12")
    public String r12(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        file.transferTo(new File("/home/diinki/tmp/" + fileName));
        return "成功获取文件：" + fileName;
    }

    // 资源可以存放在
    // 1. 请求路径
    // 2. 请求字符串
    // 3. header
    // 4. body

    // Cookie 和 Session
    // 获取到所有的Cookie
    @RequestMapping("/getCookie")
    public String r13(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                System.out.println(ck.getName() + ": " + ck.getValue());
            }
            return "成功获取到Cookie";
        }
        return "未成功获取到Cookie";
    }

    @RequestMapping("/getCookie2")
    public String r14(@CookieValue("userName") Cookie cookie) {
        if (cookie == null) {
            return "未成功获取到Cookie";
        }
        System.out.println(cookie.getName() + ": " + cookie.getValue());
        return "成功获取到Cookie";
    }

    // 获取session
    @RequestMapping("setSession")
    public String setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", "wjl");
        session.setAttribute("age", 1);
        return "session 获取成功";
    }

    @RequestMapping("getSession")
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 设置为false,则可能的到没有session时的空结果
        if (session == null) {
            return "未登录用户";
        }
        String userName = (String) session.getAttribute("userName");
        System.out.println(userName);
        return "登录用户";
    }

    @RequestMapping("getSession2")
    public String getSession2(HttpSession session) {
        if (session == null) {
            return "未登录用户";
        }
        String userName = (String) session.getAttribute("userName");
        System.out.println(userName);
        return "登录用户";
    }

    @RequestMapping("getSession3")
    public String getSession2(@SessionAttribute("userName") String userName) {
        if (userName == null) {
            return "未登录用户";
        }
        System.out.println(userName);
        return "登录用户";
    }

    // 获取报头内容
    @RequestMapping("getHeader1")
    public String getHeader(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String ret = "get info from header(User-Agent): " + userAgent;
        return ret;
    }

    @RequestMapping("getHeader2")
    public String getHeader2(@RequestHeader("User-Agent") String userAgent) {
        String ret = "get info from header(User-Agent): " + userAgent;
        return ret;
    }
}
