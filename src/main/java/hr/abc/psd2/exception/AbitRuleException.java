/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.abc.psd2.exception;

import java.util.List;

import javax.ejb.ApplicationException;

/**
 *
 * @author hr1uz0f5
 */
@ApplicationException(rollback=true)
public class AbitRuleException extends Exception {
	
    private static final long serialVersionUID = 1L;
    
    protected String msg = "";
    protected List<String> messages;
    protected Throwable initialException;
    
    public AbitRuleException() {
    	super();
    }
 
    public AbitRuleException(String message) {
    	this.msg = message;
    }
    
    public AbitRuleException(String message, Throwable initialException) {
    	super(initialException);
    }
    
    @Override
    public String getMessage() {
        return msg;
    }
  
	public List<String> getMessages() {
		return messages;
	}

	public Throwable getInitialException() {
		return initialException;
	}
	
  
}
