package com.bcnc.prices.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bcnc.prices.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	
	@Query("""
		       SELECT p
		       FROM Price p
		       WHERE p.productId = :productId
		         AND p.brandId = :brandId
		         AND p.startDate <= :applicationDate
		         AND p.endDate >= :applicationDate
		       ORDER BY p.priority DESC
		       """)
	List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate);
}
