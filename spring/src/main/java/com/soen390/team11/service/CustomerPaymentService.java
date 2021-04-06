package com.soen390.team11.service;

import com.soen390.team11.dto.CustomerPaymentDto;
import com.soen390.team11.entity.Payment;
import com.soen390.team11.repository.PaymentRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class CustomerPaymentService {

    PaymentRepository paymentRepository;
    BCrypt bCrypt;
    UserService userService;

    public CustomerPaymentService(PaymentRepository paymentRepository, BCrypt bCrypt,UserService userService) {
        this.paymentRepository = paymentRepository;
        this.bCrypt = bCrypt;
        this.userService=userService;
    }

    /**
     * create payment
     * @param customerPaymentDto address information
     * @return Payment
     */
    public Payment createPayment(CustomerPaymentDto customerPaymentDto) {
        // encrypt
        String salt = bCrypt.gensalt();
        Payment payment = new Payment(bCrypt.hashpw( customerPaymentDto.getType(),salt),customerPaymentDto.getCardName(),
                bCrypt.hashpw(customerPaymentDto.getCardName(),salt),bCrypt.hashpw(customerPaymentDto.getExpireDate(),salt),
                bCrypt.hashpw(customerPaymentDto.getCvc(),salt),userService.getLoggedUser().getUserID());
        return paymentRepository.save(payment);
    }

    /**
     * update payment
     * @param customerPaymentDto address information
     * @return payment id
     */
    public String updatePayment(CustomerPaymentDto customerPaymentDto, String paymentId) {
        Optional<Payment> optionalPayment = paymentRepository
                .findByPayId(paymentId);
        Payment payment=null;
        if (optionalPayment.isPresent()){
             String salt = bCrypt.gensalt();
             payment= new Payment(bCrypt.hashpw(customerPaymentDto.getType(), salt),customerPaymentDto.getCardName(),
                    bCrypt.hashpw(customerPaymentDto.getCardName(),salt),bCrypt.hashpw(customerPaymentDto.getExpireDate(),salt),
                    bCrypt.hashpw(customerPaymentDto.getCvc().toString(),salt),userService.getLoggedUser().getUserID());
             payment.setPayId(paymentId);
             return paymentRepository.save(payment).getPayId();
        } else {
            return "Customer address is not found! ";
        }
    }

    /**
     * delete payment by their id
     * @param payid
     * @return
     */
    public String deletePaymentById(String payid) {
        paymentRepository.deleteByPayId(payid);
        return "success";
    }


}
