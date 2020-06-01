/**
 * 
 */
package com.accolite.aums.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dto.ResponseDto;

/**
 * @author darshan
 *
 */
public interface TrainingMaterialService {

	public ResponseDto getAllTrainingMaterials();

	public ResponseDto findTrainingMaterialById(int courseId, int instructorId);

	public ResponseDto deleteTrainingMaterial(int id);

	public ResponseDto addTrainingMaterial(MultipartFile[] file, int courseId, int instructorId)
			throws SerialException, IOException, SQLException;
}
