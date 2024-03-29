package com.dmdev.http.service;

import com.dmdev.http.dao.FlightDao;
import com.dmdev.http.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    public static FlightService getInstance() {
        return INSTANCE;
    }

    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> FlightDto.builder()
                        .id(flight.getId())
                        .description("%s - %s - %s".formatted(
                                flight.getDepartureAirportCode(),
                                flight.getArrivalAirportCode(),
                                flight.getStatus())
                        )
                        .build()
                )
                .collect(toList());
    }
}