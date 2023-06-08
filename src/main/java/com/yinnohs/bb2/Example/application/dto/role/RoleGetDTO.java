package com.yinnohs.bb2.Example.application.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleGetDTO {

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("role")
    private String authority;
}
