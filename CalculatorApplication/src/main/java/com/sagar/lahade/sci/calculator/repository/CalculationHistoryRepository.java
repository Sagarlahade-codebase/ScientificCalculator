package com.sagar.lahade.sci.calculator.repository;

import com.sagar.lahade.sci.calculator.model.CalculationHistory;
import com.sagar.lahade.sci.calculator.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {
    List<CalculationHistory> findByUser(UserInfo user);
}
