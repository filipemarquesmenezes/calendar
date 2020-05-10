package br.com.fmenezes.utilities.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Calendar {
    private int dayOfMonth;
    private String dayOfWeek;
    private boolean weekend;
}
