package com.business.onboard.Service;

import java.util.List;

import com.business.onboard.model.OnboardModel;

public interface onboardService {
	
	List<OnboardModel> getAllRecords();
	
	OnboardModel OnboardData(List<OnboardModel> onboardModel);

}
