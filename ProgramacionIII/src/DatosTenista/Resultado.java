package DatosTenista;

public class Resultado {
	
	private String torneo;
	private int año;
	private String ganador;
	private String subcampeon;
	private int rankingGanador;
	private int rankingSubcampeon;
	private String resultadoFinal;
	
	public String getTorneo() {
		return torneo;
	}
	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public String getGanador() {
		return ganador;
	}
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}
	public String getSubcampeon() {
		return subcampeon;
	}
	public void setSubcampeon(String subcampeon) {
		this.subcampeon = subcampeon;
	}
	public int getRankingGanador() {
		return rankingGanador;
	}
	public void setRankingGanador(int rankingGanador) {
		this.rankingGanador = rankingGanador;
	}
	public int getRankingSubcampeon() {
		return rankingSubcampeon;
	}
	public void setRankingSubcampeon(int rankingSubcampeon) {
		this.rankingSubcampeon = rankingSubcampeon;
	}
	public String getResultadoFinal() {
		return resultadoFinal;
	}
	public void setResultadoFinal(String resultadoFinal) {
		this.resultadoFinal = resultadoFinal;
	}
	
	public Resultado(String torneo, int año, String ganador, String subcampeon, int rankingGanador,
			int rankingSubcampeon, String resultadoFinal) {
		super();
		this.torneo = torneo;
		this.año = año;
		this.ganador = ganador;
		this.subcampeon = subcampeon;
		this.rankingGanador = rankingGanador;
		this.rankingSubcampeon = rankingSubcampeon;
		this.resultadoFinal = resultadoFinal;
	}
	
	public Resultado() {
		super();
		this.torneo = "";
		this.año = 0;
		this.ganador = "";
		this.subcampeon = "";
		this.rankingGanador = 0;
		this.rankingSubcampeon = 0;
		this.resultadoFinal = "";
	}
	
	@Override
	public String toString() {
		return "Resultado [torneo=" + torneo + ", año=" + año + ", ganador=" + ganador + ", subcampeon=" + subcampeon
				+ ", rankingGanador=" + rankingGanador + ", rankingSubcampeon=" + rankingSubcampeon
				+ ", resultadoFinal=" + resultadoFinal + "]";
	}
	
	

}
