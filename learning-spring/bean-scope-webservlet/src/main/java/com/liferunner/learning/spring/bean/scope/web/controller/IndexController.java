package com.liferunner.learning.spring.bean.scope.web.controller;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * jsp 首页控制器
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/5
 **/
@Controller
public class IndexController {

    @Autowired
    private Person person;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("person", person);
        return "index";
    }
}
