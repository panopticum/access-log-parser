package com.ef.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Config {
    public static final String HOURLY_DURATION = "hourly";
    public static final String DAILY_DURATION = "daily";

    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") private String startDate;
    @NotNull @Pattern(regexp = HOURLY_DURATION + "|" + DAILY_DURATION) private String duration;
    @NotNull @Min(1) private Integer threshold;
    @NotNull @NotEmpty  private String accesslog;
}
