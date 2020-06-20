package com.gamepride.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamepride.platform.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long>{

}
