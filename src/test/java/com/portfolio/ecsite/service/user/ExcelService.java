package com.portfolio.ecsite.service.user;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ExcelService {

    public IDataSet loadExcel(String excelFilePath) throws Exception {
        XlsDataSet xlsDataSet = new XlsDataSet(new File(excelFilePath));
        return new ReplacementDataSet(xlsDataSet);
    }
}
