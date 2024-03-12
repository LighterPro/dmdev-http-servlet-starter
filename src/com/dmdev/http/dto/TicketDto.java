package com.dmdev.http.dto;

import java.math.BigDecimal;

public record TicketDto(
        Long id,
        Long flight_id,
        String passenger_name,
        String seat_no,
        BigDecimal cost
) {
}
