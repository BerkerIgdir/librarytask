package com.freesoft.task.services.implementations;

import com.freesoft.task.entities.Role;
import com.freesoft.task.repositories.RoleRepository;
import com.freesoft.task.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void save(Role role) {

        role.setRole(role.getRole().toUpperCase());

        boolean rectifyBool = role.getRole().startsWith("ROLE_");

        if(rectifyBool){
            roleRepository.save(role);
        }
        else{
            role.setRole("ROLE_".concat(role.getRole()));
            roleRepository.save(role);
        }

    }
}
