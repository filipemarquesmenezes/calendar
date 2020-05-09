package br.com.fmenezes.utilities.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class CalendarViewController {

    @GetMapping(value = "/calendar")
    public String calendar() {
        return "calendar";
    }
}
