package am.shopfx.web.controller;

import am.shopfx.core.entity.SpOrder;
import am.shopfx.core.pojo.Cart;
import am.shopfx.core.pojo.ResponseData;
import am.shopfx.core.response.ResponseDataResult;
import am.shopfx.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@ResponseDataResult
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("orderToken")
    public ResponseData<String> getOrderToken() {
        return orderService.getOrderToken();
    }

    @PostMapping
    public ResponseData<SpOrder> newOrder(@RequestBody Cart cart) {
        return orderService.newOrder(cart);
    }

    @GetMapping("{orderId}")
    public ResponseData<SpOrder> getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("paid/{id}")
    public ResponseData<SpOrder> getPaidOrder(@PathVariable String id) throws ExecutionException, InterruptedException {
        return orderService.getPaidOrder(id).get();
    }
}
