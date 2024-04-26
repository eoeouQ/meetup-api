package org.izouir.meetup_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.izouir.meetup_api.entity.Place;

import java.sql.Timestamp;

public record MeetDto(Long meetId,
                      @NotBlank
                      String title,
                      String description,
                      @NotBlank
                      String keeper,
                      @NotNull
                      Timestamp date,
                      @NotNull
                      Place place) {
}
