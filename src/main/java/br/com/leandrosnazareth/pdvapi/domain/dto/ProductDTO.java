package br.com.leandrosnazareth.pdvapi.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String barcode;
    private String name;
    private BigDecimal price;
    private int stock;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}