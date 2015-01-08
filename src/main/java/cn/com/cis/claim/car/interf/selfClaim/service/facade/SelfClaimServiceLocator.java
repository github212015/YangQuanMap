/**
 * SelfClaimServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.cis.claim.car.interf.selfClaim.service.facade;

import java.util.Properties;

import cn.com.sinosoft.mobileplat.common.util.PubTools;

public class SelfClaimServiceLocator extends org.apache.axis.client.Service implements cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimService {

	private static Properties properties;
	private java.lang.String SelfClaimServiceHttpPort_address;
	
	static{
		properties =new PubTools().getProperties("WebServiceURL.properties");
	}
	{
		SelfClaimServiceHttpPort_address = properties.getProperty("claim") + "/claim/service/selfClaimService";
	}
    public SelfClaimServiceLocator() {
    }


    public SelfClaimServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SelfClaimServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SelfClaimServiceHttpPort
//    private java.lang.String SelfClaimServiceHttpPort_address = "http://localhost:7001/claim/service/selfClaimService";

    public java.lang.String getSelfClaimServiceHttpPortAddress() {
        return SelfClaimServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SelfClaimServiceHttpPortWSDDServiceName = "SelfClaimServiceHttpPort";

    public java.lang.String getSelfClaimServiceHttpPortWSDDServiceName() {
        return SelfClaimServiceHttpPortWSDDServiceName;
    }

    public void setSelfClaimServiceHttpPortWSDDServiceName(java.lang.String name) {
        SelfClaimServiceHttpPortWSDDServiceName = name;
    }

    public cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimServicePortType getSelfClaimServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SelfClaimServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSelfClaimServiceHttpPort(endpoint);
    }

    public cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimServicePortType getSelfClaimServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimServiceHttpBindingStub _stub = new cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getSelfClaimServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSelfClaimServiceHttpPortEndpointAddress(java.lang.String address) {
        SelfClaimServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimServiceHttpBindingStub _stub = new cn.com.cis.claim.car.interf.selfClaim.service.facade.SelfClaimServiceHttpBindingStub(new java.net.URL(SelfClaimServiceHttpPort_address), this);
                _stub.setPortName(getSelfClaimServiceHttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SelfClaimServiceHttpPort".equals(inputPortName)) {
            return getSelfClaimServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://facade.service.selfClaim.interf.car.claim.cis.com.cn", "SelfClaimService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://facade.service.selfClaim.interf.car.claim.cis.com.cn", "SelfClaimServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SelfClaimServiceHttpPort".equals(portName)) {
            setSelfClaimServiceHttpPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
