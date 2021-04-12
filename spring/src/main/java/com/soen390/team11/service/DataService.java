package com.soen390.team11.service;

import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataService {
    CustomerPurchaseRepository customerPurchaseRepository;
    InvoiceRepository invoiceRepository;
    OrdersRepository ordersRepository;

    public DataService(CustomerPurchaseRepository customerPurchaseRepository, InvoiceRepository invoiceRepository, OrdersRepository ordersRepository) {
        this.customerPurchaseRepository = customerPurchaseRepository;
        this.invoiceRepository = invoiceRepository;
        this.ordersRepository = ordersRepository;
    }


    public List<Map<String, String>> getExpenseReport() {
        return ordersRepository.groupByMonth();
    }

    public List<Map<String, String>> getIncomeReport() {
        return invoiceRepository.groupByMonth();
    }

    public List<Map<String, String>> getTopProduct() {
        return customerPurchaseRepository.topProduct();
    }

    public List<Map<String, String>> getSummary() {
        return null;
    }
}
