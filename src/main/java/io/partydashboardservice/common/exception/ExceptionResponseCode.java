package io.partydashboardservice.common.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 30.
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionResponseCode {
    PARTY_DASHBOARD_NOT_FOUND(NOT_FOUND, "The party dashboard is not in DB partyId : ");
    private final HttpStatus httpStatus;
    private final String message;
}
