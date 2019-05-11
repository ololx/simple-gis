package org.group.projects.simple.gis.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Service
@Slf4j
@NoArgsConstructor
public class SearchModelAndViewBuilder {

    @Getter
    private ModelAndView data;

    {
        data = new ModelAndView();
    }

    public SearchModelAndViewBuilder setView(String viewName) {
        this.data.setViewName(viewName);
        return this;
    }

    public SearchModelAndViewBuilder addModelAttibute(String attributeName, Object attributeValue) {
        this.data.addObject(attributeName, attributeValue);
        return this;
    }
}
