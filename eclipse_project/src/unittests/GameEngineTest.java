package unittests;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

import eclipse_project.DedicationTokens;
import eclipse_project.GameEngine;
import eclipse_project.LanternCards;

public class GameEngineTest {

	
	
	@Test
	public void testGameEngine(){
		GameEngine gEngine = new GameEngine(4);
	}
	
	@Test
	public void test() {
		DedicationTokens dedicationObj=new DedicationTokens(4);
		LanternCards lanternCardObj=new LanternCards();
		GameEngine gEngine1=new GameEngine(4, 2, 20, dedicationObj, lanternCardObj);
		
	}
	
	
}
