package com.example.log.repository;

import com.example.log.model.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginOrRegister extends CrudRepository<UserDetails, String> {

    public boolean existsByUsername(String username);
    public UserDetails findByUsername(String username);
    public UserDetails save(UserDetails user);

}
