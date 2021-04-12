package com.soen390.team11.service;

import com.soen390.team11.dto.DataIncomeExpenseDto;
import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<DataIncomeExpenseDto> getExpenseReport() {
        List<Map<String, Object>> results= ordersRepository.groupByMonth();
        return extractList(results);
    }

    public List<DataIncomeExpenseDto> getIncomeReport() {
        List<Map<String, Object>> results= invoiceRepository.groupByMonth();
        return extractList(results);
    }

    public List<Map<String, String>> getTopProduct() {
        return customerPurchaseRepository.topProduct();
    }

    public List<Map<String, String>> getSummary() {
        return customerPurchaseRepository.topProduct();
    }

    public List<DataIncomeExpenseDto> extractList(List<Map<String, Object>> results){
        DataIncomeExpenseDto dataIncomeExpenseDto = null;
        List<DataIncomeExpenseDto> dataIncomeExpenseDtoList = new ArrayList<>();
        List<String> existingMonth = new ArrayList<>();
        for(Map<String, Object> m: results){
            dataIncomeExpenseDto = new DataIncomeExpenseDto();
            existingMonth.add(m.get("month").toString());
            dataIncomeExpenseDto.setMonth(Integer.parseInt(m.get("month").toString()) );
            dataIncomeExpenseDto.setAmount(m.get("PRICE").toString());
            dataIncomeExpenseDtoList.add(dataIncomeExpenseDto);
        }
        for(int i=1;i<=12;i++){
            if(!existingMonth.contains(i+"")){
                dataIncomeExpenseDto = new DataIncomeExpenseDto();
                dataIncomeExpenseDto.setMonth(i);
                dataIncomeExpenseDto.setAmount("0");
                dataIncomeExpenseDtoList.add(dataIncomeExpenseDto);
            }
        }
        return dataIncomeExpenseDtoList;
    }

}
