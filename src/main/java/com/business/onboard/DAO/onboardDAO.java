package com.business.onboard.DAO;

import java.util.List;

import com.business.onboard.model.OnboardModel;

public interface onboardDAO {

	List<OnboardModel> getAllRecords();
	
	OnboardModel OnboardData(List<OnboardModel> onboardModel);

}
