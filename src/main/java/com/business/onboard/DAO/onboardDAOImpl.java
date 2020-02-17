package com.business.onboard.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.Date;

import com.business.onboard.model.OnboardModel;


@Component
@Repository
@Transactional
public class onboardDAOImpl implements onboardDAO {
	

	@Autowired
	private EntityManager entityManager; 

	@Transactional
	@Override
	public List<OnboardModel> getAllRecords() {
		// TODO Auto-generated method stub
		List<OnboardModel> records=null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OnboardModel> cq = builder.createQuery(OnboardModel.class);
		Root<OnboardModel> root=cq.from(OnboardModel.class);
		records = entityManager.createQuery(cq).getResultList();
		return records;
		
	}

	@Override
	public OnboardModel OnboardData(List<OnboardModel> onboardedRecords) {
		// TODO Auto-generated method stub
		entityManager.persist(onboardedRecords);
		return null;
	}
	
	public String onboardData(File file) {
	       String excelFilePath = file.getPath();

	       int batchSize = 20;

	       Connection connection = null;

	       try {
	           long start = System.currentTimeMillis();
	            
	           FileInputStream inputStream = new FileInputStream(excelFilePath);
	           Workbook workbook = new Workbook(inputStream);
	           OnboardModel onboard=new Onboard(inputStream);
	           Sheet firstSheet = workbook.getSheetAt(0);
	           Iterator<Row> rowIterator = firstSheet.iterator(); 
	           int count = 0;
	           rowIterator.next();
	           while (rowIterator.hasNext()) {
	               Row nextRow = rowIterator.next();
	               Iterator<Cell> cellIterator = nextRow.cellIterator();

	               while (cellIterator.hasNext()) {
	                   Cell nextCell = cellIterator.next();

	                   int columnIndex = nextCell.getColumnIndex();

	                   switch (columnIndex) {
	                   case 0:
	                       String acronym = nextCell.getStringCellValue();
	                       onboard.setAcronym(acronym);
	                       break;
	                   case 1:
	                       Date start_date = nextCell.getDateCellValue();
	                       onboard.setStart_date(start_date);
	                   case 2:
	                       int progress = (int) nextCell.getNumericCellValue();
	                       onboard.getRank(progress);
	                   }

	               }
	             

	           ((FileInputStream) workbook).close();
	           connection.commit();
	           connection.close();
	            
	           long end = System.currentTimeMillis();
	           System.out.printf("Import done in %d ms\n", (end - start));
	            
	       } catch (IOException ex1) {
	           System.out.println("Error reading file");
	           ex1.printStackTrace();
	       } catch (SQLException ex2) {
	           System.out.println("Database error");
	           ex2.printStackTrace();
	       }

	  

}
