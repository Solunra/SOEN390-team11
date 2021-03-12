package com.soen390.team11.service;

import com.soen390.team11.constant.Status;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.entity.Customer;
import com.soen390.team11.entity.CustomerPurchase;
import com.soen390.team11.entity.CustomerPurchaseId;
import com.soen390.team11.entity.Invoice;
import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.CustomerRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomerPurchaseService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerPurchaseRepository customerPurchaseRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;
    public String makePurchase(CustomerPurchaseDto customerPurchaseDto) {
        Customer customer= customerRepository.save(customerPurchaseDto.getCustomer());// id of the customer
        Invoice invoice=invoiceRepository.save(new Invoice(OffsetDateTime.now(),customerPurchaseDto.getTotalamount()));
        List<HashMap<String, Object>> carte= customerPurchaseDto.getCarte();
        CustomerPurchaseId customerPurchaseId=null;
        CustomerPurchase customerPurchase=null;
        for(HashMap hm: carte){
            customerPurchaseId = new CustomerPurchaseId(customer.getCustomerID(), (String) hm.get("productid"),invoice.getInvoiceID());
            customerPurchase=new CustomerPurchase(customerPurchaseId, Status.PENDING, (Integer) hm.get("quantity"));
            customerPurchaseRepository.save(customerPurchase);
        }
        return invoice.getInvoiceID();
    }


}
