package br.com.leandrosnazareth.pdvapi.service;

import br.com.leandrosnazareth.pdvapi.domain.dto.PaymentDTO;
import br.com.leandrosnazareth.pdvapi.domain.model.Payment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    
    public List<PaymentDTO> getAvailablePaymentMethods() {
        Payment[] payments = Payment.values();
        List<PaymentDTO> availablePayments = new ArrayList<>();

        for(Payment payment: payments) {
            PaymentDTO availablePayment = new PaymentDTO();
            availablePayment.setLabel(payment.getDescricao());
            availablePayment.setName(payment.name());

            availablePayments.add(availablePayment);
        }

        return availablePayments;
    }
    
}
