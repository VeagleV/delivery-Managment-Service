package com.crm.delivery.core.entities;

import com.crm.delivery.core.enums.Condition;
import com.crm.delivery.core.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "warehouse_id")
    private Integer warehouseId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Column(name = "created_at")
    private Date createdAt;

}
