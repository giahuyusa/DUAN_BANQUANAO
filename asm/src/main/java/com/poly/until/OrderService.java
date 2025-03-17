package com.poly.until;

import com.poly.model.Orders;
import com.poly.until.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    // Lấy danh sách tất cả đơn hàng
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Lấy đơn hàng theo ID
    public Orders getOrderById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    // Cập nhật trạng thái đơn hàng
    public void updateOrderStatus(Long id, String status) {
        Orders order = ordersRepository.findById(id).orElse(null);
        if (order != null) {
            int statusInt = Integer.parseInt(status); // Chuyển đổi từ String sang int
            order.setStatus(statusInt);
            ordersRepository.save(order);
        }
    }

}
