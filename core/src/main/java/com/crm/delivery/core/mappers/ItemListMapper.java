package com.crm.delivery.core.mappers;

import com.crm.delivery.core.dto.ItemListRequest;
import com.crm.delivery.core.dto.ShipmentResponse;
import com.crm.delivery.core.dto.SpanResponse;
import com.crm.delivery.core.entities.Shipment;
import org.springframework.stereotype.Component;

@Component
public class ItemListMapper {
    public static ItemListRequest createItemListDecResponse(SpanResponse spanResponse) {
        return ItemListRequest.builder()
                .itemId(spanResponse.getItemId())
                .warehouseId(spanResponse.getWarehouseId())
                .quantity(-spanResponse.getItemQuantity())
                .build();
    }

    public static ItemListRequest createItemListIncResponse(SpanResponse spanResponse) {
        return ItemListRequest.builder()
                .itemId(spanResponse.getItemId())
                .warehouseId(spanResponse.getWarehouseId())
                .quantity(spanResponse.getItemQuantity())
                .build();
    }


}
