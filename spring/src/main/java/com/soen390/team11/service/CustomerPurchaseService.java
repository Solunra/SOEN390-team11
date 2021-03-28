package com.soen390.team11.service;

import com.soen390.team11.constant.Status;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.dto.ProductCustomerOrderDto;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.entity.*;
import com.soen390.team11.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerPurchaseService {

    CustomerRepository customerRepository;
    CustomerPurchaseRepository customerPurchaseRepository;
    InvoiceRepository invoiceRepository;
    ProductRepository productRepository;
    ProductInventoryRepository productInventoryRepository;
    ProductMachineryService productMachineryService;
    ProductMachineryRepository productMachineryRepository;

    public CustomerPurchaseService(CustomerRepository customerRepository, CustomerPurchaseRepository customerPurchaseRepository,
                                   InvoiceRepository invoiceRepository, ProductRepository productRepository, ProductInventoryRepository productInventoryRepository,
                                   ProductMachineryService productMachineryService, ProductMachineryRepository productMachineryRepository) {
        this.customerRepository = customerRepository;
        this.customerPurchaseRepository = customerPurchaseRepository;
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.productInventoryRepository = productInventoryRepository;
        this.productMachineryService = productMachineryService;
        this.productMachineryRepository = productMachineryRepository;
    }

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

    public List<ProductCustomerOrderDto> getAllOrder() {
        List<CustomerPurchase> customerPurchases= (List<CustomerPurchase>) customerPurchaseRepository.findAll();
        List<ProductCustomerOrderDto> productCustomerOrderDtoList= new ArrayList<>();
        ProductCustomerOrderDto productCustomerOrderDto=null;
        Product product=null;
        for(CustomerPurchase cp: customerPurchases){
            product=productRepository.findById(cp.getCustomerPurchaseId().getProductID()).get();
            productCustomerOrderDto=new ProductCustomerOrderDto(product.getProductid(),product.getName(),product.getSize(),product.getColor(),
                    product.getFinish(),product.getGrade(),product.getPrice(),cp.getStatus(),cp.getCustomerPurchaseId().getInvoiceID());
            productCustomerOrderDtoList.add(productCustomerOrderDto);
        }
        return productCustomerOrderDtoList;
    }

    public String orderActions(String productid, String invoiceid) {
        ProductInventory productInventory = productInventoryRepository.findByProductid(productid);
        CustomerPurchase customerPurchase= customerPurchaseRepository.findByCustomerPurchaseIdInvoiceIDAndCustomerPurchaseIdProductID(invoiceid,productid).get();
        Invoice invoice =invoiceRepository.findByInvoiceID(invoiceid).get();
        if(productInventory!=null && productInventory.getQuantity()>customerPurchase.getAmount()){
            customerPurchase.setStatus(Status.SHIPPING);
            customerPurchaseRepository.save(customerPurchase);
            return "Shipping Arrangement";
        }
        else{
            System.out.println("not enought invenotry");
            String result = productMachineryService.occupyMachinery(
                productMachineryService.findAvailableMachinery(), productid);
            System.out.println(result != null && result.equals("Success"));
            System.out.println(result);
            if (result != null && result.equals("Success")){
                customerPurchase.setStatus(Status.IN_MACHINERY);
                customerPurchaseRepository.save(customerPurchase);
                return "Product add to machinery";
            }
            return "Not enough inventory to ship, Cannot add to machinery";
        }
    }
}
