package com.gautama.abscencerecordhitsbackend.api.dto;

import com.gautama.abscencerecordhitsbackend.api.enums.RequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusDTO {
    @NotNull
    RequestStatus status;
}
