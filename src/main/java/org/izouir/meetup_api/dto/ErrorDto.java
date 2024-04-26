package org.izouir.meetup_api.dto;

import java.sql.Timestamp;

public record ErrorDto(int statusCode,
                       String message,
                       Timestamp timestamp) {
}
