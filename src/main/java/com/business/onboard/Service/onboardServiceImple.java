package com.business.onboard.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.business.onboard.DAO.onboardDAO;

import com.business.onboard.model.OnboardModel;

@Service
public class onboardServiceImple implements onboardService {
	
	@Autowired
	onboardDAO onboardDAO;

	@Override
	public List<OnboardModel> getAllRecords() {
		// TODO Auto-generated method stub
		return onboardDAO.getAllRecords();
	}

	@Override
	public OnboardModel OnboardData(List<OnboardModel> onboardModel) {
		// TODO Auto-generated method stub
		return onboardDAO.OnboardData(onboardModel);
	}

}
