package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.*;
import com.crm.delivery.core.entities.Request;
import com.crm.delivery.core.enums.Condition;
import com.crm.delivery.core.enums.Status;
import com.crm.delivery.core.mappers.ItemListMapper;
import com.crm.delivery.core.mappers.RequestMappers;
import com.crm.delivery.core.repositories.RequestRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableFeignClients
public class RequestService {
    private final RequestRepository requestRepository;
    private final ShipmentService shipmentService;
    private final AlgoService algoService;
    private final TransportService transportService;
    private final ItemListService itemListService;

    @Autowired
    public RequestService(RequestRepository requestRepository, ShipmentService shipmentService, AlgoService algoService, TransportService transportService, ItemListService itemListService) {
        this.requestRepository = requestRepository;
        this.shipmentService = shipmentService;
        this.algoService = algoService;
        this.transportService = transportService;
        this.itemListService = itemListService;
    }

    // GET-запросы
    public RequestResponse getRequest(Integer id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isEmpty()) return null;
        return request.stream()
                .map(RequestMappers::createRequestResponse)
                .findFirst()
                .orElse(null);
    }

    public List<RequestResponse> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) return null;
        return  requests.stream()
                .map(RequestMappers::createRequestResponse)
                .collect(Collectors.toList());
    }

    //POST-запросы
    public RequestResponse createRequest(Integer userId, Integer warehouseId, Condition condition, MultipartFile file) {

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<ShipmentRequest> shipmentRequestList = processFile(workbook);

        RequestRequest requestRequest = RequestRequest.builder()
                .warehouseId(warehouseId)
                .condition(condition)
                .shipments(shipmentRequestList)
                .build();

        Request createdRequest = requestRepository.save(RequestMappers.createRequest(requestRequest, userId));
        List<ShipmentRequest> shipments = requestRequest.getShipments();
        for(ShipmentRequest shipmentRequest : shipments) {
            shipmentService.createShipment(shipmentRequest, createdRequest);
        }

        AlgoRequest algoRequest = AlgoRequest.builder()
                .requestId(createdRequest.getId())
                .warehouseId(requestRequest.getWarehouseId())
                .condition(requestRequest.getCondition())
                .shipmentRequestList(requestRequest.getShipments())
                .build();

        algoService.startAlgorithm(algoRequest);

        return RequestMappers.createRequestResponse(createdRequest);
    }

    //PUT
    public RequestResponse updateRequestStatus(Integer requestId, Status status) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if(request == null) return null;
        request.setStatus(status);
        Request puttedRequests = requestRepository.save(request);
        return RequestMappers.createRequestResponse(puttedRequests);
    }

    //DELETE
    public RequestResponse deleteRequest(Integer requestId) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if(request == null) return null;
        requestRepository.delete(request);
        return RequestMappers.createRequestResponse(request);
    }

    public List<ShipmentRequest> processFile(XSSFWorkbook file) {
        Sheet shipmentsSheet = file.getSheetAt(0);
        List<ShipmentRequest> shipments = new ArrayList<>();

        for (int i = 1; i <= shipmentsSheet.getLastRowNum(); i++) {
            Row row = shipmentsSheet.getRow(i);

            ShipmentRequest shipment = new ShipmentRequest();
            shipment.setItemId((int) row.getCell(0).getNumericCellValue());
            shipment.setQuantity((int) row.getCell(1).getNumericCellValue());
            System.out.println(row.getCell(0).getNumericCellValue());
            System.out.println(row.getCell(1).getNumericCellValue());

            shipments.add(shipment);
        }

        return shipments;
    }

    public List<RequestResponse> getAllRequestsByUserId(Integer userId) {
        List<Request> requests = requestRepository.findAllByUserId(userId);
        if (requests.isEmpty()) return null;
        return  requests.stream()
                .map(RequestMappers::createRequestResponse)
                .collect(Collectors.toList());
    }

    public void confirmRequest(Integer routeId) {
        List<SpanResponse> spanResponseList = algoService.getSpanByRouteId(routeId).getBody();
        List<ItemListRequest> changeQuantity = spanResponseList.stream().map(ItemListMapper::createItemListDecResponse).toList();
        itemListService.changeItemQuantity(changeQuantity);
        List<Integer> transports = spanResponseList.stream().map(SpanResponse::getTransportId).toList();
        transportService.setInTransitStatus(transports);
    }

    public void confirmTransit(Integer routeId) {
        List<SpanResponse> spanResponseList = algoService.getSpanByRouteId(routeId).getBody();
        List<ItemListRequest> changeQuantity = spanResponseList.stream().map(ItemListMapper::createItemListIncResponse).toList();
        itemListService.changeItemQuantity(changeQuantity);
        List<Integer> transports = spanResponseList.stream().map(SpanResponse::getTransportId).toList();
        transportService.setGoBackStatus(transports);
    }

}
