package com.rtsoju.dku_council_homepage.domain.token.repository;

import com.rtsoju.dku_council_homepage.domain.token.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    Optional<ConfirmationToken> findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId, LocalDateTime now, boolean expired);
}
