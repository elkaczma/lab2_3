package edu.iis.mto.similarity;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimilarityFinderTest {
	
	private MockSequenceSearcher mockSearcher;
	private SimilarityFinder finder;
	
	@Before
	public void setUp() throws Exception {
		
		mockSearcher = new MockSequenceSearcher();
		finder = new SimilarityFinder(mockSearcher);
	}

	@Test
	public void testCalculateJackardSimilarityOfEmptySequences() {

		int[] seq1 = {};
		int[] seq2 = {};
		double expectedResult = 1.0d;
		
		Assert.assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(equalTo(expectedResult)));
	}
	
	@Test
	public void testCalculateJackardSimilarityOfEqualSequences() {
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {1, 3, 5};
		double expectedResult = 1.0d;
		
		Assert.assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(equalTo(expectedResult)));
	}
	
	@Test
	public void testCalculateJackardSimilarityOfEmptySequenceAndNonEmptySequence() {
		
		int[] seq1 = {};
		int[] seq2 = {1, 3, 5};
		double expectedResult = 0d;
		
		Assert.assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(equalTo(expectedResult)));
	}
	
	@Test
	public void testCalculateJackardSimilarityOfSequencesOfTheSameLengthWithNoEqualElements() {
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {7, 9, 11};
		double expectedResult = 0d;
		
		Assert.assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(equalTo(expectedResult)));
	}
	
	@Test
	public void testCalculateJackardSimilarityOfSequencesOfTheSameLengthWithSomeEqualElements() {
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {1, 9, 11};
		double expectedResult = 0.2;
		
		Assert.assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(equalTo(expectedResult)));
	}
	
	@Test
	public void testCalculateJackardSimilarityOfSequencesOfDifferentLengthWithNoEqualElements() {
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {7, 9, 11, 13};
		double expectedResult = 0d;
		
		Assert.assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(equalTo(expectedResult)));
	}
	
	@Test
	public void testCalculateJackardSimilarityOfSequencesOfDifferentLengthWithSomeEqualElements() {
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {1, 3, 5, 13, 15, 17};
		double expectedResult = 0.5;
		
		Assert.assertThat(finder.calculateJackardSimilarity(seq1, seq2), is(equalTo(expectedResult)));
	}
	
	@Test(expected = NullPointerException.class)
	public void testCalculateJackardSimilarityOfNullSequences() {
		
		int[] seq1 = null;
		int[] seq2 = null;
		int[] seq3 = {1, 3, 5, 13, 15, 17};
		
		finder.calculateJackardSimilarity(seq1, seq2);
		finder.calculateJackardSimilarity(seq1, seq3);
	}
	
	@Test
	public void testSearchMethodInvocationsNumberInCalculateJackardSimilarity() {
		
		int[] seq1 = {1, 3, 5};
		int[] seq2 = {1, 3, 5, 13, 15, 17};
		int expectedNumber = 3;
		
		finder.calculateJackardSimilarity(seq1, seq2);
		Assert.assertThat(mockSearcher.getInvocationsNumber(), is(equalTo(expectedNumber)));
	}
}
