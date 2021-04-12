package com.soen390.team11.service;

import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<Object> getExpenseReport() {
        return null;
    }

    public List<Object> getIncomeReport() {
        return null;
    }

    public List<Object> getTopProduct() {
        return null;
    }

    public List<Object> getSummary() {
        return null;
    }
}
