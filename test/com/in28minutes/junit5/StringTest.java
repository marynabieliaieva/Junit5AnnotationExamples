package com.in28minutes.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {

	
	private String str;

	// Initialization and clean up 
	@BeforeAll
	static void beforeAll(){
		System.out.println("Initialize connection for database ");
	}
	
	@AfterAll
	static void afterAll(TestInfo info) {
		System.out.println("Close database " + info.getDisplayName());
	}
	
	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Initialize Test Data for each test " + info.getDisplayName());
	}
	
	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("meow " + info.getDisplayName());
	}
	
	
	//Name options
	@Test
	@DisplayName("When length is null, throw an exception")
	void length_exception() {
		str = null;
		assertThrows(NullPointerException.class, 
				() -> {
					str.length();
				}
				);
	}
	
	
	@Test
	void toUpperCase_basic() {
		String str = "abcd";
		String result = str.toUpperCase();
		assertNotNull(result);
		assertEquals("ABCD", result);
	}
	
	@Test
	void contains_basic() {
		String str = "abcdefgh";
		boolean result = str.contains("ijk");
		assertEquals(false, result);
		assertFalse(result);
		
	}
	
	
	//Repetition and array assert
	@Test 
	@RepeatedTest(5)
	void split_basic(){
		String str = "abc def ghi";
		String actualResult[] = str.split(" ");
		String[] expectedResult = new String[] {"abc", "def", "ghi"};
		
		assertArrayEquals(expectedResult, actualResult);
	}

	//Base test for parametrization
	@Test
	void length_greather_than_zero() {
		assertTrue("ABCD".length()>0);
		assertTrue("ABC".length()>0);
		assertTrue("AB".length()>0);
		assertTrue("A".length()>0);
	}
	
	//Simple parametrization
	@ParameterizedTest
	@ValueSource(strings = {"ABCD", "ABC", "AB", "A"})
	void length_greather_than_zero_using_parameterized(String str) {
		assertTrue(str.length()>0);
	}
	
	//Parametrization (2 parameters) test + names for all tests 
	@ParameterizedTest(name = "{0} uppercase value is {1}")
	@CsvSource(value = {"abcd, ABCD", "abc, ABC"})
	void uppercase(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}
	
	//Performance test - example
	@Test
	@Disabled
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(1),
				() -> {
					for (int i=0; i<=100; i++) {
						int j= i;
						System.out.println(j);
					}
				}
				
				);
	}
	
	
	
	//Nested test structure
	@Nested
	@DisplayName("For an empty name")
	class EmptyStringTests {
		
		@BeforeEach
		void setToEmpty(){
			str = "";
		}
		
		
		@Test
		void lengthIsZero() {
			assertEquals(0, str.length());
		}
		
		@Test
		void upperCaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}
		
		
		
	@Nested
	@DisplayName("For an large string name")
	class LargeStringTests {
			
		@BeforeEach
		void setToLArge(){
			str = "abcdef";
			}
			
			
		@Test
		void lengthIs() {
			assertEquals(6, str.length());
			}
			
		@Test
		void upperCaseIs() {
			assertEquals("ABCDEF", str.toUpperCase());
			}
			
	}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
