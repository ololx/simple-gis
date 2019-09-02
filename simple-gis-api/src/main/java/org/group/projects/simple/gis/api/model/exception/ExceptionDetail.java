package org.group.projects.simple.gis.api.model.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(
        value = "ErrorDetails",
        description = "Детализация ошибки"
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionDetail {

    @ApiModelProperty(notes = "Дата возникновения")
    Date timestamp;

    @ApiModelProperty(notes = "Комментарий к исключению")
    String comment;

    @ApiModelProperty(notes = "Сообщение исключения")
    String message;

    @ApiModelProperty(notes = "Детализация запроса")
    String details;

    @ApiModelProperty(notes = "Статус респонса")
    String status;
}
