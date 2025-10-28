package com.bcnc.prices.infrastructure.adapter.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);
}
