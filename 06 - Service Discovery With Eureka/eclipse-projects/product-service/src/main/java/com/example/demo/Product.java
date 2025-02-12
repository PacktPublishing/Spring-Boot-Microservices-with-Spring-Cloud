package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
private Long productID;
private String productName;
private String productDesc;
private Integer productPrice;
private Boolean productStock;
}
