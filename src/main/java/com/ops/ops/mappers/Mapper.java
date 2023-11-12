package com.ops.ops.mappers;

public interface Mapper<DTO_CLASS, ENTITY_CLASS> {

    ENTITY_CLASS toEntity(DTO_CLASS domainClass);

    DTO_CLASS toDto(ENTITY_CLASS entityClass);

}
