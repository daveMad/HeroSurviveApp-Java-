package com.herosurvive.app;

import static org.junit.Assert.*;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.herosurvice.models.Enemy;
import com.herosurvive.service.FileLogic;
import com.herosurvive.service.ParserLogic;

public class ParserLogicTest {
	
	@Test
	public void shouldExtractEnemiesByInputData() throws FileNotFoundException{
		FileLogic logic = new FileLogic("");
		List<String> inputData = logic.getInputFile("src/test.input");
		List<Enemy> enemies = ParserLogic.getInstance().extractEnemies(inputData);
		for (Enemy enemy : enemies) {
			App.Log(enemy.toString());
		}
	}
	
	@Test
	public void shouldExtractEnemyWithNameAndPosition(){
		Enemy enemy = ParserLogic.getInstance().extractEnemyWithPosition("There is a Zombie at position 1681");
		assertEquals("Zombie", enemy.name);
		assertEquals(1681, enemy.position);
	}
	
	
	@Test
	public void shouldGenerateKeywords() {
		String[] data = ParserLogic.getInstance().getKeywords();
		assertTrue(data.length > 0);
	}

	

	@Test
	public void shouldExtractNumberFromStringArray() {
		String[] inputArray = "There is a Zombie at position 1681".split(" ");
		int expected = 1681;
		assertEquals(expected, ParserLogic.getInstance().extractNumber(inputArray));
	}

	@Test
	public void shouldReturnFalseIfLineContainsKeyword() {
		String falseSample = "There is a Zombie at position 1681";
		String trueSample = "Lion is Enemy";
		assertEquals(true, ParserLogic.getInstance().checkIfContainsKeyword(trueSample, "Enemy"));
		assertEquals(false, ParserLogic.getInstance().checkIfContainsKeyword(falseSample, "Enemy"));
	}

	@Test
	public void shouldExtractEnemyWithName() {
		String line = "Lion is Enemy";
		String expected = "Lion";
		String actual = ParserLogic.getInstance().extractEnemyType(line);
		assertTrue("Not true!", expected.equals(actual));
	}
}
