package com.jbk.Mla.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Mla {
	@Id
	@Min(1)
	private int mlaId;
	
	@NotNull(message = "mla name  is required")
	private String mlaName;
	
	@NotNull(message = "mla Constituency  is required")
	private  String mlaConstituency;
	
	@NotNull(message = "mla party  is required")
	private String mlaParty;
	
	@NotNull(message = "mla address  is required")
	private String mlaAddress;
	
	@Min(1)
	private double mlaVote;
	
	@NotNull(message = "mla State  is required")
	private String mlaState;

	public Mla() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mla(int mlaId, String mlaName, String mlaConstituency, String mlaParty, String mlaAddress, double mlaVote,
			String mlaState) {
		super();
		this.mlaId = mlaId;
		this.mlaName = mlaName;
		this.mlaConstituency = mlaConstituency;
		this.mlaParty = mlaParty;
		this.mlaAddress = mlaAddress;
		this.mlaVote = mlaVote;
		this.mlaState = mlaState;
	}

	public int getMlaId() {
		return mlaId;
	}

	public void setMlaId(int mlaId) {
		this.mlaId = mlaId;
	}

	public String getMlaName() {
		return mlaName;
	}

	public void setMlaName(String mlaName) {
		this.mlaName = mlaName;
	}

	public String getMlaConstituency() {
		return mlaConstituency;
	}

	public void setMlaConstituency(String mlaConstituency) {
		this.mlaConstituency = mlaConstituency;
	}

	public String getMlaParty() {
		return mlaParty;
	}

	public void setMlaParty(String mlaParty) {
		this.mlaParty = mlaParty;
	}

	public String getMlaAddress() {
		return mlaAddress;
	}

	public void setMlaAddress(String mlaAddress) {
		this.mlaAddress = mlaAddress;
	}

	public double getMlaVote() {
		return mlaVote;
	}

	public void setMlaVote(double mlaVote) {
		this.mlaVote = mlaVote;
	}

	public String getMlaState() {
		return mlaState;
	}

	public void setMlaState(String mlaState) {
		this.mlaState = mlaState;
	}

	@Override
	public String toString() {
		return "Mla [mlaId=" + mlaId + ", mlaName=" + mlaName + ", mlaConstituency=" + mlaConstituency + ", mlaParty="
				+ mlaParty + ", mlaAddress=" + mlaAddress + ", mlaVote=" + mlaVote + ", mlaState=" + mlaState + "]";
	}

	
	
	
	

}
