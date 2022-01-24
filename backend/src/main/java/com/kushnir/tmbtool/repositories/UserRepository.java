package com.kushnir.tmbtool.repositories;

import com.kushnir.tmbtool.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
