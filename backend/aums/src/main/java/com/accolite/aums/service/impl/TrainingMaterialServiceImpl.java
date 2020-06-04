/**
 * 
 */
package com.accolite.aums.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dao.impl.TrainingMaterialDaoImpl;
import com.accolite.aums.dto.ResponseDto;
import com.accolite.aums.service.TrainingMaterialService;

/**
 * @author darshan
 *
 */
@Service
public class TrainingMaterialServiceImpl implements TrainingMaterialService {

	@Autowired
	private TrainingMaterialDaoImpl trainingMaterialDao;

	@Override
	public ResponseDto getAllTrainingMaterials() {

		return trainingMaterialDao.getAllTrainingMaterials();
	}

	@Override
	public ResponseDto findTrainingMaterialById(int courseId, int instructorId) {
		return trainingMaterialDao.findTrainingMaterialById(courseId, instructorId);
	}

	@Override
	public ResponseDto deleteTrainingMaterial(int id) {
		return trainingMaterialDao.deleteTrainingMaterial(id);

	}

	@Override
	public ResponseDto addTrainingMaterial(MultipartFile[] file, int courseId, int instructorId) throws SerialException, IOException, SQLException {
		return trainingMaterialDao.addTrainingMaterial(file, courseId, instructorId);
	}

	/**
	 * @param id
	 * @return
	 */
	public ResponseDto getTrainingVersion(int id) {
		return trainingMaterialDao.getTrainingVersion(id);
	}

}
