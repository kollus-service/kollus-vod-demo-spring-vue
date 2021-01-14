package com.kollus.demo.eduwillvod.security;

import com.kollus.demo.eduwillvod.model.ERole;
import com.kollus.demo.eduwillvod.model.Role;
import com.kollus.demo.eduwillvod.model.User;
import com.kollus.demo.eduwillvod.repository.RoleRepository;
import com.kollus.demo.eduwillvod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PrepareUserData implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

            Role role_admin = new Role();
        if (!roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()) {
            role_admin.setName(ERole.ROLE_ADMIN);
            role_admin = roleRepository.saveAndFlush(role_admin);
        }
            Role role_user = new Role();
        if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
            role_user.setName(ERole.ROLE_USER);
            role_user =roleRepository.saveAndFlush(role_user);
        }

        User admin = new User();
        admin.setUsername("eduwill_admin");
        admin.setEmail("admin@eduwil.net");
        admin.setPassword(passwordEncoder.encode("eduwill1"));
        Set<Role> admin_roles = new HashSet<>();
        admin_roles.add(role_admin);
        admin.setRoles(admin_roles);
        userRepository.saveAndFlush(admin);

        User viewer1 = new User();
        viewer1.setUsername("eduwill_viewer1");
        viewer1.setEmail("viewer1@eduwil.net");
        viewer1.setPassword(passwordEncoder.encode("viewer1"));
        Set<Role> viewer_roles = new HashSet<>();
        viewer_roles.add(role_user);
        viewer1.setRoles(admin_roles);
        userRepository.saveAndFlush(viewer1);

        User viewer2 = new User();
        viewer2.setUsername("eduwill_viewer2");
        viewer2.setEmail("viewer2@eduwil.net");
        viewer2.setPassword(passwordEncoder.encode("viewer2"));
        viewer2.setRoles(viewer_roles);
        userRepository.saveAndFlush(viewer2);

    }
}
