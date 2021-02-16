package geek.week5;

import geek.week5.bean.Admin;
import geek.week5.bean.Book;
import geek.week5.bean.IUser;
import geek.week5.bean.Library;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

public class check {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // xml 设值注入
        Book book1 = (Book) context.getBean("book1");
        System.out.println("book1----" + book1.toString());
        // xml 构造注入
        Book book2 = (Book) context.getBean("book2");
        System.out.println("book2----" + book2.toString());
        // 基于注解装配
        Admin admin = (Admin) context.getBean("admin");
        admin.print();
        // xml自动装配
        Admin admin2 = (Admin) context.getBean("admin2");
        admin2.print();
    }
}
