package org.group.projects.simple.gis.online.service.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


@Service
@Slf4j
@NoArgsConstructor
public class SearchViewBuilder implements ViewBuilder {

    @Autowired
    SearchViewFactory factory;

    @Getter
    private ModelAndView data;

    public SearchViewBuilder getView() {
        this. data = factory.getView();
        return this;
    }

    public SearchViewBuilder getRedirectView() {
        this. data = factory.getRedirectView();
        return this;
    }

    @Override
    public SearchViewBuilder addModelAttibute(String attributeName, Object attributeValue) {
        this.data.addObject(attributeName, attributeValue);
        return this;
    }
}
