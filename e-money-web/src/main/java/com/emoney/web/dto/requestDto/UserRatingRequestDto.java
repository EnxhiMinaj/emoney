package com.emoney.web.dto.requestDto;

import com.emoney.core.model.RequestDtoBase;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRatingRequestDto extends RequestDtoBase {
    private Integer posterReview;
    private Integer workerReview;
    private Long posterId;
    private Long transactionId;
    private Long workerId;

}
