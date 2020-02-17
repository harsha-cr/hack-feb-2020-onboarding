package com.business.onboard.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.business.onboard.DTO.OnboardDTO;
import com.business.onboard.file.FileStorageService;
import com.business.onboard.file.UploadFileReponse;

public class onboardController {

	@Autowired
	FileStorageService fileStorageService;
	
    @PostMapping("/uploadFile")
    public UploadFileReponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName =fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileReponse(fileName, fileDownloadUri,file.getContentType(), file.getSize());
    }

		// post items
		@RequestMapping(value = "/import/", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<?> importData(@Validated @RequestBody OnboardDTO onboardDTO) {
			try {
//				ModelMapper modelMapper = new ModelMapper();
//				onboardModel onboardData = modelMapper.map(onboardDTO,onboardModel.class);
//				onboardModel output = onboardService.
//				return new ResponseEntity<onboardModel>(output, HttpStatus.OK);
				return null;
			} catch (Exception ex) {
				String errorMessage;
				errorMessage =ex.getMessage();
				return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
			}
		}

}
