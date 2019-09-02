package org.group.projects.simple.gis.online.service.view;

import org.springframework.web.servlet.ModelAndView;

public interface ViewBuilder {

    ViewBuilder addModelAttibute(String attributeName, Object attributeValue);

    ModelAndView getData();
}
