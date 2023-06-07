package com.yinnohs.bb2.Example.application.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeactivateItemDTO {
    @JsonProperty("item_id")
    private Long itemId;

    @JsonProperty("deactivate_reason")
    private String deactivateReason;

    @JsonProperty("deactivated_by_id")
    private Long deactivatedById;
}
