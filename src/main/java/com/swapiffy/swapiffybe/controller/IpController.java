package com.swapiffy.swapiffybe.controller;

import com.swapiffy.swapiffybe.dto.UserLoginDTO;
import com.swapiffy.swapiffybe.service.GetIp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IpController {


    @GetMapping("/getApiAddress")
    public List<UserLoginDTO> getApiAddress() {
        List<UserLoginDTO> c=new ArrayList<>();
        c.add(new UserLoginDTO());
        return c;
    }
}
