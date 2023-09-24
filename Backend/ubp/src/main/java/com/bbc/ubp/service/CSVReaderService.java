package com.bbc.ubp.service;

import com.bbc.ubp.entity.Customer;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import java.io.Reader;
import java.util.List;

@Service
public class CSVReaderService {

    public List<Customer> readCustomerCSVData(Reader reader) {
        CsvToBean<Customer> csvToBean = new CsvToBeanBuilder<Customer>(reader)
                .withType(Customer.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();
    }    

}
