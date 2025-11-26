package br.com.leandrosnazareth.pdvapi.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductSoldDTO {

    private Long id;
    private ProductDTO product;
    private BigDecimal price;
    private BigDecimal priceTotal;
    private int quantity;
}
