package com.arek.warehousetransfer.user.Role;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("roles")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @GetMapping("all")
    public Set<Role> getAllRoles(){
        return roleService.findAllRoles();
    }

}
