package com.daichao.enums;

public enum JPmessageEnum {

	hhkyzm("2509","629702"),
    mydc("7917","132763");
	
	private String code;
    private String password;
    
    JPmessageEnum(){}
    JPmessageEnum(String code,String password) {
      this.code = code;
      this.password = password;
    }
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getEnumPasswordBycode(String code) {
    	if (code == null) return null;
        for (JPmessageEnum authcode : values()) {
            if (authcode.getCode().equals(code))
                return authcode.getPassword();
        }
        return null;
    }
    
    public static String getEnumpasswordBypassword(String password) {
    	if (password == null) return null;
        for (JPmessageEnum code : values()) {
            if (code.getPassword().equals(password.trim()))
                return code.getCode();
        }
        return null;
    }
}
