package com.ky.flowable.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-01 17:30
 */
@Data
public class ActIdGroupUpdateDTO {

    @NotBlank
    private String groupId;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
}
