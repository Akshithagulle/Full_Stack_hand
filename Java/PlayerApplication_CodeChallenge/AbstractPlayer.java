package pack.Java_application;

public class AbstractPlayer {


	
	    protected int id;
	    protected String name;
	    protected String skill;
	    protected int exp;
	    protected String country;
	    protected double overallScore;

	    public AbstractPlayer(int id, String name, String skill, int exp, String country, double overallScore) {
	        this.id = id;
	        this.name = name;
	        this.skill = skill;
	        this.exp = exp;
	        this.country = country;
	        this.overallScore = overallScore;
	    }

	    // Getters and Setters
	    public int getId() { return id; }
	    public String getName() { return name; }
	    public String getSkill() { return skill; }
	    public int getExp() { return exp; }
	    public String getCountry() { return country; }
	    public double getOverallScore() { return overallScore; }

	    public void setSkill(String skill) {
	        this.skill = skill;
	    }

	    public void setExp(int exp) {
	        this.exp = exp;
	    }

	    public void setCountry(String country) {
	        this.country = country;
	    }

	    public void setOverallScore(double overallScore) {
	        this.overallScore = overallScore;
	    }
	}


