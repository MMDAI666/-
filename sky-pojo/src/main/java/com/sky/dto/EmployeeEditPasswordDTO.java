package com.sky.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeEditPasswordDTO {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
