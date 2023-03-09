
/**
 * RetrieveCustInfoSvcServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.4  Built on : Dec 19, 2010 (08:18:42 CET)
 */
package lguplus.u3.webservice.cm016;

/*
*  RetrieveCustInfoSvcServiceStub java implementation
*/
@SuppressWarnings({"rawtypes", "serial", "deprecation", "unchecked", "unused"})
public class RetrieveCustInfoSvcServiceStub extends org.apache.axis2.client.Stub {
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
		_service = new org.apache.axis2.description.AxisService("RetrieveCustInfoSvcService" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[1];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://lguplus/u3/esb", "retrieveCustInfoSvc"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

	}

	// populates the faults
	private void populateFaults() {

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public RetrieveCustInfoSvcServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public RetrieveCustInfoSvcServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
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
	public RetrieveCustInfoSvcServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://172.22.14.79:15011/CSSI/CM/RetrieveCustInfoSvc");

	}

	/**
	 * Default Constructor
	 */
	public RetrieveCustInfoSvcServiceStub() throws org.apache.axis2.AxisFault {

		this("http://172.22.14.79:15011/CSSI/CM/RetrieveCustInfoSvc");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public RetrieveCustInfoSvcServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see lguplus.u3.webservice.cm016.RetrieveCustInfoSvcService#retrieveCustInfoSvc
	 * @param retrieveCustInfoSvc
	 * 
	 */

	public lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse retrieveCustInfoSvc(

			lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc retrieveCustInfoSvc)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient.getOptions()
					.setAction("http://lguplus/u3/esb/RetrieveCustInfoSvcPortType/RetrieveCustInfoSvcRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);
			_operationClient.getOptions().setTimeOutInMilliSeconds(10000);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), retrieveCustInfoSvc,
					optimizeContent(new javax.xml.namespace.QName("http://lguplus/u3/esb", "retrieveCustInfoSvc")));

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
					lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse) object;

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

	// http://172.22.14.79:15011/CSSI/CM/RetrieveCustInfoSvc
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

	public static class DsReqInVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = DsReqInVO
		 * Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix = ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsReqInVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsReqInVO", xmlWriter);
				}

			}
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			namespace = "java:lguplus.u3.esb.cm016";
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
			if (localProdNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			namespace = "java:lguplus.u3.esb.cm016";
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
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "mode"));

			if (localMode != null) {
				elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMode));
			} else {
				throw new org.apache.axis2.databinding.ADBException("mode cannot be null!!");
			}
			if (localProdNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNo"));

				elementList.add(localProdNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNo));
			}
			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "nextOperatorId"));

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

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "mode")
							.equals(reader.getName())) {

						java.lang.String content = reader.getElementText();

						object.setMode(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "nextOperatorId")
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

	public static class RetrieveCustInfoSvcResponse implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"RetrieveCustInfoSvcResponse", "ns3");

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
					RetrieveCustInfoSvcResponse.this.serialize(MY_QNAME, factory, xmlWriter);
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
							namespacePrefix + ":RetrieveCustInfoSvcResponse", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							"RetrieveCustInfoSvcResponse", xmlWriter);
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
			public static RetrieveCustInfoSvcResponse parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				RetrieveCustInfoSvcResponse object = new RetrieveCustInfoSvcResponse();

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

							if (!"RetrieveCustInfoSvcResponse".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RetrieveCustInfoSvcResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
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

	public static class DsCustInfoOutVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsCustInfoOutVO Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix =
		 * ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for CustNo
		 */

		protected java.lang.String localCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustNo() {
			return localCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustNo
		 */
		public void setCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustNoTracker = true;
			} else {
				localCustNoTracker = true;

			}

			this.localCustNo = param;

		}

		/**
		 * field for CustNm
		 */

		protected java.lang.String localCustNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustNm() {
			return localCustNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustNm
		 */
		public void setCustNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustNmTracker = true;
			} else {
				localCustNmTracker = true;

			}

			this.localCustNm = param;

		}

		/**
		 * field for CustrnmNo
		 */

		protected java.lang.String localCustrnmNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustrnmNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustrnmNo() {
			return localCustrnmNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustrnmNo
		 */
		public void setCustrnmNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustrnmNoTracker = true;
			} else {
				localCustrnmNoTracker = true;

			}

			this.localCustrnmNo = param;

		}

		/**
		 * field for CustDvCd
		 */

		protected java.lang.String localCustDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustDvCd() {
			return localCustDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustDvCd
		 */
		public void setCustDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustDvCdTracker = true;
			} else {
				localCustDvCdTracker = true;

			}

			this.localCustDvCd = param;

		}

		/**
		 * field for CustDvNm
		 */

		protected java.lang.String localCustDvNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustDvNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustDvNm() {
			return localCustDvNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustDvNm
		 */
		public void setCustDvNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustDvNmTracker = true;
			} else {
				localCustDvNmTracker = true;

			}

			this.localCustDvNm = param;

		}

		/**
		 * field for CustKdCd
		 */

		protected java.lang.String localCustKdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustKdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustKdCd() {
			return localCustKdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustKdCd
		 */
		public void setCustKdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustKdCdTracker = true;
			} else {
				localCustKdCdTracker = true;

			}

			this.localCustKdCd = param;

		}

		/**
		 * field for CustKdNm
		 */

		protected java.lang.String localCustKdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustKdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustKdNm() {
			return localCustKdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustKdNm
		 */
		public void setCustKdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustKdNmTracker = true;
			} else {
				localCustKdNmTracker = true;

			}

			this.localCustKdNm = param;

		}

		/**
		 * field for BsRegNo
		 */

		protected java.lang.String localBsRegNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBsRegNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBsRegNo() {
			return localBsRegNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BsRegNo
		 */
		public void setBsRegNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBsRegNoTracker = true;
			} else {
				localBsRegNoTracker = true;

			}

			this.localBsRegNo = param;

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
		 * field for ProdCd
		 */

		protected java.lang.String localProdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdCd() {
			return localProdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdCd
		 */
		public void setProdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdCdTracker = true;
			} else {
				localProdCdTracker = true;

			}

			this.localProdCd = param;

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
		 * field for IntgProdCd
		 */

		protected java.lang.String localIntgProdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIntgProdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIntgProdCd() {
			return localIntgProdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IntgProdCd
		 */
		public void setIntgProdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIntgProdCdTracker = true;
			} else {
				localIntgProdCdTracker = true;

			}

			this.localIntgProdCd = param;

		}

		/**
		 * field for IntgProdEntrDvNo
		 */

		protected java.lang.String localIntgProdEntrDvNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIntgProdEntrDvNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIntgProdEntrDvNo() {
			return localIntgProdEntrDvNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IntgProdEntrDvNo
		 */
		public void setIntgProdEntrDvNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIntgProdEntrDvNoTracker = true;
			} else {
				localIntgProdEntrDvNoTracker = true;

			}

			this.localIntgProdEntrDvNo = param;

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
		 * field for Mic2
		 */

		protected java.lang.String localMic2;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localMic2Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getMic2() {
			return localMic2;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Mic2
		 */
		public void setMic2(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localMic2Tracker = true;
			} else {
				localMic2Tracker = true;

			}

			this.localMic2 = param;

		}

		/**
		 * field for EntrKdCd
		 */

		protected java.lang.String localEntrKdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrKdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrKdCd() {
			return localEntrKdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrKdCd
		 */
		public void setEntrKdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrKdCdTracker = true;
			} else {
				localEntrKdCdTracker = true;

			}

			this.localEntrKdCd = param;

		}

		/**
		 * field for EntrBizPlcyCd
		 */

		protected java.lang.String localEntrBizPlcyCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrBizPlcyCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrBizPlcyCd() {
			return localEntrBizPlcyCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrBizPlcyCd
		 */
		public void setEntrBizPlcyCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrBizPlcyCdTracker = true;
			} else {
				localEntrBizPlcyCdTracker = true;

			}

			this.localEntrBizPlcyCd = param;

		}

		/**
		 * field for LastBizPlcyCd
		 */

		protected java.lang.String localLastBizPlcyCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLastBizPlcyCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLastBizPlcyCd() {
			return localLastBizPlcyCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LastBizPlcyCd
		 */
		public void setLastBizPlcyCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLastBizPlcyCdTracker = true;
			} else {
				localLastBizPlcyCdTracker = true;

			}

			this.localLastBizPlcyCd = param;

		}

		/**
		 * field for LastBizPlcyChngDttm
		 */

		protected java.lang.String localLastBizPlcyChngDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLastBizPlcyChngDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLastBizPlcyChngDttm() {
			return localLastBizPlcyChngDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LastBizPlcyChngDttm
		 */
		public void setLastBizPlcyChngDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLastBizPlcyChngDttmTracker = true;
			} else {
				localLastBizPlcyChngDttmTracker = true;

			}

			this.localLastBizPlcyChngDttm = param;

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
		 * field for EntrSttsChngRsnCd
		 */

		protected java.lang.String localEntrSttsChngRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngRsnCd() {
			return localEntrSttsChngRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngRsnCd
		 */
		public void setEntrSttsChngRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngRsnCdTracker = true;
			} else {
				localEntrSttsChngRsnCdTracker = true;

			}

			this.localEntrSttsChngRsnCd = param;

		}

		/**
		 * field for EntrSttsChngRsnDtlCd
		 */

		protected java.lang.String localEntrSttsChngRsnDtlCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngRsnDtlCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngRsnDtlCd() {
			return localEntrSttsChngRsnDtlCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngRsnDtlCd
		 */
		public void setEntrSttsChngRsnDtlCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngRsnDtlCdTracker = true;
			} else {
				localEntrSttsChngRsnDtlCdTracker = true;

			}

			this.localEntrSttsChngRsnDtlCd = param;

		}

		/**
		 * field for EntrDlrCd
		 */

		protected java.lang.String localEntrDlrCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrDlrCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrDlrCd() {
			return localEntrDlrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrDlrCd
		 */
		public void setEntrDlrCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrDlrCdTracker = true;
			} else {
				localEntrDlrCdTracker = true;

			}

			this.localEntrDlrCd = param;

		}

		/**
		 * field for FrstEntrDttm
		 */

		protected java.lang.String localFrstEntrDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localFrstEntrDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getFrstEntrDttm() {
			return localFrstEntrDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            FrstEntrDttm
		 */
		public void setFrstEntrDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localFrstEntrDttmTracker = true;
			} else {
				localFrstEntrDttmTracker = true;

			}

			this.localFrstEntrDttm = param;

		}

		/**
		 * field for EntrSttsChngDttm
		 */

		protected java.lang.String localEntrSttsChngDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngDttm() {
			return localEntrSttsChngDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngDttm
		 */
		public void setEntrSttsChngDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngDttmTracker = true;
			} else {
				localEntrSttsChngDttmTracker = true;

			}

			this.localEntrSttsChngDttm = param;

		}

		/**
		 * field for LtpymSttsCd
		 */

		protected java.lang.String localLtpymSttsCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtpymSttsCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtpymSttsCd() {
			return localLtpymSttsCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtpymSttsCd
		 */
		public void setLtpymSttsCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtpymSttsCdTracker = true;
			} else {
				localLtpymSttsCdTracker = true;

			}

			this.localLtpymSttsCd = param;

		}

		/**
		 * field for LtpymSttsChngDt
		 */

		protected java.lang.String localLtpymSttsChngDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtpymSttsChngDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtpymSttsChngDt() {
			return localLtpymSttsChngDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtpymSttsChngDt
		 */
		public void setLtpymSttsChngDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtpymSttsChngDtTracker = true;
			} else {
				localLtpymSttsChngDtTracker = true;

			}

			this.localLtpymSttsChngDt = param;

		}

		/**
		 * field for LtpymActPathCd
		 */

		protected java.lang.String localLtpymActPathCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtpymActPathCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtpymActPathCd() {
			return localLtpymActPathCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtpymActPathCd
		 */
		public void setLtpymActPathCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtpymActPathCdTracker = true;
			} else {
				localLtpymActPathCdTracker = true;

			}

			this.localLtpymActPathCd = param;

		}

		/**
		 * field for LtpymActNextStgeNo
		 */

		protected java.lang.String localLtpymActNextStgeNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtpymActNextStgeNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtpymActNextStgeNo() {
			return localLtpymActNextStgeNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtpymActNextStgeNo
		 */
		public void setLtpymActNextStgeNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtpymActNextStgeNoTracker = true;
			} else {
				localLtpymActNextStgeNoTracker = true;

			}

			this.localLtpymActNextStgeNo = param;

		}

		/**
		 * field for LtpymActNextStgeScdlDt
		 */

		protected java.lang.String localLtpymActNextStgeScdlDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtpymActNextStgeScdlDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtpymActNextStgeScdlDt() {
			return localLtpymActNextStgeScdlDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtpymActNextStgeScdlDt
		 */
		public void setLtpymActNextStgeScdlDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtpymActNextStgeScdlDtTracker = true;
			} else {
				localLtpymActNextStgeScdlDtTracker = true;

			}

			this.localLtpymActNextStgeScdlDt = param;

		}

		/**
		 * field for CntcSttsGrdCd
		 */

		protected java.lang.String localCntcSttsGrdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCntcSttsGrdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCntcSttsGrdCd() {
			return localCntcSttsGrdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CntcSttsGrdCd
		 */
		public void setCntcSttsGrdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCntcSttsGrdCdTracker = true;
			} else {
				localCntcSttsGrdCdTracker = true;

			}

			this.localCntcSttsGrdCd = param;

		}

		/**
		 * field for ProdNm
		 */

		protected java.lang.String localProdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdNm() {
			return localProdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdNm
		 */
		public void setProdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdNmTracker = true;
			} else {
				localProdNmTracker = true;

			}

			this.localProdNm = param;

		}

		/**
		 * field for EntrSttsNm
		 */

		protected java.lang.String localEntrSttsNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsNm() {
			return localEntrSttsNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsNm
		 */
		public void setEntrSttsNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsNmTracker = true;
			} else {
				localEntrSttsNmTracker = true;

			}

			this.localEntrSttsNm = param;

		}

		/**
		 * field for EntrSttsChngRsnNm
		 */

		protected java.lang.String localEntrSttsChngRsnNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngRsnNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngRsnNm() {
			return localEntrSttsChngRsnNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngRsnNm
		 */
		public void setEntrSttsChngRsnNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngRsnNmTracker = true;
			} else {
				localEntrSttsChngRsnNmTracker = true;

			}

			this.localEntrSttsChngRsnNm = param;

		}

		/**
		 * field for EntrSttsChngRsnDtlNm
		 */

		protected java.lang.String localEntrSttsChngRsnDtlNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEntrSttsChngRsnDtlNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEntrSttsChngRsnDtlNm() {
			return localEntrSttsChngRsnDtlNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EntrSttsChngRsnDtlNm
		 */
		public void setEntrSttsChngRsnDtlNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEntrSttsChngRsnDtlNmTracker = true;
			} else {
				localEntrSttsChngRsnDtlNmTracker = true;

			}

			this.localEntrSttsChngRsnDtlNm = param;

		}

		/**
		 * field for RlusrCustNo
		 */

		protected java.lang.String localRlusrCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustNo() {
			return localRlusrCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustNo
		 */
		public void setRlusrCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustNoTracker = true;
			} else {
				localRlusrCustNoTracker = true;

			}

			this.localRlusrCustNo = param;

		}

		/**
		 * field for RlusrCustNm
		 */

		protected java.lang.String localRlusrCustNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustNm() {
			return localRlusrCustNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustNm
		 */
		public void setRlusrCustNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustNmTracker = true;
			} else {
				localRlusrCustNmTracker = true;

			}

			this.localRlusrCustNm = param;

		}

		/**
		 * field for RlusrCustrnmNo
		 */

		protected java.lang.String localRlusrCustrnmNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustrnmNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustrnmNo() {
			return localRlusrCustrnmNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustrnmNo
		 */
		public void setRlusrCustrnmNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustrnmNoTracker = true;
			} else {
				localRlusrCustrnmNoTracker = true;

			}

			this.localRlusrCustrnmNo = param;

		}

		/**
		 * field for RlusrCustDvCd
		 */

		protected java.lang.String localRlusrCustDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustDvCd() {
			return localRlusrCustDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustDvCd
		 */
		public void setRlusrCustDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustDvCdTracker = true;
			} else {
				localRlusrCustDvCdTracker = true;

			}

			this.localRlusrCustDvCd = param;

		}

		/**
		 * field for RlusrCustDvNm
		 */

		protected java.lang.String localRlusrCustDvNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustDvNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustDvNm() {
			return localRlusrCustDvNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustDvNm
		 */
		public void setRlusrCustDvNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustDvNmTracker = true;
			} else {
				localRlusrCustDvNmTracker = true;

			}

			this.localRlusrCustDvNm = param;

		}

		/**
		 * field for RlusrCustKdCd
		 */

		protected java.lang.String localRlusrCustKdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustKdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustKdCd() {
			return localRlusrCustKdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustKdCd
		 */
		public void setRlusrCustKdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustKdCdTracker = true;
			} else {
				localRlusrCustKdCdTracker = true;

			}

			this.localRlusrCustKdCd = param;

		}

		/**
		 * field for RlusrCustKdNm
		 */

		protected java.lang.String localRlusrCustKdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrCustKdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrCustKdNm() {
			return localRlusrCustKdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrCustKdNm
		 */
		public void setRlusrCustKdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrCustKdNmTracker = true;
			} else {
				localRlusrCustKdNmTracker = true;

			}

			this.localRlusrCustKdNm = param;

		}

		/**
		 * field for RlusrBsRegNo
		 */

		protected java.lang.String localRlusrBsRegNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRlusrBsRegNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRlusrBsRegNo() {
			return localRlusrBsRegNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RlusrBsRegNo
		 */
		public void setRlusrBsRegNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRlusrBsRegNoTracker = true;
			} else {
				localRlusrBsRegNoTracker = true;

			}

			this.localRlusrBsRegNo = param;

		}

		/**
		 * field for EmailAddr
		 */

		protected java.lang.String localEmailAddr;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEmailAddrTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEmailAddr() {
			return localEmailAddr;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EmailAddr
		 */
		public void setEmailAddr(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEmailAddrTracker = true;
			} else {
				localEmailAddrTracker = true;

			}

			this.localEmailAddr = param;

		}

		/**
		 * field for CustGrdCd
		 */

		protected java.lang.String localCustGrdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustGrdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustGrdCd() {
			return localCustGrdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustGrdCd
		 */
		public void setCustGrdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustGrdCdTracker = true;
			} else {
				localCustGrdCdTracker = true;

			}

			this.localCustGrdCd = param;

		}

		/**
		 * field for CustGrdNm
		 */

		protected java.lang.String localCustGrdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localCustGrdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getCustGrdNm() {
			return localCustGrdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            CustGrdNm
		 */
		public void setCustGrdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localCustGrdNmTracker = true;
			} else {
				localCustGrdNmTracker = true;

			}

			this.localCustGrdNm = param;

		}

		/**
		 * field for LtvGrdCd
		 */

		protected java.lang.String localLtvGrdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtvGrdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtvGrdCd() {
			return localLtvGrdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtvGrdCd
		 */
		public void setLtvGrdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtvGrdCdTracker = true;
			} else {
				localLtvGrdCdTracker = true;

			}

			this.localLtvGrdCd = param;

		}

		/**
		 * field for LtvGrdNm
		 */

		protected java.lang.String localLtvGrdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localLtvGrdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getLtvGrdNm() {
			return localLtvGrdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            LtvGrdNm
		 */
		public void setLtvGrdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localLtvGrdNmTracker = true;
			} else {
				localLtvGrdNmTracker = true;

			}

			this.localLtvGrdNm = param;

		}

		/**
		 * field for DevchGrdCd
		 */

		protected java.lang.String localDevchGrdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevchGrdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevchGrdCd() {
			return localDevchGrdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevchGrdCd
		 */
		public void setDevchGrdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevchGrdCdTracker = true;
			} else {
				localDevchGrdCdTracker = true;

			}

			this.localDevchGrdCd = param;

		}

		/**
		 * field for DevchGrdNm
		 */

		protected java.lang.String localDevchGrdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevchGrdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevchGrdNm() {
			return localDevchGrdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevchGrdNm
		 */
		public void setDevchGrdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevchGrdNmTracker = true;
			} else {
				localDevchGrdNmTracker = true;

			}

			this.localDevchGrdNm = param;

		}

		/**
		 * field for ExpryGrdCd
		 */

		protected java.lang.String localExpryGrdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localExpryGrdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getExpryGrdCd() {
			return localExpryGrdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ExpryGrdCd
		 */
		public void setExpryGrdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localExpryGrdCdTracker = true;
			} else {
				localExpryGrdCdTracker = true;

			}

			this.localExpryGrdCd = param;

		}

		/**
		 * field for ExpryGrdNm
		 */

		protected java.lang.String localExpryGrdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localExpryGrdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getExpryGrdNm() {
			return localExpryGrdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ExpryGrdNm
		 */
		public void setExpryGrdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localExpryGrdNmTracker = true;
			} else {
				localExpryGrdNmTracker = true;

			}

			this.localExpryGrdNm = param;

		}

		/**
		 * field for Selected
		 */

		protected java.lang.String localSelected;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSelectedTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSelected() {
			return localSelected;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Selected
		 */
		public void setSelected(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSelectedTracker = true;
			} else {
				localSelectedTracker = true;

			}

			this.localSelected = param;

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
		 * field for AftRankPymAcntNo
		 */

		protected java.lang.String localAftRankPymAcntNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAftRankPymAcntNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAftRankPymAcntNo() {
			return localAftRankPymAcntNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AftRankPymAcntNo
		 */
		public void setAftRankPymAcntNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAftRankPymAcntNoTracker = true;
			} else {
				localAftRankPymAcntNoTracker = true;

			}

			this.localAftRankPymAcntNo = param;

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
		 * field for BlMic2
		 */

		protected java.lang.String localBlMic2;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localBlMic2Tracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getBlMic2() {
			return localBlMic2;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            BlMic2
		 */
		public void setBlMic2(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localBlMic2Tracker = true;
			} else {
				localBlMic2Tracker = true;

			}

			this.localBlMic2 = param;

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
		 * field for PymManCustNo
		 */

		protected java.lang.String localPymManCustNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPymManCustNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPymManCustNo() {
			return localPymManCustNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PymManCustNo
		 */
		public void setPymManCustNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPymManCustNoTracker = true;
			} else {
				localPymManCustNoTracker = true;

			}

			this.localPymManCustNo = param;

		}

		/**
		 * field for AcntOwnrNo
		 */

		protected java.lang.String localAcntOwnrNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAcntOwnrNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAcntOwnrNo() {
			return localAcntOwnrNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AcntOwnrNo
		 */
		public void setAcntOwnrNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAcntOwnrNoTracker = true;
			} else {
				localAcntOwnrNoTracker = true;

			}

			this.localAcntOwnrNo = param;

		}

		/**
		 * field for PymMthdCd
		 */

		protected java.lang.String localPymMthdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPymMthdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPymMthdCd() {
			return localPymMthdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PymMthdCd
		 */
		public void setPymMthdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPymMthdCdTracker = true;
			} else {
				localPymMthdCdTracker = true;

			}

			this.localPymMthdCd = param;

		}

		/**
		 * field for PymMthdNm
		 */

		protected java.lang.String localPymMthdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localPymMthdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getPymMthdNm() {
			return localPymMthdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            PymMthdNm
		 */
		public void setPymMthdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localPymMthdNmTracker = true;
			} else {
				localPymMthdNmTracker = true;

			}

			this.localPymMthdNm = param;

		}

		/**
		 * field for IsDcNo
		 */

		protected java.lang.String localIsDcNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIsDcNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsDcNo() {
			return localIsDcNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IsDcNo
		 */
		public void setIsDcNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsDcNoTracker = true;
			} else {
				localIsDcNoTracker = true;

			}

			this.localIsDcNo = param;

		}

		/**
		 * field for IsAgmtDc
		 */

		protected java.lang.String localIsAgmtDc;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIsAgmtDcTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsAgmtDc() {
			return localIsAgmtDc;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IsAgmtDc
		 */
		public void setIsAgmtDc(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsAgmtDcTracker = true;
			} else {
				localIsAgmtDcTracker = true;

			}

			this.localIsAgmtDc = param;

		}

		/**
		 * field for IsPntDc
		 */

		protected java.lang.String localIsPntDc;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIsPntDcTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsPntDc() {
			return localIsPntDc;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IsPntDc
		 */
		public void setIsPntDc(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsPntDcTracker = true;
			} else {
				localIsPntDcTracker = true;

			}

			this.localIsPntDc = param;

		}

		/**
		 * field for IsFmlyLovDc
		 */

		protected java.lang.String localIsFmlyLovDc;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIsFmlyLovDcTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsFmlyLovDc() {
			return localIsFmlyLovDc;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IsFmlyLovDc
		 */
		public void setIsFmlyLovDc(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsFmlyLovDcTracker = true;
			} else {
				localIsFmlyLovDcTracker = true;

			}

			this.localIsFmlyLovDc = param;

		}

		/**
		 * field for IsFmlyRept
		 */

		protected java.lang.String localIsFmlyRept;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIsFmlyReptTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsFmlyRept() {
			return localIsFmlyRept;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IsFmlyRept
		 */
		public void setIsFmlyRept(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsFmlyReptTracker = true;
			} else {
				localIsFmlyReptTracker = true;

			}

			this.localIsFmlyRept = param;

		}

		/**
		 * field for IsBillDc
		 */

		protected java.lang.String localIsBillDc;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIsBillDcTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsBillDc() {
			return localIsBillDc;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IsBillDc
		 */
		public void setIsBillDc(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsBillDcTracker = true;
			} else {
				localIsBillDcTracker = true;

			}

			this.localIsBillDc = param;

		}

		/**
		 * field for IsEcoDc
		 */

		protected java.lang.String localIsEcoDc;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localIsEcoDcTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getIsEcoDc() {
			return localIsEcoDc;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            IsEcoDc
		 */
		public void setIsEcoDc(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localIsEcoDcTracker = true;
			} else {
				localIsEcoDcTracker = true;

			}

			this.localIsEcoDc = param;

		}

		/**
		 * field for AplyMnth
		 */

		protected java.lang.String localAplyMnth;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localAplyMnthTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getAplyMnth() {
			return localAplyMnth;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            AplyMnth
		 */
		public void setAplyMnth(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localAplyMnthTracker = true;
			} else {
				localAplyMnthTracker = true;

			}

			this.localAplyMnth = param;

		}

		/**
		 * field for RmndMnth
		 */

		protected java.lang.String localRmndMnth;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRmndMnthTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRmndMnth() {
			return localRmndMnth;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            RmndMnth
		 */
		public void setRmndMnth(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRmndMnthTracker = true;
			} else {
				localRmndMnthTracker = true;

			}

			this.localRmndMnth = param;

		}

		/**
		 * field for ImsiNo
		 */

		protected java.lang.String localImsiNo;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localImsiNoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getImsiNo() {
			return localImsiNo;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ImsiNo
		 */
		public void setImsiNo(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localImsiNoTracker = true;
			} else {
				localImsiNoTracker = true;

			}

			this.localImsiNo = param;

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
					DsCustInfoOutVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsCustInfoOutVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsCustInfoOutVO",
							xmlWriter);
				}

			}
			if (localCustNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custNo");
					}

				} else {
					xmlWriter.writeStartElement("custNo");
				}

				if (localCustNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custNm");
					}

				} else {
					xmlWriter.writeStartElement("custNm");
				}

				if (localCustNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustrnmNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custrnmNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custrnmNo");
					}

				} else {
					xmlWriter.writeStartElement("custrnmNo");
				}

				if (localCustrnmNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustrnmNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustDvCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custDvCd");
					}

				} else {
					xmlWriter.writeStartElement("custDvCd");
				}

				if (localCustDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustDvNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custDvNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custDvNm");
					}

				} else {
					xmlWriter.writeStartElement("custDvNm");
				}

				if (localCustDvNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustDvNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustKdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custKdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custKdCd");
					}

				} else {
					xmlWriter.writeStartElement("custKdCd");
				}

				if (localCustKdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustKdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustKdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custKdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custKdNm");
					}

				} else {
					xmlWriter.writeStartElement("custKdNm");
				}

				if (localCustKdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustKdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localBsRegNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "bsRegNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "bsRegNo");
					}

				} else {
					xmlWriter.writeStartElement("bsRegNo");
				}

				if (localBsRegNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBsRegNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localValdEndDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localProdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodCd");
					}

				} else {
					xmlWriter.writeStartElement("prodCd");
				}

				if (localProdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localProdNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localIntgProdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "intgProdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "intgProdCd");
					}

				} else {
					xmlWriter.writeStartElement("intgProdCd");
				}

				if (localIntgProdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIntgProdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localIntgProdEntrDvNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "intgProdEntrDvNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "intgProdEntrDvNo");
					}

				} else {
					xmlWriter.writeStartElement("intgProdEntrDvNo");
				}

				if (localIntgProdEntrDvNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIntgProdEntrDvNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcenoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localBillAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localPymAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localMrktCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localMic2Tracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "mic2", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "mic2");
					}

				} else {
					xmlWriter.writeStartElement("mic2");
				}

				if (localMic2 == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localMic2);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrKdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrKdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrKdCd");
					}

				} else {
					xmlWriter.writeStartElement("entrKdCd");
				}

				if (localEntrKdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrKdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrBizPlcyCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrBizPlcyCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrBizPlcyCd");
					}

				} else {
					xmlWriter.writeStartElement("entrBizPlcyCd");
				}

				if (localEntrBizPlcyCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrBizPlcyCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localLastBizPlcyCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "lastBizPlcyCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "lastBizPlcyCd");
					}

				} else {
					xmlWriter.writeStartElement("lastBizPlcyCd");
				}

				if (localLastBizPlcyCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLastBizPlcyCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localLastBizPlcyChngDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "lastBizPlcyChngDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "lastBizPlcyChngDttm");
					}

				} else {
					xmlWriter.writeStartElement("lastBizPlcyChngDttm");
				}

				if (localLastBizPlcyChngDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLastBizPlcyChngDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localEntrSttsChngRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngRsnCd");
				}

				if (localEntrSttsChngRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngRsnDtlCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "EntrSttsChngRsnDtlCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "EntrSttsChngRsnDtlCd");
					}

				} else {
					xmlWriter.writeStartElement("EntrSttsChngRsnDtlCd");
				}

				if (localEntrSttsChngRsnDtlCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngRsnDtlCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrDlrCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrDlrCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrDlrCd");
					}

				} else {
					xmlWriter.writeStartElement("entrDlrCd");
				}

				if (localEntrDlrCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrDlrCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localFrstEntrDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "frstEntrDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "frstEntrDttm");
					}

				} else {
					xmlWriter.writeStartElement("frstEntrDttm");
				}

				if (localFrstEntrDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localFrstEntrDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngDttm");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngDttm");
				}

				if (localEntrSttsChngDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtpymSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltpymSttsCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltpymSttsCd");
					}

				} else {
					xmlWriter.writeStartElement("ltpymSttsCd");
				}

				if (localLtpymSttsCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtpymSttsCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtpymSttsChngDtTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltpymSttsChngDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltpymSttsChngDt");
					}

				} else {
					xmlWriter.writeStartElement("ltpymSttsChngDt");
				}

				if (localLtpymSttsChngDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtpymSttsChngDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtpymActPathCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltpymActPathCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltpymActPathCd");
					}

				} else {
					xmlWriter.writeStartElement("ltpymActPathCd");
				}

				if (localLtpymActPathCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtpymActPathCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtpymActNextStgeNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltpymActNextStgeNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltpymActNextStgeNo");
					}

				} else {
					xmlWriter.writeStartElement("ltpymActNextStgeNo");
				}

				if (localLtpymActNextStgeNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtpymActNextStgeNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtpymActNextStgeScdlDtTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltpymActNextStgeScdlDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltpymActNextStgeScdlDt");
					}

				} else {
					xmlWriter.writeStartElement("ltpymActNextStgeScdlDt");
				}

				if (localLtpymActNextStgeScdlDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtpymActNextStgeScdlDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localCntcSttsGrdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "cntcSttsGrdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "cntcSttsGrdCd");
					}

				} else {
					xmlWriter.writeStartElement("cntcSttsGrdCd");
				}

				if (localCntcSttsGrdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCntcSttsGrdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localProdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodNm");
					}

				} else {
					xmlWriter.writeStartElement("prodNm");
				}

				if (localProdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsNm");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsNm");
				}

				if (localEntrSttsNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngRsnNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngRsnNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngRsnNm");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngRsnNm");
				}

				if (localEntrSttsChngRsnNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngRsnNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localEntrSttsChngRsnDtlNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "entrSttsChngRsnDtlNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "entrSttsChngRsnDtlNm");
					}

				} else {
					xmlWriter.writeStartElement("entrSttsChngRsnDtlNm");
				}

				if (localEntrSttsChngRsnDtlNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEntrSttsChngRsnDtlNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustNo");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustNo");
				}

				if (localRlusrCustNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustNm");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustNm");
				}

				if (localRlusrCustNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustrnmNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustrnmNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustrnmNo");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustrnmNo");
				}

				if (localRlusrCustrnmNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustrnmNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustDvCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustDvCd");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustDvCd");
				}

				if (localRlusrCustDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustDvNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustDvNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustDvNm");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustDvNm");
				}

				if (localRlusrCustDvNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustDvNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustKdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustKdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustKdCd");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustKdCd");
				}

				if (localRlusrCustKdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustKdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrCustKdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrCustKdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrCustKdNm");
					}

				} else {
					xmlWriter.writeStartElement("rlusrCustKdNm");
				}

				if (localRlusrCustKdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrCustKdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localRlusrBsRegNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rlusrBsRegNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rlusrBsRegNo");
					}

				} else {
					xmlWriter.writeStartElement("rlusrBsRegNo");
				}

				if (localRlusrBsRegNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRlusrBsRegNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localEmailAddrTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "emailAddr", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "emailAddr");
					}

				} else {
					xmlWriter.writeStartElement("emailAddr");
				}

				if (localEmailAddr == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEmailAddr);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustGrdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custGrdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custGrdCd");
					}

				} else {
					xmlWriter.writeStartElement("custGrdCd");
				}

				if (localCustGrdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustGrdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localCustGrdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "custGrdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "custGrdNm");
					}

				} else {
					xmlWriter.writeStartElement("custGrdNm");
				}

				if (localCustGrdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localCustGrdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtvGrdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltvGrdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltvGrdCd");
					}

				} else {
					xmlWriter.writeStartElement("ltvGrdCd");
				}

				if (localLtvGrdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtvGrdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localLtvGrdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "ltvGrdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "ltvGrdNm");
					}

				} else {
					xmlWriter.writeStartElement("ltvGrdNm");
				}

				if (localLtvGrdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localLtvGrdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localDevchGrdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devchGrdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devchGrdCd");
					}

				} else {
					xmlWriter.writeStartElement("devchGrdCd");
				}

				if (localDevchGrdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevchGrdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localDevchGrdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devchGrdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devchGrdNm");
					}

				} else {
					xmlWriter.writeStartElement("devchGrdNm");
				}

				if (localDevchGrdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevchGrdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localExpryGrdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "expryGrdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "expryGrdCd");
					}

				} else {
					xmlWriter.writeStartElement("expryGrdCd");
				}

				if (localExpryGrdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localExpryGrdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localExpryGrdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "expryGrdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "expryGrdNm");
					}

				} else {
					xmlWriter.writeStartElement("expryGrdNm");
				}

				if (localExpryGrdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localExpryGrdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSelectedTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "selected", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "selected");
					}

				} else {
					xmlWriter.writeStartElement("selected");
				}

				if (localSelected == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSelected);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillAddrSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localAftRankPymAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "aftRankPymAcntNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "aftRankPymAcntNo");
					}

				} else {
					xmlWriter.writeStartElement("aftRankPymAcntNo");
				}

				if (localAftRankPymAcntNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAftRankPymAcntNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localBillEmailAddrTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localCustAddrZipTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localBlMic2Tracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "blMic2", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "blMic2");
					}

				} else {
					xmlWriter.writeStartElement("blMic2");
				}

				if (localBlMic2 == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localBlMic2);

				}

				xmlWriter.writeEndElement();
			}
			if (localPpayAcntYnTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localPymManCustNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "pymManCustNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "pymManCustNo");
					}

				} else {
					xmlWriter.writeStartElement("pymManCustNo");
				}

				if (localPymManCustNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPymManCustNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localAcntOwnrNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "acntOwnrNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "acntOwnrNo");
					}

				} else {
					xmlWriter.writeStartElement("acntOwnrNo");
				}

				if (localAcntOwnrNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAcntOwnrNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localPymMthdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "pymMthdCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "pymMthdCd");
					}

				} else {
					xmlWriter.writeStartElement("pymMthdCd");
				}

				if (localPymMthdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPymMthdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localPymMthdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "pymMthdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "pymMthdNm");
					}

				} else {
					xmlWriter.writeStartElement("pymMthdNm");
				}

				if (localPymMthdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localPymMthdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsDcNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isDcNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isDcNo");
					}

				} else {
					xmlWriter.writeStartElement("isDcNo");
				}

				if (localIsDcNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIsDcNo);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsAgmtDcTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isAgmtDc", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isAgmtDc");
					}

				} else {
					xmlWriter.writeStartElement("isAgmtDc");
				}

				if (localIsAgmtDc == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIsAgmtDc);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsPntDcTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isPntDc", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isPntDc");
					}

				} else {
					xmlWriter.writeStartElement("isPntDc");
				}

				if (localIsPntDc == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIsPntDc);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsFmlyLovDcTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isFmlyLovDc", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isFmlyLovDc");
					}

				} else {
					xmlWriter.writeStartElement("isFmlyLovDc");
				}

				if (localIsFmlyLovDc == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIsFmlyLovDc);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsFmlyReptTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isFmlyRept", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isFmlyRept");
					}

				} else {
					xmlWriter.writeStartElement("isFmlyRept");
				}

				if (localIsFmlyRept == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIsFmlyRept);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsBillDcTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isBillDc", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isBillDc");
					}

				} else {
					xmlWriter.writeStartElement("isBillDc");
				}

				if (localIsBillDc == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIsBillDc);

				}

				xmlWriter.writeEndElement();
			}
			if (localIsEcoDcTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "isEcoDc", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "isEcoDc");
					}

				} else {
					xmlWriter.writeStartElement("isEcoDc");
				}

				if (localIsEcoDc == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localIsEcoDc);

				}

				xmlWriter.writeEndElement();
			}
			if (localAplyMnthTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "aplyMnth", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "aplyMnth");
					}

				} else {
					xmlWriter.writeStartElement("aplyMnth");
				}

				if (localAplyMnth == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localAplyMnth);

				}

				xmlWriter.writeEndElement();
			}
			if (localRmndMnthTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rmndMnth", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rmndMnth");
					}

				} else {
					xmlWriter.writeStartElement("rmndMnth");
				}

				if (localRmndMnth == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRmndMnth);

				}

				xmlWriter.writeEndElement();
			}
			if (localImsiNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "imsiNo", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "imsiNo");
					}

				} else {
					xmlWriter.writeStartElement("imsiNo");
				}

				if (localImsiNo == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localImsiNo);

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

			if (localCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custNo"));

				elementList.add(localCustNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustNo));
			}
			if (localCustNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custNm"));

				elementList.add(localCustNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustNm));
			}
			if (localCustrnmNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custrnmNo"));

				elementList.add(localCustrnmNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustrnmNo));
			}
			if (localCustDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custDvCd"));

				elementList.add(localCustDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustDvCd));
			}
			if (localCustDvNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custDvNm"));

				elementList.add(localCustDvNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustDvNm));
			}
			if (localCustKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custKdCd"));

				elementList.add(localCustKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustKdCd));
			}
			if (localCustKdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custKdNm"));

				elementList.add(localCustKdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustKdNm));
			}
			if (localBsRegNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "bsRegNo"));

				elementList.add(localBsRegNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBsRegNo));
			}
			if (localEntrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			if (localValdEndDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "valdEndDttm"));

				elementList.add(localValdEndDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localValdEndDttm));
			}
			if (localProdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodCd"));

				elementList.add(localProdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdCd));
			}
			if (localProdNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNo"));

				elementList.add(localProdNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNo));
			}
			if (localIntgProdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "intgProdCd"));

				elementList.add(localIntgProdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIntgProdCd));
			}
			if (localIntgProdEntrDvNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "intgProdEntrDvNo"));

				elementList.add(localIntgProdEntrDvNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIntgProdEntrDvNo));
			}
			if (localAcenoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aceno"));

				elementList.add(localAceno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAceno));
			}
			if (localBillAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntNo"));

				elementList.add(localBillAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntNo));
			}
			if (localPymAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymAcntNo"));

				elementList.add(localPymAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPymAcntNo));
			}
			if (localMrktCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "mrktCd"));

				elementList.add(localMrktCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMrktCd));
			}
			if (localMic2Tracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "mic2"));

				elementList.add(localMic2 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMic2));
			}
			if (localEntrKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrKdCd"));

				elementList.add(localEntrKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrKdCd));
			}
			if (localEntrBizPlcyCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrBizPlcyCd"));

				elementList.add(localEntrBizPlcyCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrBizPlcyCd));
			}
			if (localLastBizPlcyCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "lastBizPlcyCd"));

				elementList.add(localLastBizPlcyCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLastBizPlcyCd));
			}
			if (localLastBizPlcyChngDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "lastBizPlcyChngDttm"));

				elementList.add(localLastBizPlcyChngDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLastBizPlcyChngDttm));
			}
			if (localEntrSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsCd"));

				elementList.add(localEntrSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsCd));
			}
			if (localEntrSttsChngRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngRsnCd"));

				elementList.add(localEntrSttsChngRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngRsnCd));
			}
			if (localEntrSttsChngRsnDtlCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "EntrSttsChngRsnDtlCd"));

				elementList.add(localEntrSttsChngRsnDtlCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngRsnDtlCd));
			}
			if (localEntrDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrDlrCd"));

				elementList.add(localEntrDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrDlrCd));
			}
			if (localFrstEntrDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "frstEntrDttm"));

				elementList.add(localFrstEntrDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFrstEntrDttm));
			}
			if (localEntrSttsChngDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngDttm"));

				elementList.add(localEntrSttsChngDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngDttm));
			}
			if (localLtpymSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymSttsCd"));

				elementList.add(localLtpymSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLtpymSttsCd));
			}
			if (localLtpymSttsChngDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymSttsChngDt"));

				elementList.add(localLtpymSttsChngDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLtpymSttsChngDt));
			}
			if (localLtpymActPathCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymActPathCd"));

				elementList.add(localLtpymActPathCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLtpymActPathCd));
			}
			if (localLtpymActNextStgeNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymActNextStgeNo"));

				elementList.add(localLtpymActNextStgeNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLtpymActNextStgeNo));
			}
			if (localLtpymActNextStgeScdlDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymActNextStgeScdlDt"));

				elementList.add(localLtpymActNextStgeScdlDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localLtpymActNextStgeScdlDt));
			}
			if (localCntcSttsGrdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntcSttsGrdCd"));

				elementList.add(localCntcSttsGrdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntcSttsGrdCd));
			}
			if (localProdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNm"));

				elementList.add(localProdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNm));
			}
			if (localEntrSttsNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsNm"));

				elementList.add(localEntrSttsNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsNm));
			}
			if (localEntrSttsChngRsnNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngRsnNm"));

				elementList.add(localEntrSttsChngRsnNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngRsnNm));
			}
			if (localEntrSttsChngRsnDtlNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngRsnDtlNm"));

				elementList.add(localEntrSttsChngRsnDtlNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSttsChngRsnDtlNm));
			}
			if (localRlusrCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustNo"));

				elementList.add(localRlusrCustNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustNo));
			}
			if (localRlusrCustNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustNm"));

				elementList.add(localRlusrCustNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustNm));
			}
			if (localRlusrCustrnmNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustrnmNo"));

				elementList.add(localRlusrCustrnmNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustrnmNo));
			}
			if (localRlusrCustDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustDvCd"));

				elementList.add(localRlusrCustDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustDvCd));
			}
			if (localRlusrCustDvNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustDvNm"));

				elementList.add(localRlusrCustDvNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustDvNm));
			}
			if (localRlusrCustKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustKdCd"));

				elementList.add(localRlusrCustKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustKdCd));
			}
			if (localRlusrCustKdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustKdNm"));

				elementList.add(localRlusrCustKdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrCustKdNm));
			}
			if (localRlusrBsRegNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrBsRegNo"));

				elementList.add(localRlusrBsRegNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRlusrBsRegNo));
			}
			if (localEmailAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "emailAddr"));

				elementList.add(localEmailAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEmailAddr));
			}
			if (localCustGrdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custGrdCd"));

				elementList.add(localCustGrdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustGrdCd));
			}
			if (localCustGrdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custGrdNm"));

				elementList.add(localCustGrdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustGrdNm));
			}
			if (localLtvGrdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltvGrdCd"));

				elementList.add(localLtvGrdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLtvGrdCd));
			}
			if (localLtvGrdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltvGrdNm"));

				elementList.add(localLtvGrdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLtvGrdNm));
			}
			if (localDevchGrdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devchGrdCd"));

				elementList.add(localDevchGrdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevchGrdCd));
			}
			if (localDevchGrdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devchGrdNm"));

				elementList.add(localDevchGrdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevchGrdNm));
			}
			if (localExpryGrdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "expryGrdCd"));

				elementList.add(localExpryGrdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExpryGrdCd));
			}
			if (localExpryGrdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "expryGrdNm"));

				elementList.add(localExpryGrdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExpryGrdNm));
			}
			if (localSelectedTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "selected"));

				elementList.add(localSelected == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSelected));
			}
			if (localBillAddrSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAddrSeqno"));

				elementList.add(localBillAddrSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAddrSeqno));
			}
			if (localCntctPntSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntctPntSeqno"));

				elementList.add(localCntctPntSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntctPntSeqno));
			}
			if (localAftRankPymAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aftRankPymAcntNo"));

				elementList.add(localAftRankPymAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAftRankPymAcntNo));
			}
			if (localBillEmailAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billEmailAddr"));

				elementList.add(localBillEmailAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillEmailAddr));
			}
			if (localBillAcntSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntSttsCd"));

				elementList.add(localBillAcntSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsCd));
			}
			if (localBillAcntSttsNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntSttsNm"));

				elementList.add(localBillAcntSttsNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntSttsNm));
			}
			if (localCustAddrZipTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custAddrZip"));

				elementList.add(localCustAddrZip == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustAddrZip));
			}
			if (localCustAddrZip1Tracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custAddrZip1"));

				elementList.add(localCustAddrZip1 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustAddrZip1));
			}
			if (localCustAddrZip2Tracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custAddrZip2"));

				elementList.add(localCustAddrZip2 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustAddrZip2));
			}
			if (localCustVilgAbvAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custVilgAbvAddr"));

				elementList.add(localCustVilgAbvAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustVilgAbvAddr));
			}
			if (localCustVilgBlwAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custVilgBlwAddr"));

				elementList.add(localCustVilgBlwAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustVilgBlwAddr));
			}
			if (localCustVilgAddrTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custVilgAddr"));

				elementList.add(localCustVilgAddr == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCustVilgAddr));
			}
			if (localBlMic2Tracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "blMic2"));

				elementList.add(localBlMic2 == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBlMic2));
			}
			if (localPpayAcntYnTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ppayAcntYn"));

				elementList.add(localPpayAcntYn == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPpayAcntYn));
			}
			if (localPymManCustNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymManCustNo"));

				elementList.add(localPymManCustNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPymManCustNo));
			}
			if (localAcntOwnrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "acntOwnrNo"));

				elementList.add(localAcntOwnrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAcntOwnrNo));
			}
			if (localPymMthdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymMthdCd"));

				elementList.add(localPymMthdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPymMthdCd));
			}
			if (localPymMthdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymMthdNm"));

				elementList.add(localPymMthdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPymMthdNm));
			}
			if (localIsDcNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isDcNo"));

				elementList.add(localIsDcNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsDcNo));
			}
			if (localIsAgmtDcTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isAgmtDc"));

				elementList.add(localIsAgmtDc == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsAgmtDc));
			}
			if (localIsPntDcTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isPntDc"));

				elementList.add(localIsPntDc == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsPntDc));
			}
			if (localIsFmlyLovDcTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isFmlyLovDc"));

				elementList.add(localIsFmlyLovDc == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsFmlyLovDc));
			}
			if (localIsFmlyReptTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isFmlyRept"));

				elementList.add(localIsFmlyRept == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsFmlyRept));
			}
			if (localIsBillDcTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isBillDc"));

				elementList.add(localIsBillDc == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsBillDc));
			}
			if (localIsEcoDcTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isEcoDc"));

				elementList.add(localIsEcoDc == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsEcoDc));
			}
			if (localAplyMnthTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aplyMnth"));

				elementList.add(localAplyMnth == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAplyMnth));
			}
			if (localRmndMnthTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rmndMnth"));

				elementList.add(localRmndMnth == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRmndMnth));
			}
			if (localImsiNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "imsiNo"));

				elementList.add(localImsiNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localImsiNo));
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
			public static DsCustInfoOutVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsCustInfoOutVO object = new DsCustInfoOutVO();

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

							if (!"DsCustInfoOutVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsCustInfoOutVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custrnmNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustrnmNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustDvCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custDvNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustDvNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custKdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustKdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custKdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustKdNm(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "bsRegNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBsRegNo(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "valdEndDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setValdEndDttm(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "intgProdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIntgProdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "intgProdEntrDvNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIntgProdEntrDvNo(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aceno")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntNo")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymAcntNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPymAcntNo(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "mrktCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setMrktCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "mic2")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setMic2(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrKdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrKdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrBizPlcyCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrBizPlcyCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "lastBizPlcyCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLastBizPlcyCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "lastBizPlcyChngDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLastBizPlcyChngDttm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsCd")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngRsnCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "EntrSttsChngRsnDtlCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngRsnDtlCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrDlrCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrDlrCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "frstEntrDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setFrstEntrDttm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngDttm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtpymSttsCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymSttsChngDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtpymSttsChngDt(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymActPathCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtpymActPathCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymActNextStgeNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtpymActNextStgeNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltpymActNextStgeScdlDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtpymActNextStgeScdlDt(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntcSttsGrdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCntcSttsGrdCd(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngRsnNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngRsnNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSttsChngRsnDtlNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEntrSttsChngRsnDtlNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustrnmNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustrnmNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustDvCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustDvNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustDvNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustKdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustKdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrCustKdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrCustKdNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rlusrBsRegNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRlusrBsRegNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "emailAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEmailAddr(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custGrdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustGrdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custGrdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustGrdNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltvGrdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtvGrdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ltvGrdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setLtvGrdNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devchGrdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevchGrdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devchGrdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevchGrdNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "expryGrdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setExpryGrdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "expryGrdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setExpryGrdNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "selected")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSelected(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAddrSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAddrSeqno(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntctPntSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCntctPntSeqno(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aftRankPymAcntNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAftRankPymAcntNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billEmailAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillEmailAddr(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntSttsNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBillAcntSttsNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custAddrZip")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustAddrZip(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custAddrZip1")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustAddrZip1(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custAddrZip2")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustAddrZip2(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custVilgAbvAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustVilgAbvAddr(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custVilgBlwAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustVilgBlwAddr(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "custVilgAddr")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setCustVilgAddr(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "blMic2")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setBlMic2(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ppayAcntYn")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPpayAcntYn(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymManCustNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPymManCustNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "acntOwnrNo")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAcntOwnrNo(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymMthdCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPymMthdCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "pymMthdNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setPymMthdNm(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isDcNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIsDcNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isAgmtDc")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIsAgmtDc(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isPntDc")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIsPntDc(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isFmlyLovDc")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIsFmlyLovDc(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isFmlyRept")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIsFmlyRept(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isBillDc")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIsBillDc(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "isEcoDc")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setIsEcoDc(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aplyMnth")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setAplyMnth(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rmndMnth")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRmndMnth(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "imsiNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setImsiNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

	public static class DsSvcInfoOutVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsSvcInfoOutVO Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix =
		 * ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
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
		 * field for ProdCd
		 */

		protected java.lang.String localProdCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdCd() {
			return localProdCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdCd
		 */
		public void setProdCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdCdTracker = true;
			} else {
				localProdCdTracker = true;

			}

			this.localProdCd = param;

		}

		/**
		 * field for ProdNm
		 */

		protected java.lang.String localProdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localProdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getProdNm() {
			return localProdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ProdNm
		 */
		public void setProdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localProdNmTracker = true;
			} else {
				localProdNmTracker = true;

			}

			this.localProdNm = param;

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
		 * field for SvcKdNm
		 */

		protected java.lang.String localSvcKdNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcKdNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcKdNm() {
			return localSvcKdNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcKdNm
		 */
		public void setSvcKdNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcKdNmTracker = true;
			} else {
				localSvcKdNmTracker = true;

			}

			this.localSvcKdNm = param;

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
		 * field for SvcAplyLvlNm
		 */

		protected java.lang.String localSvcAplyLvlNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcAplyLvlNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcAplyLvlNm() {
			return localSvcAplyLvlNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcAplyLvlNm
		 */
		public void setSvcAplyLvlNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcAplyLvlNmTracker = true;
			} else {
				localSvcAplyLvlNmTracker = true;

			}

			this.localSvcAplyLvlNm = param;

		}

		/**
		 * field for SvcSttsCd
		 */

		protected java.lang.String localSvcSttsCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcSttsCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcSttsCd() {
			return localSvcSttsCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcSttsCd
		 */
		public void setSvcSttsCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcSttsCdTracker = true;
			} else {
				localSvcSttsCdTracker = true;

			}

			this.localSvcSttsCd = param;

		}

		/**
		 * field for SvcSttsChngDttm
		 */

		protected java.lang.String localSvcSttsChngDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcSttsChngDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcSttsChngDttm() {
			return localSvcSttsChngDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcSttsChngDttm
		 */
		public void setSvcSttsChngDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcSttsChngDttmTracker = true;
			} else {
				localSvcSttsChngDttmTracker = true;

			}

			this.localSvcSttsChngDttm = param;

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
		 * field for SvcStrtRgstDttm
		 */

		protected java.lang.String localSvcStrtRgstDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcStrtRgstDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcStrtRgstDttm() {
			return localSvcStrtRgstDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcStrtRgstDttm
		 */
		public void setSvcStrtRgstDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcStrtRgstDttmTracker = true;
			} else {
				localSvcStrtRgstDttmTracker = true;

			}

			this.localSvcStrtRgstDttm = param;

		}

		/**
		 * field for SvcStrtRsnCd
		 */

		protected java.lang.String localSvcStrtRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcStrtRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcStrtRsnCd() {
			return localSvcStrtRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcStrtRsnCd
		 */
		public void setSvcStrtRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcStrtRsnCdTracker = true;
			} else {
				localSvcStrtRsnCdTracker = true;

			}

			this.localSvcStrtRsnCd = param;

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
		 * field for SvcEndRsnCd
		 */

		protected java.lang.String localSvcEndRsnCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcEndRsnCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcEndRsnCd() {
			return localSvcEndRsnCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcEndRsnCd
		 */
		public void setSvcEndRsnCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcEndRsnCdTracker = true;
			} else {
				localSvcEndRsnCdTracker = true;

			}

			this.localSvcEndRsnCd = param;

		}

		/**
		 * field for SvcEndRgstDttm
		 */

		protected java.lang.String localSvcEndRgstDttm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSvcEndRgstDttmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSvcEndRgstDttm() {
			return localSvcEndRgstDttm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SvcEndRgstDttm
		 */
		public void setSvcEndRgstDttm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSvcEndRgstDttmTracker = true;
			} else {
				localSvcEndRgstDttmTracker = true;

			}

			this.localSvcEndRgstDttm = param;

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
		 * field for Rate
		 */

		protected java.lang.String localRate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localRateTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getRate() {
			return localRate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            Rate
		 */
		public void setRate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localRateTracker = true;
			} else {
				localRateTracker = true;

			}

			this.localRate = param;

		}

		/**
		 * field for SusRate
		 */

		protected java.lang.String localSusRate;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localSusRateTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getSusRate() {
			return localSusRate;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            SusRate
		 */
		public void setSusRate(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localSusRateTracker = true;
			} else {
				localSusRateTracker = true;

			}

			this.localSusRate = param;

		}

		/**
		 * field for TermUnitDvCd
		 */

		protected java.lang.String localTermUnitDvCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localTermUnitDvCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getTermUnitDvCd() {
			return localTermUnitDvCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            TermUnitDvCd
		 */
		public void setTermUnitDvCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localTermUnitDvCdTracker = true;
			} else {
				localTermUnitDvCdTracker = true;

			}

			this.localTermUnitDvCd = param;

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
					DsSvcInfoOutVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsSvcInfoOutVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsSvcInfoOutVO",
							xmlWriter);
				}

			}
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localProdCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodCd");
					}

				} else {
					xmlWriter.writeStartElement("prodCd");
				}

				if (localProdCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localProdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "prodNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "prodNm");
					}

				} else {
					xmlWriter.writeStartElement("prodNm");
				}

				if (localProdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localProdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localSvcKdNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcKdNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcKdNm");
					}

				} else {
					xmlWriter.writeStartElement("svcKdNm");
				}

				if (localSvcKdNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcKdNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcAplyLvlCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localSvcAplyLvlNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcAplyLvlNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcAplyLvlNm");
					}

				} else {
					xmlWriter.writeStartElement("svcAplyLvlNm");
				}

				if (localSvcAplyLvlNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcAplyLvlNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcSttsCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcSttsCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcSttsCd");
					}

				} else {
					xmlWriter.writeStartElement("svcSttsCd");
				}

				if (localSvcSttsCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcSttsCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcSttsChngDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcSttsChngDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcSttsChngDttm");
					}

				} else {
					xmlWriter.writeStartElement("svcSttsChngDttm");
				}

				if (localSvcSttsChngDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcSttsChngDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcFrstStrtDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localSvcStrtRgstDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcStrtRgstDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcStrtRgstDttm");
					}

				} else {
					xmlWriter.writeStartElement("svcStrtRgstDttm");
				}

				if (localSvcStrtRgstDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcStrtRgstDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcStrtRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcStrtRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcStrtRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("svcStrtRsnCd");
				}

				if (localSvcStrtRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcStrtRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcStrtDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localSvcEndRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcEndRsnCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcEndRsnCd");
					}

				} else {
					xmlWriter.writeStartElement("svcEndRsnCd");
				}

				if (localSvcEndRsnCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcEndRsnCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcEndRgstDttmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "svcEndRgstDttm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "svcEndRgstDttm");
					}

				} else {
					xmlWriter.writeStartElement("svcEndRgstDttm");
				}

				if (localSvcEndRgstDttm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSvcEndRgstDttm);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcDutyUseStrtDtTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localSvcDutyUseMnthCntTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localRateTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "rate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "rate");
					}

				} else {
					xmlWriter.writeStartElement("rate");
				}

				if (localRate == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localRate);

				}

				xmlWriter.writeEndElement();
			}
			if (localSusRateTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "susRate", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "susRate");
					}

				} else {
					xmlWriter.writeStartElement("susRate");
				}

				if (localSusRate == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localSusRate);

				}

				xmlWriter.writeEndElement();
			}
			if (localTermUnitDvCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "termUnitDvCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "termUnitDvCd");
					}

				} else {
					xmlWriter.writeStartElement("termUnitDvCd");
				}

				if (localTermUnitDvCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localTermUnitDvCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localSvcRelsDvCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			if (localProdNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNo"));

				elementList.add(localProdNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNo));
			}
			if (localProdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodCd"));

				elementList.add(localProdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdCd));
			}
			if (localProdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNm"));

				elementList.add(localProdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localProdNm));
			}
			if (localSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcCd"));

				elementList.add(localSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcCd));
			}
			if (localSvcNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcNm"));

				elementList.add(localSvcNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcNm));
			}
			if (localEntrSvcSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSvcSeqno"));

				elementList.add(localEntrSvcSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSvcSeqno));
			}
			if (localHposSvcCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "hposSvcCd"));

				elementList.add(localHposSvcCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHposSvcCd));
			}
			if (localHposEntrSvcSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "hposEntrSvcSeqno"));

				elementList.add(localHposEntrSvcSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHposEntrSvcSeqno));
			}
			if (localSvcKdCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcKdCd"));

				elementList.add(localSvcKdCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcKdCd));
			}
			if (localSvcKdNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcKdNm"));

				elementList.add(localSvcKdNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcKdNm));
			}
			if (localSvcAplyLvlCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcAplyLvlCd"));

				elementList.add(localSvcAplyLvlCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcAplyLvlCd));
			}
			if (localSvcAplyLvlNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcAplyLvlNm"));

				elementList.add(localSvcAplyLvlNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcAplyLvlNm));
			}
			if (localSvcSttsCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcSttsCd"));

				elementList.add(localSvcSttsCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcSttsCd));
			}
			if (localSvcSttsChngDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcSttsChngDttm"));

				elementList.add(localSvcSttsChngDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcSttsChngDttm));
			}
			if (localSvcFrstStrtDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcFrstStrtDttm"));

				elementList.add(localSvcFrstStrtDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcFrstStrtDttm));
			}
			if (localSvcStrtRgstDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtRgstDlrCd"));

				elementList.add(localSvcStrtRgstDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcStrtRgstDlrCd));
			}
			if (localSvcStrtRgstDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtRgstDttm"));

				elementList.add(localSvcStrtRgstDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcStrtRgstDttm));
			}
			if (localSvcStrtRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtRsnCd"));

				elementList.add(localSvcStrtRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcStrtRsnCd));
			}
			if (localSvcStrtDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtDttm"));

				elementList.add(localSvcStrtDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcStrtDttm));
			}
			if (localSvcEndDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcEndDttm"));

				elementList.add(localSvcEndDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcEndDttm));
			}
			if (localSvcEndRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcEndRsnCd"));

				elementList.add(localSvcEndRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcEndRsnCd));
			}
			if (localSvcEndRgstDttmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcEndRgstDttm"));

				elementList.add(localSvcEndRgstDttm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcEndRgstDttm));
			}
			if (localSvcDutyUseStrtDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseStrtDt"));

				elementList.add(localSvcDutyUseStrtDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseStrtDt));
			}
			if (localSvcDutyUseEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseEndDt"));

				elementList.add(localSvcDutyUseEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseEndDt));
			}
			if (localSvcDutyUseMnthCntTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseMnthCnt"));

				elementList.add(localSvcDutyUseMnthCnt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseMnthCnt));
			}
			if (localSvcDutyUseDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseDvCd"));

				elementList.add(localSvcDutyUseDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcDutyUseDvCd));
			}
			if (localRateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rate"));

				elementList.add(localRate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRate));
			}
			if (localSusRateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "susRate"));

				elementList.add(localSusRate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSusRate));
			}
			if (localTermUnitDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "termUnitDvCd"));

				elementList.add(localTermUnitDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTermUnitDvCd));
			}
			if (localSvcRelsDvCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcRelsDvCd"));

				elementList.add(localSvcRelsDvCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSvcRelsDvCd));
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
			public static DsSvcInfoOutVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsSvcInfoOutVO object = new DsSvcInfoOutVO();

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

							if (!"DsSvcInfoOutVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsSvcInfoOutVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNo")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodCd")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdCd(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "prodNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setProdNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcCd")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcNm")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSvcSeqno")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "hposSvcCd")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "hposEntrSvcSeqno")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcKdCd")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcKdNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcKdNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcAplyLvlCd")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcAplyLvlNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcAplyLvlNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcSttsCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcSttsCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcSttsChngDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcSttsChngDttm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcFrstStrtDttm")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtRgstDlrCd")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtRgstDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcStrtRgstDttm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcStrtRsnCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcStrtDttm")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcEndDttm")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcEndRsnCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcEndRsnCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcEndRgstDttm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSvcEndRgstDttm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseStrtDt")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseEndDt")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseMnthCnt")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcDutyUseDvCd")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "rate")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setRate(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "susRate")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setSusRate(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "termUnitDvCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setTermUnitDvCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "svcRelsDvCd")
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

	public static class RetrieveCustInfoSvc implements org.apache.axis2.databinding.ADBBean {

		public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://lguplus/u3/esb",
				"RetrieveCustInfoSvc", "ns3");

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
					RetrieveCustInfoSvc.this.serialize(MY_QNAME, factory, xmlWriter);
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
							namespacePrefix + ":RetrieveCustInfoSvc", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RetrieveCustInfoSvc",
							xmlWriter);
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
			public static RetrieveCustInfoSvc parse(javax.xml.stream.XMLStreamReader reader)
					throws java.lang.Exception {
				RetrieveCustInfoSvc object = new RetrieveCustInfoSvc();

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

							if (!"RetrieveCustInfoSvc".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (RetrieveCustInfoSvc) ExtensionMapper.getTypeObject(nsUri, type, reader);
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

	public static class RequestRecord implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * RequestRecord Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix =
		 * ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
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
			localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ESBHeader"), factory,
					xmlWriter);
			if (localRequestBodyTracker) {
				if (localRequestBody == null) {
					throw new org.apache.axis2.databinding.ADBException("RequestBody cannot be null!!");
				}
				localRequestBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "RequestBody"),
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

			elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ESBHeader"));

			if (localESBHeader == null) {
				throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
			}
			elementList.add(localESBHeader);
			if (localRequestBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "RequestBody"));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ESBHeader")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "RequestBody")
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

	public static class DsConfldsOutVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsConfldsOutVO Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix =
		 * ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for UserId
		 */

		protected java.lang.String localUserId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserId() {
			return localUserId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserId
		 */
		public void setUserId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserIdTracker = true;
			} else {
				localUserIdTracker = true;

			}

			this.localUserId = param;

		}

		/**
		 * field for UserNm
		 */

		protected java.lang.String localUserNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserNm() {
			return localUserNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserNm
		 */
		public void setUserNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserNmTracker = true;
			} else {
				localUserNmTracker = true;

			}

			this.localUserNm = param;

		}

		/**
		 * field for UserOrgCd
		 */

		protected java.lang.String localUserOrgCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserOrgCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserOrgCd() {
			return localUserOrgCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserOrgCd
		 */
		public void setUserOrgCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserOrgCdTracker = true;
			} else {
				localUserOrgCdTracker = true;

			}

			this.localUserOrgCd = param;

		}

		/**
		 * field for UserDlrCd
		 */

		protected java.lang.String localUserDlrCd;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserDlrCdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserDlrCd() {
			return localUserDlrCd;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserDlrCd
		 */
		public void setUserDlrCd(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserDlrCdTracker = true;
			} else {
				localUserDlrCdTracker = true;

			}

			this.localUserDlrCd = param;

		}

		/**
		 * field for UserDlrNm
		 */

		protected java.lang.String localUserDlrNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localUserDlrNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getUserDlrNm() {
			return localUserDlrNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            UserDlrNm
		 */
		public void setUserDlrNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localUserDlrNmTracker = true;
			} else {
				localUserDlrNmTracker = true;

			}

			this.localUserDlrNm = param;

		}

		/**
		 * field for OperatorId
		 */

		protected java.lang.String localOperatorId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localOperatorIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getOperatorId() {
			return localOperatorId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            OperatorId
		 */
		public void setOperatorId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localOperatorIdTracker = true;
			} else {
				localOperatorIdTracker = true;

			}

			this.localOperatorId = param;

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
					DsConfldsOutVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsConfldsOutVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsConfldsOutVO",
							xmlWriter);
				}

			}
			if (localUserIdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userId");
					}

				} else {
					xmlWriter.writeStartElement("userId");
				}

				if (localUserId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserId);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userNm");
					}

				} else {
					xmlWriter.writeStartElement("userNm");
				}

				if (localUserNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserOrgCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userOrgCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userOrgCd");
					}

				} else {
					xmlWriter.writeStartElement("userOrgCd");
				}

				if (localUserOrgCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserOrgCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserDlrCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userDlrCd", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userDlrCd");
					}

				} else {
					xmlWriter.writeStartElement("userDlrCd");
				}

				if (localUserDlrCd == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserDlrCd);

				}

				xmlWriter.writeEndElement();
			}
			if (localUserDlrNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "userDlrNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "userDlrNm");
					}

				} else {
					xmlWriter.writeStartElement("userDlrNm");
				}

				if (localUserDlrNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localUserDlrNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localOperatorIdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "operatorId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "operatorId");
					}

				} else {
					xmlWriter.writeStartElement("operatorId");
				}

				if (localOperatorId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localOperatorId);

				}

				xmlWriter.writeEndElement();
			}
			if (localLockModeTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localCnIdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localDirectiveTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localTransactionModeTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localUserWorkDlrCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localRunDateTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localEntrNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localEntrSysUpdateDateTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localEntrDlUpdateStampTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localAcenoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localCntcSysUpdateDateTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localCntcDlUpdateStampTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localBillAcntNoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localBillSysUpdateDateTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localBillDlUpdateStampTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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

			if (localUserIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userId"));

				elementList.add(localUserId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserId));
			}
			if (localUserNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userNm"));

				elementList.add(localUserNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserNm));
			}
			if (localUserOrgCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userOrgCd"));

				elementList.add(localUserOrgCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserOrgCd));
			}
			if (localUserDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userDlrCd"));

				elementList.add(localUserDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserDlrCd));
			}
			if (localUserDlrNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userDlrNm"));

				elementList.add(localUserDlrNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserDlrNm));
			}
			if (localOperatorIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "operatorId"));

				elementList.add(localOperatorId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOperatorId));
			}
			if (localLockModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "lockMode"));

				elementList.add(localLockMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLockMode));
			}
			if (localCnIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cnId"));

				elementList.add(localCnId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCnId));
			}
			if (localDirectiveTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "directive"));

				elementList.add(localDirective == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDirective));
			}
			if (localTransactionModeTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "transactionMode"));

				elementList.add(localTransactionMode == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransactionMode));
			}
			if (localUserWorkDlrCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userWorkDlrCd"));

				elementList.add(localUserWorkDlrCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserWorkDlrCd));
			}
			if (localUserWorkDlrNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userWorkDlrNm"));

				elementList.add(localUserWorkDlrNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserWorkDlrNm));
			}
			if (localRunDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "runDate"));

				elementList.add(localRunDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRunDate));
			}
			if (localRunDateDtmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "runDateDtm"));

				elementList.add(localRunDateDtm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localRunDateDtm));
			}
			if (localEntrNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo"));

				elementList.add(localEntrNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrNo));
			}
			if (localEntrSysUpdateDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSysUpdateDate"));

				elementList.add(localEntrSysUpdateDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrSysUpdateDate));
			}
			if (localEntrDlUpdateStampTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrDlUpdateStamp"));

				elementList.add(localEntrDlUpdateStamp == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEntrDlUpdateStamp));
			}
			if (localAcenoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aceno"));

				elementList.add(localAceno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAceno));
			}
			if (localCntcSysUpdateDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntcSysUpdateDate"));

				elementList.add(localCntcSysUpdateDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntcSysUpdateDate));
			}
			if (localCntcDlUpdateStampTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntcDlUpdateStamp"));

				elementList.add(localCntcDlUpdateStamp == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCntcDlUpdateStamp));
			}
			if (localBillAcntNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntNo"));

				elementList.add(localBillAcntNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillAcntNo));
			}
			if (localBillSysUpdateDateTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billSysUpdateDate"));

				elementList.add(localBillSysUpdateDate == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillSysUpdateDate));
			}
			if (localBillDlUpdateStampTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billDlUpdateStamp"));

				elementList.add(localBillDlUpdateStamp == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillDlUpdateStamp));
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
			public static DsConfldsOutVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsConfldsOutVO object = new DsConfldsOutVO();

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

							if (!"DsConfldsOutVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsConfldsOutVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userId")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userNm")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserNm(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userOrgCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserOrgCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userDlrCd")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserDlrCd(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userDlrNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setUserDlrNm(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "operatorId")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setOperatorId(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "lockMode")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cnId")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "directive")
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "transactionMode")
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userWorkDlrCd")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "userWorkDlrNm")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "runDate")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "runDateDtm")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrNo")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrSysUpdateDate")
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "entrDlUpdateStamp")
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "aceno")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntcSysUpdateDate")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "cntcDlUpdateStamp")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billAcntNo")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billSysUpdateDate")
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "billDlUpdateStamp")
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
		 * ResponseRecord Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix =
		 * ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
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
				localESBHeader.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ESBHeader"),
						factory, xmlWriter);
			}
			if (localBusinessHeaderTracker) {
				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				localBusinessHeader.serialize(
						new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "BusinessHeader"), factory,
						xmlWriter);
			}
			if (localResponseBodyTracker) {
				if (localResponseBody == null) {
					throw new org.apache.axis2.databinding.ADBException("ResponseBody cannot be null!!");
				}
				localResponseBody.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ResponseBody"),
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
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ESBHeader"));

				if (localESBHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("ESBHeader cannot be null!!");
				}
				elementList.add(localESBHeader);
			}
			if (localBusinessHeaderTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "BusinessHeader"));

				if (localBusinessHeader == null) {
					throw new org.apache.axis2.databinding.ADBException("BusinessHeader cannot be null!!");
				}
				elementList.add(localBusinessHeader);
			}
			if (localResponseBodyTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ResponseBody"));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ESBHeader")
									.equals(reader.getName())) {

						object.setESBHeader(ESBHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "BusinessHeader")
									.equals(reader.getName())) {

						object.setBusinessHeader(BusinessHeader.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "ResponseBody")
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

	public static class DsDevInfoOutVO implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name =
		 * DsDevInfoOutVO Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix =
		 * ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DevByEntrSeqno
		 */

		protected java.lang.String localDevByEntrSeqno;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevByEntrSeqnoTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevByEntrSeqno() {
			return localDevByEntrSeqno;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevByEntrSeqno
		 */
		public void setDevByEntrSeqno(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevByEntrSeqnoTracker = true;
			} else {
				localDevByEntrSeqnoTracker = true;

			}

			this.localDevByEntrSeqno = param;

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
		 * field for EmergencyIndicator
		 */

		protected java.lang.String localEmergencyIndicator;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localEmergencyIndicatorTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getEmergencyIndicator() {
			return localEmergencyIndicator;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            EmergencyIndicator
		 */
		public void setEmergencyIndicator(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localEmergencyIndicatorTracker = true;
			} else {
				localEmergencyIndicatorTracker = true;

			}

			this.localEmergencyIndicator = param;

		}

		/**
		 * field for DevRgstDt
		 */

		protected java.lang.String localDevRgstDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevRgstDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevRgstDt() {
			return localDevRgstDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevRgstDt
		 */
		public void setDevRgstDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevRgstDtTracker = true;
			} else {
				localDevRgstDtTracker = true;

			}

			this.localDevRgstDt = param;

		}

		/**
		 * field for DevValdEndDt
		 */

		protected java.lang.String localDevValdEndDt;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevValdEndDtTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevValdEndDt() {
			return localDevValdEndDt;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevValdEndDt
		 */
		public void setDevValdEndDt(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevValdEndDtTracker = true;
			} else {
				localDevValdEndDtTracker = true;

			}

			this.localDevValdEndDt = param;

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
		 * field for DevChngRsnNm
		 */

		protected java.lang.String localDevChngRsnNm;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDevChngRsnNmTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getDevChngRsnNm() {
			return localDevChngRsnNm;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DevChngRsnNm
		 */
		public void setDevChngRsnNm(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localDevChngRsnNmTracker = true;
			} else {
				localDevChngRsnNmTracker = true;

			}

			this.localDevChngRsnNm = param;

		}

		/**
		 * field for ChipId
		 */

		protected java.lang.String localChipId;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localChipIdTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getChipId() {
			return localChipId;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ChipId
		 */
		public void setChipId(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localChipIdTracker = true;
			} else {
				localChipIdTracker = true;

			}

			this.localChipId = param;

		}

		/**
		 * field for ChipStatus
		 */

		protected java.lang.String localChipStatus;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localChipStatusTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return java.lang.String
		 */
		public java.lang.String getChipStatus() {
			return localChipStatus;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            ChipStatus
		 */
		public void setChipStatus(java.lang.String param) {

			if (param != null) {
				// update the setting tracker
				localChipStatusTracker = true;
			} else {
				localChipStatusTracker = true;

			}

			this.localChipStatus = param;

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
					DsDevInfoOutVO.this.serialize(parentQName, factory, xmlWriter);
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":DsDevInfoOutVO", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DsDevInfoOutVO",
							xmlWriter);
				}

			}
			if (localDevByEntrSeqnoTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devByEntrSeqno", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devByEntrSeqno");
					}

				} else {
					xmlWriter.writeStartElement("devByEntrSeqno");
				}

				if (localDevByEntrSeqno == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevByEntrSeqno);

				}

				xmlWriter.writeEndElement();
			}
			if (localItemIdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localEmergencyIndicatorTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "emergencyIndicator", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "emergencyIndicator");
					}

				} else {
					xmlWriter.writeStartElement("emergencyIndicator");
				}

				if (localEmergencyIndicator == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localEmergencyIndicator);

				}

				xmlWriter.writeEndElement();
			}
			if (localDevRgstDtTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devRgstDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devRgstDt");
					}

				} else {
					xmlWriter.writeStartElement("devRgstDt");
				}

				if (localDevRgstDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevRgstDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localDevValdEndDtTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devValdEndDt", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devValdEndDt");
					}

				} else {
					xmlWriter.writeStartElement("devValdEndDt");
				}

				if (localDevValdEndDt == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevValdEndDt);

				}

				xmlWriter.writeEndElement();
			}
			if (localItemStatusTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localDevChngRsnCdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
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
			if (localDevChngRsnNmTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "devChngRsnNm", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "devChngRsnNm");
					}

				} else {
					xmlWriter.writeStartElement("devChngRsnNm");
				}

				if (localDevChngRsnNm == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localDevChngRsnNm);

				}

				xmlWriter.writeEndElement();
			}
			if (localChipIdTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "chipId", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "chipId");
					}

				} else {
					xmlWriter.writeStartElement("chipId");
				}

				if (localChipId == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localChipId);

				}

				xmlWriter.writeEndElement();
			}
			if (localChipStatusTracker) {
				namespace = "java:lguplus.u3.esb.cm016";
				if (!namespace.equals("")) {
					prefix = xmlWriter.getPrefix(namespace);

					if (prefix == null) {
						prefix = generatePrefix(namespace);

						xmlWriter.writeStartElement(prefix, "chipStatus", namespace);
						xmlWriter.writeNamespace(prefix, namespace);
						xmlWriter.setPrefix(prefix, namespace);

					} else {
						xmlWriter.writeStartElement(namespace, "chipStatus");
					}

				} else {
					xmlWriter.writeStartElement("chipStatus");
				}

				if (localChipStatus == null) {
					// write the nil attribute

					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

				} else {

					xmlWriter.writeCharacters(localChipStatus);

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

			if (localDevByEntrSeqnoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devByEntrSeqno"));

				elementList.add(localDevByEntrSeqno == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevByEntrSeqno));
			}
			if (localItemIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "itemId"));

				elementList.add(localItemId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localItemId));
			}
			if (localManfSerialNoTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "manfSerialNo"));

				elementList.add(localManfSerialNo == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localManfSerialNo));
			}
			if (localEmergencyIndicatorTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "emergencyIndicator"));

				elementList.add(localEmergencyIndicator == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEmergencyIndicator));
			}
			if (localDevRgstDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devRgstDt"));

				elementList.add(localDevRgstDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevRgstDt));
			}
			if (localDevValdEndDtTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devValdEndDt"));

				elementList.add(localDevValdEndDt == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevValdEndDt));
			}
			if (localItemStatusTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "itemStatus"));

				elementList.add(localItemStatus == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localItemStatus));
			}
			if (localDevChngRsnCdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devChngRsnCd"));

				elementList.add(localDevChngRsnCd == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevChngRsnCd));
			}
			if (localDevChngRsnNmTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devChngRsnNm"));

				elementList.add(localDevChngRsnNm == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDevChngRsnNm));
			}
			if (localChipIdTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "chipId"));

				elementList.add(localChipId == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChipId));
			}
			if (localChipStatusTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "chipStatus"));

				elementList.add(localChipStatus == null ? null
						: org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChipStatus));
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
			public static DsDevInfoOutVO parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
				DsDevInfoOutVO object = new DsDevInfoOutVO();

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

							if (!"DsDevInfoOutVO".equals(type)) {
								// find namespace for the prefix
								java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
								return (DsDevInfoOutVO) ExtensionMapper.getTypeObject(nsUri, type, reader);
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devByEntrSeqno")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevByEntrSeqno(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "itemId")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "manfSerialNo")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "emergencyIndicator")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setEmergencyIndicator(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devRgstDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevRgstDt(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devValdEndDt")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevValdEndDt(
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "itemStatus")
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

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devChngRsnCd")
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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "devChngRsnNm")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setDevChngRsnNm(
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

					if (reader.isStartElement() && new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "chipId")
							.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setChipId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "chipStatus")
									.equals(reader.getName())) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

							java.lang.String content = reader.getElementText();

							object.setChipStatus(
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

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "DsConfldsOutVO".equals(typeName)) {

				return DsConfldsOutVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "RequestRecord".equals(typeName)) {

				return RequestRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "ResponseRecord".equals(typeName)) {

				return ResponseRecord.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "DsDevInfoOutVO".equals(typeName)) {

				return DsDevInfoOutVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "DsReqInVO".equals(typeName)) {

				return DsReqInVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "ResponseBody".equals(typeName)) {

				return ResponseBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "RequestBody".equals(typeName)) {

				return RequestBody.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.common".equals(namespaceURI) && "BusinessHeader".equals(typeName)) {

				return BusinessHeader.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "DsCustInfoOutVO".equals(typeName)) {

				return DsCustInfoOutVO.Factory.parse(reader);

			}

			if ("java:lguplus.u3.esb.cm016".equals(namespaceURI) && "DsSvcInfoOutVO".equals(typeName)) {

				return DsSvcInfoOutVO.Factory.parse(reader);

			}

			throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
		}

	}

	public static class ResponseBody implements org.apache.axis2.databinding.ADBBean {
		/*
		 * This type was generated from the piece of schema that had name = ResponseBody
		 * Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix = ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
			}
			return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
		}

		/**
		 * field for DsConfldsOutVO
		 */

		protected DsConfldsOutVO localDsConfldsOutVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsConfldsOutVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsConfldsOutVO
		 */
		public DsConfldsOutVO getDsConfldsOutVO() {
			return localDsConfldsOutVO;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsConfldsOutVO
		 */
		public void setDsConfldsOutVO(DsConfldsOutVO param) {

			if (param != null) {
				// update the setting tracker
				localDsConfldsOutVOTracker = true;
			} else {
				localDsConfldsOutVOTracker = false;

			}

			this.localDsConfldsOutVO = param;

		}

		/**
		 * field for DsSvcInfoOutVO This was an Array!
		 */

		protected DsSvcInfoOutVO[] localDsSvcInfoOutVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsSvcInfoOutVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsSvcInfoOutVO[]
		 */
		public DsSvcInfoOutVO[] getDsSvcInfoOutVO() {
			return localDsSvcInfoOutVO;
		}

		/**
		 * validate the array for DsSvcInfoOutVO
		 */
		protected void validateDsSvcInfoOutVO(DsSvcInfoOutVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsSvcInfoOutVO
		 */
		public void setDsSvcInfoOutVO(DsSvcInfoOutVO[] param) {

			validateDsSvcInfoOutVO(param);

			if (param != null) {
				// update the setting tracker
				localDsSvcInfoOutVOTracker = true;
			} else {
				localDsSvcInfoOutVOTracker = false;

			}

			this.localDsSvcInfoOutVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsSvcInfoOutVO
		 */
		public void addDsSvcInfoOutVO(DsSvcInfoOutVO param) {
			if (localDsSvcInfoOutVO == null) {
				localDsSvcInfoOutVO = new DsSvcInfoOutVO[] {};
			}

			// update the setting tracker
			localDsSvcInfoOutVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsSvcInfoOutVO);
			list.add(param);
			this.localDsSvcInfoOutVO = (DsSvcInfoOutVO[]) list.toArray(new DsSvcInfoOutVO[list.size()]);

		}

		/**
		 * field for DsDevInfoOutVO This was an Array!
		 */

		protected DsDevInfoOutVO[] localDsDevInfoOutVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsDevInfoOutVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsDevInfoOutVO[]
		 */
		public DsDevInfoOutVO[] getDsDevInfoOutVO() {
			return localDsDevInfoOutVO;
		}

		/**
		 * validate the array for DsDevInfoOutVO
		 */
		protected void validateDsDevInfoOutVO(DsDevInfoOutVO[] param) {

		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsDevInfoOutVO
		 */
		public void setDsDevInfoOutVO(DsDevInfoOutVO[] param) {

			validateDsDevInfoOutVO(param);

			if (param != null) {
				// update the setting tracker
				localDsDevInfoOutVOTracker = true;
			} else {
				localDsDevInfoOutVOTracker = false;

			}

			this.localDsDevInfoOutVO = param;
		}

		/**
		 * Auto generated add method for the array for convenience
		 * 
		 * @param param
		 *            DsDevInfoOutVO
		 */
		public void addDsDevInfoOutVO(DsDevInfoOutVO param) {
			if (localDsDevInfoOutVO == null) {
				localDsDevInfoOutVO = new DsDevInfoOutVO[] {};
			}

			// update the setting tracker
			localDsDevInfoOutVOTracker = true;

			java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDsDevInfoOutVO);
			list.add(param);
			this.localDsDevInfoOutVO = (DsDevInfoOutVO[]) list.toArray(new DsDevInfoOutVO[list.size()]);

		}

		/**
		 * field for DsCustInfoOutVO
		 */

		protected DsCustInfoOutVO localDsCustInfoOutVO;

		/*
		 * This tracker boolean wil be used to detect whether the user called the set
		 * method for this attribute. It will be used to determine whether to include
		 * this field in the serialized XML
		 */
		protected boolean localDsCustInfoOutVOTracker = false;

		/**
		 * Auto generated getter method
		 * 
		 * @return DsCustInfoOutVO
		 */
		public DsCustInfoOutVO getDsCustInfoOutVO() {
			return localDsCustInfoOutVO;
		}

		/**
		 * Auto generated setter method
		 * 
		 * @param param
		 *            DsCustInfoOutVO
		 */
		public void setDsCustInfoOutVO(DsCustInfoOutVO param) {

			if (param != null) {
				// update the setting tracker
				localDsCustInfoOutVOTracker = true;
			} else {
				localDsCustInfoOutVOTracker = false;

			}

			this.localDsCustInfoOutVO = param;

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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
				if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
							namespacePrefix + ":ResponseBody", xmlWriter);
				} else {
					writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ResponseBody",
							xmlWriter);
				}

			}
			if (localDsConfldsOutVOTracker) {
				if (localDsConfldsOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsConfldsOutVO cannot be null!!");
				}
				localDsConfldsOutVO.serialize(
						new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsConfldsOutVO"), factory,
						xmlWriter);
			}
			if (localDsSvcInfoOutVOTracker) {
				if (localDsSvcInfoOutVO != null) {
					for (int i = 0; i < localDsSvcInfoOutVO.length; i++) {
						if (localDsSvcInfoOutVO[i] != null) {
							localDsSvcInfoOutVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsSvcInfoOutVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsSvcInfoOutVO cannot be null!!");

				}
			}
			if (localDsDevInfoOutVOTracker) {
				if (localDsDevInfoOutVO != null) {
					for (int i = 0; i < localDsDevInfoOutVO.length; i++) {
						if (localDsDevInfoOutVO[i] != null) {
							localDsDevInfoOutVO[i].serialize(
									new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsDevInfoOutVO"),
									factory, xmlWriter);
						} else {

							// we don't have to do any thing since minOccures is
							// zero

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsDevInfoOutVO cannot be null!!");

				}
			}
			if (localDsCustInfoOutVOTracker) {
				if (localDsCustInfoOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsCustInfoOutVO cannot be null!!");
				}
				localDsCustInfoOutVO.serialize(
						new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsCustInfoOutVO"), factory,
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

			if (localDsConfldsOutVOTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsConfldsOutVO"));

				if (localDsConfldsOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsConfldsOutVO cannot be null!!");
				}
				elementList.add(localDsConfldsOutVO);
			}
			if (localDsSvcInfoOutVOTracker) {
				if (localDsSvcInfoOutVO != null) {
					for (int i = 0; i < localDsSvcInfoOutVO.length; i++) {

						if (localDsSvcInfoOutVO[i] != null) {
							elementList
									.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsSvcInfoOutVO"));
							elementList.add(localDsSvcInfoOutVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsSvcInfoOutVO cannot be null!!");

				}

			}
			if (localDsDevInfoOutVOTracker) {
				if (localDsDevInfoOutVO != null) {
					for (int i = 0; i < localDsDevInfoOutVO.length; i++) {

						if (localDsDevInfoOutVO[i] != null) {
							elementList
									.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsDevInfoOutVO"));
							elementList.add(localDsDevInfoOutVO[i]);
						} else {

							// nothing to do

						}

					}
				} else {

					throw new org.apache.axis2.databinding.ADBException("DsDevInfoOutVO cannot be null!!");

				}

			}
			if (localDsCustInfoOutVOTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsCustInfoOutVO"));

				if (localDsCustInfoOutVO == null) {
					throw new org.apache.axis2.databinding.ADBException("DsCustInfoOutVO cannot be null!!");
				}
				elementList.add(localDsCustInfoOutVO);
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

					java.util.ArrayList list2 = new java.util.ArrayList();

					java.util.ArrayList list3 = new java.util.ArrayList();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsConfldsOutVO")
									.equals(reader.getName())) {

						object.setDsConfldsOutVO(DsConfldsOutVO.Factory.parse(reader));

						reader.next();

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsSvcInfoOutVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list2.add(DsSvcInfoOutVO.Factory.parse(reader));

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
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsSvcInfoOutVO")
										.equals(reader.getName())) {
									list2.add(DsSvcInfoOutVO.Factory.parse(reader));

								} else {
									loopDone2 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsSvcInfoOutVO((DsSvcInfoOutVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsSvcInfoOutVO.class, list2));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsDevInfoOutVO")
									.equals(reader.getName())) {

						// Process the array and step past its final element's
						// end.
						list3.add(DsDevInfoOutVO.Factory.parse(reader));

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
								if (new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsDevInfoOutVO")
										.equals(reader.getName())) {
									list3.add(DsDevInfoOutVO.Factory.parse(reader));

								} else {
									loopDone3 = true;
								}
							}
						}
						// call the converter utility to convert and set the
						// array

						object.setDsDevInfoOutVO((DsDevInfoOutVO[]) org.apache.axis2.databinding.utils.ConverterUtil
								.convertToArray(DsDevInfoOutVO.class, list3));

					} // End of if for expected property start element

					else {

					}

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsCustInfoOutVO")
									.equals(reader.getName())) {

						object.setDsCustInfoOutVO(DsCustInfoOutVO.Factory.parse(reader));

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
		 * Namespace URI = java:lguplus.u3.esb.cm016 Namespace Prefix = ns31
		 */

		private static java.lang.String generatePrefix(java.lang.String namespace) {
			if (namespace.equals("java:lguplus.u3.esb.cm016")) {
				return "ns31";
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

				java.lang.String namespacePrefix = registerPrefix(xmlWriter, "java:lguplus.u3.esb.cm016");
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
				localDsReqInVO.serialize(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsReqInVO"),
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

			if (localDsReqInVOTracker) {
				elementList.add(new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsReqInVO"));

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

					// Note all attributes that were handled. Used to differ
					// normal attributes
					// from anyAttributes.
					java.util.Vector handledAttributes = new java.util.Vector();

					reader.next();

					while (!reader.isStartElement() && !reader.isEndElement())
						reader.next();

					if (reader.isStartElement()
							&& new javax.xml.namespace.QName("java:lguplus.u3.esb.cm016", "DsReqInVO")
									.equals(reader.getName())) {

						object.setDsReqInVO(DsReqInVO.Factory.parse(reader));

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

	private org.apache.axiom.om.OMElement toOM(
			lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc.MY_QNAME, factory));
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

			if (lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc.class.equals(type)) {

				return lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvc.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse.class
					.equals(type)) {

				return lguplus.u3.webservice.cm016.RetrieveCustInfoSvcServiceStub.RetrieveCustInfoSvcResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}