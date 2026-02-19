package com.crm.delivery.core.mappers;

import com.crm.delivery.core.dto.ShipmentResponse;
import com.crm.delivery.core.entities.Shipment;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMappers {
    public static ShipmentResponse createShipmentResponse(Shipment shipment) {
        ShipmentResponse response = new ShipmentResponse();
        response.setItemId(shipment.getItemId());
        response.setQuantity(shipment.getQuantity());
        //response.setName(findItemNameByItemId(Integer itemId));
        response.setName(shipment.getItemId().toString());
        return response;
    }
}
