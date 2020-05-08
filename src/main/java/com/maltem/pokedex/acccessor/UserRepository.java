package com.maltem.pokedex.acccessor;

import com.maltem.pokedex.datamodels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailId(String emailId);
}

