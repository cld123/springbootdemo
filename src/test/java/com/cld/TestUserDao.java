package com.cld;

import com.cld.dao.UserDao;
import com.cld.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppRun.class})
public class TestUserDao {
    @Autowired
    private UserDao userDao;

    /**
     * 测试查询方法
     */
    @Test
    public void test1(){
        List<User> all = userDao.findAll();
//        打印查询结果
        for (User user : all) {
            System.out.println(user);
        }
    }

    /**
     * 导出测试
     */
    @Test
    public void testExport() throws IOException {
        String[] titles = {"编号","用户名","密码","年龄"};
        /**
         * 先写入 标题栏数据
         */
//        1.创建文件对象   创建HSSFWorkbook只能够写出为xls格式的Excel
//        要写出 xlsx 需要创建为 XSSFWorkbook 两种Api基本使用方式一样
        HSSFWorkbook workbook = new HSSFWorkbook();
//        2.创建表对象
        HSSFSheet sheet = workbook.createSheet("用户信息");
        //        3.创建标题栏（第一行）  参数为行下标  行下标从0开始
        HSSFRow titleRow = sheet.createRow(0);
        //        4.在标题栏中写入数据
        for (int i = 0; i < titles.length; i++) {
//            创建单元格
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }
        /**
         * 写入用户数据
         */
//       5 创建行 如果是用户数据的集合 需要遍历
        List<User> all = userDao.findAll();
        for(int i=0;i<all.size();i++){
            HSSFRow row = sheet.createRow(i+1);
//       6 将用户数据写入到行中
            row.createCell(0).setCellValue(all.get(i).getUid());
            row.createCell(1).setCellValue(all.get(i).getUname());
            row.createCell(2).setCellValue(all.get(i).getUpwd());
            row.createCell(3).setCellValue(all.get(i).getUage());
        }
        FileSystemView fsv = FileSystemView.getFileSystemView();//获取本地桌面路径   
        System.out.println(fsv.getHomeDirectory().toString());
//        文件保存到本地 参数为要写出的位置
        workbook.write(new FileOutputStream(fsv.getHomeDirectory().toString()+"\\用户信息.xls"));
    }

    @Test
    public void testImport() throws Exception {
        //        1.通过流读取Excel文件
        FileInputStream inputStream = new FileInputStream("C:\\Users\\你好\\Desktop\\用户信息.xls");
        //        2.通过poi解析流 HSSFWorkbook 处理流得到的对象中 就封装了Excel文件所有的数据
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        //        3.从文件中获取表对象  getSheetAt通过下标获取
        HSSFSheet sheet = workbook.getSheetAt(0);
        //        4.从表中获取到行数据  从第二行开始 到 最后一行  getLastRowNum() 获取最后一行的下标
        int lastRowNum = sheet.getLastRowNum();
        List<User> uList = new ArrayList<>();
        for(int i =1;i<=lastRowNum;i++){
            /**
             * getNumericCellValue() 获取数字
             * getStringCellValue 获取String
             */
            User user = new User();
            HSSFRow row = sheet.getRow(i);
            if(row.getCell(0)!=null){
                //将所有数据类型转换为String类型
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String id = row.getCell(0).toString();
                user.setUid(Integer.valueOf(id));
             }
            if(row.getCell(1)!=null){
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String name = row.getCell(1).toString();
                user.setUname(name);
            }
            if(row.getCell(2)!=null){
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String pwd = row.getCell(2).toString();
                user.setUpwd(pwd);
            }
            if(row.getCell(3)!=null){
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String age = row.getCell(3).toString();
                user.setUage(Integer.valueOf(age));
            }
            uList.add(user);
        }
        for(User u:uList){
            System.out.println(u.toString());
        }
    }
}
