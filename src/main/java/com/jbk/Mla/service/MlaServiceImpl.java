package com.jbk.Mla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.Mla.dao.MlaDao;
import com.jbk.Mla.entity.Mla;
@Service
public class MlaServiceImpl implements MlaService {
	@Autowired
	private MlaDao dao;

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

}
