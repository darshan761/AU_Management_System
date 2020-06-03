/**
 * 
 */
package com.accolite.aums.models;

import org.junit.jupiter.api.Test;

import com.accolite.aums.dto.ResponseDto;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * @author darshan
 *
 */
public class ResponseDtoTest {
	@Test
    public void testGetterSetter() {
        PojoClass pojoclass = PojoClassFactory.getPojoClass(ResponseDto.class);
        Validator validator = ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }
}
