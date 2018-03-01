
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.EnglishMinimalStemFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;


public class CustomAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		// TODO Auto-generated method stub
		final Tokenizer source = new StandardTokenizer();
		TokenStream filter = new StandardFilter(source);
//		filter = new QuestionMarkerTokenFilter(source);
		filter = new LowerCaseFilter(source);
		filter = new EnglishPossessiveFilter(source);
		filter = new EnglishMinimalStemFilter(source);
		filter = new ClassicFilter(source);
		//filter = new LengthFilter(source);
		
		
		return new TokenStreamComponents(source, filter);
	}


	
	

}
