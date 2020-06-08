/**
 * 
 */
package com.accolite.aums.dao;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.aums.dto.ResponseDto;

/**
 * @author darshan
 *
 */
public interface TrainingMaterialDao {
	public ResponseDto getAllTrainingMaterials();

	public ResponseDto findTrainingMaterialById(int courseId, int instructorId);

	public ResponseDto addTrainingMaterial(MultipartFile[] files, int courseId, int instructorId)
			throws IOException, SQLException;

	public ResponseDto deleteTrainingMaterial(int id);
}
