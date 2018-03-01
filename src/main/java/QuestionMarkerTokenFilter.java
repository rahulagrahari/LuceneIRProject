
import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

public class QuestionMarkerTokenFilter extends TokenFilter {
	
	public QuestionMarkerTokenFilter(TokenStream tokenStream) {
		super(tokenStream);
	}

	protected CharTermAttribute charTermAttribute =
	        addAttribute(CharTermAttribute.class);
	    protected PositionIncrementAttribute positionIncrementAttribute =
	        addAttribute(PositionIncrementAttribute.class);
	    
	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		// Loop over tokens in the token stream to find the next one
        // that is not empty
        String nextToken = null;
        while (nextToken == null) {

            // Reached the end of the token stream being processed
            if ( ! this.input.incrementToken()) {
                return false;
            }

            // Get text of the current token and remove any
            // leading/trailing whitespace.
            String currentTokenInStream =
                this.input.getAttribute(CharTermAttribute.class)
                    .toString().trim();

            // Save the token if it is not an question mark
            if (! currentTokenInStream.equals("?")) {
                nextToken = currentTokenInStream;
            }
        }

        // Save the current token
        this.charTermAttribute.setEmpty().append(nextToken);
        this.positionIncrementAttribute.setPositionIncrement(1);
        return true;
    }

}
