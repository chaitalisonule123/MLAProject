package com.jbk.Mla.dao;

import java.util.List;

import com.jbk.Mla.entity.Mla;

public interface MlaDao {
	
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
	  public int excelToDatabase(List<Mla>list) ;
	
	
	
	
	

}
