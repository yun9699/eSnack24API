package org.esnack24api.esnack24api.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.order.domain.OrderEntity;
import org.esnack24api.esnack24api.order.domain.OrderItemEntity;
import org.esnack24api.esnack24api.order.dto.CreateOrderDTO;
import org.esnack24api.esnack24api.order.dto.OrderDetailDTO;
import org.esnack24api.esnack24api.order.dto.OrderUserDTO;
import org.esnack24api.esnack24api.order.dto.OrderViewDTO;
import org.esnack24api.esnack24api.order.mapper.OrderMapper;
import org.esnack24api.esnack24api.order.repository.OrderItemRepository;
import org.esnack24api.esnack24api.order.repository.OrderRepository;
import org.esnack24api.esnack24api.product.domain.ProductEntity;
import org.esnack24api.esnack24api.product.repository.ProductRepository;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;

    public Long createOrder(Long uno, CreateOrderDTO createOrderDTO) {

        //tbl_order table part
        UserEntity user = userRepository.findById(uno).orElseThrow();

        int total = 0;

        for (int i = 0; i < createOrderDTO.getPnos().length; i++) {

            ProductEntity product = productRepository.findById(createOrderDTO.getPnos()[i]).orElseThrow();

            int ciqty = createOrderDTO.getCiqtys()[i];

            total = total + product.getPrice() * ciqty;
        }
        BigDecimal total_amount = new BigDecimal(total);

        OrderEntity order = OrderEntity.builder()
                .status("Create")
                .total_amount(total_amount)
                .user(user)
                .currency(createOrderDTO.getCurrency())
                .build();

        Long ono = orderRepository.save(order).getOno();

        //tbl_order_item table part
        for (int i = 0; i < createOrderDTO.getPnos().length; i++) {

            ProductEntity product = productRepository.findById(createOrderDTO.getPnos()[i]).orElseThrow();

            int oiqty = createOrderDTO.getCiqtys()[i];

            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .product(product)
                    .order(order)
                    .oiqty(oiqty)
                    .build();

            orderItemRepository.save(orderItem);
        }

        return ono;
    }

    public OrderViewDTO viewOrder(Long ono) {

        return orderMapper.getOrderView(ono);
    }

    public List<OrderDetailDTO> orderDetail(Long ono) {

        return orderMapper.getOrderDetail(ono);
    }

    public List<OrderUserDTO> orderUsers(Long uno) {

        return orderMapper.getOrderUser(uno);
    }
}
