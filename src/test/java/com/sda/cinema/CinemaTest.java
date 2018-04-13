package com.sda.cinema;

import com.sda.cinema.model.CinemaBookingResponse;
import com.sda.cinema.model.CinemaBookingStatus;
import com.sda.cinema.model.CinemaBookingStatusCode;
import com.sda.cinema.model.CinemaNotifierResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CinemaTest {

    @Test
    public void userCanReserveMovieAndReciveNotificationWithTicket() {
        //given
        CinemaBookingService cinemaBookingService = Mockito.mock(CinemaBookingService.class);
        Mockito.when(cinemaBookingService.bookSeating(Mockito.any(), Mockito.anyInt()))
                .then(e -> new CinemaBookingStatus(true, null));
        CinemaNotifier cinemaNotifier = Mockito.mock(CinemaNotifier.class);
        Mockito.when(cinemaNotifier.notify(Mockito.any(), Mockito.any(), Mockito.any()))
                .then(e -> new CinemaNotifierResponse(true, null));

        Cinema cinema = new Cinema(cinemaBookingService, cinemaNotifier);

        //when
        CinemaBookingResponse response = cinema.bookMovie(null, 15, null, null);

        //then
        Assert.assertEquals("Miejsce zarezerwowane. Za chwile dostaniesz swoj bilet", response.getMessage());
        Assert.assertTrue(response.isStatus());
        Mockito.verify(cinemaNotifier, Mockito.times(1)).notify(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void userCannotReserveMovieBecauseSelectedSeatingIsInvalid() {
        //given
        CinemaBookingService cinemaBookingService = Mockito.mock(CinemaBookingService.class);
        Mockito.when(cinemaBookingService.bookSeating(Mockito.any(), Mockito.anyInt()))
                .then(e -> new CinemaBookingStatus(false, CinemaBookingStatusCode.WRONG_SEATING_ID));
        CinemaNotifier cinemaNotifier = Mockito.mock(CinemaNotifier.class);
        Mockito.when(cinemaNotifier.notify(Mockito.any(), Mockito.any(), Mockito.any()))
                .then(e -> new CinemaNotifierResponse(true, null));

        Cinema cinema = new Cinema(cinemaBookingService, cinemaNotifier);

        //when
        CinemaBookingResponse response = cinema.bookMovie(null, 15, null, null);

        //then
        Assert.assertEquals("Wybrano niepoprawny numer miejsca.", response.getMessage());
        Assert.assertFalse(response.isStatus());
        Mockito.verify(cinemaNotifier, Mockito.never()).notify(Mockito.any(), Mockito.any(), Mockito.any());

    }

    @Test
    public void userCannotReserveMovieBecauseSelectedSeatingIsAlreadyBooked() {
        //given
        CinemaBookingService cinemaBookingService = Mockito.mock(CinemaBookingService.class);
        Mockito.when(cinemaBookingService.bookSeating(Mockito.any(), Mockito.anyInt()))
                .then(e -> new CinemaBookingStatus(false, CinemaBookingStatusCode.SEATING_ALREADY_BOOKED));
        CinemaNotifier cinemaNotifier = Mockito.mock(CinemaNotifier.class);
        Mockito.when(cinemaNotifier.notify(Mockito.any(), Mockito.any(), Mockito.any()))
                .then(e -> new CinemaNotifierResponse(true, null));

        Cinema cinema = new Cinema(cinemaBookingService, cinemaNotifier);

        //when
        CinemaBookingResponse response = cinema.bookMovie(null, 15, null, null);

        //then
        Assert.assertEquals("Wybrane miejsce jest juz zajete", response.getMessage());
        Assert.assertFalse(response.isStatus());
        Mockito.verify(cinemaNotifier, Mockito.times(0)).notify(Mockito.any(), Mockito.any(), Mockito.any());
    }
}
