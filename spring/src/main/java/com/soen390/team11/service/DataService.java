package com.soen390.team11.service;

import com.soen390.team11.dto.DataIncomeExpenseDto;
import com.soen390.team11.dto.DataSummaryDto;
import com.soen390.team11.dto.TopProductDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.repository.CustomerPurchaseRepository;
import com.soen390.team11.repository.InvoiceRepository;
import com.soen390.team11.repository.OrdersRepository;
import com.soen390.team11.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataService {
    CustomerPurchaseRepository customerPurchaseRepository;
    InvoiceRepository invoiceRepository;
    OrdersRepository ordersRepository;
    ProductRepository productRepository;

    public DataService(CustomerPurchaseRepository customerPurchaseRepository, InvoiceRepository invoiceRepository, OrdersRepository ordersRepository,ProductRepository productRepository) {
        this.customerPurchaseRepository = customerPurchaseRepository;
        this.invoiceRepository = invoiceRepository;
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    /**
     * get expense to display in the data tab
     * @return
     */
    public List<DataIncomeExpenseDto> getExpenseReport() {
        List<Map<String, Object>> results= ordersRepository.groupByMonth();
        return extractList(results);
    }

    /**
     * get income to display in the data tab
     * @return
     */
    public List<DataIncomeExpenseDto> getIncomeReport() {
        List<Map<String, Object>> results= invoiceRepository.groupByMonth();
        return extractList(results);
    }

    /**
     * get top 5 product that sale the most to display in data tab
     * @return
     */
    public List<TopProductDto> getTopProduct() {
        TopProductDto topProductDto = null;
        List<Map<String, Object>> topproductlist = customerPurchaseRepository.topProduct();
        List<TopProductDto> topProductDtoList=new ArrayList<>();
        Product product=null;
        for(Map<String, Object> m: topproductlist){
            String productid =m.get("productID").toString();
            product = productRepository.findById(productid).get();
            String description =product.getType()+" "+ product.getName()+", "+product.getSize()+", "+  product.getColor();
            topProductDto= new TopProductDto(description,m.get("total").toString());
            topProductDtoList.add(topProductDto);
        }
        return topProductDtoList;
    }

    /**
     * get all summary detail to display in data tab
     * @return
     */
    public List<DataSummaryDto> getSummary() {
        List<DataSummaryDto> dataSummaryDtoList = new ArrayList<>();
        DataSummaryDto dataSummaryDto = null;
        Map<String, Object > map;
        // get monthly avg income
        map=invoiceRepository.averageCostByMonth();
        dataSummaryDto = new DataSummaryDto("Monthly Avg Income", map.get("average").toString());
        dataSummaryDtoList.add(dataSummaryDto);
        // get monthly avg expense
        map=ordersRepository.averageCostByMonth();
        dataSummaryDto = new DataSummaryDto("Monthly Avg Expense", map.get("average").toString());
        dataSummaryDtoList.add(dataSummaryDto);
        // get total income
        map=invoiceRepository.totalIncome(2021);
        dataSummaryDto = new DataSummaryDto("Total Income", map.get("total").toString());
        dataSummaryDtoList.add(dataSummaryDto);
        // get total expense
        map=ordersRepository.totalIncome(2021);
        dataSummaryDto = new DataSummaryDto("Total Expense", map.get("total").toString());
        dataSummaryDtoList.add(dataSummaryDto);
        // get total product
        map=customerPurchaseRepository.totalProduct();
        dataSummaryDto = new DataSummaryDto("Total # Product sold", map.get("total").toString());
        dataSummaryDtoList.add(dataSummaryDto);
        // get avg product
        map=customerPurchaseRepository.monthlyAverageProduct();
        dataSummaryDto = new DataSummaryDto("Monthly Avg product", map.get("average").toString());
        dataSummaryDtoList.add(dataSummaryDto);
        // get total customer
        map=customerPurchaseRepository.totalCustomer();
        dataSummaryDto = new DataSummaryDto("Total customer", map.get("total").toString());
        dataSummaryDtoList.add(dataSummaryDto);
        return dataSummaryDtoList;
    }

    /**
     * help to extrac the list during income and expense
     * @param results
     * @return
     */
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
