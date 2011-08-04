package com.mrk

import java.io.Serializable;

class Phone implements Serializable{
    String number
	String local
	PhoneType type
	//Party party
	static belongsTo = Party
	
	
    static constraints = {		
		number(maxSize:32)
		local(maxSize:32)		
    }
	
}
