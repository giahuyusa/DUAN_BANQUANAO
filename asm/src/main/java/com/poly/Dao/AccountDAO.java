package com.poly.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.Accounts;

@Repository
public interface AccountDAO extends JpaRepository<Accounts, String> {
    Accounts findByUsername(String username);
    Accounts findByEmail(String email);
}
