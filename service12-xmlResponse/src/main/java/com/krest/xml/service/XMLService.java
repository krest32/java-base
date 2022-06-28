package com.krest.xml.service;

import com.krest.Service.Response.R;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author: krest
 * @date: 2021/6/28 11:58
 * @description:
 */
public interface XMLService {

    R analysisXML(String res) throws TransformerException;
}
