package org.group.projects.simple.gis.online.service.view;

import org.springframework.web.servlet.ModelAndView;

public interface ViewFactory {

    ModelAndView getRedirectView();

    ModelAndView getView();
}
