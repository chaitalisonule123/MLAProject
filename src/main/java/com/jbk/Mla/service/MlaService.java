package com.jbk.Mla.service;

import java.util.List;

import com.jbk.Mla.entity.Mla;


public interface MlaService {

	
	public Boolean  saveMla(Mla mla);
    public List<Mla> getAllMla();
    public Mla  getMlaById(int id);
	public boolean updateMla (Mla mla);
	public  boolean deleteMla (int id);
    public long getTotalCountOfMla();
	 public double getAvgOfMlaVote();
	 public Mla getMaxVoteMla();
	
	
	
	
}
