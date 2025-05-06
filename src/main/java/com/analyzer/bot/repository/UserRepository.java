package com.analyzer.bot.repository;

import com.analyzer.bot.entity.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<BotUser, UUID> {

}
