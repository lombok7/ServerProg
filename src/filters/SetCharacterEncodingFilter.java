package filters;
 
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
public class SetCharacterEncodingFilter implements Filter {
	protected String encoding = null;
	protected FilterConfig filterconfig = null;
	protected boolean ignore = true;
 
	@Override
	public void destroy() {
		
		// TODO Auto-generated method stub
		this.encoding = null;
		this.filterconfig = null;
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		// Conditionally select and set the character encoding to be used
		if (ignore || (request.getCharacterEncoding() == null)) {
	
			String encoding = selectEncoding(request);
		
			if (encoding != null)
			
				request.setCharacterEncoding(encoding);
		}
		
		chain.doFilter(request, response);
	}
 
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		
		// TODO Auto-generated method stub
		this.filterconfig = filterconfig;
		this.encoding = filterconfig.getInitParameter("encoding");
		String value = filterconfig.getInitParameter("ignore");

		if (value == null)

			this.ignore = true;

		else if (value.equalsIgnoreCase("true"))

			this.ignore = true;

		else if (value.equalsIgnoreCase("yes"))

			this.ignore = true;

		else

			this.ignore = false;
	}
	
	protected String selectEncoding(ServletRequest request) {

		return (this.encoding);
	}
}