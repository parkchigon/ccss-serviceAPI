
/**
 * RetrieveBillAcntForEntrSvcServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */
package lguplus.u3.webservice.cm019;

/*
*  RetrieveBillAcntForEntrSvcServiceStub java implementation
*/
@SuppressWarnings({"rawtypes", "serial", "deprecation", "unchecked", "unused"})
public class RetrieveBillAcntForEntrSvcServiceStub extends org.apache.axis2.client.Stub {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private static int counter = 0;

	private static synchronized java.lang.String getUniqueSuffix() {
		// reset the counter if it is greater than 99999
		if (counter > 99999) {
			counter = 0;
		}
		counter = counter + 1;
		return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
	}

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService(
				"RetrieveBillAcntForEntrSvcService" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[1];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://lguplus/u3/esb", "retrieveBillAcntForEntrSvc"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

	}

	// populates the faults
	private void populateFaults() {

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public RetrieveBillAcntForEntrSvcServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public RetrieveBillAcntForEntrSvcServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener) throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

		_serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

	}

	/**
	 * Default Constructor
	 */
	public RetrieveBillAcntForEntrSvcServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://172.22.14.79:15011/CSSI/CM/RetrieveBillAcntForEntrSvc");

	}

	/**
	 * Default Constructor
	 */
	public RetrieveBillAcntForEntrSvcServiceStub() throws org.apache.axis2.AxisFault {

		this("http://172.22.14.79:15011/CSSI/CM/RetrieveBillAcntForEntrSvc");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public RetrieveBillAcntForEntrSvcServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcService#retrieveBillAcntForEntrSvc
	 * @param retrieveBillAcntForEntrSvc
	 * 
	 */

	public lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse retrieveBillAcntForEntrSvc(

			lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc retrieveBillAcntForEntrSvc)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction(
					"http://lguplus/u3/esb/RetrieveBillAcntForEntrSvcPortType/RetrieveBillAcntForEntrSvcRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
			_operationClient.getOptions().setTimeOutInMilliSeconds(10000);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), retrieveBillAcntForEntrSvc,
					optimizeContent(
							new javax.xml.namespace.QName("http://lguplus/u3/esb", "retrieveBillAcntForEntrSvc")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(_messageContext);
		}
	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// http://172.22.14.79:15011/CSSI/CM/RetrieveBillAcntForEntrSvc
	public static class ESBHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = ESBHeader
		 * Namespace URI = java:lguplus.u3.esb.common Namespace Prefix = ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.common")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ServiceID
		 */

		protected java.lang.String localServiceID;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getServiceID() {
			return localServiceID;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ServiceID
		 */
		public void setServiceID(java.lang.String param) {

			this.localServiceID = param;

		}

		/**
		 * field for TransactionID
		 */

		protected java.lang.String localTransactionID;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getTransactionID() {
			return localTransactionID;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            TransactionID
		 */
		public void setTransactionID(java.lang.String param) {

			this.localTransactionID = param;

		}

		/**
		 * field for SystemID
		 */

		protected java.lang.String localSystemID;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSystemID() {
			return localSystemID;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SystemID
		 */
		public void setSystemID(java.lang.String param) {

			this.localSystemID = param;

		}

		/**
		 * field for ErrCode
		 */

		protected java.lang.String localErrCode;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getErrCode() {
			return localErrCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ErrCode
		 */
		public void setErrCode(java.lang.String param) {

			this.localErrCode = param;

		}

		/**
		 * field for ErrMsg
		 */

		protected java.lang.String localErrMsg;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getErrMsg() {
			return localErrMsg;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ErrMsg
		 */
		public void setErrMsg(java.lang.String param) {

			this.localErrMsg = param;

		}

		/**
		 * field for Reserved
		 */

		protected java.lang.String localReserved;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getReserved() {
			return localReserved;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Reserved
		 */
		public void setReserved(java.lang.String param) {

			this.localReserved = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ESBHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.common");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ESBHeader", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ESBHeader", xmlWriter);
				}

			}

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ServiceID", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ServiceID");
				}

			} else {
				xmlWriter.writeStartElement("ServiceID");
			}

			if (localServiceID == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("ServiceID cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localServiceID);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "TransactionID", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "TransactionID");
				}

			} else {
				xmlWriter.writeStartElement("TransactionID");
			}

			if (localTransactionID == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("TransactionID cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localTransactionID);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "SystemID", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "SystemID");
				}

			} else {
				xmlWriter.writeStartElement("SystemID");
			}

			if (localSystemID == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("SystemID cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localSystemID);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ErrCode", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ErrCode");
				}

			} else {
				xmlWriter.writeStartElement("ErrCode");
			}

			if (localErrCode == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localErrCode);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ErrMsg", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ErrMsg");
				}

			} else {
				xmlWriter.writeStartElement("ErrMsg");
			}

			if (localErrMsg == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localErrMsg);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "Reserved", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "Reserved");
				}

			} else {
				xmlWriter.writeStartElement("Reserved");
			}

			if (localReserved == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localReserved);

			}

			xmlWriter.writeEndElement();

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ServiceID"));

			if (localServiceID != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localServiceID));
			} else {
				throw new org.apache.axis2.databinding.ADBException("ServiceID cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "TransactionID"));

			if (localTransactionID != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransactionID));
			} else {
				throw new org.apache.axis2.databinding.ADBException("TransactionID cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "SystemID"));

			if (localSystemID != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSystemID));
			} else {
				throw new org.apache.axis2.databinding.ADBException("SystemID cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrCode"));

			elementList.add(localErrCode == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localErrCode));

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrMsg"));

			elementList.add(localErrMsg == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localErrMsg));

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "Reserved"));

			elementList.add(localReserved == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localReserved));

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static ESBHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				ESBHeader object = new ESBHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ESBHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ESBHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ServiceID")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setServiceID(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "TransactionID")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setTransactionID(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "SystemID")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setSystemID(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrCode")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setErrCode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ErrMsg")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setErrMsg(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "Reserved")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setReserved(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsReqInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = DsReqInVO
		 * Namespace URI = java:lguplus.u3.esb.cm019 Namespace Prefix = ns10
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm019")) {
				return "ns10";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for EntrNo
		 */

		protected java.lang.String localEntrNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrNo() {
			return localEntrNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrNo
		 */
		public void setEntrNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrNoTracker = true;
			} else {
				localEntrNoTracker = true;

			}

			this.localEntrNo = param;

		}

		/**
		 * field for ProdNo
		 */

		protected java.lang.String localProdNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdNo() {
			return localProdNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdNo
		 */
		public void setProdNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdNoTracker = true;
			} else {
				localProdNoTracker = true;

			}

			this.localProdNo = param;

		}

		/**
		 * field for BillAcntNo
		 */

		protected java.lang.String localBillAcntNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntNo() {
			return localBillAcntNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntNo
		 */
		public void setBillAcntNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntNoTracker = true;
			} else {
				localBillAcntNoTracker = true;

			}

			this.localBillAcntNo = param;

		}

		/**
		 * field for ValdEndDttm
		 */

		protected java.lang.String localValdEndDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localValdEndDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getValdEndDttm() {
			return localValdEndDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ValdEndDttm
		 */
		public void setValdEndDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localValdEndDttmTracker = true;
			} else {
				localValdEndDttmTracker = true;

			}

			this.localValdEndDttm = param;

		}

		/**
		 * field for Mode
		 */

		protected java.lang.String localMode;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMode() {
			return localMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Mode
		 */
		public void setMode(java.lang.String param) {

			this.localMode = param;

		}

		/**
		 * field for NextOperatorId
		 */

		protected java.lang.String localNextOperatorId;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getNextOperatorId() {
			return localNextOperatorId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            NextOperatorId
		 */
		public void setNextOperatorId(java.lang.String param) {

			this.localNextOperatorId = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					DsReqInVO.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm019");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsReqInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsReqInVO", xmlWriter);
				}

			}
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrNo");
					}

				} else {
					xmlWriter.writeStartElement("entrNo");
				}

				if (localEntrNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localProdNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodNo");
					}

				} else {
					xmlWriter.writeStartElement("prodNo");
				}

				if (localProdNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntNo");
					}

				} else {
					xmlWriter.writeStartElement("billAcntNo");
				}

				if (localBillAcntNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localValdEndDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "valdEndDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "valdEndDttm");
					}

				} else {
					xmlWriter.writeStartElement("valdEndDttm");
				}

				if (localValdEndDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localValdEndDttm);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.cm019";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "mode", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "mode");
				}

			} else {
				xmlWriter.writeStartElement("mode");
			}

			if (localMode == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("mode cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localMode);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.cm019";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "nextOperatorId", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "nextOperatorId");
				}

			} else {
				xmlWriter.writeStartElement("nextOperatorId");
			}

			if (localNextOperatorId == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("nextOperatorId cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localNextOperatorId);

			}

			xmlWriter.writeEndElement();

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localEntrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			if (localProdNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "prodNo"));

				elementList.add(localProdNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNo));
			}
			if (localBillAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntNo"));

				elementList.add(localBillAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntNo));
			}
			if (localValdEndDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "valdEndDttm"));

				elementList.add(localValdEndDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValdEndDttm));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mode"));

			if (localMode != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMode));
			} else {
				throw new org.apache.axis2.databinding.ADBException("mode cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "nextOperatorId"));

			if (localNextOperatorId != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNextOperatorId));
			} else {
				throw new org.apache.axis2.databinding.ADBException("nextOperatorId cannot be null!!");
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static DsReqInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsReqInVO object = new DsReqInVO();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"DsReqInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsReqInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "entrNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "prodNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "valdEndDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setValdEndDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mode")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RetrieveBillAcntForEntrSvcResponse implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"RetrieveBillAcntForEntrSvcResponse", "ns3");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://lguplus/u3/esb")) {
				return "ns3";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ResponseRecord
		 */

		protected ResponseRecord localResponseRecord;

		/**
		 * Auto generated getter method
		 * 
		 * @return ResponseRecord
		 */
		public ResponseRecord getResponseRecord() {
			return localResponseRecord;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResponseRecord
		 */
		public void setResponseRecord(ResponseRecord param) {

			this.localResponseRecord = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RetrieveBillAcntForEntrSvcResponse.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://lguplus/u3/esb");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RetrieveBillAcntForEntrSvcResponse", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"RetrieveBillAcntForEntrSvcResponse", xmlWriter);
				}

			}

			if (localResponseRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseRecord cannot be null!!");
			}
			localResponseRecord.serialize(new javax.xml.namespace.QName("http://lguplus/u3/esb", "ResponseRecord"),
					factory, xmlWriter);

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://lguplus/u3/esb", "ResponseRecord"));

			if (localResponseRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("ResponseRecord cannot be null!!");
			}
			elementList.add(localResponseRecord);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RetrieveBillAcntForEntrSvcResponse parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				RetrieveBillAcntForEntrSvcResponse object = new RetrieveBillAcntForEntrSvcResponse();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RetrieveBillAcntForEntrSvcResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RetrieveBillAcntForEntrSvcResponse) ExtensionMapper.getTypeObject(nsUri, type,
										reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://lguplus/u3/esb", "ResponseRecord")
									.equals(reader.getName())) {

						object.setResponseRecord(ResponseRecord.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsResOutVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = DsResOutVO
		 * Namespace URI = java:lguplus.u3.esb.cm019 Namespace Prefix = ns10
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm019")) {
				return "ns10";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for BillAcntNo
		 */

		protected java.lang.String localBillAcntNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntNo() {
			return localBillAcntNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntNo
		 */
		public void setBillAcntNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntNoTracker = true;
			} else {
				localBillAcntNoTracker = true;

			}

			this.localBillAcntNo = param;

		}

		/**
		 * field for BillAddrSeqno
		 */

		protected java.lang.String localBillAddrSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAddrSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAddrSeqno() {
			return localBillAddrSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAddrSeqno
		 */
		public void setBillAddrSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAddrSeqnoTracker = true;
			} else {
				localBillAddrSeqnoTracker = true;

			}

			this.localBillAddrSeqno = param;

		}

		/**
		 * field for CntctPntSeqno
		 */

		protected java.lang.String localCntctPntSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCntctPntSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCntctPntSeqno() {
			return localCntctPntSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CntctPntSeqno
		 */
		public void setCntctPntSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCntctPntSeqnoTracker = true;
			} else {
				localCntctPntSeqnoTracker = true;

			}

			this.localCntctPntSeqno = param;

		}

		/**
		 * field for PymAcntNo
		 */

		protected java.lang.String localPymAcntNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPymAcntNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPymAcntNo() {
			return localPymAcntNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PymAcntNo
		 */
		public void setPymAcntNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPymAcntNoTracker = true;
			} else {
				localPymAcntNoTracker = true;

			}

			this.localPymAcntNo = param;

		}

		/**
		 * field for PymAcntChngDt
		 */

		protected java.lang.String localPymAcntChngDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPymAcntChngDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPymAcntChngDt() {
			return localPymAcntChngDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PymAcntChngDt
		 */
		public void setPymAcntChngDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPymAcntChngDtTracker = true;
			} else {
				localPymAcntChngDtTracker = true;

			}

			this.localPymAcntChngDt = param;

		}

		/**
		 * field for MrktCd
		 */

		protected java.lang.String localMrktCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localMrktCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMrktCd() {
			return localMrktCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            MrktCd
		 */
		public void setMrktCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMrktCdTracker = true;
			} else {
				localMrktCdTracker = true;

			}

			this.localMrktCd = param;

		}

		/**
		 * field for BillAcntFrstCrteDttm
		 */

		protected java.lang.String localBillAcntFrstCrteDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntFrstCrteDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntFrstCrteDttm() {
			return localBillAcntFrstCrteDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntFrstCrteDttm
		 */
		public void setBillAcntFrstCrteDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntFrstCrteDttmTracker = true;
			} else {
				localBillAcntFrstCrteDttmTracker = true;

			}

			this.localBillAcntFrstCrteDttm = param;

		}

		/**
		 * field for BltxtRcptManNm
		 */

		protected java.lang.String localBltxtRcptManNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRcptManNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRcptManNm() {
			return localBltxtRcptManNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRcptManNm
		 */
		public void setBltxtRcptManNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRcptManNmTracker = true;
			} else {
				localBltxtRcptManNmTracker = true;

			}

			this.localBltxtRcptManNm = param;

		}

		/**
		 * field for BltxtKdCd
		 */

		protected java.lang.String localBltxtKdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtKdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtKdCd() {
			return localBltxtKdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtKdCd
		 */
		public void setBltxtKdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtKdCdTracker = true;
			} else {
				localBltxtKdCdTracker = true;

			}

			this.localBltxtKdCd = param;

		}

		/**
		 * field for BltxtKdNm
		 */

		protected java.lang.String localBltxtKdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtKdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtKdNm() {
			return localBltxtKdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtKdNm
		 */
		public void setBltxtKdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtKdNmTracker = true;
			} else {
				localBltxtKdNmTracker = true;

			}

			this.localBltxtKdNm = param;

		}

		/**
		 * field for BltxtKdValdStrtDt
		 */

		protected java.lang.String localBltxtKdValdStrtDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtKdValdStrtDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtKdValdStrtDt() {
			return localBltxtKdValdStrtDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtKdValdStrtDt
		 */
		public void setBltxtKdValdStrtDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtKdValdStrtDtTracker = true;
			} else {
				localBltxtKdValdStrtDtTracker = true;

			}

			this.localBltxtKdValdStrtDt = param;

		}

		/**
		 * field for BltxtKdValdEndDt
		 */

		protected java.lang.String localBltxtKdValdEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtKdValdEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtKdValdEndDt() {
			return localBltxtKdValdEndDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtKdValdEndDt
		 */
		public void setBltxtKdValdEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtKdValdEndDtTracker = true;
			} else {
				localBltxtKdValdEndDtTracker = true;

			}

			this.localBltxtKdValdEndDt = param;

		}

		/**
		 * field for BltxtRcpProdNo
		 */

		protected java.lang.String localBltxtRcpProdNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRcpProdNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRcpProdNo() {
			return localBltxtRcpProdNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRcpProdNo
		 */
		public void setBltxtRcpProdNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRcpProdNoTracker = true;
			} else {
				localBltxtRcpProdNoTracker = true;

			}

			this.localBltxtRcpProdNo = param;

		}

		/**
		 * field for ScurMailRcpYn
		 */

		protected java.lang.String localScurMailRcpYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localScurMailRcpYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getScurMailRcpYn() {
			return localScurMailRcpYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ScurMailRcpYn
		 */
		public void setScurMailRcpYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localScurMailRcpYnTracker = true;
			} else {
				localScurMailRcpYnTracker = true;

			}

			this.localScurMailRcpYn = param;

		}

		/**
		 * field for BillEmailAddr
		 */

		protected java.lang.String localBillEmailAddr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillEmailAddrTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillEmailAddr() {
			return localBillEmailAddr;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillEmailAddr
		 */
		public void setBillEmailAddr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillEmailAddrTracker = true;
			} else {
				localBillEmailAddrTracker = true;

			}

			this.localBillEmailAddr = param;

		}

		/**
		 * field for BillAcntSttsCd
		 */

		protected java.lang.String localBillAcntSttsCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntSttsCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntSttsCd() {
			return localBillAcntSttsCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntSttsCd
		 */
		public void setBillAcntSttsCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntSttsCdTracker = true;
			} else {
				localBillAcntSttsCdTracker = true;

			}

			this.localBillAcntSttsCd = param;

		}

		/**
		 * field for BillAcntSttsNm
		 */

		protected java.lang.String localBillAcntSttsNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntSttsNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntSttsNm() {
			return localBillAcntSttsNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntSttsNm
		 */
		public void setBillAcntSttsNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntSttsNmTracker = true;
			} else {
				localBillAcntSttsNmTracker = true;

			}

			this.localBillAcntSttsNm = param;

		}

		/**
		 * field for BillAcntSttsChngDttm
		 */

		protected java.lang.String localBillAcntSttsChngDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntSttsChngDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntSttsChngDttm() {
			return localBillAcntSttsChngDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntSttsChngDttm
		 */
		public void setBillAcntSttsChngDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntSttsChngDttmTracker = true;
			} else {
				localBillAcntSttsChngDttmTracker = true;

			}

			this.localBillAcntSttsChngDttm = param;

		}

		/**
		 * field for BillAcntSttsChngRsnCd
		 */

		protected java.lang.String localBillAcntSttsChngRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntSttsChngRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntSttsChngRsnCd() {
			return localBillAcntSttsChngRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntSttsChngRsnCd
		 */
		public void setBillAcntSttsChngRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntSttsChngRsnCdTracker = true;
			} else {
				localBillAcntSttsChngRsnCdTracker = true;

			}

			this.localBillAcntSttsChngRsnCd = param;

		}

		/**
		 * field for BillAcntSttsChngRsnNm
		 */

		protected java.lang.String localBillAcntSttsChngRsnNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntSttsChngRsnNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntSttsChngRsnNm() {
			return localBillAcntSttsChngRsnNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntSttsChngRsnNm
		 */
		public void setBillAcntSttsChngRsnNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntSttsChngRsnNmTracker = true;
			} else {
				localBillAcntSttsChngRsnNmTracker = true;

			}

			this.localBillAcntSttsChngRsnNm = param;

		}

		/**
		 * field for BillAcntSttsChngRdtlCd
		 */

		protected java.lang.String localBillAcntSttsChngRdtlCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntSttsChngRdtlCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntSttsChngRdtlCd() {
			return localBillAcntSttsChngRdtlCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntSttsChngRdtlCd
		 */
		public void setBillAcntSttsChngRdtlCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntSttsChngRdtlCdTracker = true;
			} else {
				localBillAcntSttsChngRdtlCdTracker = true;

			}

			this.localBillAcntSttsChngRdtlCd = param;

		}

		/**
		 * field for BillAcntSttsChngDtlNm
		 */

		protected java.lang.String localBillAcntSttsChngDtlNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntSttsChngDtlNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntSttsChngDtlNm() {
			return localBillAcntSttsChngDtlNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntSttsChngDtlNm
		 */
		public void setBillAcntSttsChngDtlNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntSttsChngDtlNmTracker = true;
			} else {
				localBillAcntSttsChngDtlNmTracker = true;

			}

			this.localBillAcntSttsChngDtlNm = param;

		}

		/**
		 * field for AcntBlnc
		 */

		protected java.lang.String localAcntBlnc;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAcntBlncTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAcntBlnc() {
			return localAcntBlnc;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AcntBlnc
		 */
		public void setAcntBlnc(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcntBlncTracker = true;
			} else {
				localAcntBlncTracker = true;

			}

			this.localAcntBlnc = param;

		}

		/**
		 * field for PpayAcntYn
		 */

		protected java.lang.String localPpayAcntYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPpayAcntYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPpayAcntYn() {
			return localPpayAcntYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PpayAcntYn
		 */
		public void setPpayAcntYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPpayAcntYnTracker = true;
			} else {
				localPpayAcntYnTracker = true;

			}

			this.localPpayAcntYn = param;

		}

		/**
		 * field for BillAcntGrpNo
		 */

		protected java.lang.String localBillAcntGrpNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillAcntGrpNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillAcntGrpNo() {
			return localBillAcntGrpNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillAcntGrpNo
		 */
		public void setBillAcntGrpNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillAcntGrpNoTracker = true;
			} else {
				localBillAcntGrpNoTracker = true;

			}

			this.localBillAcntGrpNo = param;

		}

		/**
		 * field for MrgrBillPrmsYn
		 */

		protected java.lang.String localMrgrBillPrmsYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localMrgrBillPrmsYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMrgrBillPrmsYn() {
			return localMrgrBillPrmsYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            MrgrBillPrmsYn
		 */
		public void setMrgrBillPrmsYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMrgrBillPrmsYnTracker = true;
			} else {
				localMrgrBillPrmsYnTracker = true;

			}

			this.localMrgrBillPrmsYn = param;

		}

		/**
		 * field for BillPrssXclnYn
		 */

		protected java.lang.String localBillPrssXclnYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillPrssXclnYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillPrssXclnYn() {
			return localBillPrssXclnYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillPrssXclnYn
		 */
		public void setBillPrssXclnYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillPrssXclnYnTracker = true;
			} else {
				localBillPrssXclnYnTracker = true;

			}

			this.localBillPrssXclnYn = param;

		}

		/**
		 * field for TaxBillIsueYn
		 */

		protected java.lang.String localTaxBillIsueYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localTaxBillIsueYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getTaxBillIsueYn() {
			return localTaxBillIsueYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            TaxBillIsueYn
		 */
		public void setTaxBillIsueYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTaxBillIsueYnTracker = true;
			} else {
				localTaxBillIsueYnTracker = true;

			}

			this.localTaxBillIsueYn = param;

		}

		/**
		 * field for SttsChngDt
		 */

		protected java.lang.String localSttsChngDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSttsChngDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSttsChngDt() {
			return localSttsChngDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SttsChngDt
		 */
		public void setSttsChngDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSttsChngDtTracker = true;
			} else {
				localSttsChngDtTracker = true;

			}

			this.localSttsChngDt = param;

		}

		/**
		 * field for BlprocSrlno
		 */

		protected java.lang.String localBlprocSrlno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBlprocSrlnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBlprocSrlno() {
			return localBlprocSrlno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BlprocSrlno
		 */
		public void setBlprocSrlno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBlprocSrlnoTracker = true;
			} else {
				localBlprocSrlnoTracker = true;

			}

			this.localBlprocSrlno = param;

		}

		/**
		 * field for LastBlprocSrlno
		 */

		protected java.lang.String localLastBlprocSrlno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLastBlprocSrlnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLastBlprocSrlno() {
			return localLastBlprocSrlno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LastBlprocSrlno
		 */
		public void setLastBlprocSrlno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLastBlprocSrlnoTracker = true;
			} else {
				localLastBlprocSrlnoTracker = true;

			}

			this.localLastBlprocSrlno = param;

		}

		/**
		 * field for VipBltxtCd
		 */

		protected java.lang.String localVipBltxtCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localVipBltxtCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getVipBltxtCd() {
			return localVipBltxtCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            VipBltxtCd
		 */
		public void setVipBltxtCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localVipBltxtCdTracker = true;
			} else {
				localVipBltxtCdTracker = true;

			}

			this.localVipBltxtCd = param;

		}

		/**
		 * field for BilnavYn
		 */

		protected java.lang.String localBilnavYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBilnavYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBilnavYn() {
			return localBilnavYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BilnavYn
		 */
		public void setBilnavYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBilnavYnTracker = true;
			} else {
				localBilnavYnTracker = true;

			}

			this.localBilnavYn = param;

		}

		/**
		 * field for BltxtRsvRsnCd
		 */

		protected java.lang.String localBltxtRsvRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRsvRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRsvRsnCd() {
			return localBltxtRsvRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRsvRsnCd
		 */
		public void setBltxtRsvRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRsvRsnCdTracker = true;
			} else {
				localBltxtRsvRsnCdTracker = true;

			}

			this.localBltxtRsvRsnCd = param;

		}

		/**
		 * field for BltxtRsvRsnYn
		 */

		protected java.lang.String localBltxtRsvRsnYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRsvRsnYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRsvRsnYn() {
			return localBltxtRsvRsnYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRsvRsnYn
		 */
		public void setBltxtRsvRsnYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRsvRsnYnTracker = true;
			} else {
				localBltxtRsvRsnYnTracker = true;

			}

			this.localBltxtRsvRsnYn = param;

		}

		/**
		 * field for BltxtRsvValdStrtDt
		 */

		protected java.lang.String localBltxtRsvValdStrtDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRsvValdStrtDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRsvValdStrtDt() {
			return localBltxtRsvValdStrtDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRsvValdStrtDt
		 */
		public void setBltxtRsvValdStrtDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRsvValdStrtDtTracker = true;
			} else {
				localBltxtRsvValdStrtDtTracker = true;

			}

			this.localBltxtRsvValdStrtDt = param;

		}

		/**
		 * field for BltxtRsvValdEndDt
		 */

		protected java.lang.String localBltxtRsvValdEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRsvValdEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRsvValdEndDt() {
			return localBltxtRsvValdEndDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRsvValdEndDt
		 */
		public void setBltxtRsvValdEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRsvValdEndDtTracker = true;
			} else {
				localBltxtRsvValdEndDtTracker = true;

			}

			this.localBltxtRsvValdEndDt = param;

		}

		/**
		 * field for BltxtRsvRecv
		 */

		protected java.lang.String localBltxtRsvRecv;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRsvRecvTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRsvRecv() {
			return localBltxtRsvRecv;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRsvRecv
		 */
		public void setBltxtRsvRecv(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRsvRecvTracker = true;
			} else {
				localBltxtRsvRecvTracker = true;

			}

			this.localBltxtRsvRecv = param;

		}

		/**
		 * field for BltxtRsvOprtr
		 */

		protected java.lang.String localBltxtRsvOprtr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtRsvOprtrTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtRsvOprtr() {
			return localBltxtRsvOprtr;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtRsvOprtr
		 */
		public void setBltxtRsvOprtr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtRsvOprtrTracker = true;
			} else {
				localBltxtRsvOprtrTracker = true;

			}

			this.localBltxtRsvOprtr = param;

		}

		/**
		 * field for BltxtDupSendYn
		 */

		protected java.lang.String localBltxtDupSendYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBltxtDupSendYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBltxtDupSendYn() {
			return localBltxtDupSendYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BltxtDupSendYn
		 */
		public void setBltxtDupSendYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBltxtDupSendYnTracker = true;
			} else {
				localBltxtDupSendYnTracker = true;

			}

			this.localBltxtDupSendYn = param;

		}

		/**
		 * field for RepEntrNo
		 */

		protected java.lang.String localRepEntrNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRepEntrNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRepEntrNo() {
			return localRepEntrNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RepEntrNo
		 */
		public void setRepEntrNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRepEntrNoTracker = true;
			} else {
				localRepEntrNoTracker = true;

			}

			this.localRepEntrNo = param;

		}

		/**
		 * field for MrgrBillNo
		 */

		protected java.lang.String localMrgrBillNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localMrgrBillNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMrgrBillNo() {
			return localMrgrBillNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            MrgrBillNo
		 */
		public void setMrgrBillNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMrgrBillNoTracker = true;
			} else {
				localMrgrBillNoTracker = true;

			}

			this.localMrgrBillNo = param;

		}

		/**
		 * field for AddrCustNo
		 */

		protected java.lang.String localAddrCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAddrCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAddrCustNo() {
			return localAddrCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AddrCustNo
		 */
		public void setAddrCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAddrCustNoTracker = true;
			} else {
				localAddrCustNoTracker = true;

			}

			this.localAddrCustNo = param;

		}

		/**
		 * field for CustAddrZip
		 */

		protected java.lang.String localCustAddrZip;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustAddrZipTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustAddrZip() {
			return localCustAddrZip;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustAddrZip
		 */
		public void setCustAddrZip(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustAddrZipTracker = true;
			} else {
				localCustAddrZipTracker = true;

			}

			this.localCustAddrZip = param;

		}

		/**
		 * field for CustAddrZip1
		 */

		protected java.lang.String localCustAddrZip1;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustAddrZip1Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustAddrZip1() {
			return localCustAddrZip1;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustAddrZip1
		 */
		public void setCustAddrZip1(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustAddrZip1Tracker = true;
			} else {
				localCustAddrZip1Tracker = true;

			}

			this.localCustAddrZip1 = param;

		}

		/**
		 * field for CustAddrZip2
		 */

		protected java.lang.String localCustAddrZip2;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustAddrZip2Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustAddrZip2() {
			return localCustAddrZip2;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustAddrZip2
		 */
		public void setCustAddrZip2(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustAddrZip2Tracker = true;
			} else {
				localCustAddrZip2Tracker = true;

			}

			this.localCustAddrZip2 = param;

		}

		/**
		 * field for CustVilgAbvAddr
		 */

		protected java.lang.String localCustVilgAbvAddr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustVilgAbvAddrTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustVilgAbvAddr() {
			return localCustVilgAbvAddr;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustVilgAbvAddr
		 */
		public void setCustVilgAbvAddr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustVilgAbvAddrTracker = true;
			} else {
				localCustVilgAbvAddrTracker = true;

			}

			this.localCustVilgAbvAddr = param;

		}

		/**
		 * field for CustVilgBlwAddr
		 */

		protected java.lang.String localCustVilgBlwAddr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustVilgBlwAddrTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustVilgBlwAddr() {
			return localCustVilgBlwAddr;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustVilgBlwAddr
		 */
		public void setCustVilgBlwAddr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustVilgBlwAddrTracker = true;
			} else {
				localCustVilgBlwAddrTracker = true;

			}

			this.localCustVilgBlwAddr = param;

		}

		/**
		 * field for CustVilgAddr
		 */

		protected java.lang.String localCustVilgAddr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustVilgAddrTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustVilgAddr() {
			return localCustVilgAddr;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustVilgAddr
		 */
		public void setCustVilgAddr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustVilgAddrTracker = true;
			} else {
				localCustVilgAddrTracker = true;

			}

			this.localCustVilgAddr = param;

		}

		/**
		 * field for CntCustNo
		 */

		protected java.lang.String localCntCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCntCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCntCustNo() {
			return localCntCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CntCustNo
		 */
		public void setCntCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCntCustNoTracker = true;
			} else {
				localCntCustNoTracker = true;

			}

			this.localCntCustNo = param;

		}

		/**
		 * field for Telno
		 */

		protected java.lang.String localTelno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localTelnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getTelno() {
			return localTelno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Telno
		 */
		public void setTelno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTelnoTracker = true;
			} else {
				localTelnoTracker = true;

			}

			this.localTelno = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					DsResOutVO.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm019");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsResOutVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsResOutVO", xmlWriter);
				}

			}
			if (localBillAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntNo");
					}

				} else {
					xmlWriter.writeStartElement("billAcntNo");
				}

				if (localBillAcntNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAddrSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAddrSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAddrSeqno");
					}

				} else {
					xmlWriter.writeStartElement("billAddrSeqno");
				}

				if (localBillAddrSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAddrSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localCntctPntSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cntctPntSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cntctPntSeqno");
					}

				} else {
					xmlWriter.writeStartElement("cntctPntSeqno");
				}

				if (localCntctPntSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCntctPntSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localPymAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "pymAcntNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "pymAcntNo");
					}

				} else {
					xmlWriter.writeStartElement("pymAcntNo");
				}

				if (localPymAcntNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPymAcntNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localPymAcntChngDtTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "pymAcntChngDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "pymAcntChngDt");
					}

				} else {
					xmlWriter.writeStartElement("pymAcntChngDt");
				}

				if (localPymAcntChngDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPymAcntChngDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localMrktCdTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "mrktCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "mrktCd");
					}

				} else {
					xmlWriter.writeStartElement("mrktCd");
				}

				if (localMrktCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localMrktCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntFrstCrteDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntFrstCrteDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntFrstCrteDttm");
					}

				} else {
					xmlWriter.writeStartElement("billAcntFrstCrteDttm");
				}

				if (localBillAcntFrstCrteDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntFrstCrteDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRcptManNmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRcptManNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRcptManNm");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRcptManNm");
				}

				if (localBltxtRcptManNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRcptManNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtKdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtKdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtKdCd");
					}

				} else {
					xmlWriter.writeStartElement("bltxtKdCd");
				}

				if (localBltxtKdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtKdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtKdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtKdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtKdNm");
					}

				} else {
					xmlWriter.writeStartElement("bltxtKdNm");
				}

				if (localBltxtKdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtKdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtKdValdStrtDtTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtKdValdStrtDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtKdValdStrtDt");
					}

				} else {
					xmlWriter.writeStartElement("bltxtKdValdStrtDt");
				}

				if (localBltxtKdValdStrtDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtKdValdStrtDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtKdValdEndDtTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtKdValdEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtKdValdEndDt");
					}

				} else {
					xmlWriter.writeStartElement("bltxtKdValdEndDt");
				}

				if (localBltxtKdValdEndDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtKdValdEndDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRcpProdNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRcpProdNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRcpProdNo");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRcpProdNo");
				}

				if (localBltxtRcpProdNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRcpProdNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localScurMailRcpYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "scurMailRcpYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "scurMailRcpYn");
					}

				} else {
					xmlWriter.writeStartElement("scurMailRcpYn");
				}

				if (localScurMailRcpYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localScurMailRcpYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillEmailAddrTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billEmailAddr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billEmailAddr");
					}

				} else {
					xmlWriter.writeStartElement("billEmailAddr");
				}

				if (localBillEmailAddr == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillEmailAddr);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntSttsCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntSttsCd");
					}

				} else {
					xmlWriter.writeStartElement("billAcntSttsCd");
				}

				if (localBillAcntSttsCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntSttsCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntSttsNmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntSttsNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntSttsNm");
					}

				} else {
					xmlWriter.writeStartElement("billAcntSttsNm");
				}

				if (localBillAcntSttsNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntSttsNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntSttsChngDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntSttsChngDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntSttsChngDttm");
					}

				} else {
					xmlWriter.writeStartElement("billAcntSttsChngDttm");
				}

				if (localBillAcntSttsChngDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntSttsChngDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntSttsChngRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntSttsChngRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntSttsChngRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("billAcntSttsChngRsnCd");
				}

				if (localBillAcntSttsChngRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntSttsChngRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntSttsChngRsnNmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntSttsChngRsnNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntSttsChngRsnNm");
					}

				} else {
					xmlWriter.writeStartElement("billAcntSttsChngRsnNm");
				}

				if (localBillAcntSttsChngRsnNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntSttsChngRsnNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntSttsChngRdtlCdTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntSttsChngRdtlCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntSttsChngRdtlCd");
					}

				} else {
					xmlWriter.writeStartElement("billAcntSttsChngRdtlCd");
				}

				if (localBillAcntSttsChngRdtlCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntSttsChngRdtlCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntSttsChngDtlNmTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntSttsChngDtlNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntSttsChngDtlNm");
					}

				} else {
					xmlWriter.writeStartElement("billAcntSttsChngDtlNm");
				}

				if (localBillAcntSttsChngDtlNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntSttsChngDtlNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcntBlncTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "acntBlnc", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "acntBlnc");
					}

				} else {
					xmlWriter.writeStartElement("acntBlnc");
				}

				if (localAcntBlnc == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAcntBlnc);

				}

				xmlWriter.writeEndElement();
			}
			if (localPpayAcntYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ppayAcntYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ppayAcntYn");
					}

				} else {
					xmlWriter.writeStartElement("ppayAcntYn");
				}

				if (localPpayAcntYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPpayAcntYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntGrpNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billAcntGrpNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billAcntGrpNo");
					}

				} else {
					xmlWriter.writeStartElement("billAcntGrpNo");
				}

				if (localBillAcntGrpNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillAcntGrpNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localMrgrBillPrmsYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "mrgrBillPrmsYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "mrgrBillPrmsYn");
					}

				} else {
					xmlWriter.writeStartElement("mrgrBillPrmsYn");
				}

				if (localMrgrBillPrmsYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localMrgrBillPrmsYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillPrssXclnYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billPrssXclnYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billPrssXclnYn");
					}

				} else {
					xmlWriter.writeStartElement("billPrssXclnYn");
				}

				if (localBillPrssXclnYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillPrssXclnYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localTaxBillIsueYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "taxBillIsueYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "taxBillIsueYn");
					}

				} else {
					xmlWriter.writeStartElement("taxBillIsueYn");
				}

				if (localTaxBillIsueYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localTaxBillIsueYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localSttsChngDtTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "sttsChngDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "sttsChngDt");
					}

				} else {
					xmlWriter.writeStartElement("sttsChngDt");
				}

				if (localSttsChngDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSttsChngDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localBlprocSrlnoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "blprocSrlno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "blprocSrlno");
					}

				} else {
					xmlWriter.writeStartElement("blprocSrlno");
				}

				if (localBlprocSrlno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBlprocSrlno);

				}

				xmlWriter.writeEndElement();
			}
			if (localLastBlprocSrlnoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "lastBlprocSrlno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "lastBlprocSrlno");
					}

				} else {
					xmlWriter.writeStartElement("lastBlprocSrlno");
				}

				if (localLastBlprocSrlno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLastBlprocSrlno);

				}

				xmlWriter.writeEndElement();
			}
			if (localVipBltxtCdTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "vipBltxtCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "vipBltxtCd");
					}

				} else {
					xmlWriter.writeStartElement("vipBltxtCd");
				}

				if (localVipBltxtCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localVipBltxtCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBilnavYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bilnavYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bilnavYn");
					}

				} else {
					xmlWriter.writeStartElement("bilnavYn");
				}

				if (localBilnavYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBilnavYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRsvRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRsvRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRsvRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRsvRsnCd");
				}

				if (localBltxtRsvRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRsvRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRsvRsnYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRsvRsnYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRsvRsnYn");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRsvRsnYn");
				}

				if (localBltxtRsvRsnYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRsvRsnYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRsvValdStrtDtTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRsvValdStrtDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRsvValdStrtDt");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRsvValdStrtDt");
				}

				if (localBltxtRsvValdStrtDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRsvValdStrtDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRsvValdEndDtTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRsvValdEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRsvValdEndDt");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRsvValdEndDt");
				}

				if (localBltxtRsvValdEndDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRsvValdEndDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRsvRecvTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRsvRecv", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRsvRecv");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRsvRecv");
				}

				if (localBltxtRsvRecv == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRsvRecv);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtRsvOprtrTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtRsvOprtr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtRsvOprtr");
					}

				} else {
					xmlWriter.writeStartElement("bltxtRsvOprtr");
				}

				if (localBltxtRsvOprtr == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtRsvOprtr);

				}

				xmlWriter.writeEndElement();
			}
			if (localBltxtDupSendYnTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bltxtDupSendYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bltxtDupSendYn");
					}

				} else {
					xmlWriter.writeStartElement("bltxtDupSendYn");
				}

				if (localBltxtDupSendYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBltxtDupSendYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localRepEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "repEntrNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "repEntrNo");
					}

				} else {
					xmlWriter.writeStartElement("repEntrNo");
				}

				if (localRepEntrNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRepEntrNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localMrgrBillNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "mrgrBillNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "mrgrBillNo");
					}

				} else {
					xmlWriter.writeStartElement("mrgrBillNo");
				}

				if (localMrgrBillNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localMrgrBillNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localAddrCustNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "addrCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "addrCustNo");
					}

				} else {
					xmlWriter.writeStartElement("addrCustNo");
				}

				if (localAddrCustNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAddrCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustAddrZipTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custAddrZip", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custAddrZip");
					}

				} else {
					xmlWriter.writeStartElement("custAddrZip");
				}

				if (localCustAddrZip == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustAddrZip);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustAddrZip1Tracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custAddrZip1", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custAddrZip1");
					}

				} else {
					xmlWriter.writeStartElement("custAddrZip1");
				}

				if (localCustAddrZip1 == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustAddrZip1);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustAddrZip2Tracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custAddrZip2", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custAddrZip2");
					}

				} else {
					xmlWriter.writeStartElement("custAddrZip2");
				}

				if (localCustAddrZip2 == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustAddrZip2);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustVilgAbvAddrTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custVilgAbvAddr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custVilgAbvAddr");
					}

				} else {
					xmlWriter.writeStartElement("custVilgAbvAddr");
				}

				if (localCustVilgAbvAddr == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustVilgAbvAddr);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustVilgBlwAddrTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custVilgBlwAddr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custVilgBlwAddr");
					}

				} else {
					xmlWriter.writeStartElement("custVilgBlwAddr");
				}

				if (localCustVilgBlwAddr == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustVilgBlwAddr);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustVilgAddrTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custVilgAddr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custVilgAddr");
					}

				} else {
					xmlWriter.writeStartElement("custVilgAddr");
				}

				if (localCustVilgAddr == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustVilgAddr);

				}

				xmlWriter.writeEndElement();
			}
			if (localCntCustNoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cntCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cntCustNo");
					}

				} else {
					xmlWriter.writeStartElement("cntCustNo");
				}

				if (localCntCustNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCntCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localTelnoTracker) {
				namespace = "java:lguplus.u3.esb.cm019";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "telno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "telno");
					}

				} else {
					xmlWriter.writeStartElement("telno");
				}

				if (localTelno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localTelno);

				}

				xmlWriter.writeEndElement();
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localBillAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntNo"));

				elementList.add(localBillAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntNo));
			}
			if (localBillAddrSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAddrSeqno"));

				elementList.add(localBillAddrSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAddrSeqno));
			}
			if (localCntctPntSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "cntctPntSeqno"));

				elementList.add(localCntctPntSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntctPntSeqno));
			}
			if (localPymAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "pymAcntNo"));

				elementList.add(localPymAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPymAcntNo));
			}
			if (localPymAcntChngDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "pymAcntChngDt"));

				elementList.add(localPymAcntChngDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPymAcntChngDt));
			}
			if (localMrktCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mrktCd"));

				elementList.add(localMrktCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMrktCd));
			}
			if (localBillAcntFrstCrteDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntFrstCrteDttm"));

				elementList.add(localBillAcntFrstCrteDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntFrstCrteDttm));
			}
			if (localBltxtRcptManNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRcptManNm"));

				elementList.add(localBltxtRcptManNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRcptManNm));
			}
			if (localBltxtKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdCd"));

				elementList.add(localBltxtKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtKdCd));
			}
			if (localBltxtKdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdNm"));

				elementList.add(localBltxtKdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtKdNm));
			}
			if (localBltxtKdValdStrtDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdValdStrtDt"));

				elementList.add(localBltxtKdValdStrtDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtKdValdStrtDt));
			}
			if (localBltxtKdValdEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdValdEndDt"));

				elementList.add(localBltxtKdValdEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtKdValdEndDt));
			}
			if (localBltxtRcpProdNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRcpProdNo"));

				elementList.add(localBltxtRcpProdNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRcpProdNo));
			}
			if (localScurMailRcpYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "scurMailRcpYn"));

				elementList.add(localScurMailRcpYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localScurMailRcpYn));
			}
			if (localBillEmailAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billEmailAddr"));

				elementList.add(localBillEmailAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillEmailAddr));
			}
			if (localBillAcntSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsCd"));

				elementList.add(localBillAcntSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsCd));
			}
			if (localBillAcntSttsNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsNm"));

				elementList.add(localBillAcntSttsNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsNm));
			}
			if (localBillAcntSttsChngDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngDttm"));

				elementList.add(localBillAcntSttsChngDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsChngDttm));
			}
			if (localBillAcntSttsChngRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngRsnCd"));

				elementList.add(localBillAcntSttsChngRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsChngRsnCd));
			}
			if (localBillAcntSttsChngRsnNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngRsnNm"));

				elementList.add(localBillAcntSttsChngRsnNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsChngRsnNm));
			}
			if (localBillAcntSttsChngRdtlCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngRdtlCd"));

				elementList.add(localBillAcntSttsChngRdtlCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localBillAcntSttsChngRdtlCd));
			}
			if (localBillAcntSttsChngDtlNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngDtlNm"));

				elementList.add(localBillAcntSttsChngDtlNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsChngDtlNm));
			}
			if (localAcntBlncTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "acntBlnc"));

				elementList.add(localAcntBlnc == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAcntBlnc));
			}
			if (localPpayAcntYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ppayAcntYn"));

				elementList.add(localPpayAcntYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPpayAcntYn));
			}
			if (localBillAcntGrpNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntGrpNo"));

				elementList.add(localBillAcntGrpNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntGrpNo));
			}
			if (localMrgrBillPrmsYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mrgrBillPrmsYn"));

				elementList.add(localMrgrBillPrmsYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMrgrBillPrmsYn));
			}
			if (localBillPrssXclnYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billPrssXclnYn"));

				elementList.add(localBillPrssXclnYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillPrssXclnYn));
			}
			if (localTaxBillIsueYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "taxBillIsueYn"));

				elementList.add(localTaxBillIsueYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTaxBillIsueYn));
			}
			if (localSttsChngDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "sttsChngDt"));

				elementList.add(localSttsChngDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSttsChngDt));
			}
			if (localBlprocSrlnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "blprocSrlno"));

				elementList.add(localBlprocSrlno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBlprocSrlno));
			}
			if (localLastBlprocSrlnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "lastBlprocSrlno"));

				elementList.add(localLastBlprocSrlno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLastBlprocSrlno));
			}
			if (localVipBltxtCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "vipBltxtCd"));

				elementList.add(localVipBltxtCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVipBltxtCd));
			}
			if (localBilnavYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bilnavYn"));

				elementList.add(localBilnavYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBilnavYn));
			}
			if (localBltxtRsvRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvRsnCd"));

				elementList.add(localBltxtRsvRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRsvRsnCd));
			}
			if (localBltxtRsvRsnYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvRsnYn"));

				elementList.add(localBltxtRsvRsnYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRsvRsnYn));
			}
			if (localBltxtRsvValdStrtDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvValdStrtDt"));

				elementList.add(localBltxtRsvValdStrtDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRsvValdStrtDt));
			}
			if (localBltxtRsvValdEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvValdEndDt"));

				elementList.add(localBltxtRsvValdEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRsvValdEndDt));
			}
			if (localBltxtRsvRecvTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvRecv"));

				elementList.add(localBltxtRsvRecv == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRsvRecv));
			}
			if (localBltxtRsvOprtrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvOprtr"));

				elementList.add(localBltxtRsvOprtr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtRsvOprtr));
			}
			if (localBltxtDupSendYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtDupSendYn"));

				elementList.add(localBltxtDupSendYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBltxtDupSendYn));
			}
			if (localRepEntrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "repEntrNo"));

				elementList.add(localRepEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRepEntrNo));
			}
			if (localMrgrBillNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mrgrBillNo"));

				elementList.add(localMrgrBillNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMrgrBillNo));
			}
			if (localAddrCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "addrCustNo"));

				elementList.add(localAddrCustNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAddrCustNo));
			}
			if (localCustAddrZipTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custAddrZip"));

				elementList.add(localCustAddrZip == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustAddrZip));
			}
			if (localCustAddrZip1Tracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custAddrZip1"));

				elementList.add(localCustAddrZip1 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustAddrZip1));
			}
			if (localCustAddrZip2Tracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custAddrZip2"));

				elementList.add(localCustAddrZip2 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustAddrZip2));
			}
			if (localCustVilgAbvAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custVilgAbvAddr"));

				elementList.add(localCustVilgAbvAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustVilgAbvAddr));
			}
			if (localCustVilgBlwAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custVilgBlwAddr"));

				elementList.add(localCustVilgBlwAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustVilgBlwAddr));
			}
			if (localCustVilgAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custVilgAddr"));

				elementList.add(localCustVilgAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustVilgAddr));
			}
			if (localCntCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "cntCustNo"));

				elementList.add(localCntCustNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntCustNo));
			}
			if (localTelnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "telno"));

				elementList.add(localTelno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTelno));
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static DsResOutVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsResOutVO object = new DsResOutVO();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"DsResOutVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsResOutVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAddrSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAddrSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "cntctPntSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCntctPntSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "pymAcntNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPymAcntNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "pymAcntChngDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPymAcntChngDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mrktCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setMrktCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntFrstCrteDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntFrstCrteDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRcptManNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRcptManNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtKdCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtKdNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdValdStrtDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtKdValdStrtDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtKdValdEndDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtKdValdEndDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRcpProdNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRcpProdNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "scurMailRcpYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setScurMailRcpYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billEmailAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillEmailAddr(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsChngDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsChngRsnCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngRsnNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsChngRsnNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngRdtlCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsChngRdtlCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntSttsChngDtlNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsChngDtlNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "acntBlnc")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAcntBlnc(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ppayAcntYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPpayAcntYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billAcntGrpNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntGrpNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mrgrBillPrmsYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setMrgrBillPrmsYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "billPrssXclnYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillPrssXclnYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "taxBillIsueYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setTaxBillIsueYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "sttsChngDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSttsChngDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "blprocSrlno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBlprocSrlno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "lastBlprocSrlno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLastBlprocSrlno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "vipBltxtCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setVipBltxtCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bilnavYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBilnavYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRsvRsnCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvRsnYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRsvRsnYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvValdStrtDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRsvValdStrtDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvValdEndDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRsvValdEndDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvRecv")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRsvRecv(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtRsvOprtr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtRsvOprtr(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "bltxtDupSendYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBltxtDupSendYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "repEntrNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRepEntrNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "mrgrBillNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setMrgrBillNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "addrCustNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAddrCustNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custAddrZip")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustAddrZip(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custAddrZip1")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustAddrZip1(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custAddrZip2")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustAddrZip2(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custVilgAbvAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustVilgAbvAddr(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custVilgBlwAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustVilgBlwAddr(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "custVilgAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustVilgAddr(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "cntCustNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCntCustNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "telno")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setTelno(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ResponseBody implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = ResponseBody
		 * Namespace URI = java:lguplus.u3.esb.cm019 Namespace Prefix = ns10
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm019")) {
				return "ns10";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DsResOutVO
		 */

		protected DsResOutVO localDsResOutVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsResOutVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsResOutVO
		 */
		public DsResOutVO getDsResOutVO() {
			return localDsResOutVO;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsResOutVO
		 */
		public void setDsResOutVO(DsResOutVO param) {

			if (param != null) {
				// update the setting tracker
				localDsResOutVOTracker = true;
			} else {
				localDsResOutVOTracker = false;

			}

			this.localDsResOutVO = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ResponseBody.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm019");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ResponseBody", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ResponseBody",
							xmlWriter);
				}

			}
			if (localDsResOutVOTracker) {
				if (localDsResOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsResOutVO cannot be null!!");
				}
				localDsResOutVO.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "DsResOutVO"),
						factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localDsResOutVOTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "DsResOutVO"));

				if (localDsResOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsResOutVO cannot be null!!");
				}
				elementList.add(localDsResOutVO);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static ResponseBody parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				ResponseBody object = new ResponseBody();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ResponseBody".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ResponseBody) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "DsResOutVO")
									.equals(reader.getName())) {

						object.setDsResOutVO(DsResOutVO.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RequestBody implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = RequestBody
		 * Namespace URI = java:lguplus.u3.esb.cm019 Namespace Prefix = ns10
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm019")) {
				return "ns10";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DsReqInVO
		 */

		protected DsReqInVO localDsReqInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsReqInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsReqInVO
		 */
		public DsReqInVO getDsReqInVO() {
			return localDsReqInVO;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsReqInVO
		 */
		public void setDsReqInVO(DsReqInVO param) {

			if (param != null) {
				// update the setting tracker
				localDsReqInVOTracker = true;
			} else {
				localDsReqInVOTracker = false;

			}

			this.localDsReqInVO = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RequestBody.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm019");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RequestBody", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RequestBody",
							xmlWriter);
				}

			}
			if (localDsReqInVOTracker) {
				if (localDsReqInVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsReqInVO cannot be null!!");
				}
				localDsReqInVO.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "DsReqInVO"),
						factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localDsReqInVOTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "DsReqInVO"));

				if (localDsReqInVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsReqInVO cannot be null!!");
				}
				elementList.add(localDsReqInVO);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RequestBody parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				RequestBody object = new RequestBody();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RequestBody".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RequestBody) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "DsReqInVO")
									.equals(reader.getName())) {

						object.setDsReqInVO(DsReqInVO.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ResponseRecord implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * ResponseRecord Namespace URI = java:lguplus.u3.esb.cm019 Namespace Prefix =
		 * ns10
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm019")) {
				return "ns10";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ESBHeader
		 */

		protected ESBHeader localESBHeader;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localESBHeaderTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return ESBHeader
		 */
		public ESBHeader getESBHeader() {
			return localESBHeader;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ESBHeader
		 */
		public void setESBHeader(ESBHeader param) {

			if (param != null) {
				// update the setting tracker
				localESBHeaderTracker = true;
			} else {
				localESBHeaderTracker = false;

			}

			this.localESBHeader = param;

		}

		/**
		 * field for BusinessHeader
		 */

		protected BusinessHeader localBusinessHeader;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBusinessHeaderTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return BusinessHeader
		 */
		public BusinessHeader getBusinessHeader() {
			return localBusinessHeader;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BusinessHeader
		 */
		public void setBusinessHeader(BusinessHeader param) {

			if (param != null) {
				// update the setting tracker
				localBusinessHeaderTracker = true;
			} else {
				localBusinessHeaderTracker = false;

			}

			this.localBusinessHeader = param;

		}

		/**
		 * field for ResponseBody
		 */

		protected ResponseBody localResponseBody;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localResponseBodyTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return ResponseBody
		 */
		public ResponseBody getResponseBody() {
			return localResponseBody;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResponseBody
		 */
		public void setResponseBody(ResponseBody param) {

			if (param != null) {
				// update the setting tracker
				localResponseBodyTracker = true;
			} else {
				localResponseBodyTracker = false;

			}

			this.localResponseBody = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					ResponseRecord.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm019");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ResponseRecord", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ResponseRecord",
							xmlWriter);
				}

			}
			if (localESBHeaderTracker) {
				if (localESBHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
				}
				localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ESBHeader"),
						factory, xmlWriter);
			}
			if (localBusinessHeaderTracker) {
				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				localBusinessHeader.serialize(
						new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "BusinessHeader"), factory,
						xmlWriter);
			}
			if (localResponseBodyTracker) {
				if (localResponseBody == null) {
					throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
				}
				localResponseBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ResponseBody"),
						factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			if (localESBHeaderTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ESBHeader"));

				if (localESBHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
				}
				elementList.add(localESBHeader);
			}
			if (localBusinessHeaderTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "BusinessHeader"));

				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				elementList.add(localBusinessHeader);
			}
			if (localResponseBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ResponseBody"));

				if (localResponseBody == null) {
					throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
				}
				elementList.add(localResponseBody);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static ResponseRecord parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				ResponseRecord object = new ResponseRecord();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"ResponseRecord".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (ResponseRecord) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ESBHeader")
									.equals(reader.getName())) {

						object.setESBHeader(ESBHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "BusinessHeader")
									.equals(reader.getName())) {

						object.setBusinessHeader(BusinessHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ResponseBody")
									.equals(reader.getName())) {

						object.setResponseBody(ResponseBody.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class ExtensionMapper {

		public static java.lang.Object getTypeObject(java.lang.String namespaceURI, java.lang.String typeName,
				javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {

			if ("java:lguplus.u3.esb.cm019".equals(namespaceURI) && "DsReqInVO".equals(typeName)) {

				return DsReqInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.common".equals(namespaceURI) && "ESBHeader".equals(typeName)) {

				return ESBHeader.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm019".equals(namespaceURI) && "DsResOutVO".equals(typeName)) {

				return DsResOutVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm019".equals(namespaceURI) && "RequestBody".equals(typeName)) {

				return RequestBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm019".equals(namespaceURI) && "ResponseBody".equals(typeName)) {

				return ResponseBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm019".equals(namespaceURI) && "ResponseRecord".equals(typeName)) {

				return ResponseRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm019".equals(namespaceURI) && "RequestRecord".equals(typeName)) {

				return RequestRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.common".equals(namespaceURI) && "BusinessHeader".equals(typeName)) {

				return BusinessHeader.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class RequestRecord implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * RequestRecord Namespace URI = java:lguplus.u3.esb.cm019 Namespace Prefix =
		 * ns10
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm019")) {
				return "ns10";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ESBHeader
		 */

		protected ESBHeader localESBHeader;

		/**
		 * Auto generated getter method
		 * 
		 * @return ESBHeader
		 */
		public ESBHeader getESBHeader() {
			return localESBHeader;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ESBHeader
		 */
		public void setESBHeader(ESBHeader param) {

			this.localESBHeader = param;

		}

		/**
		 * field for RequestBody
		 */

		protected RequestBody localRequestBody;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRequestBodyTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return RequestBody
		 */
		public RequestBody getRequestBody() {
			return localRequestBody;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestBody
		 */
		public void setRequestBody(RequestBody param) {

			if (param != null) {
				// update the setting tracker
				localRequestBodyTracker = true;
			} else {
				localRequestBodyTracker = false;

			}

			this.localRequestBody = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RequestRecord.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm019");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RequestRecord", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RequestRecord",
							xmlWriter);
				}

			}

			if (localESBHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
			}
			localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ESBHeader"), factory,
					xmlWriter);
			if (localRequestBodyTracker) {
				if (localRequestBody == null) {
					throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
				}
				localRequestBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "RequestBody"),
						factory, xmlWriter);
			}
			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ESBHeader"));

			if (localESBHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
			}
			elementList.add(localESBHeader);
			if (localRequestBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "RequestBody"));

				if (localRequestBody == null) {
					throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
				}
				elementList.add(localRequestBody);
			}

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RequestRecord parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				RequestRecord object = new RequestRecord();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RequestRecord".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RequestRecord) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "ESBHeader")
									.equals(reader.getName())) {

						object.setESBHeader(ESBHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm019", "RequestBody")
									.equals(reader.getName())) {

						object.setRequestBody(RequestBody.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class BusinessHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * BusinessHeader Namespace URI = java:lguplus.u3.esb.common Namespace Prefix =
		 * ns1
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.common")) {
				return "ns1";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for ResultCode
		 */

		protected java.lang.String localResultCode;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultCode() {
			return localResultCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultCode
		 */
		public void setResultCode(java.lang.String param) {

			this.localResultCode = param;

		}

		/**
		 * field for ResultMessage
		 */

		protected java.lang.String localResultMessage;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResultMessage() {
			return localResultMessage;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ResultMessage
		 */
		public void setResultMessage(java.lang.String param) {

			this.localResultMessage = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					parentQName) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					BusinessHeader.this.serialize(parentQName, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.common");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":BusinessHeader", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "BusinessHeader",
							xmlWriter);
				}

			}

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ResultCode", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ResultCode");
				}

			} else {
				xmlWriter.writeStartElement("ResultCode");
			}

			if (localResultCode == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("ResultCode cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localResultCode);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.common";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "ResultMessage", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "ResultMessage");
				}

			} else {
				xmlWriter.writeStartElement("ResultMessage");
			}

			if (localResultMessage == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localResultMessage);

			}

			xmlWriter.writeEndElement();

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultCode"));

			if (localResultCode != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultCode));
			} else {
				throw new org.apache.axis2.databinding.ADBException("ResultCode cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultMessage"));

			elementList.add(localResultMessage == null ? null
					: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResultMessage));

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static BusinessHeader parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				BusinessHeader object = new BusinessHeader();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"BusinessHeader".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (BusinessHeader) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultCode")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setResultCode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.common", "ResultMessage")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setResultMessage(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RetrieveBillAcntForEntrSvc implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"RetrieveBillAcntForEntrSvc", "ns3");

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("http://lguplus/u3/esb")) {
				return "ns3";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for RequestRecord
		 */

		protected RequestRecord localRequestRecord;

		/**
		 * Auto generated getter method
		 * 
		 * @return RequestRecord
		 */
		public RequestRecord getRequestRecord() {
			return localRequestRecord;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RequestRecord
		 */
		public void setRequestRecord(RequestRecord param) {

			this.localRequestRecord = param;

		}

		/**
		 * isReaderMTOMAware
		 * 
		 * @return true if the reader supports MTOM
		 */
		public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
			boolean isReaderMTOMAware = false;

			try {
				isReaderMTOMAware = java.lang.Boolean.TRUE
						.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
			} catch (java.lang.IllegalArgumentException e) {
				isReaderMTOMAware = false;
			}
			return isReaderMTOMAware;
		}

		/**
		 *
		 * @param parentQName
		 * @param factory
		 * @return org.apache.axiom.om.OMElement
		 */
		public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
				final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

			org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
					MY_QNAME) {

				public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
						throws javax.xml.stream.XMLStreamException {
					RetrieveBillAcntForEntrSvc.this.serialize(MY_QNAME, factory, xmlWriter);
				}
			};
			return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME, factory, dataSource);

		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
			serialize(parentQName, factory, xmlWriter, false);
		}

		public void serialize(final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory,
				org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter, boolean serializeType)
				throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

			java.lang.String prefix = null;
			java.lang.String namespace = null;

			prefix = parentQName.getPrefix();
			namespace = parentQName.getNamespaceURI();

			if ((namespace != null) && (namespace.trim().length() > 0)) {
				java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
				if (writerPrefix != null) {
					xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
				} else {
					if (prefix == null) {
						prefix = generatePrefix(namespace);
					}

					xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);
				}
			} else {
				xmlWriter.writeStartElement(parentQName.getLocalPart());
			}

			if (serializeType) {

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://lguplus/u3/esb");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RetrieveBillAcntForEntrSvc", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"RetrieveBillAcntForEntrSvc", xmlWriter);
				}

			}

			if (localRequestRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestRecord cannot be null!!");
			}
			localRequestRecord.serialize(new javax.xml.namespace.QName("http://lguplus/u3/esb", "RequestRecord"),
					factory, xmlWriter);

			xmlWriter.writeEndElement();

		}

		/**
		 * Util method to write an attribute with the ns prefix
		 */
		private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
				java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			if (xmlWriter.getPrefix(namespace) == null) {
				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);

			}

			xmlWriter.writeAttribute(namespace, attName, attValue);

		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
				javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attValue);
			}
		}

		/**
		 * Util method to write an attribute without the ns prefix
		 */
		private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
				javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			java.lang.String attributeNamespace = qname.getNamespaceURI();
			java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
			if (attributePrefix == null) {
				attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
			}
			java.lang.String attributeValue;
			if (attributePrefix.trim().length() > 0) {
				attributeValue = attributePrefix + ":" + qname.getLocalPart();
			} else {
				attributeValue = qname.getLocalPart();
			}

			if (namespace.equals("")) {
				xmlWriter.writeAttribute(attName, attributeValue);
			} else {
				registerPrefix(xmlWriter, namespace);
				xmlWriter.writeAttribute(namespace, attName, attributeValue);
			}
		}

		/**
		 * method to handle Qnames
		 */

		private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String namespaceURI = qname.getNamespaceURI();
			if (namespaceURI != null) {
				java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
				if (prefix == null) {
					prefix = generatePrefix(namespaceURI);
					xmlWriter.writeNamespace(prefix, namespaceURI);
					xmlWriter.setPrefix(prefix, namespaceURI);
				}

				if (prefix.trim().length() > 0) {
					xmlWriter.writeCharacters(
							prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				} else {
					// i.e this is the default namespace
					xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
				}

			} else {
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}
		}

		private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
				throws javax.xml.stream.XMLStreamException {

			if (qnames != null) {
				// we have to store this data until last moment since it is not possible to
				// write any
				// namespace data after writing the charactor data
				java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
				java.lang.String namespaceURI = null;
				java.lang.String prefix = null;

				for (int i = 0; i < qnames.length; i++) {
					if (i > 0) {
						stringToWrite.append(" ");
					}
					namespaceURI = qnames[i].getNamespaceURI();
					if (namespaceURI != null) {
						prefix = xmlWriter.getPrefix(namespaceURI);
						if ((prefix == null) || (prefix.length() == 0)) {
							prefix = generatePrefix(namespaceURI);
							xmlWriter.writeNamespace(prefix, namespaceURI);
							xmlWriter.setPrefix(prefix, namespaceURI);
						}

						if (prefix.trim().length() > 0) {
							stringToWrite.append(prefix).append(":").append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						} else {
							stringToWrite.append(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
						}
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				}
				xmlWriter.writeCharacters(stringToWrite.toString());
			}

		}

		/**
		 * Register a namespace prefix
		 */
		private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
				throws javax.xml.stream.XMLStreamException {
			java.lang.String prefix = xmlWriter.getPrefix(namespace);

			if (prefix == null) {
				prefix = generatePrefix(namespace);

				while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
					prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
				}

				xmlWriter.writeNamespace(prefix, namespace);
				xmlWriter.setPrefix(prefix, namespace);
			}

			return prefix;
		}

		/**
		 * databinding method to get an XML representation of this object
		 *
		 */
		public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
				throws org.apache.axis2.databinding.ADBException {

			java.util.ArrayList elementList = new java.util.ArrayList();
			java.util.ArrayList attribList = new java.util.ArrayList();

			elementList.add(new javax.xml.namespace.QName("http://lguplus/u3/esb", "RequestRecord"));

			if (localRequestRecord == null) {
				throw new org.apache.axis2.databinding.ADBException("RequestRecord cannot be null!!");
			}
			elementList.add(localRequestRecord);

			return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(),
					attribList.toArray());

		}

		/**
		 * Factory class that keeps the parse method
		 */
		public static class Factory {

			/**
			 * static method to create the object Precondition: If this object is an
			 * element, the current or next start element starts this object and any
			 * intervening reader events are ignorable If this object is not an element, it
			 * is a complex type and the reader is at the event just after the outer start
			 * element Postcondition: If this object is an element, the reader is positioned
			 * at its end element If this object is a complex type, the reader is positioned
			 * at the end element of its outer element
			 */
			public static RetrieveBillAcntForEntrSvc parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				RetrieveBillAcntForEntrSvc object = new RetrieveBillAcntForEntrSvc();

				int event;
				java.lang.String nillableValue = null;
				java.lang.String prefix = "";
				java.lang.String namespaceuri = "";
				try {

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
						java.lang.String fullTypeName = reader
								.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
						if (fullTypeName != null) {
							java.lang.String nsPrefix = null;
							if (fullTypeName.indexOf(":") > -1) {
								nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
							}
							nsPrefix = nsPrefix == null ? "" : nsPrefix;

							java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

							if (!"RetrieveBillAcntForEntrSvc".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RetrieveBillAcntForEntrSvc) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("http://lguplus/u3/esb", "RequestRecord")
									.equals(reader.getName())) {

						object.setRequestRecord(RequestRecord.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an invalid parameter was
						// passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	private org.apache.axiom.om.OMElement toOM(
			lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc.MY_QNAME,
					factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param, java.lang.Class type,
			java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault {

		try {

			if (lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc.class
					.equals(type)) {

				return lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvc.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse.class
					.equals(type)) {

				return lguplus.u3.webservice.cm019.RetrieveBillAcntForEntrSvcServiceStub.RetrieveBillAcntForEntrSvcResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
