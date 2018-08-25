package log.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import log.entity.Employee;

public class DaoImpl implements Dao {
	// 获取Excel表格
	@Override
	public List<Employee> list() throws Exception {
		System.out.println("开始获取Excel文件");
		List<Employee> emplyoeeList = new ArrayList<>();
		// 创建Excel表格
		File file = new File("C:\\Users\\Administrator\\Desktop\\a\\people.xlsx");
		FileInputStream fin = new FileInputStream(file);
		// 从文件刘中读取excel表格
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		// 获取工作簿
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 开始解析表格
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			// 获取每一行
			XSSFRow row = sheet.getRow(i);
			int id = 0;
			String name = null;
			int age = 0;
			Date birthday = null;
			String gender = null;
			String address = null;
			long mobile = 0;
			boolean isMarried = false;
			String contact = null;
			String major = null;
			String job = null;
			double salary = 0;
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				// 获取每个单元格
				XSSFCell cell = row.getCell(j);
				if (j == 0) {
					id = (int) cell.getNumericCellValue();
				}
				if (j == 1) {
					name = cell.getStringCellValue();
				}
				if (j == 2) {
					age = (int) cell.getNumericCellValue();
				}
				if (j == 3) {
					birthday = cell.getDateCellValue();
				}
				if (j == 4) {
					gender = cell.getStringCellValue();
				}
				if (j == 5) {
					address = cell.getStringCellValue();
				}
				if (j == 6) {
					mobile = (long) cell.getNumericCellValue();
				}
				if (j == 7) {
					isMarried = cell.getBooleanCellValue();
				}
				if (j == 8) {
					contact = cell.getStringCellValue();
				}
				if (j == 9) {
					major = cell.getStringCellValue();
				}
				if (j == 10) {
					job = cell.getStringCellValue();
				}
				if (j == 11) {
					salary = cell.getNumericCellValue();
				}
			}
			Employee employee = new Employee(id, name, age, birthday, gender, address, mobile, isMarried, contact,
					major, job, salary);
			emplyoeeList.add(employee);
		}
		System.out.println("获取文件成功...");
		return emplyoeeList;
	}

	// 表格的查找
	@Override
	public Employee find(int id) throws Exception {
		// 获取表格
		List<Employee> employeeList = this.list();
		for (Employee e : employeeList) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;

	}

	// 插入表格
	@Override
	public boolean insert(Employee employee) throws Exception {
		boolean flag = false;
		// 判断是否重复
		Employee e = this.find((int) employee.getId());
		if (e != null) {
			System.out.println("添加失败...");
			return flag;

		}
		System.out.println("开始获取表格。。。");
		// 创建Excel表格
		File file = new File("C:\\Users\\Administrator\\Desktop\\a\\people.xlsx");
		FileInputStream fin = new FileInputStream(file);
		// 从文件刘中读取excel表格
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		// 获取工作簿
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 总行数
		int total = sheet.getLastRowNum();
		// sheet里面增加一行
		XSSFRow row = sheet.createRow(total + 1);
		// id
		XSSFCell id_cell = row.createCell(0);
		id_cell.setCellValue(employee.getId());
		// name
		XSSFCell name_cell = row.createCell(1);
		name_cell.setCellValue(employee.getName());
		// age
		XSSFCell age_cell = row.createCell(2);
		age_cell.setCellValue(employee.getAge());
		// birthday
		XSSFCell hireDate_cell = row.createCell(3);
		hireDate_cell.setCellValue(employee.getBirthday());
		// 设置日期格式
		XSSFCellStyle date_cell_style = workbook.createCellStyle();
		XSSFDataFormat date_formate = workbook.createDataFormat();
		date_cell_style.setDataFormat(date_formate.getFormat("yyyy/MM/dd"));
		hireDate_cell.setCellStyle(date_cell_style);
		// gender
		XSSFCell gender_cell = row.createCell(4);
		gender_cell.setCellValue(employee.getGender());
		// address
		XSSFCell address_cell = row.createCell(5);
		address_cell.setCellValue(employee.getAddress());
		// mobile
		XSSFCell mobile_cell = row.createCell(6);
		mobile_cell.setCellValue(employee.getMobile());
		// isMarried
		XSSFCell isMarried_cell = row.createCell(7);
		isMarried_cell.setCellValue(employee.isMarried());
		// contact
		XSSFCell contact_cell = row.createCell(8);
		contact_cell.setCellValue(employee.getContact());
		// major
		XSSFCell major_cell = row.createCell(9);
		major_cell.setCellValue(employee.getMajor());
		// job
		XSSFCell job_cell = row.createCell(10);
		job_cell.setCellValue(employee.getJob());
		// salary
		XSSFCell salary_cell = row.createCell(11);
		salary_cell.setCellValue(employee.getSalary());
		// 输出到表格
		workbook.write(new FileOutputStream(file));
		flag = true;
		System.out.println("添加成功...");
		return flag;
	}

	// 修改此表格
	@Override
	public boolean update(Employee employee) throws Exception {
		// 获取id
		Employee e = this.find((int) employee.getId());
		if (e == null) {
			System.out.println("查无此人。。。");
			return false;
		}
		System.out.println("开始获取表格。。。");
		// 创建Excel表格
		File file = new File("C:\\Users\\Administrator\\Desktop\\a\\people.xlsx");
		FileInputStream fin = new FileInputStream(file);
		// 从文件刘中读取excel表格
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		// 获取工作簿
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if ((int) (row.getCell(0).getNumericCellValue()) == employee.getId()) {
				row.getCell(1).setCellValue(employee.getName());
				row.getCell(2).setCellValue(employee.getAge());
				row.getCell(3).setCellValue(employee.getBirthday());
				row.getCell(4).setCellValue(employee.getGender());
				row.getCell(5).setCellValue(employee.getAddress());
				row.getCell(6).setCellValue(employee.getMobile());
				row.getCell(7).setCellValue(employee.isMarried());
				row.getCell(8).setCellValue(employee.getContact());
				row.getCell(9).setCellValue(employee.getMajor());
				row.getCell(10).setCellValue(employee.getJob());
				row.getCell(11).setCellValue(employee.getSalary());
				//输出到表格
				workbook.write(new FileOutputStream(file));
				System.out.println("修改成功。。。");
				return true;
			}
		}
		 System.out.println("修改失败。。。");
		 return false;
	}

	@Override
	public boolean remove(int id) throws Exception {
		System.out.println("开始获取表格。。。");
		// 创建Excel表格
		File file = new File("C:\\Users\\Administrator\\Desktop\\a\\people.xlsx");
		FileInputStream fin = new FileInputStream(file);
		// 从文件刘中读取excel表格
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		// 获取工作簿
		XSSFSheet sheet = workbook.getSheetAt(0);
		for(int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			if((int)(row.getCell(0).getNumericCellValue()) == id) {
				sheet.removeRow(row);
				//输出表格
				workbook.write(new FileOutputStream(file));
				System.out.println("删除成功。。。");
				return true;
			}	
		}
		System.out.println("删除失败。。。");
		return false;
	}

}
