 import java.lang.Math;

public class LogisticRegression {
	double m_bias = 0;
	double m_lucene = 0;
	
	public LogisticRegression(double x1, double bias){
		m_bias = bias;
		m_lucene = x1;
	}
	public double test(double score){
		return 1/(Math.exp(m_bias + m_lucene*score) + 1);
	}
}
