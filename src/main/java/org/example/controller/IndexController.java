package org.example.controller;

import org.example.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("/home")
    public String getHomePage(Model model){
        User user1=new User("YXF",21,"一个幽默且热爱java的社会青年");
        List<String> userList=new ArrayList<>();
        userList.add("令狐冲");
        userList.add("张三丰");
        userList.add("霍元甲");
        Map<String ,String> map=new HashMap<>();
        map.put("place","博学谷");
        map.put("feeling","very well");
        model.addAttribute("name","令狐冲");//普通字符串
        model.addAttribute("user",user1);//储存javabean
        model.addAttribute("userlist",userList);//储存List
        model.addAttribute("map",map);//储存Map
        return "home";
    }
}
