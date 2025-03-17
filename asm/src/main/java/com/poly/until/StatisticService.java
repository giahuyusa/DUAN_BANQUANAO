package com.poly.until;

import com.poly.until.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {

    @Autowired
    private StatisticRepository orderStatisticRepository;

    public List<Map<String, Object>> getTotalSoldProducts() {
        return orderStatisticRepository.getTotalSoldProducts();
    }

    public Map<String, Object> getBestSellingProduct() {
        return orderStatisticRepository.getBestSellingProduct();
    }

    public List<Map<String, Object>> getDailyRevenue() {
        return orderStatisticRepository.getDailyRevenue();
    }

    public List<Map<String, Object>> getWeeklyRevenue() {
        return orderStatisticRepository.getWeeklyRevenue();
    }

    public List<Map<String, Object>> getMonthlyRevenue() {
        return orderStatisticRepository.getMonthlyRevenue();
    }
}
