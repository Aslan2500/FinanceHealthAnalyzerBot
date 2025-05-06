package com.analyzer;

import com.analyzer.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<BotUser, UUID> {

}
