package org.group.projects.simple.gis.service.view;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


@Service
@Slf4j
@NoArgsConstructor
public class SearchViewFactory implements ViewFactory {

    @Override
    public ModelAndView getRedirectView() {
        return new ModelAndView("redirect:/search");
    }

    @Override
    public ModelAndView getView() {
        return new ModelAndView("main")
                .addObject("middle", "search");
    }
}
