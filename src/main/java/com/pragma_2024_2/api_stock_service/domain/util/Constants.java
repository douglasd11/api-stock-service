package com.pragma_2024_2.api_stock_service.domain.util;

public final class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME = "name";

    public static final String ORDER_ASC = "asc";
    public static final String ORDER_DESC = "desc";

    public static final String FIELD_NAME_NULL_MESSAGE = "The field name cannot be null";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "The field description cannot be null";

    public static final String FIELD_NAME_EXCEEDS_SIZE_MESSAGE = "The field name exceeded the maximum size";
    public static final String FIELD_DESCRIPTION_EXCEEDS_SIZE_MESSAGE = "The field description exceeded the maximum size";

}
