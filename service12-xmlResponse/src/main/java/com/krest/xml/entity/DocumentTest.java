package com.krest.xml.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DocumentTest implements Serializable{

	private static final long serialVersionUID = 1L;

	private String requestNo;

	private String sysCode;
	
	 private String appId;

    private String responseStatus;
    
    private String message;

    private Date createDate;
    
}
