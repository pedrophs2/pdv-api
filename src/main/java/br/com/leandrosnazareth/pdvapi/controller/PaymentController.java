package br.com.leandrosnazareth.pdvapi.controller;

import br.com.leandrosnazareth.pdvapi.domain.dto.PaymentDTO;
import br.com.leandrosnazareth.pdvapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandrosnazareth.pdvapi.config.SpringFoxConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/api/pdv/payment")
@Api(tags = { SpringFoxConfig.PAYMENT_TAG })
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @ApiOperation(value = "Listar formas de pagamentos")
    @GetMapping
    public List<PaymentDTO> findAllActive() {
        return paymentService.getAvailablePaymentMethods();
    }
}
