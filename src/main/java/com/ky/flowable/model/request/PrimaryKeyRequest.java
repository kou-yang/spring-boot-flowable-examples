package com.ky.flowable.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ky
 * @description 主键id
 * @date 2024-04-22 17:33
 */
@Data
public class PrimaryKeyRequest {

    @NotBlank
    private String id;
}
