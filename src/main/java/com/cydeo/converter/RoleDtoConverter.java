package com.cydeo.converter;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String, RoleDTO> {
//When we have an object at the view and there is another object in that object we have an error about the second object. To solve this problem we use converter. This is a tyhmeleaf issue. We emplement our class with Converter like above. When the app have an issue like this: Cannot convert value of type 'java.lang.String' to required type 'com.cydeo.dto.RoleDTO' for property 'role', it automatically look for converters because of the @ConfigurationPropertiesBinding annotation and if it finds a suitable method like below it convert and solve the issue by this way.
    RoleService roleService;

    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {
        return roleService.findById(Long.parseLong(source));
    }
}
