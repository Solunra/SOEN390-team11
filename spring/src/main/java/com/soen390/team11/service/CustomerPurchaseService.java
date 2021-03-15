package com.soen390.team11.service;

import com.soen390.team11.constant.Status;
import com.soen390.team11.dto.CheckStatusResponseDto;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.entity.*;
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
        // create customer
        // create invoice
        // relation product invoice customer
        Customer customer= customerRepository.save(customerPurchaseDto.getCustomer());// id of the customer
        Invoice invoice=invoiceRepository.save(new Invoice(OffsetDateTime.now(),customerPurchaseDto.getTotalamount()));
//        VendorSaleId vendorSaleId = new VendorSaleId(rawMaterialRequestDto.getVendorID(), rawMaterialidID);
//        VendorSale vendorSale = new VendorSale(vendorSaleId, Type.RAW_MATERIAL);
//        String customerID, String productID, String invoiceID
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
    public List<CheckStatusResponseDto> checkStatus(String invoiceId){
        List<CustomerPurchase> customerPurchases= customerPurchaseRepository.findAllByCustomerPurchaseIdInvoiceID(invoiceId);
        List<CheckStatusResponseDto> responseDtoList= new ArrayList<>();
        CheckStatusResponseDto checkStatusResponseDto=null;
        Product product=null;
        for(CustomerPurchase cp: customerPurchases){
            product=productRepository.findById(cp.getCustomerPurchaseId().getProductID()).get();
            checkStatusResponseDto=new CheckStatusResponseDto(product.getName(),cp.getStatus());
            responseDtoList.add(checkStatusResponseDto);
        }
        return responseDtoList;
    }

}
