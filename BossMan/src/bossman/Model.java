package bossman;

/* *****************************************************************************************************************************************************************
 * Copyright 2018 Grant Alexander
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * 		The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 * 
 ******************************************************************************************************************************************************************/

import java.util.ArrayList;

public class Model {
	private ArrayList<Person> people;

	public Model(String input) {
		this.people = new ArrayList<Person>();
		String[] names = this.parseInput(input);
		this.populateArrayList(names);
	}
	
	private int getRandomValue() {
		double milliseconds = (double) System.currentTimeMillis();
		double seed = milliseconds % 100d;
		double random = Math.E * seed;
		int truncated = (int) random;
		
		random -= (double) truncated;
		random *= 10;
		
		return (int) random;
	}
	
	private void randomize() {
		final int LAST_INDEX = people.size()-1;
		for(int x = 0; x <= LAST_INDEX; x++) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				//nothing
			}
			Person someone = people.get(x);
			someone.setValue(this.getRandomValue());
			people.set(x, someone);
		}
	}
	
	private String createOutputString(String format) {
		String randomized = "";
		final int LAST_INDEX = people.size()-1;
		for(int x = 0; x <= LAST_INDEX; x++) {
			randomized += " " + people.get(x).getName();
			if (format == "Line by Line") {
				String delimiter = "\n";
				randomized += delimiter;
			}
			else if(format == "List" && x != LAST_INDEX) {
				String delimiter = ",";
				randomized += delimiter;
			}
		}
		return randomized;
	}
	
	private void sortPeople() {
		final int LAST_INDEX = people.size()-1;
		final int BEGINNING_INDEX = 0;
		for(int x = BEGINNING_INDEX; x <= LAST_INDEX; x++) {
			for(int y = x; y > BEGINNING_INDEX; y--) {
				Person current = people.get(y);
				Person next = people.get(y-1);
				
				if(current.getValue() >= next.getValue())
				{
					people.set(y, next);
					people.set(y-1, current);
				}
				
			}
		}
	}
	
	private String[] parseInput(String input) {
		return input.split(",");
	}
	
	private Person createPerson(String name) {
		return new Person(name.trim());
	}
	
	private void populateArrayList(String[] names) {
		for(int x = 0; x < names.length; x++) {
			Person someone = this.createPerson(names[x]);
			people.add(someone);
		}
	}
	
	public String generateOutput(String times, String format) {
		String out = "";
		int runs = Integer.parseInt(times);
		for(int x = 0; x < runs; x++)
		{
			this.randomize();
			this.sortPeople();
			if (format == "List" && runs != 1 && x != runs-1) {
				out += this.createOutputString(format) + ",";
			} 
			else {
				out += this.createOutputString(format);
			}
		}
		return out;
	}
}
