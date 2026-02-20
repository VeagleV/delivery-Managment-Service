package com.crm.delivery.core.controllers;

import com.crm.delivery.core.dto.RequestRequest;
import com.crm.delivery.core.dto.RequestResponse;
import com.crm.delivery.core.dto.TransitRequest;
import com.crm.delivery.core.dto.TransitResponse;
import com.crm.delivery.core.enums.Status;
import com.crm.delivery.core.services.RequestService;
import com.crm.delivery.core.services.TransitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.MapsId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Request Controller",
        description = "Контроллер для управления заявками/транзитами"
)
@RestController
@RequestMapping("/requests")
@Validated
public class RequestController {
    private final TransitService transitService;
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService, TransitService transitService) {
        this.requestService = requestService;
        this.transitService = transitService;
    }

    @GetMapping("/")
    @Operation(summary = "Получение всех заявок", description = "Позволяет получить все заявки")
    public ResponseEntity<List<RequestResponse>> getRequests() {
        List<RequestResponse> responses = requestService.getAllRequests();
        if (responses.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{requestId}")
    @Operation(summary = "Получение заявки", description = "Позволяет получить заявку по идентификатору")
    public ResponseEntity<RequestResponse> getRequestById(
            @PathVariable @Min(0) @Parameter(description = "Идентификатор заявки", required = true) Integer requestId
    ) {
        RequestResponse response = requestService.getRequest(requestId);
        if (response == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //TODO: user_id потом будем получать из jwt (надеюсь)
    @PostMapping("/{userId}")
    @Operation(summary = "Создание заявки", description = "Позволяет создать заявку")
    public ResponseEntity<RequestResponse> createRequest(
            @PathVariable @Min(0) @Parameter(description = "Идентификатор пользователя", required = true) Integer userId ,
            @Valid @RequestBody RequestRequest request
    ) {
        RequestResponse response = requestService.createRequest(request, userId);
        if (response == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Изменение статуса заявки", description = "Позволяет изменить статус заявки")
    @PutMapping("/status/{requestId}")
    public ResponseEntity<RequestResponse> updateRequestStatus(
            @PathVariable @Parameter(description = "Идентификатор заявки", required = true) Integer requestId,
            @RequestParam("status") @Parameter(description = "Статус") Status status
    ) {
        RequestResponse request = requestService.updateRequestStatus(requestId, status);
        if(request == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(request, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{requestId}")
    @Operation(summary = "Удаление заявки", description = "Позволяет удалить заявку по идентификатору")
    public ResponseEntity<RequestResponse> deleteRequest(
            @PathVariable @Parameter(description = "Идентификатор заявки", required = true) Integer requestId
    ) {
        RequestResponse response = requestService.deleteRequest(requestId);
        if (response == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/transit/")
    @Operation(summary = "Получение всех транзитов", description = "Позволяет получить все транзиты")
    public ResponseEntity<List<TransitResponse>> getTransits() {
        List<TransitResponse> responses = transitService.getAllTransits();
        if (responses.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/transits/{transitId}")
    @Operation(summary = "Получение транзита", description = "Позволяет получить транзит по идентификатору")
    public ResponseEntity<TransitResponse> getTransitById(
            @PathVariable @Parameter(description = "Идентификатор транзита", required = true) Integer transitId
    ){
        TransitResponse response = transitService.getTransitById(transitId);
        if (response == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/transit")
    @Operation(summary = "Создание транзита", description = "Позволяет создать транзит")
    public ResponseEntity<TransitResponse> createTransit(
            @Valid @RequestBody TransitRequest request
    ) {
        TransitResponse response = transitService.createTransit(request);
        if (response == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
