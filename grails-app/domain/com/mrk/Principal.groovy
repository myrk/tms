package com.mrk

class Principal {

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	static hasMany= [groups:Group]
	
	
	static transients = ['confirm']
	
	static constraints = {
		username blank: false, unique: true		
		password(blank: false, nullable: false, size:5..20, validator: {password, obj ->
			def confirm = obj.properties['confirm']
			if(confirm == null) return true // skip matching password validation (only important when setting/resetting pass)
			confirm == password ? true : ['invalid.matchingpasswords']
		})
		
		
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Authority> getAuthorities() {
		//println 'hello'	
		//this.groups.collect {it.authorities } as Set
		def auth = []
		this.groups.each{
			it.authorities.each{
				auth.add(it)
			}
		}
		return auth as Set
		//GroupAuthority.findAllByGroup(this).collect { it.authority } as Set
	}
}
