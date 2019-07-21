package org.group.projects.simple.gis.online.model.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDetail {

    Date timestamp;

    String comment;

    String message;

    String details;

    String status;
}
