package ua.goit.util;

import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import ua.goit.entity.ApiResponse;
import ua.goit.entity.Order;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class OrderClientUtil extends HttpUtil<Order> {
    private static final String CREATE_ORDER = "/store/order";
    private static final String READ_ORDER = "/store/order/";
    private static final String READ_ORDER_BY_STATUS = "/store/inventory";
    private static final String DELETE_ORDER = "/store/order/";



    @SneakyThrows
    public static Order createOrder(Order order) {
        HttpRequest request = HttpUtil.requestWithBody("POST",
                String.format("%s%s", HOST, CREATE_ORDER), order);
        HttpResponse<String> response = HttpUtil.getResponse(request);
        return GSON.fromJson(response.body(), Order.class);
    }

    @SneakyThrows
    public static Order getOrderById(int id) {
        HttpRequest request = HttpUtil.sendGet(String.format("%s%s%d", HOST, READ_ORDER, id));
        HttpResponse<String> response = HttpUtil.getResponse(request);
        return GSON.fromJson(response.body(), Order.class);
    }

    @SneakyThrows
    public static ApiResponse delete(int id) {
        HttpRequest request = sendDelete(String.format("%s%s%d", HOST, DELETE_ORDER, id));
        HttpResponse<String> response = HttpUtil.getResponse(request);
        return GSON.fromJson(response.body(), ApiResponse.class);
    }

    @SneakyThrows
    public static HashMap<String, Integer> getInventories() {
        HttpRequest request = HttpUtil.sendGet(String.format("%s%s", HOST, READ_ORDER_BY_STATUS));
        HttpResponse<String> response = HttpUtil.getResponse(request);
        return GSON.fromJson(response.body(), new TypeToken<HashMap<String, Integer>>() {
        }.getType());
    }
}