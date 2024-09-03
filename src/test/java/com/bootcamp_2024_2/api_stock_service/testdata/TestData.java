package com.bootcamp_2024_2.api_stock_service.testdata;

import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import com.pragma_2024_2.api_stock_service.domain.model.Category;

public class TestData {

    public static Category getCategory() {
        return new Category(1L, "Electronics", "Electronic devices");
    }

    public static Brand getBrand() {
        return new Brand(1L, "Samsung", "South Korean multinational conglomerate");
    }

}
