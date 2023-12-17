package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Json")
public class EmissionJson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private Double value;
    
    private String category;

    private int year;
    
    private String scenario;
    
    private String gasUnits;
   
    private String nk;

    public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;

   }

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	 public String getGasUnits() {
			return gasUnits;
		}

		public void setGasUnits(String gasUnits) {
			this.gasUnits = gasUnits;
		}

	
	
	public void setNK(String nk) {
		this.nk =nk;
	}

	public String getNk() {
		// TODO Auto-generated method stub
		return nk;
	}

		

}

