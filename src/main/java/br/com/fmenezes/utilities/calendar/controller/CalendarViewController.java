package br.com.fmenezes.utilities.calendar.controller;

import br.com.fmenezes.utilities.calendar.dto.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/views")
public class CalendarViewController {

    private static final int MAX_SHOW_DAYS = 42;

    @GetMapping(value = "/times")
    public String times(Model model) {

        // Holds the entire times
        Map<Integer, Calendar> times = new TreeMap<>();

        // First day of the month
        LocalDate localDate = LocalDate.now().withDayOfMonth(1);
        Month currentMonth = localDate.getMonth();

        while(localDate.getMonth() == currentMonth) {

            int dayOfMonth = localDate.getDayOfMonth();
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();

            times.put(dayOfMonth,
                    new Calendar(dayOfMonth, dayOfWeek.toString(), isWeekend(dayOfWeek)));

            // Adds one more day util it reaches end of it.
            localDate = localDate.plusDays(1);
        }

        long workDays = times.values().stream().filter(c -> !c.isWeekend()).count();

        model.addAttribute("workDays", workDays);
        model.addAttribute("times", times);

        return "times";
    }

    @GetMapping(value = "/calendar")
    public ModelAndView calendar(@RequestParam(required = false) String monthParam) {

        // Holds the entire calendar
        Map<Integer, Calendar> calendar = new TreeMap<>();

        // First day of the month
        LocalDate localDate = LocalDate.now().withDayOfMonth(1);
        if (!StringUtils.isEmpty(monthParam)) {
            localDate = localDate.withMonth(Month.valueOf(monthParam).getValue());
        }

        // Check how many days from last month should we show
        int diffFirstWeek =  localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue();
        if(diffFirstWeek != 0) {
            localDate = localDate.minusDays(diffFirstWeek);
        }

        while(calendar.size() != MAX_SHOW_DAYS) {
            int dayOfMonth = localDate.getDayOfMonth();
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();

            // Determine key
            String monthDayKey = localDate.getMonthValue() + (dayOfMonth > 9 ? "" : "0") + dayOfMonth;

            calendar.put(Integer.parseInt(monthDayKey),
                    new Calendar(dayOfMonth, dayOfWeek.toString(), isWeekend(dayOfWeek)));

            // Adds one more day util it reaches MAX_SHOW_DAYS
            localDate = localDate.plusDays(1);
        }

        return new ModelAndView("calendar", "calendar", calendar);
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }
}
