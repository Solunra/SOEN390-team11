package com.soen390.team11.service;

import com.soen390.team11.constant.Status;
import com.soen390.team11.dto.CheckStatusResponseDto;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.*;
import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.CustomerRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    /**
     * service to make the purchase
     * @param customerPurchaseDto
     * @return
     */
    public String makePurchase(CustomerPurchaseDto customerPurchaseDto) {
        final Customer customer= customerRepository.save(customerPurchaseDto.getCustomer());// id of the customer
        final Invoice invoice=invoiceRepository.save(new Invoice(OffsetDateTime.now(),customerPurchaseDto.getTotalamount()));
        List<HashMap<String, Object>> carte= customerPurchaseDto.getCarte();
        CustomerPurchaseId customerPurchaseId=null;
        CustomerPurchase customerPurchase=null;
        for(HashMap hm: carte){
            customerPurchaseId = new CustomerPurchaseId(customer.getCustomerID(), (String) hm.get("productid"),invoice.getInvoiceID());
            customerPurchase=new CustomerPurchase(customerPurchaseId, Status.PAID, (Integer) hm.get("quantity"));
            customerPurchaseRepository.save(customerPurchase);
        }
        return invoice.getInvoiceID();
    }

    /**
     * check the status
     * @param invoiceId
     * @return
     */
    public List<ProductRequestDto> checkStatus(String invoiceId){
        List<CustomerPurchase> customerPurchases= customerPurchaseRepository.findAllByCustomerPurchaseIdInvoiceID(invoiceId);
        List<ProductRequestDto> responseDtoList= new ArrayList<>();
        ProductRequestDto productRequestDto=null;
        Product product=null;
        for(CustomerPurchase cp: customerPurchases){
            product=productRepository.findById(cp.getCustomerPurchaseId().getProductID()).get();
            productRequestDto=new ProductRequestDto(product.getName(),product.getType(),product.getSize(),product.getColor(),
                    product.getFinish(),product.getGrade(),product.getPrice(),cp.getStatus());
            responseDtoList.add(productRequestDto);
        }
        return responseDtoList;
    }

    /**
     * get all product
     * @return
     */
    public List<Product> getAllProduct(){
        List<Product> finalproductList = new ArrayList<>();
        List<Product> productslist = productRepository.findFirst10BySize("Medium");
        finalproductList.addAll(productslist);
        productslist = productRepository.findFirst10BySize("Large");
        finalproductList.addAll(productslist);
        productslist = productRepository.findFirst10BySize("Small");
        finalproductList.addAll(productslist);
        return finalproductList;
    }

    /**
     * get customize product
     * @param productRequestDto
     * @return
     */
    public List<Product> getCustomerizeProduct(ProductRequestDto productRequestDto){
        List<Product> customizeProduct=productRepository.findByNameAndColorAndSizeAndFinish(productRequestDto.getName(), productRequestDto.getColor(), productRequestDto.getSize(), productRequestDto.getFinish());
        for(Product p: customizeProduct){
            System.out.println(p);
        }
        return customizeProduct;
    }
}
