package io.kenxue.devops.application.common.assembler;

import java.util.List;

import io.kenxue.devops.coreclient.dto.common.page.Page;


/**
 * @Author: haolongz
 * @Date: 21-11-26 下午9:48
 */
public interface Assembler<DTO, Domain> {
    DTO toDTO(Domain dO);
    Domain toDomain(DTO dTO);
    List<DTO> toDTOList(List<Domain> domainList);
    List<Domain> toDomainList(List<DTO> dTOList);
    Page<DTO> toDTOPage(Page<Domain> page);
}
