package br.com.fmenezes.utilities.calendar.controller;

import br.com.fmenezes.utilities.calendar.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/views")
public class PeopleViewController {

    private PersonRepository personRepository;

    public PeopleViewController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/people")
    public ModelAndView viewPeople() {
        return new ModelAndView("people", "people", personRepository.findAll());
    }
}
