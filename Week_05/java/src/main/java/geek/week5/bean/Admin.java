package geek.week5.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
public class Admin implements IUser{

    @Resource(name = "bookLibrary")
    public Library bookLibrary;

    @Override
    public void print() {
        System.out.println("我是管理员:管理书籍---"+bookLibrary.getBooks());
    }
}
