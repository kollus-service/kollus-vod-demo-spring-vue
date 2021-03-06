package com.kollus.demo.eduwillvod.repository;

import com.kollus.demo.eduwillvod.model.ERole;
import com.kollus.demo.eduwillvod.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);

}
