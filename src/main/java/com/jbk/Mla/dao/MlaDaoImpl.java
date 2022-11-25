package com.jbk.Mla.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jbk.Mla.entity.Mla;



@Repository 
public class MlaDaoImpl implements MlaDao {
	
	@Autowired
    SessionFactory sessionFactory;
	//sessionFactory ka object, container me load ho gaya, after running program.
	//with the help of this annotation ..sessionFactory ko load krenge container se
	   //application.property file, sessionFactory ka object banake deti hai.
		
	
	@Override
	public Boolean saveMla(Mla mla) {
		
		Session session= sessionFactory.openSession();
		Mla mla1=null;
		boolean isAdded=false;
		try {
			
			//check krenge ki client ne pass kiya mla database me hai ki nahi
			//agar nahi hai tohi insert krenge .
			//mla jo clint ne pass kiya
			//mla1 jo database me hai.
			
			mla1=session.get(Mla.class, mla.getMlaId());
			
			if (mla1 == null) {
				Transaction transaction= session.beginTransaction();
				session.save(mla);
				transaction.commit();
				isAdded = true;
			}
			
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		finally {
			if(session!=null) {
				session.close();
			}
			
		}
		
		
		return isAdded;
	}

	
	//session me list return krna ho to koi method  nahi hai
		 //so criteria use krenge 	

	@Override
	public List<Mla> getAllMla() {
		

		Session session= null;
		List<Mla> allMla=null;
		
		
		try {
			
			session= sessionFactory.openSession();
			//criteria create kiya
			Criteria criteria= session.createCriteria(Mla.class);
			 criteria.addOrder(Order.asc("mlaVote"));
			 // criteria.addOrder(Order.desc("mlaVote"));
			
			//query execute krne ke liye
			allMla	=criteria.list();
			
			
		}		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		finally {
			
			if(session!=null) {
				session.close();
			}
		}
		
		
		return allMla;
	}

	
	public Mla  getMlaById(int id){
		Session session=null;
		Transaction transaction=null;
		Mla mla =null;
       try {
    	     
    	   session= sessionFactory.openSession();
    	   transaction= session.beginTransaction();
    	   mla=session.get(Mla.class, id);
    	   transaction.commit();
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		finally {
			if(session!=null) {
				session.close();
			}
			
		}
		
		return mla;
				}

	@Override
	public boolean updateMla(Mla mla) {
		Session session=null;
		Transaction transaction=null;
		boolean isUpdated=false;
       try {
    	   
              session= sessionFactory.openSession();
			 transaction = session.beginTransaction();
			 Mla mla1=session.get(Mla.class,mla.getMlaId());
			 //ek session me 2 object aare(mla1  and mla)
			 //session se mla1 ko hatane ke session.evict krenge
			 session.evict(mla1);
			 //now session ke ek hi object hai mla 
			 //check krenge client ka mlaId db me present hai ya nahi
			
			 //agar client ne mlaidId di vo available hai ho ti update krenge
			 //mla1 means db ke andar ka 
			 //mla means client ne  diya 
			 //session ke andar ki get method only primary key ke upar work krti hai agar where cluse hai to
			 if(mla1!=null) {
				 mla1=mla;
				 session.update(mla);
				 transaction.commit();
				 
				 isUpdated=true;
				 
			 }
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		finally {
			if(session!=null) {
				session.close();
			}
			
		}
		
		return isUpdated;
	}

	@Override
	public boolean deleteMla(int id) {
		Session session=null;
		boolean isDeleted=false;
		Transaction transaction=null;
		Mla mla=null;
		
      try {
    	  session=sessionFactory.openSession();
			 transaction = session.beginTransaction();
			  mla =session.get(Mla.class,id);
			  
			  if(mla!=null) {
			session.delete(mla);//after delete isdeleted is value true hogi
			transaction.commit();
			isDeleted=true;
			
			  }
  }
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		finally {
			if(session!=null) {
				session.close();
			}
			
			
		}
		
		return isDeleted;
	}

	

	@Override
	public long getTotalCountOfMla() {
		Session session=null;
		long countMla=0;
		 try {
	
    	  session=sessionFactory.openSession();
			 //create criteria 
			 Criteria criteria = session.createCriteria(Mla.class);
			 //set projection into criteria
			 
			 criteria.setProjection(Projections.rowCount());
			 
			 //execute criteria using list method
			 //yaha query execute hogi
			 
			  List<Long> allMla =criteria.list();
			  
			  //agar list empty nahi hai to hi us numeric value ko get karenge 
			  //and countMla me save karenge
			  if(!allMla.isEmpty()) {
				  countMla=allMla.get(0);	
		}
		 }
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		finally {
			if(session!=null) {
				session.close();
			}
			
		}
		
		return countMla;
	}


	@Override
	public double getAvgOfMlaVote() {
		Session session = null;
		 double avg=0;
		try {
			 session = sessionFactory.openSession();
			//create criteria
				Criteria criteria= session.createCriteria(Mla.class);
				
				//set projection into criteria
				criteria.setProjection(Projections.avg("mlaVote"));
				
				//execute criteria..for executing query
				List<Double> allMla=criteria.list();
				
				//agar list empty nahi hai tohi us numeric value ko get 
				//krenge and avg variable me save krenge
				if(!allMla.isEmpty()) {
					avg=allMla.get(0);
				}
				else {
					
					System.out.println("mla not found for avg");
				}
				
	}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		finally {
			if(session!=null) {
				session.close();
			}
			
		}
		
		return avg;
	}


	
	@Override
	public Mla getMaxVoteMla() {
		
		Session session = null;
		Mla mla = null;

		try {
			
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Mla.class);

			Projection projection = Projections.max("mlaVote");

			criteria.setProjection(projection);

			List<Mla> list = criteria.list();

			mla = list.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			if(session!=null)
			session.close();
		}

		return mla;
		
	}

}
