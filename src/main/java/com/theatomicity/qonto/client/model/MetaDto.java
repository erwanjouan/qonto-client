package com.theatomicity.qonto.client.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MetaDto {
    private Integer currentPage;
    private Integer nextPage;
    private Integer prevPage;
    private Integer totalPages;
    private Integer totalCount;
    private Integer perPage;
}
