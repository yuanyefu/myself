package com.docker.yyf.manage;

import com.docker.yyf.ICustomerManager;
import com.docker.yyf.Repository.ICustomerDao;
import com.docker.yyf.base.BasePage;
import com.docker.yyf.base.Lock;
import com.docker.yyf.base.OtherQuery;
import com.docker.yyf.entity.*;
import com.docker.yyf.exception.AlreadyLockedException;
import com.docker.yyf.util.ExcelUtil;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 18329
 */
@Component
public class CustomerManageImpl implements ICustomerManager {
    @Autowired
    private ICustomerDao dao;
    @Override
    public void save(Customer customer){
        dao.save(customer);
    }
    @Override
    public void update(Customer customer){
        dao.updateFirst(customer);
    }
    @Override
    public Customer selectById(String id){
        return dao.selectById(id);
    }
    @Override
    public Customer selectOne(Customer customer){
        return dao.selectOne(customer);
    }
    @Override
    public List<Customer>  select(Customer customer){
        return dao.select(customer);
    }

    @Override
    public BasePage<Customer> page(BaseQuery<Customer> query) {
        return dao.selectPage(query.getQuery(), query.getOtherQuery(), query.getPageSize(), query.getPageNum());
    }

    @Override
    public void addProject(String id, Project project){
        Customer customer = dao.selectById(id);
        if (customer==null){
            return;
        }
        customer.addProject(project);
        dao.updateFirst(customer);
    }

    @Override
    public void addProject(List<String> ids,Project project){
        for (String id:ids){
            this.addProject(id,project);
        }
    }

    @Override
    public void exprot(HttpServletResponse response) throws Exception {
        byte[] exprot11 = exprot11();
        download(response,exprot11);
    }

    private byte[] exprot11() throws Exception {
        List<String[]> columNames = new ArrayList<String[]>();
        columNames.add(new String[] { "订单号", "类型" ,"创建时间" });
        columNames.add(new String[] { "总条数", "增加数" ,"减少" });

        List<String[]> fieldNames = new ArrayList<String[]>();
        fieldNames.add(new String[] { "orderId", "type" ,"createTime"});
        fieldNames.add(new String[] { "total", "addNumber" ,"reduceNumber"});

        List<Record> select = new ArrayList<Record>();
        for (int i=0;i<10;i++){
            Record record = new Record();
            record.setOrderId("orderId"+i);
            record.setType(2);
            record.setCreateTime(new Date());
            select.add(record);
        }
        RecordTotal recordTotal = new RecordTotal();
        recordTotal.setAddNumber(10);
        recordTotal.setReduceNumber(10);
        recordTotal.setTotal(10);
        List<RecordTotal> totals = new ArrayList<RecordTotal>();
        totals.add(recordTotal);

        ExcelUtil.ExcelExportData setInfo = new ExcelUtil.ExcelExportData();

        LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
        map.put("用户操作记录",select);
        map.put("统计",totals);
        setInfo.setDataMap(map);
        setInfo.setFieldNames(fieldNames);
        setInfo.setTitles(new String[] { "用户操作记录","统计"});
        setInfo.setColumnNames(columNames);
        return  ExcelUtil.exportByte(setInfo, "d:/test5.xls", "type", (co) -> {
            Integer integer = Integer.valueOf(co.toString());
            return NameType.getType(integer).name();
        });
    }

    private void download(HttpServletResponse response,byte[] bytes) throws IOException {

        OutputStream os = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        response.reset();
        response.setHeader("Content-Type","application/vnd.ms-excel");
        response.setHeader("Content-Type","application/force-download");
        response.addHeader("Content-Disposition","attachment;filename=\"userRecord.xls\"");

        try {
            bos.write(bytes);
        }finally {
            try {
                bos.flush();
                bos.close();
                os.flush();
                os.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }


    @Async
    @Lock
    @Override
    public String test(){
        try {
            System.out.println("Async");
            Thread.sleep(5000);
            this.error();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (AlreadyLockedException e) {
            System.out.println("AlreadyLockedException");
        }
        return "HHHH";
    }

    public void error(){
        throw new AlreadyLockedException();
    }
}
