/**
 * 
 */
package com.accolite.aums.dao;

import java.util.List;

import com.accolite.aums.models.TrainingMaterial;

/**
 * @author darshan
 *
 */
public interface TrainingMaterialDao {
	public List<TrainingMaterial> getAllTrainingMaterials();

	public TrainingMaterial findTrainingMaterialById(int id);

	public void addTrainingMaterial(TrainingMaterial trainingMaterial);

	public void updateTrainingMaterial(TrainingMaterial trainingMaterial);

	public void deleteTrainingMaterial(int id);
}
