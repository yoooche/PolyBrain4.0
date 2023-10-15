package feature.booking.util;

import feature.booking.service.BookingChooSerImpl;
import feature.booking.service.BookingChooseService;
import feature.booking.service.BookingService;
import feature.booking.service.BookingServiceImpl;

public class BookConstants {
    public static final BookingService SERVICE = new BookingServiceImpl();
    public static final BookingChooseService SERVICETable = new BookingChooSerImpl();
}
