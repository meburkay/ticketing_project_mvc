package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
//@Component
@Service//Instead of @Component we use @Service. It is the same at the background. The main purpose here is to define that this class is a service class.
public class RoleServiceImpl extends AbstractMapService<RoleDTO,Long> implements RoleService {

    @Override
    public RoleDTO save(RoleDTO role) {

        return super.save(role.getId(),role);
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(RoleDTO object) {
        super.update(object.getId(), object);
    }


}
