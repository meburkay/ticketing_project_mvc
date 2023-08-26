package com.cydeo.converter;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//@ConfigurationPropertiesBinding //Spring understand this and do it automatically. We don't need to use this annotations anymore.
@Component
public class ProjectDtoConverter implements Converter<String, ProjectDTO> {

    ProjectService projectService;

    public ProjectDtoConverter(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ProjectDTO convert(String source) {

        //I did this for dropdown button. If the user choose select it return null and because of @Notnull validation it gives warning at the screen.
        if (source == null || source.equals("")) {
            return null;
        }

        return projectService.findById(source);
    }
}
