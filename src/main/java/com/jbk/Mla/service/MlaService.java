package com.jbk.Mla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.web.multipart.MultipartFile;
import com.jbk.Mla.entity.Mla;


public interface MlaService {

	
	public Boolean  saveMla(Mla mla);
    public List<Mla> getAllMla();
    public Mla  getMlaById(int id);
	public boolean updateMla (Mla mla);
	public  boolean deleteMla (int id);
	public List<Mla> sortMlaById_ASC();
    public List<Mla> sortMlaById_DESC();
	public List<Mla> sortMlaByName_ASC();
    public List<Mla> sortMlaByName_DESC();

    public long getTotalCountOfMla();
	 public double getAvgOfMlaVote();
	 public Mla getMaxVoteMla();
	 public String uploadExcelSheet(MultipartFile file, HttpSession session);
	
}
