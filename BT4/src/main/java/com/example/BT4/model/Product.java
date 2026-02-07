package com.example.BT4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    private String productCode;
    
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 1, max = 100, message = "Tên sản phẩm phải từ 1 đến 100 ký tự")
    private String productName;
    
    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá phải từ 1 đến 9999999")
    @Max(value = 9999999, message = "Giá phải từ 1 đến 9999999")
    private Long price;
    
    private String image;
    
    @NotBlank(message = "Vui lòng chọn danh mục")
    private String category;
}
