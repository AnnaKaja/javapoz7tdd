package com.sda.cinema;

import com.sda.cinema.model.*;

public class Cinema {
    private final CinemaBookingService cinemaBookingService;

    private final CinemaNotifier cinemaNotifier;

    public Cinema(CinemaBookingService cinemaBookingService, CinemaNotifier cinemaNotifier) {
        this.cinemaBookingService = cinemaBookingService;
        this.cinemaNotifier = cinemaNotifier;
    }

    public CinemaBookingResponse bookMovie(CinemaMovie movie, int seating, CinemaUser user, CinemaChannel channel) {
        CinemaBookingStatus status = cinemaBookingService.bookSeating(movie, seating);
        if (!status.isStatus()) {
            CinemaBookingResponse failureResponse = new CinemaBookingResponse();
            failureResponse.setStatus(false);
            switch (status.getStatusCode()) {
                case WRONG_SEATING_ID:
                    failureResponse.setMessage("Wybrano niepoprawny numer miejsca.");
                    break;
                case SEATING_ALREADY_BOOKED:
                    failureResponse.setMessage("Wybrane miejsce jest juz zajete");
                    break;
                case INVALID_SEATING_TYPE:
                    failureResponse.setMessage("To miejsce nie jest przeznaczone dla takiej osoby jak ty");
                    break;
            }
            return failureResponse;
        }
        CinemaNotifierResponse response = cinemaNotifier.notify(user, channel, "Numer Twojego biletu to: 123-abc-456-def");
        if (!response.isStatus()) {
            CinemaBookingResponse cinemaBookingResponse = new CinemaBookingResponse();
            cinemaBookingResponse.setStatus(false);
            switch (response.getStatusCode()) {
                case SERVER_ERROR:
                    cinemaBookingResponse.setMessage("Ops. Twoj bilet zostal zarezerwowany, ale nie moglismy wyslac Ci biletu");
                    break;
                case CHANNEL_NOTSPECIFIED:
                    cinemaBookingResponse.setMessage("Nie moglismy wyslac biletu, poniewaz nie wskazales danych adresowych");
                    break;
            }
            return cinemaBookingResponse;
        }

        return new CinemaBookingResponse(true, "Miejsce zarezerwowane. Za chwile dostaniesz swoj bilet");
    }
}