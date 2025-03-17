package com.poly.Controller;

import com.poly.until.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticController {

    @Autowired
    private StatisticService orderStatisticService;

    @GetMapping("/admin/statistics")
    public String viewStatistics(Model model) {
        model.addAttribute("totalSoldProducts", orderStatisticService.getTotalSoldProducts());
        model.addAttribute("bestSellingProduct", orderStatisticService.getBestSellingProduct());
        model.addAttribute("dailyRevenue", orderStatisticService.getDailyRevenue());
        model.addAttribute("weeklyRevenue", orderStatisticService.getWeeklyRevenue());
        model.addAttribute("monthlyRevenue", orderStatisticService.getMonthlyRevenue());
        return "statistics";
    }
}
