package org.ds.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Глобальный обработчик всех исключений.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Обработка исключений, возникших в результате отправки запроса на сервер ландромата.
     * Отправляет клиенту сообщение об ошибке.
     */
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponse> handleWebClientException(WebClientResponseException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getStatusCode());
    }

    /**
     * Обработка ситуаций, когда входные данные от клиента не прошли валидацию.
     * Отправляет клиенту сообщение об ошибке.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse("Bad Request");
        return new ResponseEntity<>(errorResponse, e.getStatusCode());
    }
}
