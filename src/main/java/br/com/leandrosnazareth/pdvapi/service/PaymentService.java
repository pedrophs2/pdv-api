package br.com.leandrosnazareth.pdvapi.service;

import br.com.leandrosnazareth.pdvapi.domain.model.Payment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    
    public List<String> getAvailablePaymentMethods() {
        Payment[] payments = Payment.values();
        List<String> availablePaymentsTitle = new ArrayList<>();

        for(Payment payment: payments) {
            availablePaymentsTitle.add(payment.getDescricao());
        }

        return availablePaymentsTitle;
    }
    
}
