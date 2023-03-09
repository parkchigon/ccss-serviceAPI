
/**
 * SaveSvcRsvServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */
package lguplus.u3.webservice.sm066;

/*
*  SaveSvcRsvServiceStub java implementation
*/
@SuppressWarnings({"rawtypes", "serial", "deprecation", "unchecked", "unused"})
public class SaveSvcRsvServiceStub extends org.apache.axis2.client.Stub {
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
		_service = new org.apache.axis2.description.AxisService("SaveSvcRsvService" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[1];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://lguplus/u3/esb", "saveSvcRsv"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

	}

	// populates the faults
	private void populateFaults() {

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public SaveSvcRsvServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public SaveSvcRsvServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
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
	public SaveSvcRsvServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://172.22.14.79:15011/CSSI/SM/SaveSvcRsv");

	}

	/**
	 * Default Constructor
	 */
	public SaveSvcRsvServiceStub() throws org.apache.axis2.AxisFault {

		this("http://172.22.14.79:15011/CSSI/SM/SaveSvcRsv");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public SaveSvcRsvServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see lguplus.u3.webservice.sm066.SaveSvcRsvService#saveSvcRsv
	 * @param saveSvcRsv
	 * 
	 */

	public lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsvResponse saveSvcRsv(

			lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsv saveSvcRsv)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction("http://lguplus/u3/esb/SaveSvcRsvPortType/SaveSvcRsvRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
			_operationClient.getOptions().setTimeOutInMilliSeconds(10000);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), saveSvcRsv,
					optimizeContent(new javax.xml.namespace.QName("http://lguplus/u3/esb", "saveSvcRsv")));

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
					lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsvResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsvResponse) object;

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
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
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

	// http://172.22.14.79:15011/CSSI/SM/SaveSvcRsv
	public static class ESBHeader implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = ESBHeader
		 * Namespace URI = java:lguplus.u3.esb.common Namespace Prefix = ns2
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.common")) {
				return "ns2";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

					// Note all attributes that were handled. Used to differ
					// normal attributes
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
						// A start element we are not expecting indicates an
						// invalid parameter was passed
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
						// A start element we are not expecting indicates an
						// invalid parameter was passed
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
						// A start element we are not expecting indicates an
						// invalid parameter was passed
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

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
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

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
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

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsChkItemInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsChkItemInVO Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DevMode
		 */

		protected java.lang.String localDevMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevMode() {
			return localDevMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevMode
		 */
		public void setDevMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevModeTracker = true;
			} else {
				localDevModeTracker = true;

			}

			this.localDevMode = param;

		}

		/**
		 * field for ItemId
		 */

		protected java.lang.String localItemId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localItemIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getItemId() {
			return localItemId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ItemId
		 */
		public void setItemId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localItemIdTracker = true;
			} else {
				localItemIdTracker = true;

			}

			this.localItemId = param;

		}

		/**
		 * field for ManfSerialNo
		 */

		protected java.lang.String localManfSerialNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localManfSerialNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getManfSerialNo() {
			return localManfSerialNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ManfSerialNo
		 */
		public void setManfSerialNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localManfSerialNoTracker = true;
			} else {
				localManfSerialNoTracker = true;

			}

			this.localManfSerialNo = param;

		}

		/**
		 * field for DevChngRsnCd
		 */

		protected java.lang.String localDevChngRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevChngRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevChngRsnCd() {
			return localDevChngRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevChngRsnCd
		 */
		public void setDevChngRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevChngRsnCdTracker = true;
			} else {
				localDevChngRsnCdTracker = true;

			}

			this.localDevChngRsnCd = param;

		}

		/**
		 * field for EventCode
		 */

		protected java.lang.String localEventCode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEventCodeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEventCode() {
			return localEventCode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EventCode
		 */
		public void setEventCode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEventCodeTracker = true;
			} else {
				localEventCodeTracker = true;

			}

			this.localEventCode = param;

		}

		/**
		 * field for ItemStatus
		 */

		protected java.lang.String localItemStatus;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localItemStatusTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getItemStatus() {
			return localItemStatus;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ItemStatus
		 */
		public void setItemStatus(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localItemStatusTracker = true;
			} else {
				localItemStatusTracker = true;

			}

			this.localItemStatus = param;

		}

		/**
		 * field for CasNo
		 */

		protected java.lang.String localCasNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCasNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCasNo() {
			return localCasNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CasNo
		 */
		public void setCasNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCasNoTracker = true;
			} else {
				localCasNoTracker = true;

			}

			this.localCasNo = param;

		}

		/**
		 * field for NewTrxCd
		 */

		protected java.lang.String localNewTrxCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localNewTrxCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getNewTrxCd() {
			return localNewTrxCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            NewTrxCd
		 */
		public void setNewTrxCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localNewTrxCdTracker = true;
			} else {
				localNewTrxCdTracker = true;

			}

			this.localNewTrxCd = param;

		}

		/**
		 * field for ChipUseYn
		 */

		protected java.lang.String localChipUseYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localChipUseYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getChipUseYn() {
			return localChipUseYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ChipUseYn
		 */
		public void setChipUseYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localChipUseYnTracker = true;
			} else {
				localChipUseYnTracker = true;

			}

			this.localChipUseYn = param;

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
					DsChkItemInVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsChkItemInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsChkItemInVO",
							xmlWriter);
				}

			}
			if (localDevModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devMode");
					}

				} else {
					xmlWriter.writeStartElement("devMode");
				}

				if (localDevMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localItemIdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "itemId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "itemId");
					}

				} else {
					xmlWriter.writeStartElement("itemId");
				}

				if (localItemId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localItemId);

				}

				xmlWriter.writeEndElement();
			}
			if (localManfSerialNoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "manfSerialNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "manfSerialNo");
					}

				} else {
					xmlWriter.writeStartElement("manfSerialNo");
				}

				if (localManfSerialNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localManfSerialNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localDevChngRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devChngRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devChngRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("devChngRsnCd");
				}

				if (localDevChngRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevChngRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEventCodeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "eventCode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "eventCode");
					}

				} else {
					xmlWriter.writeStartElement("eventCode");
				}

				if (localEventCode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEventCode);

				}

				xmlWriter.writeEndElement();
			}
			if (localItemStatusTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "itemStatus", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "itemStatus");
					}

				} else {
					xmlWriter.writeStartElement("itemStatus");
				}

				if (localItemStatus == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localItemStatus);

				}

				xmlWriter.writeEndElement();
			}
			if (localCasNoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "casNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "casNo");
					}

				} else {
					xmlWriter.writeStartElement("casNo");
				}

				if (localCasNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCasNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localNewTrxCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "newTrxCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "newTrxCd");
					}

				} else {
					xmlWriter.writeStartElement("newTrxCd");
				}

				if (localNewTrxCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localNewTrxCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localChipUseYnTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "chipUseYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "chipUseYn");
					}

				} else {
					xmlWriter.writeStartElement("chipUseYn");
				}

				if (localChipUseYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localChipUseYn);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			if (localDevModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "devMode"));

				elementList.add(localDevMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevMode));
			}
			if (localItemIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "itemId"));

				elementList.add(localItemId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localItemId));
			}
			if (localManfSerialNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "manfSerialNo"));

				elementList.add(localManfSerialNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localManfSerialNo));
			}
			if (localDevChngRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "devChngRsnCd"));

				elementList.add(localDevChngRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevChngRsnCd));
			}
			if (localEventCodeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "eventCode"));

				elementList.add(localEventCode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEventCode));
			}
			if (localItemStatusTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "itemStatus"));

				elementList.add(localItemStatus == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localItemStatus));
			}
			if (localCasNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "casNo"));

				elementList.add(localCasNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCasNo));
			}
			if (localNewTrxCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "newTrxCd"));

				elementList.add(localNewTrxCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNewTrxCd));
			}
			if (localChipUseYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "chipUseYn"));

				elementList.add(localChipUseYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChipUseYn));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId"));

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
			public static DsChkItemInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsChkItemInVO object = new DsChkItemInVO();

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

							if (!"DsChkItemInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsChkItemInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "devMode")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "itemId")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setItemId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "manfSerialNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setManfSerialNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "devChngRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevChngRsnCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "eventCode")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEventCode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "itemStatus")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setItemStatus(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "casNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCasNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "newTrxCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setNewTrxCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "chipUseYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setChipUseYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsSaveSvcInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsSaveSvcInVO Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for BillAcntNo
		 */

		protected java.lang.String localBillAcntNo;

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

			this.localBillAcntNo = param;

		}

		/**
		 * field for EntrNo
		 */

		protected java.lang.String localEntrNo;

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

			this.localEntrNo = param;

		}

		/**
		 * field for ProdNo
		 */

		protected java.lang.String localProdNo;

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

			this.localProdNo = param;

		}

		/**
		 * field for HldrCustNo
		 */

		protected java.lang.String localHldrCustNo;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getHldrCustNo() {
			return localHldrCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            HldrCustNo
		 */
		public void setHldrCustNo(java.lang.String param) {

			this.localHldrCustNo = param;

		}

		/**
		 * field for SaleEmpno
		 */

		protected java.lang.String localSaleEmpno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSaleEmpnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSaleEmpno() {
			return localSaleEmpno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SaleEmpno
		 */
		public void setSaleEmpno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSaleEmpnoTracker = true;
			} else {
				localSaleEmpnoTracker = true;

			}

			this.localSaleEmpno = param;

		}

		/**
		 * field for SvcDutyUseMnthCnt
		 */

		protected java.lang.String localSvcDutyUseMnthCnt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseMnthCntTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseMnthCnt() {
			return localSvcDutyUseMnthCnt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseMnthCnt
		 */
		public void setSvcDutyUseMnthCnt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseMnthCntTracker = true;
			} else {
				localSvcDutyUseMnthCntTracker = true;

			}

			this.localSvcDutyUseMnthCnt = param;

		}

		/**
		 * field for SvcDutyUseDvCd
		 */

		protected java.lang.String localSvcDutyUseDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseDvCd() {
			return localSvcDutyUseDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseDvCd
		 */
		public void setSvcDutyUseDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseDvCdTracker = true;
			} else {
				localSvcDutyUseDvCdTracker = true;

			}

			this.localSvcDutyUseDvCd = param;

		}

		/**
		 * field for SvcDutyUseStrtDt
		 */

		protected java.lang.String localSvcDutyUseStrtDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseStrtDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseStrtDt() {
			return localSvcDutyUseStrtDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseStrtDt
		 */
		public void setSvcDutyUseStrtDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseStrtDtTracker = true;
			} else {
				localSvcDutyUseStrtDtTracker = true;

			}

			this.localSvcDutyUseStrtDt = param;

		}

		/**
		 * field for SvcDutyUseEndDt
		 */

		protected java.lang.String localSvcDutyUseEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseEndDt() {
			return localSvcDutyUseEndDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseEndDt
		 */
		public void setSvcDutyUseEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseEndDtTracker = true;
			} else {
				localSvcDutyUseEndDtTracker = true;

			}

			this.localSvcDutyUseEndDt = param;

		}

		/**
		 * field for RgstDlrCd
		 */

		protected java.lang.String localRgstDlrCd;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRgstDlrCd() {
			return localRgstDlrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RgstDlrCd
		 */
		public void setRgstDlrCd(java.lang.String param) {

			this.localRgstDlrCd = param;

		}

		/**
		 * field for RjnDt
		 */

		protected java.lang.String localRjnDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRjnDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRjnDt() {
			return localRjnDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RjnDt
		 */
		public void setRjnDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRjnDtTracker = true;
			} else {
				localRjnDtTracker = true;

			}

			this.localRjnDt = param;

		}

		/**
		 * field for RunDttm
		 */

		protected java.lang.String localRunDttm;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRunDttm() {
			return localRunDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RunDttm
		 */
		public void setRunDttm(java.lang.String param) {

			this.localRunDttm = param;

		}

		/**
		 * field for NoGuidPrcType
		 */

		protected java.lang.String localNoGuidPrcType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localNoGuidPrcTypeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getNoGuidPrcType() {
			return localNoGuidPrcType;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            NoGuidPrcType
		 */
		public void setNoGuidPrcType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localNoGuidPrcTypeTracker = true;
			} else {
				localNoGuidPrcTypeTracker = true;

			}

			this.localNoGuidPrcType = param;

		}

		/**
		 * field for PrcType
		 */

		protected java.lang.String localPrcType;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPrcType() {
			return localPrcType;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PrcType
		 */
		public void setPrcType(java.lang.String param) {

			this.localPrcType = param;

		}

		/**
		 * field for PrcSubType
		 */

		protected java.lang.String localPrcSubType;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPrcSubTypeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPrcSubType() {
			return localPrcSubType;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PrcSubType
		 */
		public void setPrcSubType(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPrcSubTypeTracker = true;
			} else {
				localPrcSubTypeTracker = true;

			}

			this.localPrcSubType = param;

		}

		/**
		 * field for PrcMode
		 */

		protected java.lang.String localPrcMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPrcModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPrcMode() {
			return localPrcMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PrcMode
		 */
		public void setPrcMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPrcModeTracker = true;
			} else {
				localPrcModeTracker = true;

			}

			this.localPrcMode = param;

		}

		/**
		 * field for PrcSubMode
		 */

		protected java.lang.String localPrcSubMode;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPrcSubMode() {
			return localPrcSubMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PrcSubMode
		 */
		public void setPrcSubMode(java.lang.String param) {

			this.localPrcSubMode = param;

		}

		/**
		 * field for PosCd
		 */

		protected java.lang.String localPosCd;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPosCd() {
			return localPosCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PosCd
		 */
		public void setPosCd(java.lang.String param) {

			this.localPosCd = param;

		}

		/**
		 * field for RsalePosCd
		 */

		protected java.lang.String localRsalePosCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRsalePosCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRsalePosCd() {
			return localRsalePosCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RsalePosCd
		 */
		public void setRsalePosCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRsalePosCdTracker = true;
			} else {
				localRsalePosCdTracker = true;

			}

			this.localRsalePosCd = param;

		}

		/**
		 * field for NewTrxYn
		 */

		protected java.lang.String localNewTrxYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localNewTrxYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getNewTrxYn() {
			return localNewTrxYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            NewTrxYn
		 */
		public void setNewTrxYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localNewTrxYnTracker = true;
			} else {
				localNewTrxYnTracker = true;

			}

			this.localNewTrxYn = param;

		}

		/**
		 * field for ItemTrx
		 */

		protected java.lang.String localItemTrx;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localItemTrxTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getItemTrx() {
			return localItemTrx;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ItemTrx
		 */
		public void setItemTrx(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localItemTrxTracker = true;
			} else {
				localItemTrxTracker = true;

			}

			this.localItemTrx = param;

		}

		/**
		 * field for SvcCd
		 */

		protected java.lang.String localSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcCd() {
			return localSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcCd
		 */
		public void setSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcCdTracker = true;
			} else {
				localSvcCdTracker = true;

			}

			this.localSvcCd = param;

		}

		/**
		 * field for SvcNm
		 */

		protected java.lang.String localSvcNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcNm() {
			return localSvcNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcNm
		 */
		public void setSvcNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcNmTracker = true;
			} else {
				localSvcNmTracker = true;

			}

			this.localSvcNm = param;

		}

		/**
		 * field for KongUppChrgAmt
		 */

		protected java.lang.String localKongUppChrgAmt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localKongUppChrgAmtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getKongUppChrgAmt() {
			return localKongUppChrgAmt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            KongUppChrgAmt
		 */
		public void setKongUppChrgAmt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localKongUppChrgAmtTracker = true;
			} else {
				localKongUppChrgAmtTracker = true;

			}

			this.localKongUppChrgAmt = param;

		}

		/**
		 * field for UserMemo
		 */

		protected java.lang.String localUserMemo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserMemoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserMemo() {
			return localUserMemo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserMemo
		 */
		public void setUserMemo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserMemoTracker = true;
			} else {
				localUserMemoTracker = true;

			}

			this.localUserMemo = param;

		}

		/**
		 * field for EntrSttsCd
		 */

		protected java.lang.String localEntrSttsCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsCd() {
			return localEntrSttsCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsCd
		 */
		public void setEntrSttsCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsCdTracker = true;
			} else {
				localEntrSttsCdTracker = true;

			}

			this.localEntrSttsCd = param;

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
					DsSaveSvcInVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsSaveSvcInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsSaveSvcInVO",
							xmlWriter);
				}

			}

			namespace = "java:lguplus.u3.esb.sm066";
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

				throw new org.apache.axis2.databinding.ADBException("billAcntNo cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localBillAcntNo);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.sm066";
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

				throw new org.apache.axis2.databinding.ADBException("entrNo cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localEntrNo);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.sm066";
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

				throw new org.apache.axis2.databinding.ADBException("prodNo cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localProdNo);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.sm066";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "hldrCustNo", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "hldrCustNo");
				}

			} else {
				xmlWriter.writeStartElement("hldrCustNo");
			}

			if (localHldrCustNo == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("hldrCustNo cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localHldrCustNo);

			}

			xmlWriter.writeEndElement();
			if (localSaleEmpnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "saleEmpno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "saleEmpno");
					}

				} else {
					xmlWriter.writeStartElement("saleEmpno");
				}

				if (localSaleEmpno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSaleEmpno);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseMnthCntTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseMnthCnt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseMnthCnt");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseMnthCnt");
				}

				if (localSvcDutyUseMnthCnt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseMnthCnt);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseDvCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseDvCd");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseDvCd");
				}

				if (localSvcDutyUseDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseStrtDtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseStrtDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseStrtDt");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseStrtDt");
				}

				if (localSvcDutyUseStrtDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseStrtDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseEndDtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseEndDt");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseEndDt");
				}

				if (localSvcDutyUseEndDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseEndDt);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "rgstDlrCd", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "rgstDlrCd");
				}

			} else {
				xmlWriter.writeStartElement("rgstDlrCd");
			}

			if (localRgstDlrCd == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("rgstDlrCd cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localRgstDlrCd);

			}

			xmlWriter.writeEndElement();
			if (localRjnDtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rjnDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rjnDt");
					}

				} else {
					xmlWriter.writeStartElement("rjnDt");
				}

				if (localRjnDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRjnDt);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "runDttm", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "runDttm");
				}

			} else {
				xmlWriter.writeStartElement("runDttm");
			}

			if (localRunDttm == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("runDttm cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localRunDttm);

			}

			xmlWriter.writeEndElement();
			if (localNoGuidPrcTypeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "noGuidPrcType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "noGuidPrcType");
					}

				} else {
					xmlWriter.writeStartElement("noGuidPrcType");
				}

				if (localNoGuidPrcType == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localNoGuidPrcType);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "prcType", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "prcType");
				}

			} else {
				xmlWriter.writeStartElement("prcType");
			}

			if (localPrcType == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("prcType cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localPrcType);

			}

			xmlWriter.writeEndElement();
			if (localPrcSubTypeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prcSubType", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prcSubType");
					}

				} else {
					xmlWriter.writeStartElement("prcSubType");
				}

				if (localPrcSubType == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPrcSubType);

				}

				xmlWriter.writeEndElement();
			}
			if (localPrcModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prcMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prcMode");
					}

				} else {
					xmlWriter.writeStartElement("prcMode");
				}

				if (localPrcMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPrcMode);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "prcSubMode", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "prcSubMode");
				}

			} else {
				xmlWriter.writeStartElement("prcSubMode");
			}

			if (localPrcSubMode == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("prcSubMode cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localPrcSubMode);

			}

			xmlWriter.writeEndElement();

			namespace = "java:lguplus.u3.esb.sm066";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "posCd", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "posCd");
				}

			} else {
				xmlWriter.writeStartElement("posCd");
			}

			if (localPosCd == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("posCd cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localPosCd);

			}

			xmlWriter.writeEndElement();
			if (localRsalePosCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rsalePosCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rsalePosCd");
					}

				} else {
					xmlWriter.writeStartElement("rsalePosCd");
				}

				if (localRsalePosCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRsalePosCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localNewTrxYnTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "newTrxYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "newTrxYn");
					}

				} else {
					xmlWriter.writeStartElement("newTrxYn");
				}

				if (localNewTrxYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localNewTrxYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localItemTrxTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "itemTrx", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "itemTrx");
					}

				} else {
					xmlWriter.writeStartElement("itemTrx");
				}

				if (localItemTrx == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localItemTrx);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcCd");
					}

				} else {
					xmlWriter.writeStartElement("svcCd");
				}

				if (localSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcNmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcNm");
					}

				} else {
					xmlWriter.writeStartElement("svcNm");
				}

				if (localSvcNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localKongUppChrgAmtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "kongUppChrgAmt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "kongUppChrgAmt");
					}

				} else {
					xmlWriter.writeStartElement("kongUppChrgAmt");
				}

				if (localKongUppChrgAmt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localKongUppChrgAmt);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserMemoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userMemo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userMemo");
					}

				} else {
					xmlWriter.writeStartElement("userMemo");
				}

				if (localUserMemo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserMemo);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsCd");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsCd");
				}

				if (localEntrSttsCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsCd);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billAcntNo"));

			if (localBillAcntNo != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntNo));
			} else {
				throw new org.apache.axis2.databinding.ADBException("billAcntNo cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrNo"));

			if (localEntrNo != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			} else {
				throw new org.apache.axis2.databinding.ADBException("entrNo cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prodNo"));

			if (localProdNo != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNo));
			} else {
				throw new org.apache.axis2.databinding.ADBException("prodNo cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hldrCustNo"));

			if (localHldrCustNo != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHldrCustNo));
			} else {
				throw new org.apache.axis2.databinding.ADBException("hldrCustNo cannot be null!!");
			}
			if (localSaleEmpnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "saleEmpno"));

				elementList.add(localSaleEmpno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSaleEmpno));
			}
			if (localSvcDutyUseMnthCntTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseMnthCnt"));

				elementList.add(localSvcDutyUseMnthCnt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseMnthCnt));
			}
			if (localSvcDutyUseDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseDvCd"));

				elementList.add(localSvcDutyUseDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseDvCd));
			}
			if (localSvcDutyUseStrtDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseStrtDt"));

				elementList.add(localSvcDutyUseStrtDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseStrtDt));
			}
			if (localSvcDutyUseEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseEndDt"));

				elementList.add(localSvcDutyUseEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseEndDt));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rgstDlrCd"));

			if (localRgstDlrCd != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRgstDlrCd));
			} else {
				throw new org.apache.axis2.databinding.ADBException("rgstDlrCd cannot be null!!");
			}
			if (localRjnDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rjnDt"));

				elementList.add(localRjnDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRjnDt));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "runDttm"));

			if (localRunDttm != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRunDttm));
			} else {
				throw new org.apache.axis2.databinding.ADBException("runDttm cannot be null!!");
			}
			if (localNoGuidPrcTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "noGuidPrcType"));

				elementList.add(localNoGuidPrcType == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNoGuidPrcType));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcType"));

			if (localPrcType != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrcType));
			} else {
				throw new org.apache.axis2.databinding.ADBException("prcType cannot be null!!");
			}
			if (localPrcSubTypeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcSubType"));

				elementList.add(localPrcSubType == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrcSubType));
			}
			if (localPrcModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcMode"));

				elementList.add(localPrcMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrcMode));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcSubMode"));

			if (localPrcSubMode != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrcSubMode));
			} else {
				throw new org.apache.axis2.databinding.ADBException("prcSubMode cannot be null!!");
			}

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "posCd"));

			if (localPosCd != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPosCd));
			} else {
				throw new org.apache.axis2.databinding.ADBException("posCd cannot be null!!");
			}
			if (localRsalePosCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsalePosCd"));

				elementList.add(localRsalePosCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRsalePosCd));
			}
			if (localNewTrxYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "newTrxYn"));

				elementList.add(localNewTrxYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNewTrxYn));
			}
			if (localItemTrxTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "itemTrx"));

				elementList.add(localItemTrx == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localItemTrx));
			}
			if (localSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcCd"));

				elementList.add(localSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcCd));
			}
			if (localSvcNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcNm"));

				elementList.add(localSvcNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcNm));
			}
			if (localKongUppChrgAmtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "kongUppChrgAmt"));

				elementList.add(localKongUppChrgAmt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localKongUppChrgAmt));
			}
			if (localUserMemoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "userMemo"));

				elementList.add(localUserMemo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserMemo));
			}
			if (localEntrSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSttsCd"));

				elementList.add(localEntrSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsCd));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId"));

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
			public static DsSaveSvcInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsSaveSvcInVO object = new DsSaveSvcInVO();

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

							if (!"DsSaveSvcInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsSaveSvcInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billAcntNo")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setBillAcntNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrNo")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setEntrNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prodNo")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setProdNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hldrCustNo")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setHldrCustNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "saleEmpno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSaleEmpno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseMnthCnt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseMnthCnt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseDvCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseStrtDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseStrtDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseEndDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseEndDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rgstDlrCd")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRgstDlrCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rjnDt")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRjnDt(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "runDttm")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setRunDttm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "noGuidPrcType")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setNoGuidPrcType(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcType")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPrcType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcSubType")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPrcSubType(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcMode")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPrcMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "prcSubMode")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPrcSubMode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "posCd")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setPosCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsalePosCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRsalePosCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "newTrxYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setNewTrxYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "itemTrx")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setItemTrx(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "kongUppChrgAmt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setKongUppChrgAmt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "userMemo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserMemo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
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
		 * ns2
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.common")) {
				return "ns2";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

					// Note all attributes that were handled. Used to differ
					// normal attributes
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
						// A start element we are not expecting indicates an
						// invalid parameter was passed
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

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsConfldsInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsConfldsInVO Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Directive
		 */

		protected java.lang.String localDirective;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDirectiveTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDirective() {
			return localDirective;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Directive
		 */
		public void setDirective(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDirectiveTracker = true;
			} else {
				localDirectiveTracker = true;

			}

			this.localDirective = param;

		}

		/**
		 * field for RunDate
		 */

		protected java.lang.String localRunDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRunDateTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRunDate() {
			return localRunDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RunDate
		 */
		public void setRunDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRunDateTracker = true;
			} else {
				localRunDateTracker = true;

			}

			this.localRunDate = param;

		}

		/**
		 * field for RunDateDtm
		 */

		protected java.lang.String localRunDateDtm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRunDateDtmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRunDateDtm() {
			return localRunDateDtm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RunDateDtm
		 */
		public void setRunDateDtm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRunDateDtmTracker = true;
			} else {
				localRunDateDtmTracker = true;

			}

			this.localRunDateDtm = param;

		}

		/**
		 * field for TransactionMode
		 */

		protected java.lang.String localTransactionMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localTransactionModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getTransactionMode() {
			return localTransactionMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            TransactionMode
		 */
		public void setTransactionMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTransactionModeTracker = true;
			} else {
				localTransactionModeTracker = true;

			}

			this.localTransactionMode = param;

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
		 * field for EntrDlUpdateStamp
		 */

		protected java.lang.String localEntrDlUpdateStamp;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrDlUpdateStampTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrDlUpdateStamp() {
			return localEntrDlUpdateStamp;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrDlUpdateStamp
		 */
		public void setEntrDlUpdateStamp(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrDlUpdateStampTracker = true;
			} else {
				localEntrDlUpdateStampTracker = true;

			}

			this.localEntrDlUpdateStamp = param;

		}

		/**
		 * field for EntrSysUpdateDate
		 */

		protected java.lang.String localEntrSysUpdateDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSysUpdateDateTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSysUpdateDate() {
			return localEntrSysUpdateDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSysUpdateDate
		 */
		public void setEntrSysUpdateDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSysUpdateDateTracker = true;
			} else {
				localEntrSysUpdateDateTracker = true;

			}

			this.localEntrSysUpdateDate = param;

		}

		/**
		 * field for Aceno
		 */

		protected java.lang.String localAceno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAcenoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAceno() {
			return localAceno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Aceno
		 */
		public void setAceno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcenoTracker = true;
			} else {
				localAcenoTracker = true;

			}

			this.localAceno = param;

		}

		/**
		 * field for CntcDlUpdateStamp
		 */

		protected java.lang.String localCntcDlUpdateStamp;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCntcDlUpdateStampTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCntcDlUpdateStamp() {
			return localCntcDlUpdateStamp;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CntcDlUpdateStamp
		 */
		public void setCntcDlUpdateStamp(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCntcDlUpdateStampTracker = true;
			} else {
				localCntcDlUpdateStampTracker = true;

			}

			this.localCntcDlUpdateStamp = param;

		}

		/**
		 * field for CntcSysUpdateDate
		 */

		protected java.lang.String localCntcSysUpdateDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCntcSysUpdateDateTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCntcSysUpdateDate() {
			return localCntcSysUpdateDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CntcSysUpdateDate
		 */
		public void setCntcSysUpdateDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCntcSysUpdateDateTracker = true;
			} else {
				localCntcSysUpdateDateTracker = true;

			}

			this.localCntcSysUpdateDate = param;

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
		 * field for BillDlUpdateStamp
		 */

		protected java.lang.String localBillDlUpdateStamp;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillDlUpdateStampTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillDlUpdateStamp() {
			return localBillDlUpdateStamp;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillDlUpdateStamp
		 */
		public void setBillDlUpdateStamp(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillDlUpdateStampTracker = true;
			} else {
				localBillDlUpdateStampTracker = true;

			}

			this.localBillDlUpdateStamp = param;

		}

		/**
		 * field for BillSysUpdateDate
		 */

		protected java.lang.String localBillSysUpdateDate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBillSysUpdateDateTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBillSysUpdateDate() {
			return localBillSysUpdateDate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BillSysUpdateDate
		 */
		public void setBillSysUpdateDate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBillSysUpdateDateTracker = true;
			} else {
				localBillSysUpdateDateTracker = true;

			}

			this.localBillSysUpdateDate = param;

		}

		/**
		 * field for CnId
		 */

		protected java.lang.String localCnId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCnIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCnId() {
			return localCnId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CnId
		 */
		public void setCnId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCnIdTracker = true;
			} else {
				localCnIdTracker = true;

			}

			this.localCnId = param;

		}

		/**
		 * field for LockMode
		 */

		protected java.lang.String localLockMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLockModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLockMode() {
			return localLockMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LockMode
		 */
		public void setLockMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLockModeTracker = true;
			} else {
				localLockModeTracker = true;

			}

			this.localLockMode = param;

		}

		/**
		 * field for UserWorkDlrCd
		 */

		protected java.lang.String localUserWorkDlrCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserWorkDlrCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserWorkDlrCd() {
			return localUserWorkDlrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserWorkDlrCd
		 */
		public void setUserWorkDlrCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserWorkDlrCdTracker = true;
			} else {
				localUserWorkDlrCdTracker = true;

			}

			this.localUserWorkDlrCd = param;

		}

		/**
		 * field for UserWorkDlrNm
		 */

		protected java.lang.String localUserWorkDlrNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserWorkDlrNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserWorkDlrNm() {
			return localUserWorkDlrNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserWorkDlrNm
		 */
		public void setUserWorkDlrNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserWorkDlrNmTracker = true;
			} else {
				localUserWorkDlrNmTracker = true;

			}

			this.localUserWorkDlrNm = param;

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
					DsConfldsInVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsConfldsInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsConfldsInVO",
							xmlWriter);
				}

			}
			if (localDirectiveTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "directive", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "directive");
					}

				} else {
					xmlWriter.writeStartElement("directive");
				}

				if (localDirective == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDirective);

				}

				xmlWriter.writeEndElement();
			}
			if (localRunDateTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "runDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "runDate");
					}

				} else {
					xmlWriter.writeStartElement("runDate");
				}

				if (localRunDate == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRunDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localRunDateDtmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "runDateDtm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "runDateDtm");
					}

				} else {
					xmlWriter.writeStartElement("runDateDtm");
				}

				if (localRunDateDtm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRunDateDtm);

				}

				xmlWriter.writeEndElement();
			}
			if (localTransactionModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "transactionMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "transactionMode");
					}

				} else {
					xmlWriter.writeStartElement("transactionMode");
				}

				if (localTransactionMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localTransactionMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
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
			if (localEntrDlUpdateStampTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrDlUpdateStamp", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrDlUpdateStamp");
					}

				} else {
					xmlWriter.writeStartElement("entrDlUpdateStamp");
				}

				if (localEntrDlUpdateStamp == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrDlUpdateStamp);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSysUpdateDateTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSysUpdateDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSysUpdateDate");
					}

				} else {
					xmlWriter.writeStartElement("entrSysUpdateDate");
				}

				if (localEntrSysUpdateDate == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSysUpdateDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcenoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "aceno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "aceno");
					}

				} else {
					xmlWriter.writeStartElement("aceno");
				}

				if (localAceno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAceno);

				}

				xmlWriter.writeEndElement();
			}
			if (localCntcDlUpdateStampTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cntcDlUpdateStamp", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cntcDlUpdateStamp");
					}

				} else {
					xmlWriter.writeStartElement("cntcDlUpdateStamp");
				}

				if (localCntcDlUpdateStamp == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCntcDlUpdateStamp);

				}

				xmlWriter.writeEndElement();
			}
			if (localCntcSysUpdateDateTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cntcSysUpdateDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cntcSysUpdateDate");
					}

				} else {
					xmlWriter.writeStartElement("cntcSysUpdateDate");
				}

				if (localCntcSysUpdateDate == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCntcSysUpdateDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
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
			if (localBillDlUpdateStampTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billDlUpdateStamp", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billDlUpdateStamp");
					}

				} else {
					xmlWriter.writeStartElement("billDlUpdateStamp");
				}

				if (localBillDlUpdateStamp == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillDlUpdateStamp);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillSysUpdateDateTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "billSysUpdateDate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "billSysUpdateDate");
					}

				} else {
					xmlWriter.writeStartElement("billSysUpdateDate");
				}

				if (localBillSysUpdateDate == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBillSysUpdateDate);

				}

				xmlWriter.writeEndElement();
			}
			if (localCnIdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cnId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cnId");
					}

				} else {
					xmlWriter.writeStartElement("cnId");
				}

				if (localCnId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCnId);

				}

				xmlWriter.writeEndElement();
			}
			if (localLockModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "lockMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "lockMode");
					}

				} else {
					xmlWriter.writeStartElement("lockMode");
				}

				if (localLockMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLockMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserWorkDlrCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userWorkDlrCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userWorkDlrCd");
					}

				} else {
					xmlWriter.writeStartElement("userWorkDlrCd");
				}

				if (localUserWorkDlrCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserWorkDlrCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserWorkDlrNmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userWorkDlrNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userWorkDlrNm");
					}

				} else {
					xmlWriter.writeStartElement("userWorkDlrNm");
				}

				if (localUserWorkDlrNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserWorkDlrNm);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			if (localDirectiveTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "directive"));

				elementList.add(localDirective == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDirective));
			}
			if (localRunDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "runDate"));

				elementList.add(localRunDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRunDate));
			}
			if (localRunDateDtmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "runDateDtm"));

				elementList.add(localRunDateDtm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRunDateDtm));
			}
			if (localTransactionModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "transactionMode"));

				elementList.add(localTransactionMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransactionMode));
			}
			if (localEntrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			if (localEntrDlUpdateStampTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrDlUpdateStamp"));

				elementList.add(localEntrDlUpdateStamp == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrDlUpdateStamp));
			}
			if (localEntrSysUpdateDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSysUpdateDate"));

				elementList.add(localEntrSysUpdateDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSysUpdateDate));
			}
			if (localAcenoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "aceno"));

				elementList.add(localAceno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAceno));
			}
			if (localCntcDlUpdateStampTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "cntcDlUpdateStamp"));

				elementList.add(localCntcDlUpdateStamp == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntcDlUpdateStamp));
			}
			if (localCntcSysUpdateDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "cntcSysUpdateDate"));

				elementList.add(localCntcSysUpdateDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntcSysUpdateDate));
			}
			if (localBillAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billAcntNo"));

				elementList.add(localBillAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntNo));
			}
			if (localBillDlUpdateStampTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billDlUpdateStamp"));

				elementList.add(localBillDlUpdateStamp == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillDlUpdateStamp));
			}
			if (localBillSysUpdateDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billSysUpdateDate"));

				elementList.add(localBillSysUpdateDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillSysUpdateDate));
			}
			if (localCnIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "cnId"));

				elementList.add(localCnId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCnId));
			}
			if (localLockModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "lockMode"));

				elementList.add(localLockMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLockMode));
			}
			if (localUserWorkDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "userWorkDlrCd"));

				elementList.add(localUserWorkDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserWorkDlrCd));
			}
			if (localUserWorkDlrNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "userWorkDlrNm"));

				elementList.add(localUserWorkDlrNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserWorkDlrNm));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId"));

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
			public static DsConfldsInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsConfldsInVO object = new DsConfldsInVO();

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

							if (!"DsConfldsInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsConfldsInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "directive")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDirective(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "runDate")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRunDate(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "runDateDtm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRunDateDtm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "transactionMode")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setTransactionMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrDlUpdateStamp")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrDlUpdateStamp(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSysUpdateDate")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSysUpdateDate(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "aceno")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAceno(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "cntcDlUpdateStamp")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCntcDlUpdateStamp(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "cntcSysUpdateDate")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCntcSysUpdateDate(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billAcntNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntNo(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billDlUpdateStamp")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillDlUpdateStamp(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "billSysUpdateDate")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillSysUpdateDate(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "cnId")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCnId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "lockMode")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLockMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "userWorkDlrCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserWorkDlrCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "userWorkDlrNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserWorkDlrNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
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
		 * ResponseRecord Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
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
				localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ESBHeader"),
						factory, xmlWriter);
			}
			if (localBusinessHeaderTracker) {
				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				localBusinessHeader.serialize(
						new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "BusinessHeader"), factory,
						xmlWriter);
			}
			if (localResponseBodyTracker) {
				if (localResponseBody == null) {
					throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
				}
				localResponseBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ResponseBody"),
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
				// we have to store this data until last moment since it is not
				// possible to write any
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
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ESBHeader"));

				if (localESBHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
				}
				elementList.add(localESBHeader);
			}
			if (localBusinessHeaderTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "BusinessHeader"));

				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				elementList.add(localBusinessHeader);
			}
			if (localResponseBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ResponseBody"));

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

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ESBHeader")
									.equals(reader.getName())) {

						object.setESBHeader(ESBHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "BusinessHeader")
									.equals(reader.getName())) {

						object.setBusinessHeader(BusinessHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ResponseBody")
									.equals(reader.getName())) {

						object.setResponseBody(ResponseBody.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class RequestRecord implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * RequestRecord Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
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
			localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ESBHeader"), factory,
					xmlWriter);
			if (localRequestBodyTracker) {
				if (localRequestBody == null) {
					throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
				}
				localRequestBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "RequestBody"),
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ESBHeader"));

			if (localESBHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
			}
			elementList.add(localESBHeader);
			if (localRequestBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "RequestBody"));

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

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ESBHeader")
									.equals(reader.getName())) {

						object.setESBHeader(ESBHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "RequestBody")
									.equals(reader.getName())) {

						object.setRequestBody(RequestBody.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsChkSvcRsvInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsChkSvcRsvInVO Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for SvcCd
		 */

		protected java.lang.String localSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcCd() {
			return localSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcCd
		 */
		public void setSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcCdTracker = true;
			} else {
				localSvcCdTracker = true;

			}

			this.localSvcCd = param;

		}

		/**
		 * field for SvcNm
		 */

		protected java.lang.String localSvcNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcNm() {
			return localSvcNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcNm
		 */
		public void setSvcNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcNmTracker = true;
			} else {
				localSvcNmTracker = true;

			}

			this.localSvcNm = param;

		}

		/**
		 * field for EntrSvcSeqno
		 */

		protected java.lang.String localEntrSvcSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSvcSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSvcSeqno() {
			return localEntrSvcSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSvcSeqno
		 */
		public void setEntrSvcSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSvcSeqnoTracker = true;
			} else {
				localEntrSvcSeqnoTracker = true;

			}

			this.localEntrSvcSeqno = param;

		}

		/**
		 * field for HposSvcCd
		 */

		protected java.lang.String localHposSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localHposSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getHposSvcCd() {
			return localHposSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            HposSvcCd
		 */
		public void setHposSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHposSvcCdTracker = true;
			} else {
				localHposSvcCdTracker = true;

			}

			this.localHposSvcCd = param;

		}

		/**
		 * field for HposEntrSvcSeqno
		 */

		protected java.lang.String localHposEntrSvcSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localHposEntrSvcSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getHposEntrSvcSeqno() {
			return localHposEntrSvcSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            HposEntrSvcSeqno
		 */
		public void setHposEntrSvcSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHposEntrSvcSeqnoTracker = true;
			} else {
				localHposEntrSvcSeqnoTracker = true;

			}

			this.localHposEntrSvcSeqno = param;

		}

		/**
		 * field for SvcKdCd
		 */

		protected java.lang.String localSvcKdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcKdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcKdCd() {
			return localSvcKdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcKdCd
		 */
		public void setSvcKdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcKdCdTracker = true;
			} else {
				localSvcKdCdTracker = true;

			}

			this.localSvcKdCd = param;

		}

		/**
		 * field for SvcAplyLvlCd
		 */

		protected java.lang.String localSvcAplyLvlCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcAplyLvlCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcAplyLvlCd() {
			return localSvcAplyLvlCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcAplyLvlCd
		 */
		public void setSvcAplyLvlCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcAplyLvlCdTracker = true;
			} else {
				localSvcAplyLvlCdTracker = true;

			}

			this.localSvcAplyLvlCd = param;

		}

		/**
		 * field for SvcFrstStrtDttm
		 */

		protected java.lang.String localSvcFrstStrtDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcFrstStrtDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcFrstStrtDttm() {
			return localSvcFrstStrtDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcFrstStrtDttm
		 */
		public void setSvcFrstStrtDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcFrstStrtDttmTracker = true;
			} else {
				localSvcFrstStrtDttmTracker = true;

			}

			this.localSvcFrstStrtDttm = param;

		}

		/**
		 * field for SvcStrtRgstDlrCd
		 */

		protected java.lang.String localSvcStrtRgstDlrCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcStrtRgstDlrCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcStrtRgstDlrCd() {
			return localSvcStrtRgstDlrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcStrtRgstDlrCd
		 */
		public void setSvcStrtRgstDlrCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcStrtRgstDlrCdTracker = true;
			} else {
				localSvcStrtRgstDlrCdTracker = true;

			}

			this.localSvcStrtRgstDlrCd = param;

		}

		/**
		 * field for SvcStrtDttm
		 */

		protected java.lang.String localSvcStrtDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcStrtDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcStrtDttm() {
			return localSvcStrtDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcStrtDttm
		 */
		public void setSvcStrtDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcStrtDttmTracker = true;
			} else {
				localSvcStrtDttmTracker = true;

			}

			this.localSvcStrtDttm = param;

		}

		/**
		 * field for SvcEndDttm
		 */

		protected java.lang.String localSvcEndDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcEndDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcEndDttm() {
			return localSvcEndDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcEndDttm
		 */
		public void setSvcEndDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcEndDttmTracker = true;
			} else {
				localSvcEndDttmTracker = true;

			}

			this.localSvcEndDttm = param;

		}

		/**
		 * field for SvcDutyUseMnthCnt
		 */

		protected java.lang.String localSvcDutyUseMnthCnt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseMnthCntTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseMnthCnt() {
			return localSvcDutyUseMnthCnt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseMnthCnt
		 */
		public void setSvcDutyUseMnthCnt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseMnthCntTracker = true;
			} else {
				localSvcDutyUseMnthCntTracker = true;

			}

			this.localSvcDutyUseMnthCnt = param;

		}

		/**
		 * field for SvcDutyUseDvCd
		 */

		protected java.lang.String localSvcDutyUseDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseDvCd() {
			return localSvcDutyUseDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseDvCd
		 */
		public void setSvcDutyUseDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseDvCdTracker = true;
			} else {
				localSvcDutyUseDvCdTracker = true;

			}

			this.localSvcDutyUseDvCd = param;

		}

		/**
		 * field for SvcDutyUseStrtDt
		 */

		protected java.lang.String localSvcDutyUseStrtDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseStrtDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseStrtDt() {
			return localSvcDutyUseStrtDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseStrtDt
		 */
		public void setSvcDutyUseStrtDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseStrtDtTracker = true;
			} else {
				localSvcDutyUseStrtDtTracker = true;

			}

			this.localSvcDutyUseStrtDt = param;

		}

		/**
		 * field for SvcDutyUseEndDt
		 */

		protected java.lang.String localSvcDutyUseEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcDutyUseEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcDutyUseEndDt() {
			return localSvcDutyUseEndDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcDutyUseEndDt
		 */
		public void setSvcDutyUseEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcDutyUseEndDtTracker = true;
			} else {
				localSvcDutyUseEndDtTracker = true;

			}

			this.localSvcDutyUseEndDt = param;

		}

		/**
		 * field for SaleEmpno
		 */

		protected java.lang.String localSaleEmpno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSaleEmpnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSaleEmpno() {
			return localSaleEmpno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SaleEmpno
		 */
		public void setSaleEmpno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSaleEmpnoTracker = true;
			} else {
				localSaleEmpnoTracker = true;

			}

			this.localSaleEmpno = param;

		}

		/**
		 * field for SvcRelsDvCd
		 */

		protected java.lang.String localSvcRelsDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcRelsDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcRelsDvCd() {
			return localSvcRelsDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcRelsDvCd
		 */
		public void setSvcRelsDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcRelsDvCdTracker = true;
			} else {
				localSvcRelsDvCdTracker = true;

			}

			this.localSvcRelsDvCd = param;

		}

		/**
		 * field for NdblCvrtSvcCd
		 */

		protected java.lang.String localNdblCvrtSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localNdblCvrtSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getNdblCvrtSvcCd() {
			return localNdblCvrtSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            NdblCvrtSvcCd
		 */
		public void setNdblCvrtSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localNdblCvrtSvcCdTracker = true;
			} else {
				localNdblCvrtSvcCdTracker = true;

			}

			this.localNdblCvrtSvcCd = param;

		}

		/**
		 * field for InventoryItemId
		 */

		protected java.lang.String localInventoryItemId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localInventoryItemIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getInventoryItemId() {
			return localInventoryItemId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            InventoryItemId
		 */
		public void setInventoryItemId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localInventoryItemIdTracker = true;
			} else {
				localInventoryItemIdTracker = true;

			}

			this.localInventoryItemId = param;

		}

		/**
		 * field for OrganizationId
		 */

		protected java.lang.String localOrganizationId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localOrganizationIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOrganizationId() {
			return localOrganizationId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OrganizationId
		 */
		public void setOrganizationId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOrganizationIdTracker = true;
			} else {
				localOrganizationIdTracker = true;

			}

			this.localOrganizationId = param;

		}

		/**
		 * field for RevisionId
		 */

		protected java.lang.String localRevisionId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRevisionIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRevisionId() {
			return localRevisionId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RevisionId
		 */
		public void setRevisionId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRevisionIdTracker = true;
			} else {
				localRevisionIdTracker = true;

			}

			this.localRevisionId = param;

		}

		/**
		 * field for SvcMode
		 */

		protected java.lang.String localSvcMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcMode() {
			return localSvcMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcMode
		 */
		public void setSvcMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcModeTracker = true;
			} else {
				localSvcModeTracker = true;

			}

			this.localSvcMode = param;

		}

		/**
		 * field for SvcSubMode
		 */

		protected java.lang.String localSvcSubMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcSubModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcSubMode() {
			return localSvcSubMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcSubMode
		 */
		public void setSvcSubMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcSubModeTracker = true;
			} else {
				localSvcSubModeTracker = true;

			}

			this.localSvcSubMode = param;

		}

		/**
		 * field for _rowStatus
		 */

		protected java.lang.String local_rowStatus;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean local_rowStatusTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String get_rowStatus() {
			return local_rowStatus;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _rowStatus
		 */
		public void set_rowStatus(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				local_rowStatusTracker = true;
			} else {
				local_rowStatusTracker = true;

			}

			this.local_rowStatus = param;

		}

		/**
		 * field for RsvOprtr
		 */

		protected java.lang.String localRsvOprtr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRsvOprtrTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRsvOprtr() {
			return localRsvOprtr;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RsvOprtr
		 */
		public void setRsvOprtr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRsvOprtrTracker = true;
			} else {
				localRsvOprtrTracker = true;

			}

			this.localRsvOprtr = param;

		}

		/**
		 * field for SusAftRsvYn
		 */

		protected java.lang.String localSusAftRsvYn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSusAftRsvYnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSusAftRsvYn() {
			return localSusAftRsvYn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SusAftRsvYn
		 */
		public void setSusAftRsvYn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSusAftRsvYnTracker = true;
			} else {
				localSusAftRsvYnTracker = true;

			}

			this.localSusAftRsvYn = param;

		}

		/**
		 * field for RsvRcptDvCd
		 */

		protected java.lang.String localRsvRcptDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRsvRcptDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRsvRcptDvCd() {
			return localRsvRcptDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RsvRcptDvCd
		 */
		public void setRsvRcptDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRsvRcptDvCdTracker = true;
			} else {
				localRsvRcptDvCdTracker = true;

			}

			this.localRsvRcptDvCd = param;

		}

		/**
		 * field for RsvSvcChngDvCd
		 */

		protected java.lang.String localRsvSvcChngDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRsvSvcChngDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRsvSvcChngDvCd() {
			return localRsvSvcChngDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RsvSvcChngDvCd
		 */
		public void setRsvSvcChngDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRsvSvcChngDvCdTracker = true;
			} else {
				localRsvSvcChngDvCdTracker = true;

			}

			this.localRsvSvcChngDvCd = param;

		}

		/**
		 * field for BfrPpCd
		 */

		protected java.lang.String localBfrPpCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBfrPpCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBfrPpCd() {
			return localBfrPpCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BfrPpCd
		 */
		public void setBfrPpCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBfrPpCdTracker = true;
			} else {
				localBfrPpCdTracker = true;

			}

			this.localBfrPpCd = param;

		}

		/**
		 * field for RsvDttm
		 */

		protected java.lang.String localRsvDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRsvDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRsvDttm() {
			return localRsvDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RsvDttm
		 */
		public void setRsvDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRsvDttmTracker = true;
			} else {
				localRsvDttmTracker = true;

			}

			this.localRsvDttm = param;

		}

		/**
		 * field for RsvDlrCd
		 */

		protected java.lang.String localRsvDlrCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRsvDlrCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRsvDlrCd() {
			return localRsvDlrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RsvDlrCd
		 */
		public void setRsvDlrCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRsvDlrCdTracker = true;
			} else {
				localRsvDlrCdTracker = true;

			}

			this.localRsvDlrCd = param;

		}

		/**
		 * field for SvcRsvSttsCd
		 */

		protected java.lang.String localSvcRsvSttsCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcRsvSttsCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcRsvSttsCd() {
			return localSvcRsvSttsCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcRsvSttsCd
		 */
		public void setSvcRsvSttsCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcRsvSttsCdTracker = true;
			} else {
				localSvcRsvSttsCdTracker = true;

			}

			this.localSvcRsvSttsCd = param;

		}

		/**
		 * field for Rmks
		 */

		protected java.lang.String localRmks;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRmksTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRmks() {
			return localRmks;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Rmks
		 */
		public void setRmks(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRmksTracker = true;
			} else {
				localRmksTracker = true;

			}

			this.localRmks = param;

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
					DsChkSvcRsvInVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsChkSvcRsvInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsChkSvcRsvInVO",
							xmlWriter);
				}

			}
			if (localSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcCd");
					}

				} else {
					xmlWriter.writeStartElement("svcCd");
				}

				if (localSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcNmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcNm");
					}

				} else {
					xmlWriter.writeStartElement("svcNm");
				}

				if (localSvcNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSvcSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSvcSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSvcSeqno");
					}

				} else {
					xmlWriter.writeStartElement("entrSvcSeqno");
				}

				if (localEntrSvcSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSvcSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localHposSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "hposSvcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "hposSvcCd");
					}

				} else {
					xmlWriter.writeStartElement("hposSvcCd");
				}

				if (localHposSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localHposSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHposEntrSvcSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "hposEntrSvcSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "hposEntrSvcSeqno");
					}

				} else {
					xmlWriter.writeStartElement("hposEntrSvcSeqno");
				}

				if (localHposEntrSvcSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localHposEntrSvcSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcKdCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcKdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcKdCd");
					}

				} else {
					xmlWriter.writeStartElement("svcKdCd");
				}

				if (localSvcKdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcKdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcAplyLvlCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcAplyLvlCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcAplyLvlCd");
					}

				} else {
					xmlWriter.writeStartElement("svcAplyLvlCd");
				}

				if (localSvcAplyLvlCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcAplyLvlCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcFrstStrtDttmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcFrstStrtDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcFrstStrtDttm");
					}

				} else {
					xmlWriter.writeStartElement("svcFrstStrtDttm");
				}

				if (localSvcFrstStrtDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcFrstStrtDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcStrtRgstDlrCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcStrtRgstDlrCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcStrtRgstDlrCd");
					}

				} else {
					xmlWriter.writeStartElement("svcStrtRgstDlrCd");
				}

				if (localSvcStrtRgstDlrCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcStrtRgstDlrCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcStrtDttmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcStrtDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcStrtDttm");
					}

				} else {
					xmlWriter.writeStartElement("svcStrtDttm");
				}

				if (localSvcStrtDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcStrtDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcEndDttmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcEndDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcEndDttm");
					}

				} else {
					xmlWriter.writeStartElement("svcEndDttm");
				}

				if (localSvcEndDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcEndDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseMnthCntTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseMnthCnt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseMnthCnt");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseMnthCnt");
				}

				if (localSvcDutyUseMnthCnt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseMnthCnt);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseDvCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseDvCd");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseDvCd");
				}

				if (localSvcDutyUseDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseStrtDtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseStrtDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseStrtDt");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseStrtDt");
				}

				if (localSvcDutyUseStrtDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseStrtDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseEndDtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcDutyUseEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcDutyUseEndDt");
					}

				} else {
					xmlWriter.writeStartElement("svcDutyUseEndDt");
				}

				if (localSvcDutyUseEndDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcDutyUseEndDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localSaleEmpnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "saleEmpno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "saleEmpno");
					}

				} else {
					xmlWriter.writeStartElement("saleEmpno");
				}

				if (localSaleEmpno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSaleEmpno);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcRelsDvCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcRelsDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcRelsDvCd");
					}

				} else {
					xmlWriter.writeStartElement("svcRelsDvCd");
				}

				if (localSvcRelsDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcRelsDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localNdblCvrtSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ndblCvrtSvcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ndblCvrtSvcCd");
					}

				} else {
					xmlWriter.writeStartElement("ndblCvrtSvcCd");
				}

				if (localNdblCvrtSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localNdblCvrtSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localInventoryItemIdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "inventoryItemId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "inventoryItemId");
					}

				} else {
					xmlWriter.writeStartElement("inventoryItemId");
				}

				if (localInventoryItemId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localInventoryItemId);

				}

				xmlWriter.writeEndElement();
			}
			if (localOrganizationIdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "organizationId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "organizationId");
					}

				} else {
					xmlWriter.writeStartElement("organizationId");
				}

				if (localOrganizationId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localOrganizationId);

				}

				xmlWriter.writeEndElement();
			}
			if (localRevisionIdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "revisionId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "revisionId");
					}

				} else {
					xmlWriter.writeStartElement("revisionId");
				}

				if (localRevisionId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRevisionId);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcMode");
					}

				} else {
					xmlWriter.writeStartElement("svcMode");
				}

				if (localSvcMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcSubModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcSubMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcSubMode");
					}

				} else {
					xmlWriter.writeStartElement("svcSubMode");
				}

				if (localSvcSubMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcSubMode);

				}

				xmlWriter.writeEndElement();
			}
			if (local_rowStatusTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "_rowStatus", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "_rowStatus");
					}

				} else {
					xmlWriter.writeStartElement("_rowStatus");
				}

				if (local_rowStatus == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(local_rowStatus);

				}

				xmlWriter.writeEndElement();
			}
			if (localRsvOprtrTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rsvOprtr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rsvOprtr");
					}

				} else {
					xmlWriter.writeStartElement("rsvOprtr");
				}

				if (localRsvOprtr == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRsvOprtr);

				}

				xmlWriter.writeEndElement();
			}
			if (localSusAftRsvYnTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "susAftRsvYn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "susAftRsvYn");
					}

				} else {
					xmlWriter.writeStartElement("susAftRsvYn");
				}

				if (localSusAftRsvYn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSusAftRsvYn);

				}

				xmlWriter.writeEndElement();
			}
			if (localRsvRcptDvCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rsvRcptDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rsvRcptDvCd");
					}

				} else {
					xmlWriter.writeStartElement("rsvRcptDvCd");
				}

				if (localRsvRcptDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRsvRcptDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localRsvSvcChngDvCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rsvSvcChngDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rsvSvcChngDvCd");
					}

				} else {
					xmlWriter.writeStartElement("rsvSvcChngDvCd");
				}

				if (localRsvSvcChngDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRsvSvcChngDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localBfrPpCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bfrPpCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bfrPpCd");
					}

				} else {
					xmlWriter.writeStartElement("bfrPpCd");
				}

				if (localBfrPpCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBfrPpCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localRsvDttmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rsvDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rsvDttm");
					}

				} else {
					xmlWriter.writeStartElement("rsvDttm");
				}

				if (localRsvDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRsvDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localRsvDlrCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rsvDlrCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rsvDlrCd");
					}

				} else {
					xmlWriter.writeStartElement("rsvDlrCd");
				}

				if (localRsvDlrCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRsvDlrCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcRsvSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcRsvSttsCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcRsvSttsCd");
					}

				} else {
					xmlWriter.writeStartElement("svcRsvSttsCd");
				}

				if (localSvcRsvSttsCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcRsvSttsCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localRmksTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rmks", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rmks");
					}

				} else {
					xmlWriter.writeStartElement("rmks");
				}

				if (localRmks == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRmks);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			if (localSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcCd"));

				elementList.add(localSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcCd));
			}
			if (localSvcNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcNm"));

				elementList.add(localSvcNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcNm));
			}
			if (localEntrSvcSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSvcSeqno"));

				elementList.add(localEntrSvcSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSvcSeqno));
			}
			if (localHposSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposSvcCd"));

				elementList.add(localHposSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHposSvcCd));
			}
			if (localHposEntrSvcSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposEntrSvcSeqno"));

				elementList.add(localHposEntrSvcSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHposEntrSvcSeqno));
			}
			if (localSvcKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcKdCd"));

				elementList.add(localSvcKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcKdCd));
			}
			if (localSvcAplyLvlCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcAplyLvlCd"));

				elementList.add(localSvcAplyLvlCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcAplyLvlCd));
			}
			if (localSvcFrstStrtDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcFrstStrtDttm"));

				elementList.add(localSvcFrstStrtDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcFrstStrtDttm));
			}
			if (localSvcStrtRgstDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcStrtRgstDlrCd"));

				elementList.add(localSvcStrtRgstDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcStrtRgstDlrCd));
			}
			if (localSvcStrtDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcStrtDttm"));

				elementList.add(localSvcStrtDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcStrtDttm));
			}
			if (localSvcEndDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcEndDttm"));

				elementList.add(localSvcEndDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcEndDttm));
			}
			if (localSvcDutyUseMnthCntTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseMnthCnt"));

				elementList.add(localSvcDutyUseMnthCnt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseMnthCnt));
			}
			if (localSvcDutyUseDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseDvCd"));

				elementList.add(localSvcDutyUseDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseDvCd));
			}
			if (localSvcDutyUseStrtDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseStrtDt"));

				elementList.add(localSvcDutyUseStrtDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseStrtDt));
			}
			if (localSvcDutyUseEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseEndDt"));

				elementList.add(localSvcDutyUseEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseEndDt));
			}
			if (localSaleEmpnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "saleEmpno"));

				elementList.add(localSaleEmpno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSaleEmpno));
			}
			if (localSvcRelsDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcRelsDvCd"));

				elementList.add(localSvcRelsDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcRelsDvCd));
			}
			if (localNdblCvrtSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ndblCvrtSvcCd"));

				elementList.add(localNdblCvrtSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNdblCvrtSvcCd));
			}
			if (localInventoryItemIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "inventoryItemId"));

				elementList.add(localInventoryItemId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localInventoryItemId));
			}
			if (localOrganizationIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "organizationId"));

				elementList.add(localOrganizationId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrganizationId));
			}
			if (localRevisionIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "revisionId"));

				elementList.add(localRevisionId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRevisionId));
			}
			if (localSvcModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcMode"));

				elementList.add(localSvcMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcMode));
			}
			if (localSvcSubModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcSubMode"));

				elementList.add(localSvcSubMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcSubMode));
			}
			if (local_rowStatusTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "_rowStatus"));

				elementList.add(local_rowStatus == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_rowStatus));
			}
			if (localRsvOprtrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvOprtr"));

				elementList.add(localRsvOprtr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRsvOprtr));
			}
			if (localSusAftRsvYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "susAftRsvYn"));

				elementList.add(localSusAftRsvYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSusAftRsvYn));
			}
			if (localRsvRcptDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvRcptDvCd"));

				elementList.add(localRsvRcptDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRsvRcptDvCd));
			}
			if (localRsvSvcChngDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvSvcChngDvCd"));

				elementList.add(localRsvSvcChngDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRsvSvcChngDvCd));
			}
			if (localBfrPpCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "bfrPpCd"));

				elementList.add(localBfrPpCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBfrPpCd));
			}
			if (localRsvDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvDttm"));

				elementList.add(localRsvDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRsvDttm));
			}
			if (localRsvDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvDlrCd"));

				elementList.add(localRsvDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRsvDlrCd));
			}
			if (localSvcRsvSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcRsvSttsCd"));

				elementList.add(localSvcRsvSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcRsvSttsCd));
			}
			if (localRmksTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rmks"));

				elementList.add(localRmks == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRmks));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId"));

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
			public static DsChkSvcRsvInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsChkSvcRsvInVO object = new DsChkSvcRsvInVO();

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

							if (!"DsChkSvcRsvInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsChkSvcRsvInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSvcSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSvcSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposSvcCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setHposSvcCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposEntrSvcSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setHposEntrSvcSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcKdCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcKdCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcAplyLvlCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcAplyLvlCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcFrstStrtDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcFrstStrtDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcStrtRgstDlrCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcStrtRgstDlrCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcStrtDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcStrtDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcEndDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcEndDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseMnthCnt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseMnthCnt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseDvCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseStrtDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseStrtDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcDutyUseEndDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcDutyUseEndDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "saleEmpno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSaleEmpno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcRelsDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcRelsDvCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ndblCvrtSvcCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setNdblCvrtSvcCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "inventoryItemId")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setInventoryItemId(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "organizationId")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setOrganizationId(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "revisionId")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRevisionId(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcMode")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcSubMode")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcSubMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "_rowStatus")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.set_rowStatus(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvOprtr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRsvOprtr(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "susAftRsvYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSusAftRsvYn(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvRcptDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRsvRcptDvCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvSvcChngDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRsvSvcChngDvCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "bfrPpCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBfrPpCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvDttm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRsvDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rsvDlrCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRsvDlrCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcRsvSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcRsvSttsCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "rmks")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRmks(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SaveSvcRsv implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"SaveSvcRsv", "ns3");

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
					SaveSvcRsv.this.serialize(MY_QNAME, factory, xmlWriter);
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
							namespacePrefix + ":SaveSvcRsv", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "SaveSvcRsv", xmlWriter);
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
				// we have to store this data until last moment since it is not
				// possible to write any
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
			public static SaveSvcRsv parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SaveSvcRsv object = new SaveSvcRsv();

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

							if (!"SaveSvcRsv".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SaveSvcRsv) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
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
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class SaveSvcRsvResponse implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"SaveSvcRsvResponse", "ns3");

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
					SaveSvcRsvResponse.this.serialize(MY_QNAME, factory, xmlWriter);
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
							namespacePrefix + ":SaveSvcRsvResponse", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "SaveSvcRsvResponse",
							xmlWriter);
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
				// we have to store this data until last moment since it is not
				// possible to write any
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
			public static SaveSvcRsvResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				SaveSvcRsvResponse object = new SaveSvcRsvResponse();

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

							if (!"SaveSvcRsvResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (SaveSvcRsvResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
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
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
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
		 * Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix = ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for LDataRsltOutVO
		 */

		protected LDataRsltOutVO localLDataRsltOutVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLDataRsltOutVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return LDataRsltOutVO
		 */
		public LDataRsltOutVO getLDataRsltOutVO() {
			return localLDataRsltOutVO;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LDataRsltOutVO
		 */
		public void setLDataRsltOutVO(LDataRsltOutVO param) {

			if (param != null) {
				// update the setting tracker
				localLDataRsltOutVOTracker = true;
			} else {
				localLDataRsltOutVOTracker = false;

			}

			this.localLDataRsltOutVO = param;

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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ResponseBody", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ResponseBody",
							xmlWriter);
				}

			}
			if (localLDataRsltOutVOTracker) {
				if (localLDataRsltOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("LDataRsltOutVO cannot be null!!");
				}
				localLDataRsltOutVO.serialize(
						new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "LDataRsltOutVO"), factory,
						xmlWriter);
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			if (localLDataRsltOutVOTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "LDataRsltOutVO"));

				if (localLDataRsltOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("LDataRsltOutVO cannot be null!!");
				}
				elementList.add(localLDataRsltOutVO);
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

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "LDataRsltOutVO")
									.equals(reader.getName())) {

						object.setLDataRsltOutVO(LDataRsltOutVO.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
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
		 * Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix = ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DsConfldsInVO This was an Array!
		 */

		protected DsConfldsInVO[] localDsConfldsInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsConfldsInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsConfldsInVO[]
		 */
		public DsConfldsInVO[] getDsConfldsInVO() {
			return localDsConfldsInVO;
		}

		/**
		 * validate the array for DsConfldsInVO
		 */
		protected void validateDsConfldsInVO(DsConfldsInVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsConfldsInVO
		 */
		public void setDsConfldsInVO(DsConfldsInVO[] param) {

			validateDsConfldsInVO(param);

			if (param != null) {
				// update the setting tracker
				localDsConfldsInVOTracker = true;
			} else {
				localDsConfldsInVOTracker = false;

			}

			this.localDsConfldsInVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsConfldsInVO
		 */
		public void addDsConfldsInVO(DsConfldsInVO param) {
			if (localDsConfldsInVO == null) {
				localDsConfldsInVO = new DsConfldsInVO[] {};
			}

			// update the setting tracker
			localDsConfldsInVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsConfldsInVO);
			list.add(param);
			this.localDsConfldsInVO = (DsConfldsInVO[]) list.toArray(new DsConfldsInVO[list.size()]);

		}

		/**
		 * field for DsChkSvcRsvInVO This was an Array!
		 */

		protected DsChkSvcRsvInVO[] localDsChkSvcRsvInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsChkSvcRsvInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsChkSvcRsvInVO[]
		 */
		public DsChkSvcRsvInVO[] getDsChkSvcRsvInVO() {
			return localDsChkSvcRsvInVO;
		}

		/**
		 * validate the array for DsChkSvcRsvInVO
		 */
		protected void validateDsChkSvcRsvInVO(DsChkSvcRsvInVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsChkSvcRsvInVO
		 */
		public void setDsChkSvcRsvInVO(DsChkSvcRsvInVO[] param) {

			validateDsChkSvcRsvInVO(param);

			if (param != null) {
				// update the setting tracker
				localDsChkSvcRsvInVOTracker = true;
			} else {
				localDsChkSvcRsvInVOTracker = false;

			}

			this.localDsChkSvcRsvInVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsChkSvcRsvInVO
		 */
		public void addDsChkSvcRsvInVO(DsChkSvcRsvInVO param) {
			if (localDsChkSvcRsvInVO == null) {
				localDsChkSvcRsvInVO = new DsChkSvcRsvInVO[] {};
			}

			// update the setting tracker
			localDsChkSvcRsvInVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsChkSvcRsvInVO);
			list.add(param);
			this.localDsChkSvcRsvInVO = (DsChkSvcRsvInVO[]) list.toArray(new DsChkSvcRsvInVO[list.size()]);

		}

		/**
		 * field for DsChkFtrInVO This was an Array!
		 */

		protected DsChkFtrInVO[] localDsChkFtrInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsChkFtrInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsChkFtrInVO[]
		 */
		public DsChkFtrInVO[] getDsChkFtrInVO() {
			return localDsChkFtrInVO;
		}

		/**
		 * validate the array for DsChkFtrInVO
		 */
		protected void validateDsChkFtrInVO(DsChkFtrInVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsChkFtrInVO
		 */
		public void setDsChkFtrInVO(DsChkFtrInVO[] param) {

			validateDsChkFtrInVO(param);

			if (param != null) {
				// update the setting tracker
				localDsChkFtrInVOTracker = true;
			} else {
				localDsChkFtrInVOTracker = false;

			}

			this.localDsChkFtrInVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsChkFtrInVO
		 */
		public void addDsChkFtrInVO(DsChkFtrInVO param) {
			if (localDsChkFtrInVO == null) {
				localDsChkFtrInVO = new DsChkFtrInVO[] {};
			}

			// update the setting tracker
			localDsChkFtrInVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsChkFtrInVO);
			list.add(param);
			this.localDsChkFtrInVO = (DsChkFtrInVO[]) list.toArray(new DsChkFtrInVO[list.size()]);

		}

		/**
		 * field for DsSaveSvcInVO This was an Array!
		 */

		protected DsSaveSvcInVO[] localDsSaveSvcInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsSaveSvcInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsSaveSvcInVO[]
		 */
		public DsSaveSvcInVO[] getDsSaveSvcInVO() {
			return localDsSaveSvcInVO;
		}

		/**
		 * validate the array for DsSaveSvcInVO
		 */
		protected void validateDsSaveSvcInVO(DsSaveSvcInVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsSaveSvcInVO
		 */
		public void setDsSaveSvcInVO(DsSaveSvcInVO[] param) {

			validateDsSaveSvcInVO(param);

			if (param != null) {
				// update the setting tracker
				localDsSaveSvcInVOTracker = true;
			} else {
				localDsSaveSvcInVOTracker = false;

			}

			this.localDsSaveSvcInVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsSaveSvcInVO
		 */
		public void addDsSaveSvcInVO(DsSaveSvcInVO param) {
			if (localDsSaveSvcInVO == null) {
				localDsSaveSvcInVO = new DsSaveSvcInVO[] {};
			}

			// update the setting tracker
			localDsSaveSvcInVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsSaveSvcInVO);
			list.add(param);
			this.localDsSaveSvcInVO = (DsSaveSvcInVO[]) list.toArray(new DsSaveSvcInVO[list.size()]);

		}

		/**
		 * field for DsChkItemInVO This was an Array!
		 */

		protected DsChkItemInVO[] localDsChkItemInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsChkItemInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsChkItemInVO[]
		 */
		public DsChkItemInVO[] getDsChkItemInVO() {
			return localDsChkItemInVO;
		}

		/**
		 * validate the array for DsChkItemInVO
		 */
		protected void validateDsChkItemInVO(DsChkItemInVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsChkItemInVO
		 */
		public void setDsChkItemInVO(DsChkItemInVO[] param) {

			validateDsChkItemInVO(param);

			if (param != null) {
				// update the setting tracker
				localDsChkItemInVOTracker = true;
			} else {
				localDsChkItemInVOTracker = false;

			}

			this.localDsChkItemInVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsChkItemInVO
		 */
		public void addDsChkItemInVO(DsChkItemInVO param) {
			if (localDsChkItemInVO == null) {
				localDsChkItemInVO = new DsChkItemInVO[] {};
			}

			// update the setting tracker
			localDsChkItemInVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsChkItemInVO);
			list.add(param);
			this.localDsChkItemInVO = (DsChkItemInVO[]) list.toArray(new DsChkItemInVO[list.size()]);

		}

		/**
		 * field for DsAsgnNoListInVO This was an Array!
		 */

		protected DsAsgnNoListInVO[] localDsAsgnNoListInVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsAsgnNoListInVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsAsgnNoListInVO[]
		 */
		public DsAsgnNoListInVO[] getDsAsgnNoListInVO() {
			return localDsAsgnNoListInVO;
		}

		/**
		 * validate the array for DsAsgnNoListInVO
		 */
		protected void validateDsAsgnNoListInVO(DsAsgnNoListInVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsAsgnNoListInVO
		 */
		public void setDsAsgnNoListInVO(DsAsgnNoListInVO[] param) {

			validateDsAsgnNoListInVO(param);

			if (param != null) {
				// update the setting tracker
				localDsAsgnNoListInVOTracker = true;
			} else {
				localDsAsgnNoListInVOTracker = false;

			}

			this.localDsAsgnNoListInVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsAsgnNoListInVO
		 */
		public void addDsAsgnNoListInVO(DsAsgnNoListInVO param) {
			if (localDsAsgnNoListInVO == null) {
				localDsAsgnNoListInVO = new DsAsgnNoListInVO[] {};
			}

			// update the setting tracker
			localDsAsgnNoListInVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsAsgnNoListInVO);
			list.add(param);
			this.localDsAsgnNoListInVO = (DsAsgnNoListInVO[]) list.toArray(new DsAsgnNoListInVO[list.size()]);

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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":RequestBody", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RequestBody",
							xmlWriter);
				}

			}
			if (localDsConfldsInVOTracker) {
				if (localDsConfldsInVO != null) {
					for (int i = 0; i < localDsConfldsInVO.length; i++) {
						if (localDsConfldsInVO[i] != null) {
							localDsConfldsInVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsConfldsInVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsConfldsInVO cannot be null!!");

				}
			}
			if (localDsChkSvcRsvInVOTracker) {
				if (localDsChkSvcRsvInVO != null) {
					for (int i = 0; i < localDsChkSvcRsvInVO.length; i++) {
						if (localDsChkSvcRsvInVO[i] != null) {
							localDsChkSvcRsvInVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkSvcRsvInVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsChkSvcRsvInVO cannot be null!!");

				}
			}
			if (localDsChkFtrInVOTracker) {
				if (localDsChkFtrInVO != null) {
					for (int i = 0; i < localDsChkFtrInVO.length; i++) {
						if (localDsChkFtrInVO[i] != null) {
							localDsChkFtrInVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkFtrInVO"), factory,
									xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsChkFtrInVO cannot be null!!");

				}
			}
			if (localDsSaveSvcInVOTracker) {
				if (localDsSaveSvcInVO != null) {
					for (int i = 0; i < localDsSaveSvcInVO.length; i++) {
						if (localDsSaveSvcInVO[i] != null) {
							localDsSaveSvcInVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsSaveSvcInVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsSaveSvcInVO cannot be null!!");

				}
			}
			if (localDsChkItemInVOTracker) {
				if (localDsChkItemInVO != null) {
					for (int i = 0; i < localDsChkItemInVO.length; i++) {
						if (localDsChkItemInVO[i] != null) {
							localDsChkItemInVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkItemInVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsChkItemInVO cannot be null!!");

				}
			}
			if (localDsAsgnNoListInVOTracker) {
				if (localDsAsgnNoListInVO != null) {
					for (int i = 0; i < localDsAsgnNoListInVO.length; i++) {
						if (localDsAsgnNoListInVO[i] != null) {
							localDsAsgnNoListInVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsAsgnNoListInVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsAsgnNoListInVO cannot be null!!");

				}
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			if (localDsConfldsInVOTracker) {
				if (localDsConfldsInVO != null) {
					for (int i = 0; i < localDsConfldsInVO.length; i++) {

						if (localDsConfldsInVO[i] != null) {
							elementList
									.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsConfldsInVO"));
							elementList.add(localDsConfldsInVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsConfldsInVO cannot be null!!");

				}

			}
			if (localDsChkSvcRsvInVOTracker) {
				if (localDsChkSvcRsvInVO != null) {
					for (int i = 0; i < localDsChkSvcRsvInVO.length; i++) {

						if (localDsChkSvcRsvInVO[i] != null) {
							elementList
									.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkSvcRsvInVO"));
							elementList.add(localDsChkSvcRsvInVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsChkSvcRsvInVO cannot be null!!");

				}

			}
			if (localDsChkFtrInVOTracker) {
				if (localDsChkFtrInVO != null) {
					for (int i = 0; i < localDsChkFtrInVO.length; i++) {

						if (localDsChkFtrInVO[i] != null) {
							elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkFtrInVO"));
							elementList.add(localDsChkFtrInVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsChkFtrInVO cannot be null!!");

				}

			}
			if (localDsSaveSvcInVOTracker) {
				if (localDsSaveSvcInVO != null) {
					for (int i = 0; i < localDsSaveSvcInVO.length; i++) {

						if (localDsSaveSvcInVO[i] != null) {
							elementList
									.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsSaveSvcInVO"));
							elementList.add(localDsSaveSvcInVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsSaveSvcInVO cannot be null!!");

				}

			}
			if (localDsChkItemInVOTracker) {
				if (localDsChkItemInVO != null) {
					for (int i = 0; i < localDsChkItemInVO.length; i++) {

						if (localDsChkItemInVO[i] != null) {
							elementList
									.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkItemInVO"));
							elementList.add(localDsChkItemInVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsChkItemInVO cannot be null!!");

				}

			}
			if (localDsAsgnNoListInVOTracker) {
				if (localDsAsgnNoListInVO != null) {
					for (int i = 0; i < localDsAsgnNoListInVO.length; i++) {

						if (localDsAsgnNoListInVO[i] != null) {
							elementList.add(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsAsgnNoListInVO"));
							elementList.add(localDsAsgnNoListInVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsAsgnNoListInVO cannot be null!!");

				}

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

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					java.util.ArrayList list1 = new java.util.ArrayList();

					java.util.ArrayList list2 = new java.util.ArrayList();

					java.util.ArrayList list3 = new java.util.ArrayList();

					java.util.ArrayList list4 = new java.util.ArrayList();

					java.util.ArrayList list5 = new java.util.ArrayList();

					java.util.ArrayList list6 = new java.util.ArrayList();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsConfldsInVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list1.add(DsConfldsInVO.Factory.parse(reader));

						// loop until we find a start element that is not part
						// of this array
						boolean loopDone1 = false;
						while (!loopDone1) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are
								// exiting the xml structure
								loopDone1 = true;
							} else {
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsConfldsInVO")
										.equals(reader.getName())) {
									list1.add(DsConfldsInVO.Factory.parse(reader));

								} else {
									loopDone1 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsConfldsInVO((DsConfldsInVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsConfldsInVO.class, list1));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkSvcRsvInVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list2.add(DsChkSvcRsvInVO.Factory.parse(reader));

						// loop until we find a start element that is not part
						// of this array
						boolean loopDone2 = false;
						while (!loopDone2) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are
								// exiting the xml structure
								loopDone2 = true;
							} else {
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkSvcRsvInVO")
										.equals(reader.getName())) {
									list2.add(DsChkSvcRsvInVO.Factory.parse(reader));

								} else {
									loopDone2 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsChkSvcRsvInVO((DsChkSvcRsvInVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsChkSvcRsvInVO.class, list2));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkFtrInVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list3.add(DsChkFtrInVO.Factory.parse(reader));

						// loop until we find a start element that is not part
						// of this array
						boolean loopDone3 = false;
						while (!loopDone3) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are
								// exiting the xml structure
								loopDone3 = true;
							} else {
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkFtrInVO")
										.equals(reader.getName())) {
									list3.add(DsChkFtrInVO.Factory.parse(reader));

								} else {
									loopDone3 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsChkFtrInVO((DsChkFtrInVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsChkFtrInVO.class, list3));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsSaveSvcInVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list4.add(DsSaveSvcInVO.Factory.parse(reader));

						// loop until we find a start element that is not part
						// of this array
						boolean loopDone4 = false;
						while (!loopDone4) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are
								// exiting the xml structure
								loopDone4 = true;
							} else {
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsSaveSvcInVO")
										.equals(reader.getName())) {
									list4.add(DsSaveSvcInVO.Factory.parse(reader));

								} else {
									loopDone4 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsSaveSvcInVO((DsSaveSvcInVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsSaveSvcInVO.class, list4));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkItemInVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list5.add(DsChkItemInVO.Factory.parse(reader));

						// loop until we find a start element that is not part
						// of this array
						boolean loopDone5 = false;
						while (!loopDone5) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are
								// exiting the xml structure
								loopDone5 = true;
							} else {
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsChkItemInVO")
										.equals(reader.getName())) {
									list5.add(DsChkItemInVO.Factory.parse(reader));

								} else {
									loopDone5 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsChkItemInVO((DsChkItemInVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsChkItemInVO.class, list5));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsAsgnNoListInVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list6.add(DsAsgnNoListInVO.Factory.parse(reader));

						// loop until we find a start element that is not part
						// of this array
						boolean loopDone6 = false;
						while (!loopDone6) {
							// We should be at the end element, but make sure
							while (!reader.isEndElement())
								reader.next();
							// Step out of this element
							reader.next();
							// Step to next element event.
							while (!reader.isStartElement() && !reader.isEndElement())
								reader.next();
							if (reader.isEndElement()) {
								// two continuous end elements means we are
								// exiting the xml structure
								loopDone6 = true;
							} else {
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "DsAsgnNoListInVO")
										.equals(reader.getName())) {
									list6.add(DsAsgnNoListInVO.Factory.parse(reader));

								} else {
									loopDone6 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsAsgnNoListInVO((DsAsgnNoListInVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsAsgnNoListInVO.class, list6));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
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

			if ("java:lguplus.u3.esb.common".equals(namespaceURI) && "ESBHeader".equals(typeName)) {

				return ESBHeader.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "DsChkItemInVO".equals(typeName)) {

				return DsChkItemInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "DsSaveSvcInVO".equals(typeName)) {

				return DsSaveSvcInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.common".equals(namespaceURI) && "BusinessHeader".equals(typeName)) {

				return BusinessHeader.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "DsConfldsInVO".equals(typeName)) {

				return DsConfldsInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "ResponseRecord".equals(typeName)) {

				return ResponseRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "RequestRecord".equals(typeName)) {

				return RequestRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "DsChkSvcRsvInVO".equals(typeName)) {

				return DsChkSvcRsvInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "ResponseBody".equals(typeName)) {

				return ResponseBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "RequestBody".equals(typeName)) {

				return RequestBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "LDataRsltOutVO".equals(typeName)) {

				return LDataRsltOutVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "DsAsgnNoListInVO".equals(typeName)) {

				return DsAsgnNoListInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.sm066".equals(namespaceURI) && "DsChkFtrInVO".equals(typeName)) {

				return DsChkFtrInVO.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class LDataRsltOutVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * LDataRsltOutVO Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for Results
		 */

		protected java.lang.String localResults;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getResults() {
			return localResults;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Results
		 */
		public void setResults(java.lang.String param) {

			this.localResults = param;

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
					LDataRsltOutVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":LDataRsltOutVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "LDataRsltOutVO",
							xmlWriter);
				}

			}

			namespace = "java:lguplus.u3.esb.sm066";
			if (!namespace.equals("")) {
				prefix = xmlWriter.getPrefix(namespace);

				if (prefix == null) {
					prefix = generatePrefix(namespace);

					xmlWriter.writeStartElement(prefix, "results", namespace);
					xmlWriter.writeNamespace(prefix, namespace);
					xmlWriter.setPrefix(prefix, namespace);

				} else {
					xmlWriter.writeStartElement(namespace, "results");
				}

			} else {
				xmlWriter.writeStartElement("results");
			}

			if (localResults == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("results cannot be null!!");

			} else {

				xmlWriter.writeCharacters(localResults);

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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "results"));

			if (localResults != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResults));
			} else {
				throw new org.apache.axis2.databinding.ADBException("results cannot be null!!");
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
			public static LDataRsltOutVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				LDataRsltOutVO object = new LDataRsltOutVO();

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

							if (!"LDataRsltOutVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (LDataRsltOutVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "results")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setResults(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsAsgnNoListInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsAsgnNoListInVO Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix =
		 * ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
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
		 * field for DscntStrtDttm
		 */

		protected java.lang.String localDscntStrtDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDscntStrtDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDscntStrtDttm() {
			return localDscntStrtDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DscntStrtDttm
		 */
		public void setDscntStrtDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDscntStrtDttmTracker = true;
			} else {
				localDscntStrtDttmTracker = true;

			}

			this.localDscntStrtDttm = param;

		}

		/**
		 * field for AsgnNoSeqno
		 */

		protected java.lang.String localAsgnNoSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAsgnNoSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAsgnNoSeqno() {
			return localAsgnNoSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AsgnNoSeqno
		 */
		public void setAsgnNoSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAsgnNoSeqnoTracker = true;
			} else {
				localAsgnNoSeqnoTracker = true;

			}

			this.localAsgnNoSeqno = param;

		}

		/**
		 * field for AsgnDscntTelno
		 */

		protected java.lang.String localAsgnDscntTelno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAsgnDscntTelnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAsgnDscntTelno() {
			return localAsgnDscntTelno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AsgnDscntTelno
		 */
		public void setAsgnDscntTelno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAsgnDscntTelnoTracker = true;
			} else {
				localAsgnDscntTelnoTracker = true;

			}

			this.localAsgnDscntTelno = param;

		}

		/**
		 * field for DscntEndDttm
		 */

		protected java.lang.String localDscntEndDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDscntEndDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDscntEndDttm() {
			return localDscntEndDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DscntEndDttm
		 */
		public void setDscntEndDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDscntEndDttmTracker = true;
			} else {
				localDscntEndDttmTracker = true;

			}

			this.localDscntEndDttm = param;

		}

		/**
		 * field for DscntKdDtlCd
		 */

		protected java.lang.String localDscntKdDtlCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDscntKdDtlCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDscntKdDtlCd() {
			return localDscntKdDtlCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DscntKdDtlCd
		 */
		public void setDscntKdDtlCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDscntKdDtlCdTracker = true;
			} else {
				localDscntKdDtlCdTracker = true;

			}

			this.localDscntKdDtlCd = param;

		}

		/**
		 * field for DscntSvcCd
		 */

		protected java.lang.String localDscntSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDscntSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDscntSvcCd() {
			return localDscntSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DscntSvcCd
		 */
		public void setDscntSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDscntSvcCdTracker = true;
			} else {
				localDscntSvcCdTracker = true;

			}

			this.localDscntSvcCd = param;

		}

		/**
		 * field for HposSvcCd
		 */

		protected java.lang.String localHposSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localHposSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getHposSvcCd() {
			return localHposSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            HposSvcCd
		 */
		public void setHposSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHposSvcCdTracker = true;
			} else {
				localHposSvcCdTracker = true;

			}

			this.localHposSvcCd = param;

		}

		/**
		 * field for Ctn
		 */

		protected java.lang.String localCtn;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCtnTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCtn() {
			return localCtn;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Ctn
		 */
		public void setCtn(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCtnTracker = true;
			} else {
				localCtnTracker = true;

			}

			this.localCtn = param;

		}

		/**
		 * field for SvcGrpSeqno
		 */

		protected java.lang.String localSvcGrpSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcGrpSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcGrpSeqno() {
			return localSvcGrpSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcGrpSeqno
		 */
		public void setSvcGrpSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcGrpSeqnoTracker = true;
			} else {
				localSvcGrpSeqnoTracker = true;

			}

			this.localSvcGrpSeqno = param;

		}

		/**
		 * field for SvcRsvSttsCd
		 */

		protected java.lang.String localSvcRsvSttsCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcRsvSttsCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcRsvSttsCd() {
			return localSvcRsvSttsCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcRsvSttsCd
		 */
		public void setSvcRsvSttsCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcRsvSttsCdTracker = true;
			} else {
				localSvcRsvSttsCdTracker = true;

			}

			this.localSvcRsvSttsCd = param;

		}

		/**
		 * field for HposSvcNm
		 */

		protected java.lang.String localHposSvcNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localHposSvcNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getHposSvcNm() {
			return localHposSvcNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            HposSvcNm
		 */
		public void setHposSvcNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localHposSvcNmTracker = true;
			} else {
				localHposSvcNmTracker = true;

			}

			this.localHposSvcNm = param;

		}

		/**
		 * field for _rowStatus
		 */

		protected java.lang.String local_rowStatus;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean local_rowStatusTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String get_rowStatus() {
			return local_rowStatus;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            _rowStatus
		 */
		public void set_rowStatus(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				local_rowStatusTracker = true;
			} else {
				local_rowStatusTracker = true;

			}

			this.local_rowStatus = param;

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
					DsAsgnNoListInVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsAsgnNoListInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsAsgnNoListInVO",
							xmlWriter);
				}

			}
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
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
			if (localDscntStrtDttmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "dscntStrtDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "dscntStrtDttm");
					}

				} else {
					xmlWriter.writeStartElement("dscntStrtDttm");
				}

				if (localDscntStrtDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDscntStrtDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localAsgnNoSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "asgnNoSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "asgnNoSeqno");
					}

				} else {
					xmlWriter.writeStartElement("asgnNoSeqno");
				}

				if (localAsgnNoSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAsgnNoSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localAsgnDscntTelnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "asgnDscntTelno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "asgnDscntTelno");
					}

				} else {
					xmlWriter.writeStartElement("asgnDscntTelno");
				}

				if (localAsgnDscntTelno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAsgnDscntTelno);

				}

				xmlWriter.writeEndElement();
			}
			if (localDscntEndDttmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "dscntEndDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "dscntEndDttm");
					}

				} else {
					xmlWriter.writeStartElement("dscntEndDttm");
				}

				if (localDscntEndDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDscntEndDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localDscntKdDtlCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "dscntKdDtlCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "dscntKdDtlCd");
					}

				} else {
					xmlWriter.writeStartElement("dscntKdDtlCd");
				}

				if (localDscntKdDtlCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDscntKdDtlCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localDscntSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "dscntSvcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "dscntSvcCd");
					}

				} else {
					xmlWriter.writeStartElement("dscntSvcCd");
				}

				if (localDscntSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDscntSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHposSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "hposSvcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "hposSvcCd");
					}

				} else {
					xmlWriter.writeStartElement("hposSvcCd");
				}

				if (localHposSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localHposSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localCtnTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ctn", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ctn");
					}

				} else {
					xmlWriter.writeStartElement("ctn");
				}

				if (localCtn == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCtn);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcGrpSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcGrpSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcGrpSeqno");
					}

				} else {
					xmlWriter.writeStartElement("svcGrpSeqno");
				}

				if (localSvcGrpSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcGrpSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcRsvSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcRsvSttsCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcRsvSttsCd");
					}

				} else {
					xmlWriter.writeStartElement("svcRsvSttsCd");
				}

				if (localSvcRsvSttsCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcRsvSttsCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localHposSvcNmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "hposSvcNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "hposSvcNm");
					}

				} else {
					xmlWriter.writeStartElement("hposSvcNm");
				}

				if (localHposSvcNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localHposSvcNm);

				}

				xmlWriter.writeEndElement();
			}
			if (local_rowStatusTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "_rowStatus", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "_rowStatus");
					}

				} else {
					xmlWriter.writeStartElement("_rowStatus");
				}

				if (local_rowStatus == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(local_rowStatus);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			if (localDscntStrtDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntStrtDttm"));

				elementList.add(localDscntStrtDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDscntStrtDttm));
			}
			if (localAsgnNoSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "asgnNoSeqno"));

				elementList.add(localAsgnNoSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAsgnNoSeqno));
			}
			if (localAsgnDscntTelnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "asgnDscntTelno"));

				elementList.add(localAsgnDscntTelno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAsgnDscntTelno));
			}
			if (localDscntEndDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntEndDttm"));

				elementList.add(localDscntEndDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDscntEndDttm));
			}
			if (localDscntKdDtlCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntKdDtlCd"));

				elementList.add(localDscntKdDtlCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDscntKdDtlCd));
			}
			if (localDscntSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntSvcCd"));

				elementList.add(localDscntSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDscntSvcCd));
			}
			if (localHposSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposSvcCd"));

				elementList.add(localHposSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHposSvcCd));
			}
			if (localCtnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ctn"));

				elementList.add(localCtn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCtn));
			}
			if (localSvcGrpSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcGrpSeqno"));

				elementList.add(localSvcGrpSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcGrpSeqno));
			}
			if (localSvcRsvSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcRsvSttsCd"));

				elementList.add(localSvcRsvSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcRsvSttsCd));
			}
			if (localHposSvcNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposSvcNm"));

				elementList.add(localHposSvcNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHposSvcNm));
			}
			if (local_rowStatusTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "_rowStatus"));

				elementList.add(local_rowStatus == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(local_rowStatus));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId"));

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
			public static DsAsgnNoListInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsAsgnNoListInVO object = new DsAsgnNoListInVO();

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

							if (!"DsAsgnNoListInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsAsgnNoListInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntStrtDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDscntStrtDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "asgnNoSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAsgnNoSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "asgnDscntTelno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAsgnDscntTelno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntEndDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDscntEndDttm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntKdDtlCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDscntKdDtlCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "dscntSvcCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDscntSvcCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposSvcCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setHposSvcCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ctn")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCtn(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcGrpSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcGrpSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcRsvSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcRsvSttsCd(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "hposSvcNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setHposSvcNm(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "_rowStatus")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.set_rowStatus(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	public static class DsChkFtrInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = DsChkFtrInVO
		 * Namespace URI = java:lguplus.u3.esb.sm066 Namespace Prefix = ns26
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.sm066")) {
				return "ns26";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for SvcCd
		 */

		protected java.lang.String localSvcCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcCd() {
			return localSvcCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcCd
		 */
		public void setSvcCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcCdTracker = true;
			} else {
				localSvcCdTracker = true;

			}

			this.localSvcCd = param;

		}

		/**
		 * field for FtrCd
		 */

		protected java.lang.String localFtrCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFtrCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFtrCd() {
			return localFtrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FtrCd
		 */
		public void setFtrCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFtrCdTracker = true;
			} else {
				localFtrCdTracker = true;

			}

			this.localFtrCd = param;

		}

		/**
		 * field for FtrNm
		 */

		protected java.lang.String localFtrNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFtrNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFtrNm() {
			return localFtrNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FtrNm
		 */
		public void setFtrNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFtrNmTracker = true;
			} else {
				localFtrNmTracker = true;

			}

			this.localFtrNm = param;

		}

		/**
		 * field for FtrValdStrtDt
		 */

		protected java.lang.String localFtrValdStrtDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFtrValdStrtDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFtrValdStrtDt() {
			return localFtrValdStrtDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FtrValdStrtDt
		 */
		public void setFtrValdStrtDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFtrValdStrtDtTracker = true;
			} else {
				localFtrValdStrtDtTracker = true;

			}

			this.localFtrValdStrtDt = param;

		}

		/**
		 * field for FtrValdEndDt
		 */

		protected java.lang.String localFtrValdEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFtrValdEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFtrValdEndDt() {
			return localFtrValdEndDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FtrValdEndDt
		 */
		public void setFtrValdEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFtrValdEndDtTracker = true;
			} else {
				localFtrValdEndDtTracker = true;

			}

			this.localFtrValdEndDt = param;

		}

		/**
		 * field for VarParam
		 */

		protected java.lang.String localVarParam;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localVarParamTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getVarParam() {
			return localVarParam;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            VarParam
		 */
		public void setVarParam(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localVarParamTracker = true;
			} else {
				localVarParamTracker = true;

			}

			this.localVarParam = param;

		}

		/**
		 * field for FtrVarDtlSeqno
		 */

		protected java.lang.String localFtrVarDtlSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFtrVarDtlSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFtrVarDtlSeqno() {
			return localFtrVarDtlSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FtrVarDtlSeqno
		 */
		public void setFtrVarDtlSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFtrVarDtlSeqnoTracker = true;
			} else {
				localFtrVarDtlSeqnoTracker = true;

			}

			this.localFtrVarDtlSeqno = param;

		}

		/**
		 * field for EntrSvcSeqno
		 */

		protected java.lang.String localEntrSvcSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSvcSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSvcSeqno() {
			return localEntrSvcSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSvcSeqno
		 */
		public void setEntrSvcSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSvcSeqnoTracker = true;
			} else {
				localEntrSvcSeqnoTracker = true;

			}

			this.localEntrSvcSeqno = param;

		}

		/**
		 * field for FtrMode
		 */

		protected java.lang.String localFtrMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFtrModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFtrMode() {
			return localFtrMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FtrMode
		 */
		public void setFtrMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFtrModeTracker = true;
			} else {
				localFtrModeTracker = true;

			}

			this.localFtrMode = param;

		}

		/**
		 * field for FtrSubMode
		 */

		protected java.lang.String localFtrSubMode;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFtrSubModeTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFtrSubMode() {
			return localFtrSubMode;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FtrSubMode
		 */
		public void setFtrSubMode(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFtrSubModeTracker = true;
			} else {
				localFtrSubModeTracker = true;

			}

			this.localFtrSubMode = param;

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
					DsChkFtrInVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.sm066");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsChkFtrInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsChkFtrInVO",
							xmlWriter);
				}

			}
			if (localSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcCd");
					}

				} else {
					xmlWriter.writeStartElement("svcCd");
				}

				if (localSvcCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localFtrCdTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ftrCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ftrCd");
					}

				} else {
					xmlWriter.writeStartElement("ftrCd");
				}

				if (localFtrCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFtrCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localFtrNmTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ftrNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ftrNm");
					}

				} else {
					xmlWriter.writeStartElement("ftrNm");
				}

				if (localFtrNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFtrNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localFtrValdStrtDtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ftrValdStrtDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ftrValdStrtDt");
					}

				} else {
					xmlWriter.writeStartElement("ftrValdStrtDt");
				}

				if (localFtrValdStrtDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFtrValdStrtDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localFtrValdEndDtTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ftrValdEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ftrValdEndDt");
					}

				} else {
					xmlWriter.writeStartElement("ftrValdEndDt");
				}

				if (localFtrValdEndDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFtrValdEndDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localVarParamTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "varParam", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "varParam");
					}

				} else {
					xmlWriter.writeStartElement("varParam");
				}

				if (localVarParam == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localVarParam);

				}

				xmlWriter.writeEndElement();
			}
			if (localFtrVarDtlSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ftrVarDtlSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ftrVarDtlSeqno");
					}

				} else {
					xmlWriter.writeStartElement("ftrVarDtlSeqno");
				}

				if (localFtrVarDtlSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFtrVarDtlSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSvcSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSvcSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSvcSeqno");
					}

				} else {
					xmlWriter.writeStartElement("entrSvcSeqno");
				}

				if (localEntrSvcSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSvcSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localFtrModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ftrMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ftrMode");
					}

				} else {
					xmlWriter.writeStartElement("ftrMode");
				}

				if (localFtrMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFtrMode);

				}

				xmlWriter.writeEndElement();
			}
			if (localFtrSubModeTracker) {
				namespace = "java:lguplus.u3.esb.sm066";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ftrSubMode", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ftrSubMode");
					}

				} else {
					xmlWriter.writeStartElement("ftrSubMode");
				}

				if (localFtrSubMode == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFtrSubMode);

				}

				xmlWriter.writeEndElement();
			}
			namespace = "java:lguplus.u3.esb.sm066";
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
				// we have to store this data until last moment since it is not
				// possible to write any
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

			if (localSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcCd"));

				elementList.add(localSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcCd));
			}
			if (localFtrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrCd"));

				elementList.add(localFtrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFtrCd));
			}
			if (localFtrNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrNm"));

				elementList.add(localFtrNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFtrNm));
			}
			if (localFtrValdStrtDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrValdStrtDt"));

				elementList.add(localFtrValdStrtDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFtrValdStrtDt));
			}
			if (localFtrValdEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrValdEndDt"));

				elementList.add(localFtrValdEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFtrValdEndDt));
			}
			if (localVarParamTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "varParam"));

				elementList.add(localVarParam == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localVarParam));
			}
			if (localFtrVarDtlSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrVarDtlSeqno"));

				elementList.add(localFtrVarDtlSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFtrVarDtlSeqno));
			}
			if (localEntrSvcSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSvcSeqno"));

				elementList.add(localEntrSvcSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSvcSeqno));
			}
			if (localFtrModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrMode"));

				elementList.add(localFtrMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFtrMode));
			}
			if (localFtrSubModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrSubMode"));

				elementList.add(localFtrSubMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFtrSubMode));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId"));

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
			public static DsChkFtrInVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsChkFtrInVO object = new DsChkFtrInVO();

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

							if (!"DsChkFtrInVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsChkFtrInVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
							}

						}

					}

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "svcCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFtrCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFtrNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrValdStrtDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFtrValdStrtDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrValdEndDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFtrValdEndDt(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "varParam")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setVarParam(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrVarDtlSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFtrVarDtlSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "entrSvcSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSvcSeqno(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrMode")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFtrMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "ftrSubMode")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFtrSubMode(
									org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						} else {

							reader.getElementText(); // throw away text nodes if
														// any.
						}

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.sm066", "nextOperatorId")
									.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setNextOperatorId(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

						reader.next();

					} // End of if for expected property start element

					else {
						// A start element we are not expecting indicates an
						// invalid parameter was passed
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());
					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement())
						// A start element we are not expecting indicates a
						// trailing invalid property
						throw new org.apache.axis2.databinding.ADBException(
								"Unexpected subelement " + reader.getLocalName());

				} catch (javax.xml.stream.XMLStreamException e) {
					throw new java.lang.Exception(e);
				}

				return object;
			}

		}// end of factory class

	}

	private org.apache.axiom.om.OMElement toOM(lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsv param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsv.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsvResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsvResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsv param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsv.MY_QNAME, factory));
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

			if (lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsv.class.equals(type)) {

				return lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsv.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsvResponse.class.equals(type)) {

				return lguplus.u3.webservice.sm066.SaveSvcRsvServiceStub.SaveSvcRsvResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
