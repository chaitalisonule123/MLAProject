package com.jbk.Mla.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import com.jbk.Mla.dao.MlaDao;
import com.jbk.Mla.entity.Mla;
@Service
public class MlaServiceImpl implements MlaService {
	@Autowired
	private MlaDao dao;
	
	int countMlaInSheet = 0;

	@Override
	public Boolean saveMla(Mla mla) {
		boolean isAdded = dao.saveMla(mla);
		
		return isAdded ;
	}

	@Override
	public List<Mla> getAllMla() {
		List<Mla> allSupplier=dao.getAllMla();
		
		return allSupplier;
	}

	@Override
	 public Mla  getMlaById(int id) {
		Mla mla=dao.getMlaById(id);
		
		return mla;
	}

	@Override
	public boolean updateMla(Mla mla) {
       boolean isUpdated =dao.updateMla(mla);
		return isUpdated;
	}

	@Override
	public boolean deleteMla(int id) {
		boolean isDeleted =dao.deleteMla(id);
		
		return isDeleted;
	}

	
	@Override
	public long getTotalCountOfMla() {
		long countOfMla =dao.getTotalCountOfMla();
		
		return countOfMla ;
	}

	@Override
	public double getAvgOfMlaVote() {
		 double avg=dao.getAvgOfMlaVote();
		return avg;
	}

	@Override
	public Mla getMaxVoteMla() {
		Mla mla =dao.getMaxVoteMla();
		return mla ;
	}

	@Override
	public List<Mla> sortMlaById_ASC() {
		List<Mla> list=dao.sortMlaById_ASC();
		return list;
	}

	@Override
	public List<Mla> sortMlaById_DESC() {
		List<Mla> list=dao.sortMlaById_DESC();
		return list;
	}

	@Override
	public List<Mla> sortMlaByName_ASC() {
		List<Mla> list=dao.sortMlaByName_ASC();
		return list;
	}

	@Override
	public List<Mla> sortMlaByName_DESC() {
		List<Mla> list=dao.sortMlaByName_DESC();
		return list;
	}


	public List<Mla> readExcelSheetData(String path) {
		Mla mla = null;
		List<Mla> list = new ArrayList<>();
		try {
			FileInputStream fileIn = new FileInputStream(new File(path));

			Workbook workbook = new XSSFWorkbook(fileIn);
			Sheet sheet = workbook.getSheetAt(0);
			countMlaInSheet = sheet.getLastRowNum();

			Iterator<Row> rows = sheet.rowIterator();

			int count = 0;
			while (rows.hasNext()) {
				Row row = rows.next();
				mla = new Mla();

				if (count == 0) {
					count = count + 1;
					continue;
				}

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					int col = cell.getColumnIndex();

					switch (col) {
					case 0: {

						int id = (int) cell.getNumericCellValue();
						mla.setMlaId(id);

						break;
					}
					case 1: {
						String name = cell.getStringCellValue();
						mla.setMlaName(name);

						break;
					}
					case 2: {
						String party = cell.getStringCellValue();
						mla.setMlaParty(party);

						break;
					}
					case 3: {
						int vote = (int) cell.getNumericCellValue();
						mla.setMlaVote(vote);

						break;
					}
					case 4: {
						String state = cell.getStringCellValue();
						mla.setMlaState(state);

						break;

					}
					
					case 5: {
						String address = cell.getStringCellValue();
						mla.setMlaAddress(address);

						break;
					}
                 
					
					case 6: {
						String constituency = cell.getStringCellValue();
						mla.setMlaConstituency(constituency);

						break;
					}

					default:
						break;
					}

				}
				list.add(mla);
				workbook.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public String uploadExcelSheet(MultipartFile file, HttpSession session) {

		int addedCount = 0;
		String msg = null;
		String path = session.getServletContext().getRealPath("/uploaded");
		String fileName = file.getOriginalFilename();

		try {
			byte[] data = file.getBytes();
			FileOutputStream fileOut = new FileOutputStream(new File(path + File.separator + fileName));
			fileOut.write(data);

			List<Mla> list = readExcelSheetData(path + File.separator + fileName);

			addedCount = dao.excelToDatabase(list);

			msg = " Total MlaInfo in ExcelSheet = " + countMlaInSheet;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
}
