package com.dmdev.http.service;

import com.dmdev.http.dao.TicketDao;
import com.dmdev.http.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();

    public static TicketService getInstance() {
        return INSTANCE;
    }

    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(ticket -> new TicketDto(
                        ticket.getId(),
                        ticket.getFlight_id(),
                        ticket.getPassenger_name(),
                        ticket.getSeat_no(),
                        ticket.getCost()
                ))
                .collect(toList());
    }

}