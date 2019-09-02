package org.group.projects.simple.gis.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.projects.simple.gis.api.model.entity.Building;
import org.group.projects.simple.gis.api.model.exception.ExceptionDetail;
import org.group.projects.simple.gis.api.model.transport.SearchRequest;
import org.group.projects.simple.gis.api.model.transport.SearchResult;
import org.group.projects.simple.gis.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.context.IContext;
import springfox.documentation.service.OAuth;

@Api(
        value="SearchController",
        description="Контроллер возвращающий гео информациоцию по строке запроса"
)
@Slf4j
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "/**")
@RequestMapping(value = "search")
@RestController
public class SearchController {

    @Autowired
    private SearchService service;

    @ApiOperation(
            value = "Найти адрес",
            notes = "Метод принимает POST запросы на выборку гео информации по строке запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Запрос был успешно обработан",
                    response = SearchResult.class
            ),
            @ApiResponse(
                    code = 400,
                    message = "Запрос не был обработан - возможно не валидные данные",
                    response = ExceptionDetail.class
            )
    })
    @PostMapping(
            path = "/find",
            consumes = "application/json",
            produces = "application/json"
    )
    public SearchResult find(@RequestBody SearchRequest request) {

        return service.getResult(request, 5);
    }
}



