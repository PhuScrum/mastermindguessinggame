public class Result {
	private int hits;
	private int strikes;
	Result() {
		hits = 0;
		strikes = 0;
	}
	Result(int hits, int strikes) {
		this.hits = hits;
		this.strikes = strikes;
	}
	
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getStrikes() {
		return strikes;
	}
	public void setStrikes(int strikes) {
		this.strikes = strikes;
	}
}