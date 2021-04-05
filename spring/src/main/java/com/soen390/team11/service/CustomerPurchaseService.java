package com.soen390.team11.service;

import com.soen390.team11.constant.Status;
import com.soen390.team11.dto.CustomerPurchaseDto;
import com.soen390.team11.dto.ProductCustomerOrderDto;
import com.soen390.team11.dto.ProductRequestDto;
import com.soen390.team11.dto.CustomizeReportDto;
import com.soen390.team11.dto.AccountReceivableDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.CustomerPurchase;
import com.soen390.team11.entity.CustomerPurchaseId;
import com.soen390.team11.entity.Invoice;
import com.soen390.team11.entity.Customer;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.entity.ProductInventory;
import com.soen390.team11.repository.CustomerRepository;
import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.repository.ProductRepository;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductMachineryRepository;
import com.soen390.team11.repository.UserAccountRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomerPurchaseService {

    CustomerRepository customerRepository;
    CustomerPurchaseRepository customerPurchaseRepository;
    InvoiceRepository invoiceRepository;
    ProductRepository productRepository;
    ProductInventoryRepository productInventoryRepository;
    ProductMachineryService productMachineryService;
    ProductMachineryRepository productMachineryRepository;
    UserService userService;
    UserAccountRepository userAccountRepository;

    public CustomerPurchaseService(CustomerRepository customerRepository,
        CustomerPurchaseRepository customerPurchaseRepository,
        InvoiceRepository invoiceRepository, ProductRepository productRepository,
        ProductInventoryRepository productInventoryRepository,
        ProductMachineryService productMachineryService,
        ProductMachineryRepository productMachineryRepository, UserService userService,
        UserAccountRepository userAccountRepository) {
        this.customerRepository = customerRepository;
        this.customerPurchaseRepository = customerPurchaseRepository;
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.productInventoryRepository = productInventoryRepository;
        this.productMachineryService = productMachineryService;
        this.productMachineryRepository = productMachineryRepository;
        this.userService = userService;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * service to make the purchase
     *
     * @param customerPurchaseDto
     * @return
     */
    public String makePurchase(CustomerPurchaseDto customerPurchaseDto) {
        Optional<Customer> optionalCustomer = customerRepository
            .findByCustomerID(customerPurchaseDto.getCustomerID());
        if (optionalCustomer.isEmpty()) return "";
        final Invoice invoice = invoiceRepository
            .save(new Invoice(OffsetDateTime.now(), customerPurchaseDto.getTotalamount()));
        List<HashMap<String, Object>> cartList = customerPurchaseDto.getCart();
        CustomerPurchaseId customerPurchaseId;
        CustomerPurchase customerPurchase;
        for (HashMap item : cartList) {
            customerPurchaseId = new CustomerPurchaseId(optionalCustomer.get().getCustomerID(),
                (String) item.get("productid"), invoice.getInvoiceID());
            customerPurchase = new CustomerPurchase(customerPurchaseId, Status.PAID,
                (Integer) item.get("quantity"), userService.getLoggedUser().getUserID());
            customerPurchaseRepository.save(customerPurchase);
        }
        return invoice.getInvoiceID();
    }

    /**
     * check the status
     *
     * @param invoiceId
     * @return
     */
    public List<ProductRequestDto> checkStatus(String invoiceId) {
        List<CustomerPurchase> customerPurchases = customerPurchaseRepository
            .findAllByCustomerPurchaseIdInvoiceID(invoiceId);
        List<ProductRequestDto> responseDtoList = new ArrayList<>();
        ProductRequestDto productRequestDto;
        Product product;
        for (CustomerPurchase cp : customerPurchases) {
            product = productRepository.findById(cp.getCustomerPurchaseId().getProductID()).get();
            productRequestDto = new ProductRequestDto(product.getName(), product.getType(),
                product.getSize(), product.getColor(),
                product.getFinish(), product.getGrade(), product.getPrice(), cp.getStatus());
            responseDtoList.add(productRequestDto);
        }
        return responseDtoList;
    }

    /**
     * get all product
     *
     * @return
     */
    public List<Product> getAllProduct() {
        List<Product> productslist = productRepository.findFirst10BySize("Medium");
        List<Product> finalproductList = new ArrayList<>(productslist);
        productslist = productRepository.findFirst10BySize("Large");
        finalproductList.addAll(productslist);
        productslist = productRepository.findFirst10BySize("Small");
        finalproductList.addAll(productslist);

        return finalproductList;
    }

    /**
     * get customize product
     *
     * @param productRequestDto
     * @return
     */
    public List<Product> getCustomerizeProduct(ProductRequestDto productRequestDto) {
        return productRepository
            .findByNameAndColorAndSizeAndFinish(productRequestDto.getName(),
                productRequestDto.getColor(), productRequestDto.getSize(),
                productRequestDto.getFinish());
    }

    public List<ProductCustomerOrderDto> getAllOrder() {
        List<CustomerPurchase> customerPurchases = (List<CustomerPurchase>) customerPurchaseRepository
            .findAll();
        List<ProductCustomerOrderDto> productCustomerOrderDtoList = new ArrayList<>();
        ProductCustomerOrderDto productCustomerOrderDto;
        Product product;
        for (CustomerPurchase cp : customerPurchases) {
            product = productRepository.findById(cp.getCustomerPurchaseId().getProductID()).get();
            productCustomerOrderDto = new ProductCustomerOrderDto(product.getProductid(),
                product.getName(), product.getSize(), product.getColor(),
                product.getFinish(), product.getGrade(), product.getPrice(), cp.getStatus(),
                cp.getCustomerPurchaseId().getInvoiceID());
            productCustomerOrderDtoList.add(productCustomerOrderDto);
        }
        return productCustomerOrderDtoList;
    }

    public String orderActions(String productid, String invoiceid) {
        ProductInventory productInventory = productInventoryRepository.findByProductid(productid);
        CustomerPurchase customerPurchase = customerPurchaseRepository
            .findByCustomerPurchaseIdInvoiceIDAndCustomerPurchaseIdProductID(invoiceid, productid)
            .get();
        Invoice invoice = invoiceRepository.findByInvoiceID(invoiceid).get();
        if (productInventory != null && productInventory.getQuantity() > customerPurchase
            .getAmount()) {
            customerPurchase.setStatus(Status.SHIPPING);
            customerPurchaseRepository.save(customerPurchase);
            return "Shipping Arrangement";
        } else {
            String result = productMachineryService.occupyMachinery(
                productMachineryService.findAvailableMachinery(), productid);
            if (result != null && result.equals("Success")) {
                return "Product add to machinery";
            }
            return "Not enough inventory to ship, Cannot add to machinery";
        }
    }

    /**
     * get all customer order to display in account tab
     *
     * @return
     */
    public List<AccountReceivableDto> getAllAccountOrder() {
        List<Invoice> invoiceList = (List<Invoice>) invoiceRepository.findAll();
        if (invoiceList.isEmpty()) {
            return new ArrayList<>();
        }
        return extractAccountReceivable(invoiceList);
    }

    /**
     * get customer report within the start and end date, boundary not include
     *
     * @param customizeReportDto
     * @return
     */
    public List<AccountReceivableDto> getCustomizeReport(CustomizeReportDto customizeReportDto) {
        List<Invoice> invoiceList = invoiceRepository.findAllByPurchasedateBetween(
            OffsetDateTime.of(customizeReportDto.getStartDate(), LocalTime.now(), ZoneOffset.UTC),
            OffsetDateTime.of(customizeReportDto.getEndDate(), LocalTime.now(), ZoneOffset.UTC));
        if (invoiceList.isEmpty()) {
            return new ArrayList<>();
        }
        return extractAccountReceivable(invoiceList);
    }

    /**
     * extract the account receiveable DTO
     *
     * @param invoiceList
     * @return
     */
    public List<AccountReceivableDto> extractAccountReceivable(List<Invoice> invoiceList) {
        List<AccountReceivableDto> accountReceivableDtoList = new ArrayList<>();
        List<CustomerPurchase> customerPurchaseList;
        AccountReceivableDto accountReceivableDto;
        UserAccount userAccount;
        for (Invoice invoice1 : invoiceList) {
            customerPurchaseList = customerPurchaseRepository
                .findAllByCustomerPurchaseIdInvoiceID(invoice1.getInvoiceID());
            userAccount = userAccountRepository
                .findByUserID(customerPurchaseList.get(0).getUserid());
            accountReceivableDto = new AccountReceivableDto(invoice1.getPurchasedate(),
                invoice1.getInvoiceID(), userAccount.getUsername(),
                customerPurchaseList.get(0).getUserid(), invoice1.getPaymentamount());
            accountReceivableDtoList.add(accountReceivableDto);
        }
        return accountReceivableDtoList;
    }
}
