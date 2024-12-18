package am.shopfx.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import am.shopfx.core.Cache;
import am.shopfx.core.Const;
import am.shopfx.core.entity.SpOrder;
import am.shopfx.core.pojo.Cart;
import am.shopfx.core.response.ResponseDataResult;
import am.shopfx.order.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseDataResult
public class OrderController {

    private final OrderService orderService;

    public final Cache cache;

    /**
     * to prevent duplicate submissions
     * client submit order with a token
     * server verify before create order
     * @return
     */
    @GetMapping("order/token")
    public String getOrderToken() {
        return orderService.getOrderToken();
    }

    @PostMapping("order")
    public SpOrder newOrder(@RequestBody Cart cart) {
        try {
            return orderService.newOrder(cart);
        } catch (Throwable e) {
            cache.delete(Const.TOKEN_ORDER_PREFIX + cart.getOrderToken());
            throw e;
        }
    }

    @GetMapping("order/{id}")
    public SpOrder getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @GetMapping("order/unpaid")
    public List<SpOrder> getUnpaidOrders(String uid) {
        return orderService.getUnpaidOrders(uid);
    }

    @GetMapping("order/paid/{id}")
    public SpOrder getPaidOrder(@PathVariable String id) {
        return orderService.getPaidOrder(id);
    }

    @GetMapping("order/toPay/{id}")
    public SpOrder getToPayOrder(@PathVariable String id) {
        return orderService.getToPayOrder(id);
    }
}
