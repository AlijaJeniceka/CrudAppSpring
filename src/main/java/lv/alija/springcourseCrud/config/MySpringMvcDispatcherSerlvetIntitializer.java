package lv.alija.springcourseCrud.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class MySpringMvcDispatcherSerlvetIntitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        @Override
        protected Class<?>[] getRootConfigClasses() {
            return null;
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class[]{SpringConfig.class};
        }

        @Override
        protected String[] getServletMappings() {
            return new String[]{"/"};
        }

        //Filters configuration, that is needed for spring to see hidden post method. With springboot it will take only one
    // string of the code to use it in configuration file.
        @Override
        public void onStartup(ServletContext aServletContext) throws ServletException {
            super.onStartup(aServletContext);
            registerHiddenFieldFilter(aServletContext);

        }
        private void registerHiddenFieldFilter(ServletContext aContext){
            aContext.addFilter("hiddenHttpMethodFilter",
                    new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*" );

        }


}
