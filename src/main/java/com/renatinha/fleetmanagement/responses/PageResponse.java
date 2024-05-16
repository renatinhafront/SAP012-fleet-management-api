package com.renatinha.fleetmanagement.responses;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

//builder = construção do obj de forma facilitada
@Builder
@Getter
public class PageResponse {
    private Long totalElements;
    private Integer totalPages;
    private Iterable content;
}
