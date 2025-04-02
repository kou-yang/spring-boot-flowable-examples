package com.ky.flowable.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: kouyang
 * @description:
 * @date: 2025-04-02 15:00
 */
@Data
public class ActIdUserUpdateDTO {

    @NotBlank
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
