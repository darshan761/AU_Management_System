/**
 * 
 */
package com.accolite.aums.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * @author darshan
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features =  {"classpath:features"},plugin = {"pretty", "html:target/cucumber/bagbasics"},
glue = {"com.accolite.aums.cucumber.stepdefs"})
public class CucumberTest {

}
