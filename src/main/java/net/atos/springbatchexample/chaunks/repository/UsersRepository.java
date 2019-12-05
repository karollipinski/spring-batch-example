package net.atos.springbatchexample.chaunks.repository;

import net.atos.springbatchexample.chaunks.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
