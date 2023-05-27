package com.example.demo.controller;

import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.mapper.seven.EmployeeMapper;
import com.example.demo.mapper.seven.UserMapper;
import com.example.demo.pojo.seven.Employee;
import com.example.demo.pojo.seven.User;
import com.example.demo.utils.SqlSessionUtils;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = {"*", "null"})
@MapperScan("com.example.demo")
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping(value = "/user")
public class ForgetController {
    
    @PostMapping("/mail")
    @ResponseBody
    public int mail(@RequestBody User user) throws Exception {
        //拿到账号，通过账号找employeeID->邮箱，发送随机验证,后端保存验证码发给前端，用户输入验证码，前端比对是否相同，相同则输入新密码（另一个方法）
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmployeeMapper emapper = sqlSession.getMapper(EmployeeMapper.class);//
        Employee hello = emapper.selectMailById(user.getEmployeeID());//hello可以拿到邮箱
//        he.getEmployeeID();
        // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", "smtp.qq.com");
        //端口号，QQ邮箱端口587
        props.put("mail.smtp.port", "587");
        // 此处填写，写信人的账号
        props.put("mail.user", "272744476@qq.com");
        // 此处填写16位STMP口令
        props.put("mail.password", "kwaugylpemtebiic");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress(hello.getEmail());//209283193   657803436
        message.setRecipient(MimeMessage.RecipientType.TO, to);
        // 设置邮件标题
        message.setSubject("项目管理系统忘记密码验证");
        int flag = 0;
        for (int i = 0; i <= 100; i++) {
            flag = new Random().nextInt(999999);
            if (flag < 100000) {
                flag += 100000;
            }
        }
        // 设置邮件的内容体
        message.setContent("您的验证码为： " + flag, "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
        return flag;
    }

    @PostMapping("/updatepasswordnop")
    @ResponseBody
    public String updatepasswordnop(@RequestBody User user)  throws SQLException, Exception{//根据邮箱修改密码
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String salt = RandomStringUtils.randomAlphanumeric(8);
        User userToBeUpdated = mapper.selectUser(user.getEmployeeID());//也得改
        userToBeUpdated.setPassword(new Sha1Hash(user.getPassword(), salt, 512).toHex());
        //mapper改掉，改成更新用户名，密码和salt
        mapper.updatePassword2(userToBeUpdated.getPassword(),userToBeUpdated.getEmployeeID(),salt);
        return "密码已修改，请重新登录";
    }
}
